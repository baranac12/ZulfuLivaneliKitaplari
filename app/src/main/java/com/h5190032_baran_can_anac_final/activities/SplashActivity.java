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

import static com.h5190032_baran_can_anac_final.R.string.internetUyariButon1;
import static com.h5190032_baran_can_anac_final.R.string.internetUyariMesaj;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();

    }
    private void init(){
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
    private void openNextActivity(){
        Intent secondActivityIntent= new Intent(getApplicationContext(), ListeActivity.class);
        startActivity(secondActivityIntent);
        finish();
    }
    private void internetKontrol(){
        if (NetworkUtil.internetKontrol(getApplicationContext())){
            openNextActivity();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("Bağlantı Hatası");
            builder.setMessage("İnternet Bağlantısı Yok, uygulama için açmak istermisiniz ?");
            builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                }
            });
            builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
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