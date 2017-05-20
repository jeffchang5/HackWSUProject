package jeffreychang.xyz.wholesomefoods;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import jeffreychang.xyz.wholesomefoods.ui.GridAdapter;

public class MyCameraActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    static Uri capturedImageUri = null;
    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;
    static int i = 0;
    GridView gv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gv = (GridView) findViewById(R.id.foodPicsList);

        gv.setAdapter(new GridAdapter(this, readFileFromInternalStorage()));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

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

        ArrayList<File> fList = imageReader(cw.getDir("imageDir", Context.MODE_APPEND));

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

}
