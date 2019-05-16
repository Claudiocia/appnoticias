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
public class BDUnidade {
    private SQLiteDatabase bd;

    public BDUnidade(Context context){
        //BDCore auxBD = new BDCore(context);
        BDPlunge auxBD = new BDPlunge(context);

        bd = auxBD.getWritableDatabase();
    }

    public void inserir(Unidade unid) {
        ContentValues valores = new ContentValues();
        valores.put("campi_idcampus", unid.getCampiIdCampus());
        valores.put("unid_nome", unid.getUnidNome());
        valores.put("unid_sig", unid.getUnidSigla());
        valores.put("unid_tit", unid.getUnidTit());
        valores.put("unid_tel", unid.getUnidTel());
        valores.put("unid_tel2", unid.getUnidTel2());
        valores.put("unid_tel3", unid.getUnidTel3());
        valores.put("unid_tel4", unid.getUnidTel4());
        valores.put("unid_celinst", unid.getUnidCelInst());
        valores.put("unid_celpart", unid.getUnidCelPart());
        valores.put("unid_emailinst", unid.getUnidEmailInst());
        valores.put("unid_emailpart", unid.getUnidEmailPart());
        valores.put("unid_chave", unid.getUnidChave());

        bd.insert("unidades", null, valores);
    }

    public void atualizar(Unidade unid) {
        ContentValues valores = new ContentValues();
        valores.put("campi_idcampus", unid.getCampiIdCampus());
        valores.put("unid_nome", unid.getUnidNome());
        valores.put("unid_sig", unid.getUnidSigla());
        valores.put("unid_tit", unid.getUnidTit());
        valores.put("unid_tel", unid.getUnidTel());
        valores.put("unid_tel2", unid.getUnidTel2());
        valores.put("unid_tel3", unid.getUnidTel3());
        valores.put("unid_tel4", unid.getUnidTel4());
        valores.put("unid_celinst", unid.getUnidCelInst());
        valores.put("unid_celpart", unid.getUnidCelPart());
        valores.put("unid_emailinst", unid.getUnidEmailInst());
        valores.put("unid_emailpart", unid.getUnidEmailPart());
        valores.put("unid_chave", unid.getUnidChave());

        bd.update("unidades", valores, "_idunid = ?", new String[]{"" + unid.getIdUnid()});
    }

    public void deletar(Unidade unid){
        bd.delete("unidades", "_idunid =" + unid.getIdUnid(), null);
    }

    public List<Unidade> buscarTodos(){
        List<Unidade> list = new ArrayList<Unidade>();
        String[] colunas = new String[]{"_idunid", "campi_idcampus", "unid_nome", "unid_sig", "unid_tit", "unid_tel", "unid_tel2",
                "unid_tel3", "unid_tel4", "unid_celinst", "unid_celpart", "unid_emailinst", "unid_emailpart", "unid_chave" };

        Cursor cursor = bd.query("unidades", colunas, null, null, null, null, "_idunid ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Unidade unid = new Unidade();
                unid.setIdUnid(cursor.getInt(0));
                unid.setCampiIdCampus(cursor.getInt(1));
                unid.setUnidNome(cursor.getString(2));
                unid.setUnidSigla(cursor.getString(3));
                unid.setUnidTit(cursor.getString(4));
                unid.setUnidTel(cursor.getString(5));
                unid.setUnidTel2(cursor.getString(6));
                unid.setUnidTel3(cursor.getString(7));
                unid.setUnidTel4(cursor.getString(8));
                unid.setUnidCelInst(cursor.getString(9));
                unid.setUnidCelPart(cursor.getString(10));
                unid.setUnidEmailInst(cursor.getString(11));
                unid.setUnidEmailPart(cursor.getString(12));
                list.add(unid);

            }while (cursor.moveToNext());
        }

