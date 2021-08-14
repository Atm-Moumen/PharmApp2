package com.example.pharmapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import java.util.Collections;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

        private Context context;
        private static final String DATABASE_NAME = "DataBase.db";
        private static final int DATABASE_VERSION = 1;

        // le tableau de patien
        private static final String TABLE_NAME = "patient";
        private static final String COLUMN_ID = "id_pat";
        private static final String COLUMN_NOM = "nom_pat";
        private static final String COLUMN_PRENOM = "prenom_pat";
        private static final String COLUMN_POID = "poid_pat";
        private static final String COLUMN_AGE = "age_pat";

        // le tableau de medicament
        private static final String TABLE_NAME_MED = "medicament";
        private static final String COLUMN_ID_MED = "id_med";
        private static final String COLUMN_NOM_MED = "nom_med";
        private static final String COLUMN_LABO_MED = "labo_med";
        private static final String COLUMN_TYPE_BILAN_MED = "type_bilan_med";

        // le tableau des Règles
        private static final String TABLE_NAME_REG = "regle";
        private static final String COLUMN_ID_REG = "id_reg";
        private static final String COLUMN_ID_MED_REG = "id_med_reg";
        private static final String COLUMN_CONC_INF = "conc_inf";
        private static final String COLUMN_CONC_SUP = "conc_sup";
        private static final String COLUMN_COEFF_REG = "coeff_reg";
        private static final String COLUMN_IS_COEFF_REG = "is_coeff_reg";
        private static final String COLUMN_TYPE_BILAN_REG = "type_bilan_reg";


        DataBase(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            // creation de tableau Patient
            String query = "CREATE TABLE " + TABLE_NAME +
                    " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOM + " TEXT NOT NULL, " +
                    COLUMN_PRENOM + " TEXT NOT NULL, " +
                    COLUMN_POID + " DOUBLE NOT NULL, " +
                    COLUMN_AGE + " INTEGER NOT NULL);";
            db.execSQL(query);

            // creation de tableau Medicament
            String query_med = " CREATE TABLE " + TABLE_NAME_MED +
                    " (" + COLUMN_ID_MED + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOM_MED + " TEXT NOT NULL, " +
                    COLUMN_LABO_MED + " TEXT NOT NULL, " +
                    COLUMN_TYPE_BILAN_MED + " TEXT NOT NULL);";
            db.execSQL(query_med);

            // creation de tableau regle
            String query_reg = " CREATE TABLE " + TABLE_NAME_REG +
                    " (" + COLUMN_ID_REG + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ID_MED_REG + " INTEGER NOT NULL, " +
                    COLUMN_CONC_INF + " DOUBLE, " +
                    COLUMN_CONC_SUP + " DOUBLE, " +
                    COLUMN_COEFF_REG + " DOUBLE NOT NULL, " +
                    COLUMN_IS_COEFF_REG + " INTEGER NOT NULL, " +
                    COLUMN_TYPE_BILAN_REG + " TEXT NOT NULL);";

            db.execSQL(query_reg);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // tableau patient
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            // tableau medicament
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MED);
            // tableau regle
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_REG);
            onCreate(db);
        }

        // les methodes de patient
        public void ajouterBDMeth(String nom, String prenom, Double poid, int age) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues(); // il contient tt les donnees

            cv.put(COLUMN_NOM, nom);
            cv.put(COLUMN_PRENOM, prenom);
            cv.put(COLUMN_POID, poid);
            cv.put(COLUMN_AGE, age);
            long result = db.insert(TABLE_NAME, null, cv);
            if (result == -1) {
                Toast.makeText(context, "le patient n'a été pas ajouté", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "le patient a été ajouté avec succes", Toast.LENGTH_SHORT).show();
            }
        }

        // la fonction qui lit les donnees depuis le tab patient
        Cursor readAllData() {
            String query = "SELECT  * FROM " + TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = null;
            if (db != null) {
                cursor = db.rawQuery(query, null);
            }
            return cursor;
        }

        //la fonction de modification
        void updateData(String row_id, String nom, String prenom, String poid, String age) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_NOM, nom);
            cv.put(COLUMN_PRENOM, prenom);
            cv.put(COLUMN_POID, poid);
            cv.put(COLUMN_AGE, age);

            long result = db.update(TABLE_NAME, cv, "id_pat=?", new String[]{row_id});
            if (result == -1) {
                Toast.makeText(context, "le patient n'a été pas modifié", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "le patient a été modifié avec succes", Toast.LENGTH_SHORT).show();
            }
        }

        void deleteOnRow(String row_id) {
            SQLiteDatabase db = this.getWritableDatabase();
            long result = db.delete(TABLE_NAME, "id_pat=?", new String[]{row_id});
            if (result == -1) {
                Toast.makeText(context, "le patient n'a été pas supprimé", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "le patient a été supprimé avec succes", Toast.LENGTH_SHORT).show();
            }
        }

        void deleteAllData() {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DELETE FROM " + TABLE_NAME);
        }

        // les methodes de medicament
        public void ajouterBDMethMed(String nom_med, String labo_med, String type_bilan_med) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues(); // il contient tt les donnees

            cv.put(COLUMN_NOM_MED, nom_med);
            cv.put(COLUMN_LABO_MED, labo_med);
            cv.put(COLUMN_TYPE_BILAN_MED, type_bilan_med);
            long result = db.insert(TABLE_NAME_MED, null, cv);
            if (result == -1) {
                Toast.makeText(context, "le médicament n'a été pas ajouté", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "le médicament a été ajouté avec succes", Toast.LENGTH_SHORT).show();
            }
        }

        Cursor readAllDataMed() {
            String query_med = "SELECT  * FROM " + TABLE_NAME_MED;
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = null;
            if (db != null) {
                cursor = db.rawQuery(query_med, null);
            }
            return cursor;
        }

        void updateDataMed(String row_id, String nom_med, String labo_med, String type_bilan_med) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_NOM_MED, nom_med);
            cv.put(COLUMN_LABO_MED, labo_med);
            cv.put(COLUMN_TYPE_BILAN_MED, type_bilan_med);

            long result = db.update(TABLE_NAME_MED, cv, "id_med=?", new String[]{row_id});
            if (result == -1) {
                Toast.makeText(context, "le médicament n'a été pas modifié", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "le médicament a été modifié avec succes", Toast.LENGTH_SHORT).show();
            }
        }

        void deleteOnRowMed(String row_id) {
            SQLiteDatabase db = this.getWritableDatabase();
            long result = db.delete(TABLE_NAME_MED, "id_med=?", new String[]{row_id});
            if (result == -1) {
                Toast.makeText(context, "le médicament n'a été pas supprimé", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "le médicament a été supprimé avec succes", Toast.LENGTH_SHORT).show();
            }
        }

        void deleteAllDataMed() {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DELETE FROM " + TABLE_NAME_MED);
            deleteAllDataReg();
        }
        int recupererIDMed(String nom_med) {
        int result = -1;
        String query = "SELECT "+ COLUMN_ID_MED + " FROM " + TABLE_NAME_MED + " WHERE "+ COLUMN_NOM_MED +" = '"+nom_med+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            result = cursor.getInt(0);
        }else{
            Toast.makeText(context, "le médicament n'existe pas.Ajouter le SVP", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        db.close();
        return result;
    }

        //les methodes de regle
        public void ajouterBDMethReg(int id_med_reg, double conc_inf, double conc_sup, double coeff_reg,
                                     int is_coeff_reg, String type_bilan_reg)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues(); // il contient tt les donnees

            cv.put(COLUMN_ID_MED_REG, id_med_reg);
            cv.put(COLUMN_CONC_INF, conc_inf);
            cv.put(COLUMN_CONC_SUP, conc_sup);
            cv.put(COLUMN_COEFF_REG, coeff_reg);
            cv.put(COLUMN_IS_COEFF_REG, is_coeff_reg);
            cv.put(COLUMN_TYPE_BILAN_REG, type_bilan_reg);

            long result = db.insert(TABLE_NAME_REG, null, cv);
            if (result == -1) {
                Toast.makeText(context, "la règle n'a été pas ajouté", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "la règle a été ajouté avec succes", Toast.LENGTH_SHORT).show();
            }


        }

        Cursor readAllDataReg() {
            String query_reg = "SELECT * FROM " + TABLE_NAME_REG;
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = null;
            if (db != null) {
                cursor = db.rawQuery(query_reg, null);
            }
            return cursor;
        }

    void updateDataReg(String row_id, String id_med_reg, String conc_min, String conc_max, String coeff,
                       String type_bilan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID_MED_REG, id_med_reg);
        cv.put(COLUMN_CONC_INF, conc_min);
        cv.put(COLUMN_CONC_SUP, conc_max);
        cv.put(COLUMN_COEFF_REG, coeff);
        cv.put(COLUMN_TYPE_BILAN_REG, type_bilan);

        long result = db.update(TABLE_NAME_REG, cv, "id_reg=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "la règle n'a été pas modifié", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "la règle a été modifié avec succes", Toast.LENGTH_SHORT).show();
        }
    }


    void deleteOnRowReg(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_REG, "id_reg=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "la règle n'a été pas supprimé", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "la règle a été supprimé avec succes", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllDataReg() {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DELETE FROM " + TABLE_NAME_REG);
        }

    double recupererCoeffMin(int id_med, double BR){
        double result = -1;
        double born_inf, born_sup;
        int si_coeff = -1;
        boolean test;
        List<Double> resultList;

        resultList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME_REG + " WHERE "+ COLUMN_ID_MED_REG +" = '"+id_med+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            do {
                 born_inf = cursor.getDouble(2);
                 born_sup = cursor.getDouble(3);

                //si_coeff = cursor.getInt(4);
                 test = (born_inf != -999 ? BR >= born_inf : true) && (born_sup != -999 ? BR <= born_sup : true);
                 if(test){
                     resultList.add(cursor.getDouble(4));
                 }
            }while(cursor.moveToNext());
        }
        //resultList.add(1000.0);
        result = Collections.min(resultList);
        cursor.close();
        db.close();
        return result;
    }

        public double recupererPoid(String nom)
        {
            double result = -1;
            String query = "SELECT "+ COLUMN_POID + " FROM " + TABLE_NAME + " WHERE "+ COLUMN_NOM +" = '"+nom+"'";
            SQLiteDatabase db = this.getReadableDatabase();
           Cursor cursor = db.rawQuery(query, null);
           if(cursor.moveToFirst())
           {
               result = cursor.getDouble(0);
           }
            cursor.close();
            db.close();
            return result;
        }
        public String recupererTypeBilanMed(String nom_med)
        {
            String result = "null";
            String query = "SELECT "+ COLUMN_TYPE_BILAN_MED + " FROM " + TABLE_NAME_MED + " WHERE "+ COLUMN_NOM_MED +" = '"+nom_med+"'";
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            if(cursor.moveToFirst())
            {
                result = cursor.getString(0);
            }else{
                Toast.makeText(context, "le médicament n'existe pas.Ajouter le SVP", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
            db.close();
            return result;
        }

    }


