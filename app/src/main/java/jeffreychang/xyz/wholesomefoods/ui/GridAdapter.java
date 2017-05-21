package jeffreychang.xyz.wholesomefoods.ui;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

import jeffreychang.xyz.wholesomefoods.MainActivity;
import jeffreychang.xyz.wholesomefoods.MyCameraActivity;
import jeffreychang.xyz.wholesomefoods.R;

import static org.jetbrains.anko.ContextUtilsKt.getResources;

/**
 * Created by pkj on 5/20/17.
 */

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    static int i = 0;
    int filesCount;
    ArrayList<Drawable> fList;

    public GridAdapter(Context c) {
        mContext = c;
    }


    public GridAdapter(Context c, ArrayList<Drawable> arrayList) {
        this.fList = arrayList;
        mContext = c;
    }

    public int getCount() {
        return fList.size();
    }

    public Object getItem(int position) {
        return fList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(230, 235));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 50, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        //imageView.setImageResource(mThumbIds[position]);

        imageView.setImageDrawable(fList.get(position));

        return imageView;
    }


}
