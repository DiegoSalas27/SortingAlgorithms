package com.example.android.sortingalgorithms.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sortingalgorithms.R;
import com.example.android.sortingalgorithms.activities.GuideMenu;
import com.example.android.sortingalgorithms.activities.MainActivity;
import com.example.android.sortingalgorithms.activities.SavedActivity;
import com.example.android.sortingalgorithms.models.Partida;

import java.util.ArrayList;

public class PartidasAdapter extends RecyclerView.Adapter<PartidasAdapter.ViewHolderPartidas> {

    ArrayList<Partida> partidas;
    private Context mContext;

    public PartidasAdapter(Context context, ArrayList<Partida> partidas) {
        this.partidas = partidas;
        mContext = context;
    }

    @Override
    public ViewHolderPartidas onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_saved, null, false);
        return new ViewHolderPartidas(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderPartidas holder, int position) {
        holder.bindTo(partidas.get(position));
    }

    @Override
    public int getItemCount() {
        return partidas.size();
    }

    public class ViewHolderPartidas extends  RecyclerView.ViewHolder {

        TextView partidaName;
        TextView partidaFecha;
        CardView contentPartida;

        public ViewHolderPartidas(View itemView) {
            super(itemView);
            partidaName = itemView.findViewById(R.id.partidaName);
            partidaFecha = itemView.findViewById(R.id.partidaFecha);
            contentPartida = itemView.findViewById(R.id.contentPartida);
        }

        public void bindTo(final Partida partida) {
            partidaName.setText("Partida " + partida.getId().toString());
            partidaFecha.setText("Fecha: " + partida.getFecha());
            contentPartida.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("nivel", String.valueOf(partida.getNivel()));
                    bundle.putString("algoritmo", partida.getAlgoritmo());
                    bundle.putString("puntaje", String.valueOf(partida.getPuntaje()));
                    bundle.putString("iteracion", String.valueOf(partida.getIteracion()));
                    bundle.putString("numeros", partida.getNumeros());
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
