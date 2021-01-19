package com.h5190032_baran_can_anac_final.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.h5190032_baran_can_anac_final.R;
//bu clasda internetteki resmi ekrana basabilmek için bir fonksiyon oluşturuldu.
//context, url ve ImageView verilerek ekrana resim basılabilir.
public class GlideUtil {
    public  static  void resmiIndiripGoster(Context context, String url, ImageView img)
    {
        Glide.with(context)
                .load(url)
                .error(R.drawable.error)
                .centerCrop()
                .into(img);
    }
}
