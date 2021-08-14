package com.example.pharmapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class patient extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    SearchView searchView;

    DataBase myDB;
    ArrayList<String> id_pat, nom_pat, prenom_pat, poid_pat, age_pat;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient);

        recyclerView = findViewById(R.id.recycle_1);
       // searchView = (SearchView) findViewById(R.id.searchViewPat);
        add_button = findViewById(R.id.addPat_float);

        myDB = new DataBase(patient.this);
        id_pat = new ArrayList<>();
        nom_pat = new ArrayList<>();
        prenom_pat = new ArrayList<>();
        poid_pat = new ArrayList<>();
        age_pat = new ArrayList<>();

        StoreDataInArrays();

        customAdapter = new CustomAdapter(patient.this, this, id_pat, nom_pat, prenom_pat, poid_pat, age_pat);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(patient.this));

     /*   searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            recreate();
        }
    }

    public void StoreDataInArrays()
    {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "Y'a pas des patients enrégistrés", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext())
            {
                id_pat.add(cursor.getString(0));
                nom_pat.add(cursor.getString(1));
                prenom_pat.add(cursor.getString(2));
                poid_pat.add(cursor.getString(3));
                age_pat.add(cursor.getString(4));

            }
        }
    }

    public void ajouterMeth(View view)
    {
        Intent intent = new Intent(this, ajou_pat.class);
        startActivity(intent);

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
    void confirmDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Supprimer tous?");
        builder.setMessage("Êtes vous sûr de supprimer tous les patients " );
        builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataBase myDB = new DataBase(patient.this);
                myDB.deleteAllData();
                //refresh activity
                Intent intent = new Intent(patient.this, patient.class);
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