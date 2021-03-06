package com.h5190032_baran_can_anac_final.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.h5190032_baran_can_anac_final.R;
import com.h5190032_baran_can_anac_final.model.Kitap;
import com.h5190032_baran_can_anac_final.util.GlideUtil;

import java.util.List;

public class KitapAdapter extends RecyclerView.Adapter<KitapViewHolder> {
    List<Kitap> kitapArrayList;
    Context context;
    OnItemClickListener listener;
    //tıklama özelliği verebilmek için intarface tanımlandı.
    public interface OnItemClickListener{
        void OnClick(int position);
    }

    //aşağıdaki fonksiyonda constructorlar oluşturuldu.
    public KitapAdapter(List<Kitap> kitapArrayList, Context context, OnItemClickListener listener) {
        this.kitapArrayList = kitapArrayList;
        this.context = context;
        this.listener=listener;
    }

    @NonNull
    @Override
    public KitapViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //view adında bir view oluşturuldu ve list_item_kitap adıdaki xml i viewe dönüştürdü.
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_kitap,viewGroup,false);
        //KitapViewHolder e view ve listenerlerı vererek yeni bir view oluşturuldu.
        return new KitapViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull KitapViewHolder viewHolder, int position) {

        //viewHolderdaki nesnelere datamızdaki verileri atadık.
        viewHolder.txtAdi.setText(kitapArrayList.get(position).getAdi());
        viewHolder.txtDil.setText(kitapArrayList.get(position).getDil());
        viewHolder.txtTarih.setText(kitapArrayList.get(position).getYayinlanmaTarihi());
        viewHolder.txtKategori.setText(kitapArrayList.get(position).getKategori());
        viewHolder.txtSayfa.setText(kitapArrayList.get(position).getSayfaSayisi());
        viewHolder.txtYazar.setText(kitapArrayList.get(position).getYazar());
        //glide ile datamızdaki resmi ekrana bastık.
        GlideUtil.resmiIndiripGoster(context,kitapArrayList.get(position).getLogoURL(),viewHolder.imgLogo);

    }

    @Override
    public int getItemCount() {
        //ekrana basılıcak item sayısı listedeki kitap sayısı kadar olucak.
        return kitapArrayList.size();
    }
}
