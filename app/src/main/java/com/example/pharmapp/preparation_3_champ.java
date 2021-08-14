package com.example.pharmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class preparation_3_champ extends AppCompatActivity {

    EditText resultat_bilan_input, resultat_bilan_input2, resultat_bilan_input3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preparation_3_champ);

        resultat_bilan_input = (EditText) findViewById(R.id.resultat_input);
        resultat_bilan_input2 = (EditText) findViewById(R.id.resultat_input_2);
        resultat_bilan_input3 = (EditText) findViewById(R.id.resultat_input_3);
    }

    public void calculer_dose_adm(View view) {
        String res_1 = resultat_bilan_input.getText().toString().trim();
        String res_2 = resultat_bilan_input2.getText().toString().trim();
        String res_3 = resultat_bilan_input3.getText().toString().trim();

        if (res_1.isEmpty() || res_2.isEmpty() || res_3.isEmpty()) {
            if (res_1.isEmpty()) {
                resultat_bilan_input.setError("Le champ doit être rempli");
            }
            if (res_2.isEmpty()) {
                resultat_bilan_input2.setError("Le champ doit être rempli");
            }
            if (res_3.isEmpty()) {
                resultat_bilan_input3.setError("Le champ doit être rempli");
            }
        } else {

            int id_med = -1;
            double coeff_min = -1, dose_complet = -1, dose_adm = -1, coeff_min_1 = -1, coeff_min_2 = -1, coeff_min_3 = -1;

            DataBase myDB = new DataBase(preparation_3_champ.this);
            Bundle bundle = getIntent().getExtras();
            id_med = bundle.getInt("id_med");
            dose_complet = bundle.getDouble("dose_complet");
            coeff_min_1 = myDB.recupererCoeffMin(id_med,
                    Double.valueOf(resultat_bilan_input.getText().toString().trim()));
            coeff_min_2 = myDB.recupererCoeffMin(id_med,
                    Double.valueOf(resultat_bilan_input2.getText().toString().trim()));
            coeff_min_3 = myDB.recupererCoeffMin(id_med,
                    Double.valueOf(resultat_bilan_input3.getText().toString().trim()));

            coeff_min = trouve_min(coeff_min_1, coeff_min_2, coeff_min_3);

            if (coeff_min <= 1) {
                dose_adm = coeff_min * dose_complet;

                Intent intent = new Intent(this, resultat.class);

                // passer les donnees a 2 eme activity
                Bundle bundle_dose = new Bundle();
                bundle_dose.putDouble("dose_adm", dose_adm);
                intent.putExtras(bundle_dose);

                startActivity(intent);
            } else {
                Intent intent = new Intent(this, resultat.class);

                // passer les donnees a 2 eme activity
                Bundle bundle_dose = new Bundle();
                bundle_dose.putDouble("dose_adm", coeff_min);
                intent.putExtras(bundle_dose);
                startActivity(intent);
            }

        }
    }
    double trouve_min(double a, double b, double c)
    {
        double min = -1;
        if(a <= b)
            min = a;
        else {
           min = b;
        }
        if(min >= c)
            min = c;

        return min;
    }
}