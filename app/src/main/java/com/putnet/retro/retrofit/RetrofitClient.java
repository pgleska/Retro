package com.putnet.retro.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    private RetrofitClient() {}

    private static Retrofit getClient(String baseUrl) {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static PokemonInterface getPokemonInterface(String baseUrl) {
        return getClient(baseUrl).create(PokemonInterface.class);
    }
}
