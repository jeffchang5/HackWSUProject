package jeffreychang.xyz.wholesomefoods.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pkj on 5/20/17.
 */

public class RestClient {
    // Trailing slash is needed
    public static final String BASE_URL = "http://api.myservice.com/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
