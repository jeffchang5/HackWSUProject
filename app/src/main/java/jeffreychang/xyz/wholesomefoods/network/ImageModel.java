package jeffreychang.xyz.wholesomefoods.network;

import android.media.Image;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pkj on 5/20/17.
 */

public class ImageModel {
    @SerializedName("condition")
    String _condition;

    @SerializedName("item")
    String _item;

    @SerializedName("calories")
    int _calories;

    public String get_condition() {
        return _condition;
    }

    public void set_condition(String _condition) {
        this._condition = _condition;
    }

    public String get_item() {
        return _item;
    }

    public void set_item(String _item) {
        this._item = _item;
    }

    public int get_calories() {
        return _calories;
    }

    public void set_calories(int _calories) {
        this._calories = _calories;
    }
}
