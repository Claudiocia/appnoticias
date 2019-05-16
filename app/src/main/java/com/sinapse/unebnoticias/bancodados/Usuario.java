package com.sinapse.unebnoticias.bancodados;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class Usuario {
    private String userNome;
    private String userEmail;
    private String userMat;
    private String userDept;
    private String regId;
    private String userTel;
    private long   idUser;

    public String getUserNome() {
        return userNome;
    }

    public void setUserNome(String userNome) {
        this.userNome = userNome;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserMat() {
        return userMat;
    }

    public void setUserMat(String userMat) {
        this.userMat = userMat;
    }

    public String getUserDept() {
        return userDept;
    }

    public void setUserDept(String userDept) {
        this.userDept = userDept;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }
}
