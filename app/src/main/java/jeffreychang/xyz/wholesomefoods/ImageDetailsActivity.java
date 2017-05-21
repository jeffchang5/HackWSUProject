package jeffreychang.xyz.wholesomefoods;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;

public class ImageDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        Log.d("TAG", getIntent().getStringExtra ("imgPath"));

        ImageView imageView = (ImageView) findViewById(R.id.selectedImage);

        imageView.setImageURI(Uri.fromFile (new File(getIntent().getStringExtra ("imgPath"))));

    }
}
