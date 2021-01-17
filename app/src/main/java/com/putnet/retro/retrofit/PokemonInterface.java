package com.putnet.retro.retrofit;

import com.putnet.retro.model.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonInterface {
    @GET("/pokemon")
    Call<List<Pokemon>> getAll();
}
