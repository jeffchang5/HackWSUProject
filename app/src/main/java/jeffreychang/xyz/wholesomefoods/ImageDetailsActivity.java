package jeffreychang.xyz.wholesomefoods;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ImageDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        Log.d("TAG", getIntent().getStringExtra ("pos"));
        Log.d("TAG", getIntent().getStringExtra("data"));
    }
}
