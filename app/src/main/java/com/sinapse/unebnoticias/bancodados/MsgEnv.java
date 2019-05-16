package com.sinapse.unebnoticias.bancodados;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class MsgEnv {
    private String destino;
    private String mensagEnv;
    private String dataHora;
    private String status;
    private long idMsgEnv;

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getMensagEnv() {
        return mensagEnv;
    }

    public void setMensagEnv(String mensagEnv) {
        this.mensagEnv = mensagEnv;
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

    public long getIdMsgEnv() {
        return idMsgEnv;
    }

    public void setIdMsgEnv(long idMsgEnv) {
        this.idMsgEnv = idMsgEnv;
    }
}
