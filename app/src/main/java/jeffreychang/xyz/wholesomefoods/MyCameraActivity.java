package jeffreychang.xyz.wholesomefoods;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import jeffreychang.xyz.wholesomefoods.login.LoginActivity;
import jeffreychang.xyz.wholesomefoods.network.APITester;
import jeffreychang.xyz.wholesomefoods.ui.GridAdapter;

public class MyCameraActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    static Uri capturedImageUri = null;
    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;
    static int i = 0;
    GridView gv;
    ArrayList<File> fList = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the toolbar view inside the activity layout
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        gv = (GridView) findViewById(R.id.foodPicsList);

        gv.setAdapter(new GridAdapter(this, readFileFromInternalStorage()));


        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id){
                Intent intent;
                intent = new Intent(getApplicationContext() , ImageDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("pos", String.valueOf(position));

                ArrayList<Drawable> drawables = readFileFromInternalStorage();

                //Bitmap = drawables.get(position);
                Bitmap bitmap = drawableToBitmap(position, drawables);
                File pngFile = convertBitmapToPng(bitmap);

                Log.d("PNG", pngFile.getAbsolutePath());

                //Call Api
//                APITester apiTester = new APITester();
//                apiTester.myTest(pngFile.getAbsolutePath());

                //Set into intent

                String[] imgDescs = {"Salad", "Cookie", "Cake/Donut"};

                intent.putExtra("imgPath", pngFile.getAbsolutePath());
                intent.putExtra("imgdesc", imgDescs);

                startActivity(intent);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

    }

    //Convert to png file
    private File convertBitmapToPng(Bitmap bitmap) {

        //create a file to write bitmap data
        File pngFile = new File(getApplicationContext().getCacheDir(), "food" + System.currentTimeMillis()/1000 + ".png");
        try {
            pngFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);

        byte[] bitmapdata = bos.toByteArray();

        //write the bytes in file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(pngFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(bitmapdata);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pngFile;

    }

    private Bitmap drawableToBitmap(int position, ArrayList<Drawable> drawables) {
        Bitmap bitmap = null;
        if(drawables.get(position) instanceof BitmapDrawable){
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawables.get(position);
            if(bitmapDrawable.getBitmap() != null){
                return bitmapDrawable.getBitmap();
            }
        }
        Canvas canvas = new Canvas(bitmap);
        drawables.get(position).setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawables.get(position).draw(canvas);
        return bitmap;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }

    //After picture is taken handle the data that is returned
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("TAG", "dasfd");
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Log.d("TAG", "dasfd");
            String s = saveToInternalStorage(imageBitmap);
            gv.setAdapter(new GridAdapter(this, readFileFromInternalStorage()));

            Log.d("TAG", s);

        }
    }
    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"food" + System.currentTimeMillis()/1000 +".png");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            //fos = openFileOutput(mypath.getName(), Context.MODE_APPEND);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    private ArrayList<Drawable> readFileFromInternalStorage(){
        Log.d("TAG", "IN readFileFromInternalStorage ");
        ImageView imageView;
        ArrayList<Drawable> drawables = new ArrayList<>();

        ContextWrapper cw = new ContextWrapper(getApplicationContext());

        //path to /data/data/yourapp/app_data/dirName
        //File directory = cw.getDir("imageDir", Context.MODE_APPEND);
        //filesCount = cw.getFilesDir().listFiles().length;

        fList = imageReader(cw.getDir("imageDir", Context.MODE_APPEND));

        for(int i = 0; i < fList.size();i++){
            //File mypath = new File(,"food" + i++ +".png");
            drawables.add(Drawable.createFromPath(fList.get(i).getAbsolutePath()));
            Log.d("TAG", "DOROMON " + fList.get(i).getAbsolutePath());
        }

        return drawables;
    }

    private ArrayList<File> imageReader(File root) {
        ArrayList<File> a = new ArrayList<>();

        File[] files = root.listFiles();
        for(int i=0; i< files.length; i++){
            if(files[i].isDirectory()){
                a.addAll(imageReader(files[i]));
            }
            else{
                if(files[i].getName().endsWith(".png")){
                    a.add(files[i]);
                }
            }
        }
        return a;
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
