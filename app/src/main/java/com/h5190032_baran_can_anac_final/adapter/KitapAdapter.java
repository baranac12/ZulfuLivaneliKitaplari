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

    public interface OnItemClickListener{
        void OnClick(int position);
    }
    public KitapAdapter(List<Kitap> kitapArrayList, Context context, OnItemClickListener listener) {
        this.kitapArrayList = kitapArrayList;
        this.context = context;
        this.listener=listener;
    }

    @NonNull
    @Override
    public KitapViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_kitap,viewGroup,false);

        return new KitapViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull KitapViewHolder viewHolder, int position) {

        viewHolder.txtAdi.setText(kitapArrayList.get(position).getAdi());
        viewHolder.txtDil.setText(kitapArrayList.get(position).getDil());
        viewHolder.txtTarih.setText(kitapArrayList.get(position).getYayinlanmaTarihi());
        viewHolder.txtKategori.setText(kitapArrayList.get(position).getKategori());
        viewHolder.txtSayfa.setText(kitapArrayList.get(position).getSayfaSayisi());
        viewHolder.txtYazar.setText(kitapArrayList.get(position).getYazar());

        GlideUtil.resmiIndiripGoster(context,kitapArrayList.get(position).getLogoURL(),viewHolder.imgLogo);

    }

    @Override
    public int getItemCount() {
        return kitapArrayList.size();
    }
}
