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
public class BDDept {
    private SQLiteDatabase bd;

    public BDDept(Context context){
        BDPlunge auxBD = new BDPlunge(context);

        bd = auxBD.getWritableDatabase();
    }

    public void inserir(Departamento dept) {
        ContentValues valores = new ContentValues();
        valores.put("dept_nome", dept.getDeptNome());
        valores.put("dept_sigla", dept.getDeptSigla());

        bd.insert("departamentos", null, valores);
    }

    public void atualizar(Departamento dept) {
        ContentValues valores = new ContentValues();
        valores.put("dept_nome", dept.getDeptNome());
        valores.put("dept_sigla", dept.getDeptSigla());

        bd.update("departamentos", valores, "_iddept = ?", new String[]{"" + dept.getIdDept()});
    }

    public void deletar(Departamento dept){
        bd.delete("departamentos", "_iddept =" + dept.getIdDept(), null);
    }

    public List<Departamento> buscarTodos(){
        List<Departamento> list = new ArrayList<Departamento>();
        String[] colunas = new String[]{"_iddept", "dept_nome", "dept_sigla"};

        Cursor cursor = bd.query("departamentos", colunas, null, null, null, null, "dept_nome ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Departamento dept = new Departamento();
                dept.setIdDept(cursor.getLong(0));
                dept.setDeptNome(cursor.getString(1));
                dept.setDeptSigla(cursor.getString(2));
                list.add(dept);

            }while (cursor.moveToNext());
        }

        return (list);
    }

    public Departamento buscarPorId(String id){
        Departamento dept = new Departamento();
        String[] colunas = new String[]{"_iddept", "dept_nome", "dept_sigla"};
        String[] args = {""+id};

        //Cursor cursor = bd.rawQuery("SELECT * FROM campi WHERE _idcampus = " + id, null);
        Cursor cursor = bd.query("departamentos", colunas, "_iddept = ?", args, null, null, null );

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            dept.setIdDept(cursor.getInt(0));
            dept.setDeptNome(cursor.getString(1));
            dept.setDeptSigla(cursor.getString(2));
        }

        return dept;
    }
}
