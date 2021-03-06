package com.h5190032_baran_can_anac_final.network;

import com.h5190032_baran_can_anac_final.model.Kitap;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ServiceApi {
    //internetten çekilecek json dosyasının ismi yazılarak listedeye get edildi.
    @GET("zlivaneliKitapApi.json") Observable<List<Kitap>> kitaplariGetir();
}
