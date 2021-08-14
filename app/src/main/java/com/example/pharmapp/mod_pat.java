package com.example.pharmapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class mod_pat extends AppCompatActivity {

    EditText nom_input, prenom_input, poid_input, age_input;
    Button modifier_btn, supprimer_btn;
   public String id, nom, prenom, poid, age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mod_pat);

        nom_input = findViewById(R.id.nom_input2);
        prenom_input = findViewById(R.id.prenom_input2);
        poid_input = findViewById(R.id.poid_input2);
        age_input = findViewById(R.id.age_input2);
        modifier_btn = findViewById(R.id.Modifier);
        supprimer_btn = findViewById(R.id.Supprimer);

        //first we call this
        getAndSetIntentData();

        modifier_btn.setOnClickListener(v -> {
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

                DataBase myDB = new DataBase(mod_pat.this);
                //And only then we call this

                nom = nom_input.getText().toString().trim();
                prenom = prenom_input.getText().toString().trim();
                poid = poid_input.getText().toString().trim();
                age = age_input.getText().toString().trim();

                myDB.updateData(id, nom, prenom, poid, age);
            }
        });

        supprimer_btn.setOnClickListener(v -> {
            confirmDialog();
        });



    }


    void  getAndSetIntentData()
    {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("nom") && getIntent().hasExtra("prenom") &&
                getIntent().hasExtra("poid") && getIntent().hasExtra("age") )
        {
            //getting data from intent
            id = getIntent().getStringExtra("id");
            nom = getIntent().getStringExtra("nom");
            prenom = getIntent().getStringExtra("prenom");
            poid = getIntent().getStringExtra("poid");
            age = getIntent().getStringExtra("age");
            //setting intent data
            nom_input.setText(nom);
            prenom_input.setText(prenom);
            poid_input.setText(poid);
            age_input.setText(age);

        }else{
            Toast.makeText(this, "y'a pas des données.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Supprimer " + nom + " ?");
        builder.setMessage("Êtes vous sûr de supprimer le patient " );
        builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataBase myDB = new DataBase(mod_pat.this);
                myDB.deleteOnRow(id);
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