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

    //açılışta çalışan metottur ve activity_liste set edilmiştir
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
        // progress dialog ile liste yüklenene kadar ekranda bilgi vermesi sağlandı.
        ProgressDialog progressDialog = new ProgressDialog(ListeActivity.this);
        progressDialog.setMessage(getResources().getString(dialogMessage));

        //kitap adında bir liste oluşturulduktan sonra onComplete fonksiyonu ile listedeki kitaplar liste boyutu kadar jsondan çekilerek recycleView içine basıldı. Ve ekranda ki progress dialog kapatıldı.
        new Service().getServiceApi().kitaplariGetir()//servis çağrıldı
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//gözlemlendi, değişiklere bakıldı
                .subscribe(new Observer<List<Kitap>>() { // üye olundu ve gözlemci kitap listesini vericek

                    List<Kitap> kitaplar=new ArrayList<>(); //kitaplar adında bir liste oluşturuldu

                    @Override
                    public void onSubscribe(Disposable d) {
                        progressDialog.show();
                    }//bu fonksiyon servise abone olduğuda progress dialog çalıştırıldı

                    @Override
                    public void onNext(List<Kitap> kitapList) {
                        kitaplar=kitapList;
                    } //her bir nexte veriler liste eklendi.
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() //bu fonksiyonda veriler oluşturuldu ve ekrana basıldı.
                    {
                        if(kitaplar.size()>0) {
                            ObjectPrinter.printJson(kitaplar);
                            initRecycleView(kitaplar);
                            progressDialog.dismiss();
                        }
                    }
                });
    }

       // glide kullanılarak tanımlanan imgBannerın içine atanan url ile ekrana resim basıldı.
    private void kapakResminiAl()
    {
        imgBanner =findViewById(R.id.imgBanner);
        GlideUtil.resmiIndiripGoster(getApplicationContext(),resimUrl,imgBanner);
    }
     // bu fonksiyonda bir adaptör oluşturup bir liste ve tıklanma özelliği verildi.
    //tıklanma özelliğinde  listede tıklanan kitabın içeriğine openNextActivity fonksiyonu ile geçildi.
    //daha sonrasında tıklanan kitabın içerikleri yeni sayfada ekrana basıldı.
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
    // bu fonksiyonda bir sonraki ekrana veri taşımak için nextActivityIntent adında nesne oluşturuldu.Ve sonraki sayfa olarak KitapDetayActiviy classı seçildi.
    // tiklananKitapString Adında bir string oluşturarak tıklanan kitabın verilerini stringe çevirerek değişkene atadık..
    //Sonraki Sayfa geçildi.
    private void openNextActivity(Kitap tiklananKitap){
        Intent nextActivityIntent= new Intent(getApplicationContext(), KitapDetayActivity.class);
        String tiklananKitapString = ObjectUtil.kitapToJsonString(tiklananKitap);
        nextActivityIntent.putExtra(Constants.TIKLANAN_KITAP_BASLIGI,tiklananKitapString);
        startActivity(nextActivityIntent);
    }

    //bu fonksiyonda ListeActiviy sayfasıda geri tuşuna basıldığında Alert Dİalog ile kullanıcaya çıkmak isteyip istemediğini soruyoruz.
    //Çıkış yap butonuna basılırsa uygulama kapatalıcak. Eğer iptale basarsa dialog kapanıcak.
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