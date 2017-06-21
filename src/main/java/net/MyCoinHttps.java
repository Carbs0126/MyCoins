package net;


import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by Rick.Wang on 2016/10/22.
 */
public class MyCoinHttps {

    private Api mApi;

    private static MyCoinHttps instance;

    private MyCoinHttps() {
        mApi = ApiFactory.getInstance().getEbtAPI();
    }

    public static MyCoinHttps getInstance(){
        if (instance == null) {
            synchronized (MyCoinHttps.class) {
                if (instance == null) {
                    instance = new MyCoinHttps();
                }
            }
        }
        return instance;
    }

    public void getCustomerList(Subscriber<HuoBiBitcoinJson> subscriber){
        mApi.getBitcoinPrice(BitcoinPlatforms.huobibtccny)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.trampoline())
                .subscribe(subscriber);
    }
}