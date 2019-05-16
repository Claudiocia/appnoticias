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
public class BDMapaInfo {
    private SQLiteDatabase bd;

    public BDMapaInfo(Context context){

        BDPlunge auxBD = new BDPlunge(context);
        bd = auxBD.getWritableDatabase();
    }

    public void inserir(MapaInfo mapaInfo){
        ContentValues valores = new ContentValues();
        valores.put("mapasinfo_munic", mapaInfo.getMapaMunic());
        valores.put("mapasinfo_terr_ident", mapaInfo.getMapaTerrit());
        valores.put("mapasinfo_regiao", mapaInfo.getMapaReg());
        valores.put("mapasinfo_campus", mapaInfo.getMapaCampi());
        valores.put("mapasinfo_dept1", mapaInfo.getMapaDept1());
        valores.put("mapasinfo_dept2", mapaInfo.getMapaDept2());
        valores.put("mapasinfo_dept3", mapaInfo.getMapaDept3());
        valores.put("mapasinfo_dept4", mapaInfo.getMapaDept4());
        valores.put("mapasinfo_pos_ead", mapaInfo.getMapaPosEad());
        valores.put("mapasinfo_pos_pres", mapaInfo.getMapaPosPres());
        valores.put("mapasinfo_grad_ead", mapaInfo.getMapaGradEad());
        valores.put("mapasinfo_liceei_polo", mapaInfo.getMapaLiceeiPolo());
        valores.put("mapasinfo_parfor", mapaInfo.getMapaParfor());
        valores.put("mapasinfo_pibid", mapaInfo.getMapaPibid());
        valores.put("mapasinfo_pnaic", mapaInfo.getMapaPnaic());
        valores.put("mapasinfo_mestre", mapaInfo.getMapaMestre());
        valores.put("mapasinfo_doutor", mapaInfo.getMapaDoutor());
        valores.put("mapasinfo_suprof_met", mapaInfo.getMapaSuprofMet());
        valores.put("mapasinfo_suprof_gest", mapaInfo.getMapaSuprofGest());
        valores.put("mapasinfo_topa", mapaInfo.getMapaTopa());
        valores.put("mapasinfo_uati", mapaInfo.getMapaUati());
        valores.put("mapasinfo_upt", mapaInfo.getMapaUpt());

        bd.insert("mapasinfo", null, valores);
    }

    public void atualizar(MapaInfo mapaInfo){
        ContentValues valores = new ContentValues();
        valores.put("mapasinfo_munic", mapaInfo.getMapaMunic());
        valores.put("mapasinfo_terr_ident", mapaInfo.getMapaTerrit());
        valores.put("mapasinfo_regiao", mapaInfo.getMapaReg());
        valores.put("mapasinfo_campus", mapaInfo.getMapaCampi());
        valores.put("mapasinfo_dept1", mapaInfo.getMapaDept1());
        valores.put("mapasinfo_dept2", mapaInfo.getMapaDept2());
        valores.put("mapasinfo_dept3", mapaInfo.getMapaDept3());
        valores.put("mapasinfo_dept4", mapaInfo.getMapaDept4());
        valores.put("mapasinfo_pos_ead", mapaInfo.getMapaPosEad());
        valores.put("mapasinfo_pos_pres", mapaInfo.getMapaPosPres());
        valores.put("mapasinfo_grad_ead", mapaInfo.getMapaGradEad());
        valores.put("mapasinfo_liceei_polo", mapaInfo.getMapaLiceeiPolo());
        valores.put("mapasinfo_parfor", mapaInfo.getMapaParfor());
        valores.put("mapasinfo_pibid", mapaInfo.getMapaPibid());
        valores.put("mapasinfo_pnaic", mapaInfo.getMapaPnaic());
        valores.put("mapasinfo_mestre", mapaInfo.getMapaMestre());
        valores.put("mapasinfo_doutor", mapaInfo.getMapaDoutor());
        valores.put("mapasinfo_suprof_met", mapaInfo.getMapaSuprofMet());
        valores.put("mapasinfo_suprof_gest", mapaInfo.getMapaSuprofGest());
        valores.put("mapasinfo_topa", mapaInfo.getMapaTopa());
        valores.put("mapasinfo_uati", mapaInfo.getMapaUati());
        valores.put("mapasinfo_upt", mapaInfo.getMapaUpt());

        bd.update("mapasinfo", valores, "_idmapasinfo = ?", new String[]{"" + mapaInfo.getIdMapa()});

    }

