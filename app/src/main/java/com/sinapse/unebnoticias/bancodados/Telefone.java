package com.sinapse.unebnoticias.bancodados;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class Telefone {
    private String tel;

    public Telefone(){};
    public Telefone(String n){
        tel = n;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


}
