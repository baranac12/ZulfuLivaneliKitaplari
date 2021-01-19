package com.h5190032_baran_can_anac_final.network;




import com.h5190032_baran_can_anac_final.util.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {


    private static Retrofit retrofit;
    //bu fonksiyonda eğer retrofit boş ise nesneyi oluşturmasını sağladık.
    //eğer boş değilse retrofiti döndürmesini sağladık.
    private static Retrofit getRetrofit() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder() //retroif inşa edildi.
                    .baseUrl(Constants.BASE_URL)//url verildi
                    .client(getOkHttpClient())// client verildi
                    .addConverterFactory(GsonConverterFactory.create())//gsona çevrildi
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//adaptor belirtildi
                    .build();
        }
        return retrofit;
    }
    //istek atmak için client oluşturuldu.
    static OkHttpClient okHttpClient;
    private static OkHttpClient getOkHttpClient()
    {
        if(okHttpClient == null) {
            okHttpClient =  new OkHttpClient().newBuilder().build(); //eğer nullsa client inşa edildi
        }

        return okHttpClient;//null değilse olanı döndürdü
    }

    //servise erişebilmek için serviceApi oluşturuldu.
    ServiceApi serviceApi;
    public ServiceApi getServiceApi() {

        if(serviceApi == null) {
            serviceApi = getRetrofit().create(ServiceApi.class);
        }
        //retrofiti oluşturup ServiceApi verildi ve serviceApi döndürüldü.
        return serviceApi;
    }
}
