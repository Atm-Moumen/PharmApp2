package com.example.pharmapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

public class mod_med extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    EditText nom_med_input, labo_med_input;
    Button modifier_btn, supprimer_btn, affiche_reg_btn;
    public String id_med, nom_med, labo_med, type_bilan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mod_med);

        nom_med_input = (EditText) findViewById(R.id.nom_Med_input2);
        labo_med_input = (EditText) findViewById(R.id.labo_Med_input2);
        modifier_btn = (Button) findViewById(R.id.Modifier_med);
        supprimer_btn = (Button) findViewById(R.id.Supprimer_med);
        affiche_reg_btn = (Button) findViewById(R.id.afficheReg);

        getAndSetIntentData();

        // changer le titre de la page
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(nom_med);
        }
        modifier_btn.setOnClickListener(v -> {
            String nom_med_ = nom_med_input.getText().toString().trim();
            String labo = labo_med_input.getText().toString().trim();

            if(nom_med_.isEmpty() || labo.isEmpty() )
            {
                if(nom_med_.isEmpty())
                    {nom_med_input.setError("Le champ doit être rempli");}
                if(labo.isEmpty())
                    {labo_med_input.setError("Le champ doit être rempli");}

            }else {

                DataBase myDB = new DataBase(mod_med.this);

                nom_med = nom_med_input.getText().toString().trim();
                labo_med = labo_med_input.getText().toString().trim();
                myDB.updateDataMed(id_med, nom_med, labo_med, type_bilan);
            }
        });
        supprimer_btn.setOnClickListener(v -> {
            confirmDialogMed();
        });

    }
    void  getAndSetIntentData()
    {
        if(getIntent().hasExtra("id_med") && getIntent().hasExtra("nom_med") && getIntent().hasExtra("labo_med") )
        {
            //getting data from intent
            id_med = getIntent().getStringExtra("id_med");
            nom_med = getIntent().getStringExtra("nom_med");
            labo_med = getIntent().getStringExtra("labo_med");
            //setting intent data
            nom_med_input.setText(nom_med);
            labo_med_input.setText(labo_med);

        }else{
            Toast.makeText(this, "y'a pas des données.", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialogMed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Supprimer " + nom_med + " ?");
        builder.setMessage("Êtes vous sûr de supprimer le médicament " );
        builder.setPositiveButton("oui", (dialog, which) -> {
            DataBase myDB = new DataBase(mod_med.this);
            myDB.deleteOnRowMed(id_med);
            finish();

        });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void afficherReg(View view) {
        Intent intent = new Intent(this, regle.class);
        startActivity(intent);
    }
}