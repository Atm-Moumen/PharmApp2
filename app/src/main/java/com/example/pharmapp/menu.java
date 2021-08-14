package com.example.pharmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    public void patientMethod(View view)
    {
        Intent intent = new Intent(this, patient.class);
        startActivity(intent);
    }

    public void medicamentMethod(View view)
    {
        Intent intent = new Intent(this, medicament.class);
        startActivity(intent);
    }

    public void preparationMethod(View view) {
        Intent intent = new Intent(this, preparation.class);
        startActivity(intent);
    }
}