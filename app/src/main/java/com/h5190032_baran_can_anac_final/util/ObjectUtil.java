package com.h5190032_baran_can_anac_final.util;

import com.google.gson.Gson;
import com.h5190032_baran_can_anac_final.model.Kitap;

public class ObjectUtil {
    //bu fonksiyonda obje stringe dönüştürülüyor.
    public  static String kitapToJsonString(Kitap kitap)
    {
        Gson gson = new Gson();
        return   gson.toJson(kitap);
    }
    //bu fonksiyonda string objeye dönüştürülüyor.
    public  static Kitap jsonStringToKitap(String jsonString)
    {
        Gson gson = new Gson();
        return  gson.fromJson(jsonString,Kitap.class);
    }
}
