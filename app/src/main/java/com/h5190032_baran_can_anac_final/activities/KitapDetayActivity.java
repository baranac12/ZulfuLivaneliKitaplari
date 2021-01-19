package com.h5190032_baran_can_anac_final.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.h5190032_baran_can_anac_final.R;
import com.h5190032_baran_can_anac_final.model.Kitap;
import com.h5190032_baran_can_anac_final.util.Constants;
import com.h5190032_baran_can_anac_final.util.GlideUtil;
import com.h5190032_baran_can_anac_final.util.ObjectUtil;

public class KitapDetayActivity extends AppCompatActivity {
    ImageView imgKapak;
    TextView txtKitapAdi,txtYazarAdi,txtKitapKategori,txtKitapDil,txtKitapSayfaSayisi,txtKitapTarih,txtAciklama;
    //açılışta çalışan metottur ve activity_kitap_detay set edilmiştir
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitap_detay);

    init();

    }
    private void init(){
        //bir önceki sayfadan taşıdan veri bu sayfada tasinanKitapString e atandı.
        //atanan veriler kitap adında objeye atandı.
        //daha sonra ekrana basılması için değerler set edildi ve ekrana resim basıldı.
        String tasinanKitapString = getIntent().getStringExtra(Constants.TIKLANAN_KITAP_BASLIGI);
        Kitap kitap = ObjectUtil.jsonStringToKitap(tasinanKitapString);

        imgKapak=findViewById(R.id.imgDetayKapak);
        txtKitapAdi=findViewById(R.id.txtKitapAdi);
        txtKitapDil=findViewById(R.id.txtKitapDil);
        txtYazarAdi=findViewById(R.id.txtYazarAdi);
        txtKitapKategori=findViewById(R.id.txtKitapKategori);
        txtKitapSayfaSayisi=findViewById(R.id.txtKitapSayfa);
        txtKitapTarih=findViewById(R.id.txtKitapTarih);
        txtAciklama=findViewById(R.id.txtAciklama);

        txtKitapAdi.setText(kitap.getAdi());
        txtKitapDil.setText(kitap.getDil());
        txtAciklama.setText(kitap.getAciklama());
        txtKitapKategori.setText(kitap.getKategori());
        txtKitapSayfaSayisi.setText(kitap.getSayfaSayisi());
        txtKitapTarih.setText(kitap.getYayinlanmaTarihi());
        txtYazarAdi.setText(kitap.getYazar());

        GlideUtil.resmiIndiripGoster(getApplicationContext(),kitap.getKapakURL(),imgKapak);
    }



}