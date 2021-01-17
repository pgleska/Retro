package com.putnet.retro.ui.all;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.putnet.retro.PokemonAdapter;
import com.putnet.retro.R;
import com.putnet.retro.model.Pokemon;
import com.putnet.retro.retrofit.PokemonInterface;
import com.putnet.retro.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllFragment extends Fragment {

    private View root;

    private PokemonInterface pokemonInterface;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_all, container, false);

        initComponents();

        getAllPokemon();

        return root;
    }

    private void initComponents() {
        recyclerView = root.findViewById(R.id.list_pokemon_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void getAllPokemon() {
        pokemonInterface = RetrofitClient.getPokemonInterface(getResources().getString(R.string.api));

        Call<List<Pokemon>> call = pokemonInterface.getAll();

        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                List<Pokemon> all = response.body();
                adapter = new PokemonAdapter(all);
                recyclerView.setAdapter(adapter);
                Toast.makeText(getContext(), "Number of pokemons: " + all.size(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                Toast.makeText(getContext(), "Unknown error", Toast.LENGTH_LONG).show();
            }
        });
    }
}