package com.h5190032_baran_can_anac_final.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.cagataymuhammet.objectprinter.ObjectPrinter;
import com.h5190032_baran_can_anac_final.R;
import com.h5190032_baran_can_anac_final.adapter.KitapAdapter;
import com.h5190032_baran_can_anac_final.model.Kitap;
import com.h5190032_baran_can_anac_final.network.Service;
import com.h5190032_baran_can_anac_final.util.Constants;
import com.h5190032_baran_can_anac_final.util.GlideUtil;
import com.h5190032_baran_can_anac_final.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.h5190032_baran_can_anac_final.R.string.dialogMessage;
import static com.h5190032_baran_can_anac_final.R.string.internetUyariBaslik;
import static com.h5190032_baran_can_anac_final.R.string.onBackPressButton1;
import static com.h5190032_baran_can_anac_final.R.string.onBackPressButton2;
import static com.h5190032_baran_can_anac_final.R.string.onBackPressMessage;


public class ListeActivity extends AppCompatActivity {
    RecyclerView rcvKitap;
    ImageView imgBanner;
    String resimUrl="https://raw.githubusercontent.com/baranac12/okulFinal/main/zlivaneliAnaSayfaKapak.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);
        init();
    }
    private  void init(){
        kapakResminiAl();
        kitaplariGetir();
    }
    void  kitaplariGetir()
    {

        ProgressDialog progressDialog = new ProgressDialog(ListeActivity.this);
        progressDialog.setMessage(getResources().getString(dialogMessage));


        new Service().getServiceApi().kitaplariGetir()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Kitap>>() {

                    List<Kitap> kitaplar=new ArrayList<>();

                    @Override
                    public void onSubscribe(Disposable d) {
                        progressDialog.show();
                    }

                    @Override
                    public void onNext(List<Kitap> kitapList) {
                        kitaplar=kitapList;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete()
                    {

                        if(kitaplar.size()>0) {
                            ObjectPrinter.printJson(kitaplar);
                            initRecycleView(kitaplar);
                            progressDialog.dismiss();
                        }
                    }
                });
    }
    private void kapakResminiAl()
    {
        imgBanner =findViewById(R.id.imgBanner);
        GlideUtil.resmiIndiripGoster(getApplicationContext(),resimUrl,imgBanner);
    }
    private void initRecycleView(List<Kitap> kitapList){
        rcvKitap = findViewById(R.id.rcvKitaplar);

        KitapAdapter kitapAdapter = new KitapAdapter(kitapList, getApplicationContext(), new KitapAdapter.OnItemClickListener() {
            @Override
            public void OnClick(int position) {
                Kitap tiklananKitap = kitapList.get(position);
                openNextActivity(tiklananKitap);
            }
        });
        rcvKitap.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rcvKitap.setAdapter(kitapAdapter);
    }
    private void openNextActivity(Kitap tiklananKitap){
        Intent nextActivityIntent= new Intent(getApplicationContext(), KitapDetayActivity.class);
        String tiklananKitapString = ObjectUtil.kitapToJsonString(tiklananKitap);
        nextActivityIntent.putExtra(Constants.TIKLANAN_KITAP_BASLIGI,tiklananKitapString);
        startActivity(nextActivityIntent);
    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage(getResources().getString(onBackPressMessage));
        builder.setPositiveButton(getResources().getString(onBackPressButton1), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton(getResources().getString(onBackPressButton2), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}