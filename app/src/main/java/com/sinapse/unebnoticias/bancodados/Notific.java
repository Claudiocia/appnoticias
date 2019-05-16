package com.sinapse.unebnoticias.bancodados;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class Notific {
    private int idNotific;
    private String notificHora;
    private String notificText;
    private String notificTipo;

    public int getIdNotific() {
        return idNotific;
    }

    public void setIdNotific(int idNotific) {
        this.idNotific = idNotific;
    }

    public String getNotificHora() {
        return notificHora;
    }

    public void setNotificHora(String notificHora) {
        this.notificHora = notificHora;
    }

    public String getNotificText() {
        return notificText;
    }

    public void setNotificText(String notificText) {
        this.notificText = notificText;
    }

    public String getNotificTipo() {
        return notificTipo;
    }

    public void setNotificTipo(String notificTipo) {
        this.notificTipo = notificTipo;
    }
}
