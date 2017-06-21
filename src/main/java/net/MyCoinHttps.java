package net;


import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by Rick.Wang on 2016/10/22.
 */
public class MyCoinHttps {

    private net.api.Api mApi;

    private static MyCoinHttps instance;

    private MyCoinHttps() {
        mApi = net.api.ApiFactory.getInstance().getEbtAPI();
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

    public void getBitcoinPrice(Subscriber<net.entity.HuoBiBitcoinJson> subscriber){
        mApi.getBitcoinPrice(constants.BitcoinPlatforms.huobibtccny)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.trampoline())
                .subscribe(subscriber);
    }

    public void getLitecoinPrice(Subscriber<net.entity.HuoBiBitcoinJson> subscriber){
        mApi.getBitcoinPrice(constants.BitcoinPlatforms.huobiltccny)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.trampoline())
                .subscribe(subscriber);
    }
}