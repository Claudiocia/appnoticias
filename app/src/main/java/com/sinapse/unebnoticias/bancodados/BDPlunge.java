package com.sinapse.unebnoticias.bancodados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class BDPlunge extends SQLiteOpenHelper {
    private static String DB_PATH = "data/data/com.sinapse.unebnoticias/databases/";
    private static final String NOME_BD = "unebnoti.db";
    private static final int VERSAO_BD = 2;
    private SQLiteDatabase dbQuery;
    private final Context dbContexto;

    public BDPlunge(Context context){
        super(context, NOME_BD, null, VERSAO_BD);
        this.dbContexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void criarDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        if(!dbExist){
            this.getReadableDatabase();
            try{
                this.copiarDataBase();
            }catch (IOException e){
                throw new Error("Erro ao copiar Banco de dados");
            }
        }
        else{
            int x = buscarVersaoBd();
            if (VERSAO_BD > x ){
                String myPath = DB_PATH + NOME_BD;
                Usuario user = new Usuario();
                SQLiteDatabase db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

                String[] colunas = new String[]{"_iduser", "user_nome", "user_email", "user_mat", "user_dept", "user_tel", "regid"};
                String arg = ""+1;
                String[] args = new String[]{arg};

                Cursor cursor =  db.query("usuarios", colunas, "_iduser = ?", args, null, null, null);
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
                db.close();
                this.getReadableDatabase();
                try {
                    this.deletarDataBase();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                this.getReadableDatabase();
                try{
                    this.copiarDataBase();
                }catch (IOException e){
                    throw new Error("Erro ao copiar Banco de dados");
                }
                SQLiteDatabase db2 = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
                ContentValues valores = new ContentValues();
                valores.put("user_nome", user.getUserNome());
                valores.put("user_email", user.getUserEmail());
                valores.put("user_mat", user.getUserMat());
                valores.put("user_dept", user.getUserDept());
                valores.put("user_tel", user.getUserTel());
                valores.put("regid", user.getRegId());

                db2.insert("usuarios", null, valores);
                db2.close();
            }
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + NOME_BD;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch (SQLiteException e){

        }
        if(checkDB != null){
            checkDB.close();
        }

        return  checkDB != null ? true : false;
    }

    private void copiarDataBase() throws IOException{
        InputStream myInput = dbContexto.getAssets().open(NOME_BD);
        String outFileName = DB_PATH + NOME_BD;
        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void abrirDataBase() throws SQLException {
        String myPath = DB_PATH + NOME_BD;
        dbQuery = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    public void deletarDataBase()throws SQLException{
        String myPath = DB_PATH + NOME_BD;
        SQLiteDatabase checkDB = null;
        checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        checkDB.close();
        dbContexto.deleteDatabase(NOME_BD);
    }

    public int buscarVersaoBd(){
        String myPath = DB_PATH + NOME_BD;
        int x ;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        x = db.getVersion();
        return x;
    }



}
