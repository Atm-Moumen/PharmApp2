package com.example.pharmapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class medicament extends AppCompatActivity {

    RecyclerView recyclerView;
    DataBase myDB;
    ArrayList<String> id_med, nom_med, labo_med, type_bilan_med;
    CustomAdapterMed customAdapterMed;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            recreate();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicament);

        recyclerView = findViewById(R.id.recycle_med);

        myDB = new DataBase(medicament.this);
        id_med = new ArrayList<>();
        nom_med = new ArrayList<>();
        labo_med = new ArrayList<>();
        type_bilan_med = new ArrayList<>();

        StoreDataInArrays();
        customAdapterMed = new CustomAdapterMed(medicament.this, this,id_med, nom_med, labo_med, type_bilan_med);
        recyclerView.setAdapter(customAdapterMed);
        recyclerView.setLayoutManager(new LinearLayoutManager(medicament.this));


    }

    public void ajouter_Methode(View view) {
        Intent intent = new Intent(this, ajou_med.class);
        startActivity(intent);
    }

    public void StoreDataInArrays()
    {
        Cursor cursor = myDB.readAllDataMed();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "Y'a pas des médicaments enrégistrés", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext())
            {
                id_med.add(cursor.getString(0));
                nom_med.add(cursor.getString(1));
                labo_med.add(cursor.getString(2));
                type_bilan_med.add(cursor.getString(3));

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_spp, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all)
        {
            confirmDialog();
        }

        return super.onOptionsItemSelected(item);
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Supprimer tous?");
        builder.setMessage("Êtes vous sûr de supprimer tous les médicaments " );
        builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataBase myDB = new DataBase(medicament.this);
                myDB.deleteAllDataMed();
                //refresh activity
                Intent intent = new Intent(medicament.this, medicament.class);
                startActivity(intent);
                finish();

            }
        });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }


}