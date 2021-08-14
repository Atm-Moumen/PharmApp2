package com.example.pharmapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private Context context;
    Activity activity;
    public ArrayList id_pat, nom_pat, prenom_pat, poid_pat, age_pat;
    int id_pat_, age_pat_;
    String nom_pat_, prenom_pat_;
    double poid_pat_;

    CustomAdapter(Activity activity, Context context, ArrayList id_pat, ArrayList nom_pat, ArrayList prenom_pat,
                  ArrayList poid_pat, ArrayList age_pat){
        this.activity = activity;
        this.context = context;
        this.id_pat = id_pat ;
        this.nom_pat = nom_pat;
        this.prenom_pat = prenom_pat;
        this.poid_pat = poid_pat;
        this.age_pat = age_pat;

    }

    public CustomAdapter(int id_pat, String nom_pat, String prenom_pat, double poid_pat, int age_pat) {
        this.id_pat_ = id_pat ;
        this.nom_pat_ = nom_pat;
        this.prenom_pat_ = prenom_pat;
        this.poid_pat_ = poid_pat;
        this.age_pat_ = age_pat;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, final int position) {

        holder.id_pat_txt.setText(String.valueOf(id_pat.get(position)));
        holder.nom_pat_txt.setText(String.valueOf(nom_pat.get(position)));
        holder.prenom_pat_txt.setText(String.valueOf(prenom_pat.get(position)));
        holder.poid_pat_txt.setText("poid: " + String.valueOf(poid_pat.get(position)));
        holder.age_pat_txt.setText("age: " + String.valueOf(age_pat.get(position)));
        holder.mainLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, mod_pat.class);
            intent.putExtra("id", String.valueOf(id_pat.get(position)));
            intent.putExtra("nom", String.valueOf(nom_pat.get(position)));
            intent.putExtra("prenom", String.valueOf(prenom_pat.get(position)));
            intent.putExtra("poid", String.valueOf(poid_pat.get(position)));
            intent.putExtra("age", String.valueOf(age_pat.get(position)));
            activity.startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return id_pat.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_pat_txt, nom_pat_txt, prenom_pat_txt, poid_pat_txt, age_pat_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_pat_txt = itemView.findViewById(R.id.id_pat_txt);
            nom_pat_txt = itemView.findViewById(R.id.nom_pat_txt);
            prenom_pat_txt = itemView.findViewById(R.id.prenom_pat_txt);
            poid_pat_txt = itemView.findViewById(R.id.poid_pat_txt);
            age_pat_txt = itemView.findViewById(R.id.age_pat_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
