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
import android.widget.Toast;

import java.util.ArrayList;

public class regle extends AppCompatActivity {

    RecyclerView recyclerView;
    DataBase myDB;
    ArrayList<String> id_reg, id_med_reg, conc_min, conc_max, coeff, type_bilan_reg;
    CustomAdapterReg customAdapterReg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regle);

      recyclerView = (RecyclerView) findViewById(R.id.recycle_reg);

        myDB = new DataBase(regle.this);
        id_reg = new ArrayList<>();
        id_med_reg = new ArrayList<>();
        conc_min = new ArrayList<>();
        conc_max = new ArrayList<>();
        coeff = new ArrayList<>();
        type_bilan_reg = new ArrayList<>();

        StoreDataInArrays();

        customAdapterReg = new CustomAdapterReg(regle.this,this, id_reg, id_med_reg,
                                                conc_min, conc_max, coeff, type_bilan_reg);
        recyclerView.setAdapter(customAdapterReg);
        recyclerView.setLayoutManager(new LinearLayoutManager(regle.this));

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
        Cursor cursor = myDB.readAllDataReg();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "Y'a pas des règles enrégistrés", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext())
            {
                id_reg.add(cursor.getString(0));
                id_med_reg.add(cursor.getString(1));
                conc_min.add(cursor.getString(2));
                conc_max.add(cursor.getString(3));
                coeff.add(cursor.getString(4));
                type_bilan_reg.add(cursor.getString(6));
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
        builder.setMessage("Êtes vous sûr de supprimer tous les règles " );
        builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataBase myDB = new DataBase(regle.this);
                myDB.deleteAllDataReg();
                //refresh activity
                Intent intent = new Intent(regle.this, regle.class);
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
