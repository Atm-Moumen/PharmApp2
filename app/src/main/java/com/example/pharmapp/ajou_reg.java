package com.example.pharmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.Toast;

public class ajou_reg extends AppCompatActivity implements  PopupMenu.OnMenuItemClickListener{

    EditText nomMed_input, concMin_input, concMax_input, coeff_input;
    String type_bilan;
    int si_coeff = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajou_reg);

        nomMed_input = (EditText) findViewById(R.id.nomMed_input);
        concMin_input  = (EditText)  findViewById(R.id.concMin_input);
        concMax_input = (EditText) findViewById(R.id.concMax_input);
        coeff_input  = (EditText)  findViewById(R.id.coeff_input);

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


    public void ajou_reg_meth(View view)
    {
        double conc_min, conc_max;
        String nomMed = nomMed_input.getText().toString().trim();
        String concMin = concMin_input.getText().toString().trim();
        String concMax = concMax_input.getText().toString().trim();
        String coeff = coeff_input.getText().toString().trim();
        if(nomMed.isEmpty() || coeff.isEmpty() )
        {
            if(nomMed.isEmpty())
            {nomMed_input.setError("Le champ doit être rempli");}
            if(coeff.isEmpty())
            {coeff_input.setError("Le champ doit être rempli");}
        }else {
            if(concMin.isEmpty())
                {conc_min = -999;}
            else{
                conc_min = Double.valueOf(concMin_input.getText().toString().trim());
            }
            if(concMax.isEmpty())
            {conc_max = -999;}
            else{
                conc_max = Double.valueOf(concMax_input.getText().toString().trim());
            }
            DataBase myDB = new DataBase(ajou_reg.this);
            myDB.ajouterBDMethReg(myDB.recupererIDMed(nomMed_input.getText().toString().trim()), conc_min,
                    conc_max, Double.valueOf(coeff_input.getText().toString().trim()),
                    si_coeff, type_bilan);

            nomMed_input.setText("");
            concMin_input.setText("");
            concMax_input.setText("");
            coeff_input.setText("");
        }
    }


    public void radioButtomMeth(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.btn1:
                if (checked)
                    si_coeff = 1;
                break;
            case R.id.btn2:
                if (checked)
                    si_coeff = 0;
                break;
        }
    }

}
