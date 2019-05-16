package com.sinapse.unebnoticias.bancodados;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class MsgRec {
    private String remetente;
    private String mensagRec;
    private String dataHora;
    private String status;
    private long idMsgRec;

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getMensagRec() {
        return mensagRec;
    }

    public void setMensagRec(String mensagRec) {
        this.mensagRec = mensagRec;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getIdMsgRec() {
        return idMsgRec;
    }

    public void setIdMsgRec(long idMsgRec) {
        this.idMsgRec = idMsgRec;
    }
}
