package com.sinapse.unebnoticias.mapas;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class Mapa {
    private String nomeMapa;
    private String descrMapa;
    private int idMapa;

    public Mapa(){}
    public Mapa(String n, String d, int id){
        nomeMapa = n;
        descrMapa = d;
        idMapa = id;
    }

    public String getNomeMapa() {
        return nomeMapa;
    }

    public void setNomeMapa(String nomeMapa) {
        this.nomeMapa = nomeMapa;
    }

    public String getDescrMapa() {
        return descrMapa;
    }

    public void setDescrMapa(String descrMapa) {
        this.descrMapa = descrMapa;
    }

    public int getIdMapa() {
        return idMapa;
    }

    public void setIdMapa(int idMapa) {
        this.idMapa = idMapa;
    }
}
