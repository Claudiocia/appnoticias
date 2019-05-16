package com.sinapse.unebnoticias.bancodados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class BDPrograma {
    SQLiteDatabase bd;

    public BDPrograma(Context context){
        BDPlunge auxBD = new BDPlunge(context);
        bd = auxBD.getWritableDatabase();
    }

    public void inserir(Programa prog){
        ContentValues valores = new ContentValues();
        valores.put("program_tit", prog.getProgramTit());
        valores.put("program_sigla", prog.getProgramSigla());
        valores.put("program_resum", prog.getProgramResum());
        valores.put("program_desc", prog.getProgramDesc());
        valores.put("program_mptit", prog.getProgramMptit());
        valores.put("program_url_img", prog.getProgramUrlImg());
        valores.put("program_benef", prog.getProgramBenef());

        bd.insert("program", null, valores);
    }

    public void atualizar(Programa prog){
        ContentValues valores = new ContentValues();
        valores.put("program_tit", prog.getProgramTit());
        valores.put("program_sigla", prog.getProgramSigla());
        valores.put("program_resum", prog.getProgramResum());
        valores.put("program_desc", prog.getProgramDesc());
        valores.put("program_mptit", prog.getProgramMptit());
        valores.put("program_url_img", prog.getProgramUrlImg());
        valores.put("program_benef", prog.getProgramBenef());

        bd.update("program", valores, "_idprogram = ?", new String[]{"" + prog.getIdProgram()});
    }

    public void deletar(Programa prog){

        bd.delete("program", "_idprogram = ?", new String[]{"" + prog.getIdProgram()});
    }

    public Programa buscarPorId(int arg){
        Programa prog = new Programa();
        String[] colunas = new String[]{"_idprogram", "program_tit", "program_sigla", "program_resum",
                "program_desc", "program_mptit", "program_url_img", "program_benef"};
        String[] args = new String[]{"" + arg};
        Cursor cursor = bd.query("program", colunas, "_idprogram = ?", args, null, null, null);


        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            prog.setIdProgram(cursor.getInt(0));
            prog.setProgramTit(cursor.getString(1));
            prog.setProgramSigla(cursor.getString(2));
            prog.setProgramResum(cursor.getString(3));
            prog.setProgramDesc(cursor.getString(4));
            prog.setProgramMptit(cursor.getString(5));
            prog.setProgramUrlImg(cursor.getString(6));
            prog.setProgramBenef(cursor.getString(7));
        }

        return (prog);

    }
}
