package com.putnet.retro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.putnet.retro.model.Pokemon;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private List<Pokemon> pokemons;

    public PokemonAdapter(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        PokemonAdapter.PokemonViewHolder mvh = new PokemonAdapter.PokemonViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon pokemon = pokemons.get(position);
        holder.name.setText(pokemon.getName());
        holder.description.setText(pokemon.getDescription());
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public static  class PokemonViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            description = itemView.findViewById(R.id.item_description);
        }
    }
}
