package net.api;

import net.entity.HuoBiBitcoinJson;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by andy.wang on 2016/12/9.
 */

public interface Api {

    @GET("/api/getTicker")
    Observable<HuoBiBitcoinJson> getBitcoinPrice(@Query("symbol") String symbol);

}


