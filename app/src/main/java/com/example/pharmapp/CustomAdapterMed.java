package com.example.pharmapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterMed extends RecyclerView.Adapter<CustomAdapterMed.MyViewHolderMed> {

    private Context context;
    Activity activity;
    private ArrayList id_med, nom_med, labo_med, type_bilan_med;

    CustomAdapterMed(Activity activity, Context context, ArrayList id_med, ArrayList nom_med, ArrayList labo_med, ArrayList type_bilan_med){
        this.activity = activity;
        this.context = context;
        this.id_med = id_med ;
        this.nom_med = nom_med;
        this.labo_med = labo_med;
        this.type_bilan_med = type_bilan_med;

    }

    @NonNull
    @Override
    public CustomAdapterMed.MyViewHolderMed onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_med, parent, false);
        return new CustomAdapterMed.MyViewHolderMed(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterMed.MyViewHolderMed holder, final int position) {

        holder.id_med_txt.setText(String.valueOf(id_med.get(position)));
        holder.nom_med_txt.setText("Médicament: " +String.valueOf(nom_med.get(position)));
        holder.labo_med_txt.setText("Laboratoire: "+String.valueOf(labo_med.get(position)));
        holder.type_bilan_med_txt.setText("Bilan: "+String.valueOf(type_bilan_med.get(position)));

        holder.mainLayout.setOnClickListener(v -> {
            Intent intent = new Intent (context, mod_med.class);
            intent.putExtra("id_med", String.valueOf(id_med.get(position)));
            intent.putExtra("nom_med", String.valueOf(nom_med.get(position)));
            intent.putExtra("labo_med", String.valueOf(labo_med.get(position)));
            intent.putExtra("type_bilan_med", String.valueOf(type_bilan_med.get(position)));
            activity.startActivityForResult(intent, 1);

        });

    }

    @Override
    public int getItemCount() { return id_med.size(); }
    public static class MyViewHolderMed extends RecyclerView.ViewHolder {
        TextView id_med_txt, nom_med_txt, labo_med_txt, type_bilan_med_txt;
        LinearLayout mainLayout;

        public MyViewHolderMed(@NonNull View itemView) {
            super(itemView);
            id_med_txt = itemView.findViewById(R.id.id_med_txt);
            nom_med_txt = itemView.findViewById(R.id.nom_med_txt);
            labo_med_txt = itemView.findViewById(R.id.labo_med_txt);
            type_bilan_med_txt = itemView.findViewById(R.id.type_bilan_med_txt);
            mainLayout = itemView.findViewById(R.id.mainLayoutMed);
        }
    }

}

