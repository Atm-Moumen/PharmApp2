package com.example.pharmapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class preparation extends AppCompatActivity {

    public String fenetre;
    public double dose_complet ;
    public int id_med;
    EditText nom_input, dose_input, nom_med_input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preparation);

        nom_input = (EditText) findViewById(R.id.nomPat_input);
        dose_input = (EditText) findViewById(R.id.dose_input);
        nom_med_input = (EditText) findViewById(R.id.nomMed_input);
    }


    public void ajouMedMeth(View view) {
        Intent intent = new Intent(this, ajou_med.class);
        startActivity(intent);
    }

    public void continuerPreparationMeth(View view) {
        String nomMed = nom_med_input.getText().toString().trim();
        String nomPat = nom_input.getText().toString().trim();
        String dose = dose_input.getText().toString().trim();

        if(nomMed.isEmpty() || nomPat.isEmpty() || dose.isEmpty() )
        {
            if(nomMed.isEmpty())
            {nom_med_input.setError("Le champ doit être rempli");}
            if(nomPat.isEmpty())
            {nom_input.setError("Le champ doit être rempli");}
            if(dose.isEmpty())
            {dose_input.setError("Le champ doit être rempli");}
        }else {

            DataBase myDB = new DataBase(preparation.this);
            double poid = myDB.recupererPoid(nom_input.getText().toString().trim());
            double dose_ord = Double.valueOf(dose_input.getText().toString().trim());

            dose_complet = poid * dose_ord;
            id_med = myDB.recupererIDMed(nom_med_input.getText().toString().trim());

            fenetre = myDB.recupererTypeBilanMed(nom_med_input.getText().toString().trim());

            if (poid == -1) // si le patient existe
                nom_input.setError("le patient n'exite pas, \n Ajouter le SVP");
            else {
                switch (fenetre) {
                    case "Clairance rénale": {
                        Intent intent = new Intent(this, preparation_1_champ.class);

                        // passer les donnees a 2 eme activity
                        Bundle bundle = new Bundle();
                        bundle.putDouble("dose_complet", dose_complet);
                        bundle.putInt("id_med", id_med);
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                    break;
                    case "Bilirubin + tgo/tgp": {
                        Intent intent = new Intent(this, preparation_2_champ.class);

                        // passer les donnees a 2 eme activity
                        Bundle bundle = new Bundle();
                        bundle.putDouble("dose_complet", dose_complet);
                        bundle.putInt("id_med", id_med);
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                    break;
                    case "Clairance rénale + Bilirubin + tgo/tgp": {
                        Intent intent = new Intent(this, preparation_3_champ.class);

                        // passer les donnees a 2 eme activity
                        Bundle bundle = new Bundle();
                        bundle.putDouble("dose_complet", dose_complet);
                        bundle.putInt("id_med", id_med);
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                    break;
                    default: {
                        Intent intent = new Intent(preparation.this, preparation.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        }
    }

    public void ajouPatMeth(View view)
    {
        Intent intent = new Intent(this, ajou_pat.class);
        startActivity(intent);
    }



}
