package com.example.pharmapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class preparation_1_champ extends AppCompatActivity {

    EditText resultat_bilan_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preparation_1_champ);

        resultat_bilan_input = (EditText) findViewById(R.id.resultat_input);
    }

    public void calculer_dose_adm(View view) {
        String result = resultat_bilan_input.getText().toString().trim();
        if (result.isEmpty() )
        {resultat_bilan_input.setError("Le champ doit être rempli");}
      else{
          int id_med = -1;
            double coeff_min = -1, dose_complet = -1, dose_adm = -1;
            DataBase myDB = new DataBase(preparation_1_champ.this);
            Bundle bundle = getIntent().getExtras();
            id_med = bundle.getInt("id_med");

            dose_complet = bundle.getDouble("dose_complet");
            coeff_min = myDB.recupererCoeffMin(id_med,
                    Double.valueOf(resultat_bilan_input.getText().toString().trim()));

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

}