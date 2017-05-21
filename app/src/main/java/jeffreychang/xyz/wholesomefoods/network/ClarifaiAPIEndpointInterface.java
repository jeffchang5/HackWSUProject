package jeffreychang.xyz.wholesomefoods.network;

import android.media.Image;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by pkj on 5/20/17.
 */

public interface ClarifaiAPIEndpointInterface {

    @Multipart
    @POST("/api/image")
    Call<ImageModel> getImageDetails (
                         @Part("file\"; filename=\"pp.png\" ") RequestBody file,
                         @Part("food") String food
                         );

}
