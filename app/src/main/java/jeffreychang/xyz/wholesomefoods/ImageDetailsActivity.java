package jeffreychang.xyz.wholesomefoods;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class ImageDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        Log.d("TAG", getIntent().getStringExtra ("imgPath"));

        ImageView imageView = (ImageView) findViewById(R.id.selectedImage);
        TextView textView = (TextView) findViewById(R.id.imageDesc);

        imageView.setImageURI(Uri.fromFile (new File(getIntent().getStringExtra ("imgPath"))));

        String[] descs =  getIntent().getStringArrayExtra("imgdesc");
        Log.d("TAGDESC", getIntent().getStringExtra("pos"));
        if(getIntent().getStringExtra("pos") != null) {
            if (getIntent().getStringExtra("pos").equals("0")) {
                textView.setText(descs[0]);
                Log.d("TAGDESC", descs[0]);
            }
            else if (getIntent().getStringExtra("pos").equals("1")) {
                textView.setText(descs[1]);
                Log.d("TAGDESC", descs[1]);
            }
            else if (getIntent().getStringExtra("pos").equals("2")) {
                textView.setText(descs[2]);
                Log.d("TAGDESC", descs[2]);
            }
        }
    }
}
