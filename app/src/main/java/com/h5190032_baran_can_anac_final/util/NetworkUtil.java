package com.h5190032_baran_can_anac_final.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;

import static androidx.core.content.ContextCompat.getSystemService;
//bu classda internet kontrolü yapılması için bir fonksiyon oluşturuldu.
//networkInfa değeri null değilse ve networkInfa kullanılabilir ve networkInfa bağlıysa true eğer bu şartlar doğru değilse false dönecektir.
public class NetworkUtil {
    public static boolean internetKontrol(Context context){
        ConnectivityManager connectivityManager =(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isAvailable() && networkInfo.isConnected()){
            return true;
        }else{
            return false;
        }
    }
}
