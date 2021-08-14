package com.example.pharmapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ajou_pat extends AppCompatActivity {
    EditText nom_input, prenom_input, poid_input, age_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajou_pat);

        nom_input = findViewById(R.id.nom_input);
        prenom_input = findViewById(R.id.prenom_input);
        poid_input = findViewById(R.id.poid_input);
        age_input = findViewById(R.id.age_input);
    }


    public void ajouterMeth(View view) {
        String nom = nom_input.getText().toString().trim();
        String prenom = prenom_input.getText().toString().trim();
        String age = age_input.getText().toString().trim();
        String poid = poid_input.getText().toString().trim();
        if(nom.isEmpty() || prenom.isEmpty() || age.isEmpty() || poid.isEmpty() )
        {
            if(nom.isEmpty())
                {nom_input.setError("Le champ doit être rempli");}
            if(prenom.isEmpty())
                {prenom_input.setError("Le champ doit être rempli");}
            if(age.isEmpty())
                {age_input.setError("Le champ doit être rempli");}
            if(poid.isEmpty())
                {poid_input.setError("Le champ doit être rempli");}
        }else {
            DataBase myDB = new DataBase(ajou_pat.this);
            myDB.ajouterBDMeth(nom_input.getText().toString().trim(),
                    prenom_input.getText().toString().trim(),
                    Double.valueOf(poid_input.getText().toString().trim()),
                    Integer.valueOf(age_input.getText().toString().trim()));

            nom_input.setText("");
            prenom_input.setText("");
            poid_input.setText("");
            age_input.setText("");
        }

    }

}