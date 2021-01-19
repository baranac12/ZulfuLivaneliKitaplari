package com.h5190032_baran_can_anac_final.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.widget.Toast;

import com.h5190032_baran_can_anac_final.R;
import com.h5190032_baran_can_anac_final.util.NetworkUtil;

import java.util.List;

import static com.h5190032_baran_can_anac_final.R.string.internetUyariBaslik;
import static com.h5190032_baran_can_anac_final.R.string.internetUyariButon1;
import static com.h5190032_baran_can_anac_final.R.string.internetUyariButon2;
import static com.h5190032_baran_can_anac_final.R.string.internetUyariMesaj;

public class SplashActivity extends AppCompatActivity {

    //açılışta çalışan metottur ve activity_spalash set edilmiştir
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }
    private void init(){

        //bu fonksiyon 3 saniyelik bir timer başlatır ve bitince internetKontrol fonksiyonunu çalıştırır.
        CountDownTimer countDownTimer = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
                internetKontrol();
            }
        };
        countDownTimer.start();
    }
        // bu fonksiyon bir sonraki sayfaya veri taşımak için secondActivityIntent adında nesne oluşturulmuş ve ListeActivity classına taşınmıştır.
    private void openNextActivity(){
        Intent secondActivityIntent= new Intent(getApplicationContext(), ListeActivity.class);
        startActivity(secondActivityIntent);
        finish();
    }

    //bu fonksiyonda internet kontrolü yapılıyor. Eğer internet varsa openNextActivity fonksiyonu çalışır.Eğer internet yoksa Alert dialog oluşturulup internet olmadığına dair bir bilgi verilir.
    //bu bilgide interneti açmak için bir buton ve uygulamadan çıkmak için bir buton bulunur. İnternetini açmak isteyen kullanıcı evet butonuna basarak telefon ayarlarına yönlendirilir.
    private void internetKontrol(){
        if (NetworkUtil.internetKontrol(getApplicationContext())){
            openNextActivity();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle(getResources().getString(internetUyariBaslik));
            builder.setMessage(getResources().getString(internetUyariMesaj));
            builder.setPositiveButton(getResources().getString(internetUyariButon1), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                }
            });
            builder.setNegativeButton(getResources().getString(internetUyariButon2), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    finish();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }


}