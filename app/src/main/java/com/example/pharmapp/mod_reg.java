package com.example.pharmapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

public class mod_reg extends AppCompatActivity implements  PopupMenu.OnMenuItemClickListener {

    EditText IDMed_input, concMin_input, concMax_input, coeff_input;
    String type_bilan, id_reg, id_med_reg, conc_min, conc_max, coeff;
    Button modifier_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mod_reg);

        IDMed_input = (EditText) findViewById(R.id.nomMed_input2);
        concMin_input  = (EditText)  findViewById(R.id.concMin_input2);
        concMax_input = (EditText) findViewById(R.id.concMax_input2);
        coeff_input  = (EditText)  findViewById(R.id.coeff_input2);
        modifier_btn = (Button) findViewById(R.id.modifier_reg);

        getAndSetIntentData();
        modifier_btn.setOnClickListener(v -> {
            String nomMed = IDMed_input.getText().toString().trim();
            String concMin = concMin_input.getText().toString().trim();
            String concMax = concMax_input.getText().toString().trim();
            String coeff = coeff_input.getText().toString().trim();
            if(nomMed.isEmpty() || concMin.isEmpty() || concMax.isEmpty() || coeff.isEmpty() )
            {
                if(nomMed.isEmpty())
                {IDMed_input.setError("Le champ doit être rempli");}
                if(concMin.isEmpty())
                {concMin_input.setError("Le champ doit être rempli");}
                if(concMax.isEmpty())
                {concMax_input.setError("Le champ doit être rempli");}
                if(coeff.isEmpty())
                {coeff_input.setError("Le champ doit être rempli");}
            }else {

                DataBase myDB = new DataBase(mod_reg.this);
                id_med_reg = IDMed_input.getText().toString().trim();
                conc_min = concMin_input.getText().toString().trim();
                conc_max = concMax_input.getText().toString().trim();
                coeff = coeff_input.getText().toString().trim();

                myDB.updateDataReg(id_reg, id_med_reg, conc_min, conc_max, coeff, type_bilan);
            }
        });

    }

    public void afficherMenu(View view)
    {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.type_bilan_2);
        popup.show();

    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_1: {
                Toast.makeText(getApplicationContext(), "clairance rénale sélectionné", Toast.LENGTH_SHORT).show();
                type_bilan = "Clairance rénale";
            }break;
            case R.id.item_2: {
                Toast.makeText(getApplicationContext(), "bilirubin sélectionné", Toast.LENGTH_SHORT).show();
                type_bilan = "Bilirubin";
            }break;
            case R.id.item_3: {
                Toast.makeText(getApplicationContext(), "tgo/tgp  sélectionné", Toast.LENGTH_SHORT).show();
                type_bilan = "tgo/tgp";
            }break;
            default:return  false;
        }
        return true;
    }

    void  getAndSetIntentData()
    {
        if(getIntent().hasExtra("id_reg") && getIntent().hasExtra("id_med_reg") && getIntent().hasExtra("conc_min") &&
                getIntent().hasExtra("conc_max") && getIntent().hasExtra("coeff") )
        {
            //getting data from intent
            id_reg = getIntent().getStringExtra("id_reg");
            id_med_reg = getIntent().getStringExtra("id_med_reg");
            conc_min = getIntent().getStringExtra("conc_min");
            conc_max = getIntent().getStringExtra("conc_max");
            coeff = getIntent().getStringExtra("coeff");
            //setting intent data
            IDMed_input.setText(id_med_reg);
            concMin_input.setText(conc_min);
            concMax_input.setText(conc_max);
            coeff_input.setText(coeff);

        }else{
            Toast.makeText(this, "y'a pas des données.", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialogReg(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Supprimer ?");
        builder.setMessage("Êtes vous sûr de supprimer la règle " );
        builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataBase myDB = new DataBase(mod_reg.this);
                myDB.deleteOnRowReg(id_reg);
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

    public void supprimerMth(View view) {
        confirmDialogReg();
    }
}