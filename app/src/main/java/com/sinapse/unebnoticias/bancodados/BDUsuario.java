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
public class BDUsuario {
    private SQLiteDatabase bd;

    public BDUsuario(Context context){
        //BDCore auxBD = new BDCore(context);
        BDPlunge auxBD = new BDPlunge(context);

        bd = auxBD.getWritableDatabase();
    }

    public void inserir(Usuario usuario) {
        ContentValues valores = new ContentValues();
        valores.put("user_nome", usuario.getUserNome());
        valores.put("user_email", usuario.getUserEmail());
        valores.put("user_mat", usuario.getUserMat());
        valores.put("user_dept", usuario.getUserDept());
        valores.put("user_tel", usuario.getUserTel());
        valores.put("regid", usuario.getRegId());

        bd.insert("usuarios", null, valores);
    }

    public void atualizar(Usuario usuario) {
        ContentValues valores = new ContentValues();
        //valores.put("user_nome", usuario.getUserNome());
        //valores.put("user_email", usuario.getUserEmail());
        //valores.put("user_mat", usuario.getUserMat());
        //valores.put("user_dept", usuario.getUserDept());
        valores.put("regid", usuario.getRegId());

        bd.update("usuarios", valores, "_iduser = ?", new String[]{"" + usuario.getIdUser()});
    }

    public void deletar(Usuario usuario){
        bd.delete("usuarios", "_iduser =" + usuario.getIdUser(), null);
    }

    public boolean buscaRegId(String arg){
        String[] colunas = new String[]{"_iduser", "user_nome", "user_email", "user_mat", "user_dept", "user_tel", "regid"};
        String[] args = new String[]{arg};


        Cursor c =  bd.query("usuarios", colunas, "regid = ?", args, null, null, null );
        if(c.getCount() > 0) {
            c.moveToFirst();
            if (c.getString(6) == null) {
                return false;
            }
            else {
                return true;
            }
        }
        else{
            return true;
        }
    }

    public boolean buscaPorId(String arg){
        String[] colunas = new String[]{"_iduser", "user_nome", "user_email", "user_mat", "user_dept", "user_tel", "regid"};
        String[] args = new String[]{arg};


        Cursor c =  bd.query("usuarios", colunas, "_iduser = ?", args, null, null, null );
        if(c.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public Usuario buscarPorArg(int id){
        Usuario user = new Usuario();
        String[] colunas = new String[]{"_iduser", "user_nome", "user_email", "user_mat", "user_dept", "user_tel", "regid"};
        String[] args = new String[]{""+id};
        Cursor cursor = bd.query("usuarios", colunas, "_iduser = ?", args, null, null, null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            user.setIdUser(cursor.getLong(0));
            user.setUserNome(cursor.getString(1));
            user.setUserEmail(cursor.getString(2));
            user.setUserMat(cursor.getString(3));
            user.setUserDept(cursor.getString(4));
            user.setUserTel(cursor.getString(5));
            user.setRegId(cursor.getString(6));
        }
        return user;
    }

    public List<Usuario> buscarTodos(){
        List<Usuario> list = new ArrayList<Usuario>();
        String[] colunas = new String[]{"_iduser", "user_nome", "user_email", "user_mat", "user_dept", "user_tel", "regid"};

        Cursor cursor = bd.query("usuarios", colunas, null, null, null, null, "user_nome ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Usuario user = new Usuario();
                user.setIdUser(cursor.getLong(0));
                user.setUserNome(cursor.getString(1));
                user.setUserEmail(cursor.getString(2));
                user.setUserMat(cursor.getString(3));
                user.setUserDept(cursor.getString(4));
                user.setUserTel(cursor.getString(5));
                user.setRegId(cursor.getString(6));
                list.add(user);

            }while (cursor.moveToNext());
        }

        return (list);
    }
}
