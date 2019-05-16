package com.sinapse.unebnoticias.bancodados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class BDCampus {
    private SQLiteDatabase bd;

    public BDCampus(Context context){
        BDPlunge auxBD = new BDPlunge(context);

        bd = auxBD.getWritableDatabase();
    }

    public BDCampus() {

    }

    public void inserir(Campus campus){
        ContentValues valores = new ContentValues();
        valores.put("campus_loc", campus.getCampusLoc());
        valores.put("campus_num", campus.getCampusNum());
        valores.put("campus_nome", campus.getCampusNome());
        valores.put("campus_sigla", campus.getCampusSigla());
        valores.put("campus_end", campus.getCampusEnd());
        valores.put("campus_bairro", campus.getCampusBairro());
        valores.put("campus_cep", campus.getCampusCep());
        valores.put("campus_cidade", campus.getCampusCidade());
        valores.put("campus_uf", campus.getCampusUf());

        bd.insert("campi", null, valores);
    }

    public void atualizar(Campus campus){
        ContentValues valores = new ContentValues();
        valores.put("campus_loc", campus.getCampusLoc());
        valores.put("campus_end", campus.getCampusEnd());
        valores.put("campus_bairro", campus.getCampusBairro());
        valores.put("campus_cep", campus.getCampusCep());
        valores.put("campus_cidade", campus.getCampusCidade());
        valores.put("campus_uf", campus.getCampusUf());

        bd.update("campi", valores, "_idcampus = ?", new String[]{"" + campus.getIdCampus()});
    }

    public void deletar(Campus campus){
        bd.delete("campi", "_idcampus =" + campus.getIdCampus(), null);
    }

    public List<Campus> buscarTodos(){
        List<Campus> list = new ArrayList<Campus>();
        String[] colunas = new String[]{"_idcampus", "campus_loc", "campus_num", "campus_nome", "campus_sigla", "campus_end", "campus_bairro",
                "campus_cep", "campus_cidade", "campus_uf" };

        Cursor cursor = bd.query("campi", colunas, null, null, null, null, "_idcampus ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Campus camp = new Campus();
                camp.setIdCampus(cursor.getInt(0));
                camp.setCampusLoc(cursor.getString(1));
                camp.setCampusNum(cursor.getString(2));
                camp.setCampusNome(cursor.getString(3));
                camp.setCampusSigla(cursor.getString(4));
                camp.setCampusEnd(cursor.getString(5));
                camp.setCampusBairro(cursor.getString(6));
                camp.setCampusCep(cursor.getString(7));
                camp.setCampusCidade(cursor.getString(8));
                camp.setCampusUf(cursor.getString(9));
                list.add(camp);

            }while (cursor.moveToNext());
        }

        return (list);
    }

    public Campus buscarPorId(String id){
        Campus camp = new Campus();
        String[] colunas = new String[]{"_idcampus", "campus_loc", "campus_num", "campus_nome", "campus_sigla", "campus_end", "campus_bairro",
                "campus_cep", "campus_cidade", "campus_uf" };
        String[] args = {""+id};

        //Cursor cursor = bd.rawQuery("SELECT * FROM campi WHERE _idcampus = " + id, null);
        Cursor cursor = bd.query("campi", colunas, "_idcampus = ?", args, null, null, null );

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            camp.setIdCampus(cursor.getInt(0));
            camp.setCampusLoc(cursor.getString(1));
            camp.setCampusNum(cursor.getString(2));
            camp.setCampusNome(cursor.getString(3));
            camp.setCampusSigla(cursor.getString(4));
            camp.setCampusEnd(cursor.getString(5));
            camp.setCampusBairro(cursor.getString(6));
            camp.setCampusCep(cursor.getString(7));
            camp.setCampusCidade(cursor.getString(8));
            camp.setCampusUf(cursor.getString(9));
        }

        return camp;
    }
}
