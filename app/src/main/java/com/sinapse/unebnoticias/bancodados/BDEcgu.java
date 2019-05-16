package com.sinapse.unebnoticias.bancodados;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class BDEcgu {
    private SQLiteDatabase bd;

    public BDEcgu(Context context){
        BDPlunge auxBD = new BDPlunge(context);

        bd = auxBD.getWritableDatabase();
    }

    public List<NomeEcgu> buscarSigla(){
        List<NomeEcgu> list = new ArrayList<NomeEcgu>();
        String[] coluna = new String[]{"_idecgu", "ecgu_nome", "ecgu_sigla"};

        Cursor cursor = bd.query("ecgu", coluna, null, null, null, null, "_idecgu ASC LIMIT 74");

        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            do{
                NomeEcgu ecgu = new NomeEcgu();
                ecgu.setIdEcgu(cursor.getLong(0));
                ecgu.setNomeEcgu(cursor.getString(1));
                ecgu.setSiglaEcgu(cursor.getString(2));
                list.add(ecgu);
            }while(cursor.moveToNext());
        }
        return (list);
    }

    public NomeEcgu buscarPorId(NomeEcgu ecga){
        NomeEcgu ecgu = new NomeEcgu();
        String[] coluna = new String[]{"_idecgu", "ecgu_nome", "ecgu_sigla"};
        String arg = ""+ ecga.getIdEcgu();
        String[] args = new String[]{arg};

        Cursor cursor = bd.query("ecgu", coluna, "_idecgu = ?", args, null, null,null );
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            ecgu.setIdEcgu(cursor.getLong(0));
            ecgu.setNomeEcgu(cursor.getString(1));
            ecgu.setSiglaEcgu(cursor.getString(2));
        }

        return (ecgu);
    }
}
