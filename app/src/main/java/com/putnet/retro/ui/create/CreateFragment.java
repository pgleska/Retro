package com.putnet.retro.ui.create;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.putnet.retro.R;
import com.putnet.retro.model.Pokemon;
import com.putnet.retro.retrofit.PokemonInterface;
import com.putnet.retro.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateFragment extends Fragment {

    private View root;
    private EditText nameEt, descriptionEt;
    private Button btn;
    private PokemonInterface pokemonInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_create, container, false);

        initComponents();
        initOnClickListeners();

        return root;
    }

    private void initComponents() {
        pokemonInterface = RetrofitClient.getPokemonInterface(getResources().getString(R.string.api));
        nameEt = root.findViewById(R.id.create_name);
        descriptionEt = root.findViewById(R.id.create_description);
        btn = root.findViewById(R.id.create_button);
    }

    private void initOnClickListeners() {
        btn.setOnClickListener(view -> {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(root.getWindowToken(), 0);
            Pokemon pokemon = new Pokemon();
            pokemon.setName(nameEt.getText().toString());
            pokemon.setDescription(descriptionEt.getText().toString());
            Call<Pokemon> call = pokemonInterface.create(pokemon);

            call.enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    Toast.makeText(getContext(), "Pokemon created", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {
                    Toast.makeText(getContext(), "Unknown error", Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}