    public void deletar(MapaInfo mapaInfo){

        bd.delete("mapasinfo", "_idmapasinfo = ?", new String[]{""+ mapaInfo.getIdMapa()});
    }

    public List<MapaInfo> buscarDetalhe(String arg1, String arg2){
        List<MapaInfo> list = new ArrayList<MapaInfo>();
        String[] colunas = new String[]{"_idmapasinfo", "mapasinfo_munic", "mapasinfo_terr_ident", "mapasinfo_regiao", "mapasinfo_campus",
                "mapasinfo_dept1", "mapasinfo_dept2", "mapasinfo_dept3", "mapasinfo_dept4", "mapasinfo_pos_ead",
                "mapasinfo_pos_pres", "mapasinfo_grad_ead", "mapasinfo_liceei_polo", "mapasinfo_parfor", "mapasinfo_pibid",
                "mapasinfo_pnaic", "mapasinfo_mestre", "mapasinfo_doutor", "mapasinfo_suprof_met", "mapasinfo_suprof_gest",
                "mapasinfo_topa", "mapasinfo_uati", "mapasinfo_upt"};
        String arg = arg1;
        String[] args;// = new String[]{""};

        if(arg2 == "I") {
            args = new String[]{arg2};

        }
        else if(arg2 == "Paulo_Afon"){
            args = new String[]{"Paulo_Afon", "tex_freitas"};
        }else{
            args = new String[]{arg2};
        }

        Cursor cursor = bd.query("mapasinfo", colunas, arg, args, null, null, "_idmapasinfo ASC");

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                MapaInfo mapaInfo = new MapaInfo();
                mapaInfo.setIdMapa(cursor.getInt(0));
                mapaInfo.setMapaMunic(cursor.getString(1));
                mapaInfo.setMapaTerrit(cursor.getInt(2));
                mapaInfo.setMapaReg(cursor.getString(3));
                mapaInfo.setMapaCampi(cursor.getString(4));
                mapaInfo.setMapaDept1(cursor.getString(5));
                mapaInfo.setMapaDept2(cursor.getString(6));
                mapaInfo.setMapaDept3(cursor.getString(7));
                mapaInfo.setMapaDept4(cursor.getString(8));
                mapaInfo.setMapaPosEad(cursor.getString(9));
                mapaInfo.setMapaPosPres(cursor.getString(10));
                mapaInfo.setMapaGradEad(cursor.getString(11));
                mapaInfo.setMapaLiceeiPolo(cursor.getString(12));
                mapaInfo.setMapaParfor(cursor.getString(13));
                mapaInfo.setMapaPibid(cursor.getString(14));
                mapaInfo.setMapaPnaic(cursor.getString(15));
                mapaInfo.setMapaMestre(cursor.getString(16));
                mapaInfo.setMapaDoutor(cursor.getString(17));
                mapaInfo.setMapaSuprofMet(cursor.getString(18));
                mapaInfo.setMapaSuprofGest(cursor.getString(19));
                mapaInfo.setMapaTopa(cursor.getString(20));
                mapaInfo.setMapaUati(cursor.getString(21));
                mapaInfo.setMapaUpt(cursor.getString(22));
                list.add(mapaInfo);
            }while (cursor.moveToNext());
        }
        return (list);
    }


}