        return (list);
    }

    public List<Unidade> buscarUnidSigla(){
        List<Unidade> list = new ArrayList<Unidade>();
        String[] colunas = new String[]{"_idunid", "campi_idcampus", "unid_nome", "unid_sig", "unid_tit", "unid_tel", "unid_tel2",
                "unid_tel3", "unid_tel4", "unid_celinst", "unid_celpart", "unid_emailinst", "unid_emailpart" };
        String[] coluna = new String[]{ "unid_sig"};
        String sql = "SELECT distinct unid_sig FROM unidades ORDERBY _idunid";

        //Cursor cursor = bd.query( "unidades", colunas, null, null, null, null, "unid_nome ASC");

        Cursor cursor = bd.query(true, "unidades", coluna, null, null, null, null, null, null, null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();
            //StringBuilder unid = new StringBuilder();

            //unid.append("Selecione! \n");
            do{
                Unidade unid = new Unidade();
                unid.setUnidSigla(cursor.getString(0));
                //unid.append(cursor.getString(0) + "\n");
                list.add(unid);

            }while (cursor.moveToNext());
        }

        return (list);
    }

    public List<Unidade> searchTelef(String search){
        List<Unidade> slist = new ArrayList<Unidade>();

        String[] colunas = new String[]{"_idunid", "campi_idcampus", "unid_nome", "unid_sig", "unid_tit", "unid_tel", "unid_tel2",
                "unid_tel3", "unid_tel4", "unid_celinst", "unid_celpart", "unid_emailinst", "unid_emailpart", "unid_chave" };

        Cursor cursor = bd.rawQuery("SELECT * FROM unidades WHERE unid_chave LIKE '%"+ search + "%' OR unid_nome LIKE '%" + search + "%' OR unid_sig LIKE '%" + search + "%' OR unid_tit LIKE '%" + search + "%'", null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Unidade unid = new Unidade();
                unid.setIdUnid(cursor.getInt(0));
                unid.setCampiIdCampus(cursor.getInt(1));
                unid.setUnidNome(cursor.getString(2));
                unid.setUnidSigla(cursor.getString(3));
                unid.setUnidTit(cursor.getString(4));
                unid.setUnidTel(cursor.getString(5));
                unid.setUnidTel2(cursor.getString(6));
                unid.setUnidTel3(cursor.getString(7));
                unid.setUnidTel4(cursor.getString(8));
                unid.setUnidCelInst(cursor.getString(9));
                unid.setUnidCelPart(cursor.getString(10));
                unid.setUnidEmailInst(cursor.getString(11));
                unid.setUnidEmailPart(cursor.getString(12));
                slist.add(unid);

            }while (cursor.moveToNext());
        }

        return (slist);
    }

    public Unidade buscarPorId(String id){
        Unidade unid = new Unidade();
        String[] colunas = new String[]{"_idunid", "campi_idcampus", "unid_nome", "unid_sig", "unid_tit", "unid_tel", "unid_tel2",
                "unid_tel3", "unid_tel4", "unid_celinst", "unid_celpart", "unid_emailinst", "unid_emailpart" };
        String[] args = {""+id};

        //Cursor cursor = bd.rawQuery("SELECT * FROM unidades WHERE _idunid = " + id, null);
        Cursor cursor = bd.query("unidades", colunas, "_idunid = ?", args, null, null, null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            unid.setIdUnid(cursor.getInt(0));
            unid.setCampiIdCampus(cursor.getInt(1));
            unid.setUnidNome(cursor.getString(2));
            unid.setUnidSigla(cursor.getString(3));
            unid.setUnidTit(cursor.getString(4));
            unid.setUnidTel(cursor.getString(5));
            unid.setUnidTel2(cursor.getString(6));
            unid.setUnidTel3(cursor.getString(7));
            unid.setUnidTel4(cursor.getString(8));
            unid.setUnidCelInst(cursor.getString(9));
            unid.setUnidCelPart(cursor.getString(10));
            unid.setUnidEmailInst(cursor.getString(11));
            unid.setUnidEmailPart(cursor.getString(12));
        }

        return unid;
    }
}
