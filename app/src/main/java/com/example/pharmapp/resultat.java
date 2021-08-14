package com.example.pharmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class resultat extends AppCompatActivity {

    TextView txt;
    public double dose_adm_;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultat);

        txt = (TextView) findViewById(R.id.resultat_txt);
        Bundle bundle_dose = getIntent().getExtras();
        dose_adm_ = bundle_dose.getDouble("dose_adm");
        if(dose_adm_ != 0) {
            text = "La dose à administrer pour cette médicament est: " + String.valueOf(dose_adm_);
        }else {
            text = "Le médicament est contre-indiqué. ";
        }
        txt.setText(text);
    }

    public void exiteMeth(View view) {
        finish();
    }
}