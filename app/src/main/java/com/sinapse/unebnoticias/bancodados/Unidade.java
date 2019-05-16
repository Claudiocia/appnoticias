package com.sinapse.unebnoticias.bancodados;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class Unidade implements Parcelable {

    private String unidNome;
    private String unidSigla;
    private String unidTit;
    private String unidEmailInst;
    private String unidEmailPart;
    private String unidTel;
    private String unidTel2;
    private String unidTel3;
    private String unidTel4;
    private String unidCelInst;
    private String unidCelPart;
    private int   idUnid;
    private int   campiIdCampus;
    private String unidChave;

    public Unidade(){}

    public String getUnidNome() {
        return unidNome;
    }

    public void setUnidNome(String unidNome) {
        this.unidNome = unidNome;
    }

    public String getUnidSigla() {
        return unidSigla;
    }

    public void setUnidSigla(String unidSigla) {
        this.unidSigla = unidSigla;
    }

    public String getUnidTit() {
        return unidTit;
    }

    public void setUnidTit(String unidTit) {
        this.unidTit = unidTit;
    }

    public String getUnidEmailInst() {
        return unidEmailInst;
    }

    public void setUnidEmailInst(String unidEmailInst) {
        this.unidEmailInst = unidEmailInst;
    }

    public String getUnidEmailPart() {
        return unidEmailPart;
    }

    public void setUnidEmailPart(String unidEmailPart) {
        this.unidEmailPart = unidEmailPart;
    }

    public String getUnidTel() {
        return unidTel;
    }

    public void setUnidTel(String unidTel) {
        this.unidTel = unidTel;
    }

    public String getUnidTel2() {
        return unidTel2;
    }

    public void setUnidTel2(String unidTel2) {
        this.unidTel2 = unidTel2;
    }

    public String getUnidTel3() {
        return unidTel3;
    }

    public void setUnidTel3(String unidTel3) {
        this.unidTel3 = unidTel3;
    }

    public String getUnidTel4() {
        return unidTel4;
    }

    public void setUnidTel4(String unidTel4) {
        this.unidTel4 = unidTel4;
    }

    public String getUnidCelInst() {
        return unidCelInst;
    }

    public void setUnidCelInst(String unidCelInst) {
        this.unidCelInst = unidCelInst;
    }

    public String getUnidCelPart() {
        return unidCelPart;
    }

    public void setUnidCelPart(String unidCelPart) {
        this.unidCelPart = unidCelPart;
    }

    public int getIdUnid() {
        return idUnid;
    }

    public void setIdUnid(int idUnid) {
        this.idUnid = idUnid;
    }

    public int getCampiIdCampus() {
        return campiIdCampus;
    }

    public void setCampiIdCampus(int campiIdCampus) {
        this.campiIdCampus = campiIdCampus;
    }

    public String getUnidChave() {
        return unidChave;
    }

    public void setUnidChave(String unidChave) {
        this.unidChave = unidChave;
    }

    //PARCELABLE
    public Unidade (Parcel parcel){
        setUnidNome(parcel.readString());
        setUnidSigla(parcel.readString());
        setUnidTit(parcel.readString());
        setUnidEmailInst(parcel.readString());
        setUnidEmailPart(parcel.readString());
        setUnidTel(parcel.readString());
        setUnidTel2(parcel.readString());
        setUnidTel3(parcel.readString());
        setUnidTel4(parcel.readString());
        setUnidCelInst(parcel.readString());
        setUnidCelPart(parcel.readString());
        setIdUnid(parcel.readInt());
        setCampiIdCampus(parcel.readInt());
        setUnidChave(parcel.readString());
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( getUnidNome());
        dest.writeString( getUnidSigla());
        dest.writeString( getUnidTit());
        dest.writeString( getUnidEmailInst());
        dest.writeString( getUnidEmailPart());
        dest.writeString( getUnidTel());
        dest.writeString( getUnidTel2());
        dest.writeString( getUnidTel3());
        dest.writeString( getUnidTel4());
        dest.writeString( getUnidCelInst());
        dest.writeString( getUnidCelPart());
        dest.writeInt(getIdUnid());
        dest.writeInt(getCampiIdCampus());
        dest.writeString( getUnidChave());
    }
    public static final Parcelable.Creator<Unidade> CREATOR = new Parcelable.Creator<Unidade>(){

        @Override
        public Unidade createFromParcel(Parcel source) {
            return new Unidade(source);
        }

        @Override
        public Unidade[] newArray(int size) {
            return new Unidade[size];
        }
    };
}
