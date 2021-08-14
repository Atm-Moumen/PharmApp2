package com.example.pharmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

public class ajou_med extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    EditText nom_med_input, labo_input;
    String type_bilan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajou_med);

        nom_med_input = (EditText) findViewById(R.id.nom_Med_input);
        labo_input  = (EditText)  findViewById(R.id.labo_Med_input);
    }

    public void ajouRegleMeth(View view)
    {
        Intent intent = new Intent(this, ajou_reg.class);
        startActivity(intent);
    }

     public void ajouter_Med_Meth(View view) {
         String nom_med = nom_med_input.getText().toString().trim();
         String labo = labo_input.getText().toString().trim();

         if(nom_med.isEmpty() || labo.isEmpty() )
         {
             if(nom_med.isEmpty())
             {nom_med_input.setError("Le champ doit être rempli");}
             if(labo.isEmpty())
             {labo_input.setError("Le champ doit être rempli");}

         }else {

             DataBase myDB = new DataBase(ajou_med.this);
             myDB.ajouterBDMethMed(nom_med_input.getText().toString().trim(),
                     labo_input.getText().toString().trim(),
                     type_bilan);

             nom_med_input.setText("");
             labo_input.setText("");
         }
    }
    public void afficherMenu (View view)
    {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.type_bilan);
        popup.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_1: {
                Toast.makeText(getApplicationContext(), "clairance rénale sélectionné", Toast.LENGTH_SHORT).show();
                 type_bilan = "Clairance rénale";
            }break;
            case R.id.item_2: {
                Toast.makeText(getApplicationContext(), "bilirubin + tgo/tgp sélectionnés", Toast.LENGTH_SHORT).show();
                type_bilan = "Bilirubin + tgo/tgp";
            }break;
            case R.id.item_3: {
                Toast.makeText(getApplicationContext(), "clairance rénale + bilirubin + tgo/tgp sélectionnés", Toast.LENGTH_SHORT).show();
                type_bilan = "Clairance rénale + Bilirubin + tgo/tgp";
            }break;
            default:return false;
        }
        return true;
    }

}