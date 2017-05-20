package jeffreychang.xyz.wholesomefoods.network;

import android.media.Image;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by pkj on 5/20/17.
 */

public interface ClarifaiAPIEndpointInterface {

    @POST("/image")
    Call<ImageModel> sendImage(@Field("name") String name,
                        @Field("img") Image img);

}
