package com.h5190032_baran_can_anac_final.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.h5190032_baran_can_anac_final.R;

public class KitapViewHolder extends RecyclerView.ViewHolder {
    TextView txtAdi,txtYazar,txtSayfa,txtKategori,txtTarih,txtDil;
    ImageView imgLogo;

    public KitapViewHolder(@NonNull View itemView ,KitapAdapter.OnItemClickListener listener) {
        super(itemView);
        txtAdi=itemView.findViewById(R.id.txtAdiC);
        txtYazar=itemView.findViewById(R.id.txtYazarC);
        txtSayfa=itemView.findViewById(R.id.txtSayfaSayisiC);
        txtKategori=itemView.findViewById(R.id.txtKategoriC);
        txtTarih=itemView.findViewById(R.id.txtTarihC);
        txtDil=itemView.findViewById(R.id.txtDilC);
        imgLogo=itemView.findViewById(R.id.imgLogo);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClick(getAdapterPosition());
            }
        });
    }
}
