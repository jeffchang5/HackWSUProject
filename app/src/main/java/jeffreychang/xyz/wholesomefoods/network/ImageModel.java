package jeffreychang.xyz.wholesomefoods.network;

import android.media.Image;

/**
 * Created by pkj on 5/20/17.
 */

public class ImageModel {
    String name;
    Image img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
