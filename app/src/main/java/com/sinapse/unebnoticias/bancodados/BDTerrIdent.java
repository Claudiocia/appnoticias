package com.sinapse.unebnoticias.bancodados;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class BDTerrIdent {
    SQLiteDatabase bd;

    public BDTerrIdent(Context context){
        BDPlunge auxBD = new BDPlunge(context);
        bd = auxBD.getWritableDatabase();
    }

    public TerrIdent buscaPorId(int arg){
        TerrIdent terr = new TerrIdent();

        String[] colunas = new String[]{"_idterrident", "terrident_nome"};
        String[] args = new String[]{"" + arg};

        Cursor cursor = bd.query("terrident", colunas, "_idterrident = ?", args, null, null, null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            terr.setIdTerrIdent(cursor.getInt(0));
            terr.setNomeTerrIdent(cursor.getString(1));
        }

        return (terr);
    }
}
