package com.putnet.retro.ui.all;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.putnet.retro.R;
import com.putnet.retro.model.Pokemon;
import com.putnet.retro.retrofit.PokemonInterface;
import com.putnet.retro.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllFragment extends Fragment {

    private PokemonInterface pokemonInterface;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all, container, false);

        getAllPokemon();

        return root;
    }

    private void getAllPokemon() {
        pokemonInterface = RetrofitClient.getPokemonInterface(getResources().getString(R.string.api));

        Call<List<Pokemon>> call = pokemonInterface.getAll();

        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                List<Pokemon> all = response.body();
                Toast.makeText(getContext(), "Number of pokemons: " + all.size(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                Toast.makeText(getContext(), "Unknown error", Toast.LENGTH_LONG).show();
            }
        });
    }
}