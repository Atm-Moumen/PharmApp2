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

public class CustomAdapterReg extends RecyclerView.Adapter<CustomAdapterReg.MyViewHolderAdapterReg> {

    private Context context;
    Activity activity;
    private ArrayList id_reg, id_med_reg, conc_min, conc_max, coeff, type_bilan_reg;

    CustomAdapterReg(Activity activity, Context context, ArrayList id_reg, ArrayList id_med_reg, ArrayList conc_min,
                     ArrayList conc_max, ArrayList coeff, ArrayList type_bilan_reg){
        this.activity = activity;
        this.context = context;
        this.id_reg = id_reg;
        this.id_med_reg = id_med_reg;
        this.conc_min = conc_min;
        this.conc_max = conc_max;
        this.coeff = coeff;
        this.type_bilan_reg = type_bilan_reg;
    }

    @NonNull
    @Override
    public MyViewHolderAdapterReg onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_regle, parent, false);
        return new MyViewHolderAdapterReg(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderAdapterReg holder, int position) {
        holder.id_reg_txt.setText("ID règle: " + String.valueOf(id_reg.get(position)));
        holder.id_med_reg_txt.setText("ID médicament: " + String.valueOf(id_med_reg.get(position)));
        holder.concMin_txt.setText("Concentration minimal: " + String.valueOf(conc_min.get(position)));
        holder.concMax_txt.setText("Concentration maximal: " + String.valueOf(conc_max.get(position)));
        holder.coeff_txt.setText("Coefficient: " + String.valueOf(coeff.get(position)));
        holder.type_bilan_reg_txt.setText("Type Bilan: " + String.valueOf(type_bilan_reg.get(position)));
        holder.mainlayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, mod_reg.class);
            intent.putExtra("id_reg", String.valueOf(id_reg.get(position)));
            intent.putExtra("id_med_reg", String.valueOf(id_med_reg.get(position)));
            intent.putExtra("conc_min", String.valueOf(conc_min.get(position)));
            intent.putExtra("conc_max", String.valueOf(conc_max.get(position)));
            intent.putExtra("coeff", String.valueOf(coeff.get(position)));
            //intent.putExtra("type_bilan_reg", String.valueOf(type_bilan_reg.get(position)));
            activity.startActivityForResult(intent, 1);
        });

    }

    @Override
    public int getItemCount() {
        return id_reg.size();
    }

    public class MyViewHolderAdapterReg extends RecyclerView.ViewHolder {

        TextView id_reg_txt, id_med_reg_txt, concMin_txt, concMax_txt, coeff_txt, type_bilan_reg_txt;
        LinearLayout mainlayout;

        public MyViewHolderAdapterReg(@NonNull View itemView) {
            super(itemView);
            id_reg_txt = itemView.findViewById(R.id.id_reg_txt);
            id_med_reg_txt = itemView.findViewById(R.id.id_med_reg_txt);
            concMin_txt = itemView.findViewById(R.id.concMin_txt);
            concMax_txt = itemView.findViewById(R.id.concMax_txt);
            coeff_txt = itemView.findViewById(R.id.coeff_txt);
            type_bilan_reg_txt = itemView.findViewById(R.id.type_bilan_reg_txt);
            mainlayout = itemView.findViewById(R.id.mainLayoutReg);
        }
    }
}
