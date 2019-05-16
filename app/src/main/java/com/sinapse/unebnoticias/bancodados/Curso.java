package com.sinapse.unebnoticias.bancodados;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class Curso implements Parcelable {
    private String cursoNome;
    private String cursoNat;
    private String cursoTipo;
    private String cursoTurno;
    private String cursoSem;
    private String cursoPolo;
    private String cursoCod;
    private String cursoVagas;
    private int   idCurso;
    private int   cursoIdCampus;
    private int   cursoIdDept;
    private String nomeCampus;
    private String nomeDept;


    public Curso(){}

    public String getCursoNome() {
        return cursoNome;
    }

    public void setCursoNome(String cursoNome) {
        this.cursoNome = cursoNome;
    }

    public String getCursoNat() {
        return cursoNat;
    }

    public void setCursoNat(String cursoNat) {
        this.cursoNat = cursoNat;
    }

    public String getCursoTipo() {
        return cursoTipo;
    }

    public void setCursoTipo(String cursoTipo) {
        this.cursoTipo = cursoTipo;
    }

    public String getCursoTurno() {
        return cursoTurno;
    }

    public void setCursoTurno(String cursoTurno) {
        this.cursoTurno = cursoTurno;
    }

    public String getCursoSem() {
        return cursoSem;
    }

    public void setCursoSem(String cursoSem) {
        this.cursoSem = cursoSem;
    }

    public String getCursoPolo() {
        return cursoPolo;
    }

    public void setCursoPolo(String cursoPolo) {
        this.cursoPolo = cursoPolo;
    }

    public String getCursoCod() {
        return cursoCod;
    }

    public void setCursoCod(String cursoCod) {
        this.cursoCod = cursoCod;
    }

    public String getCursoVagas() {
        return cursoVagas;
    }

    public void setCursoVagas(String cursoVagas) {
        this.cursoVagas = cursoVagas;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getCursoIdCampus() {
        return cursoIdCampus;
    }

    public void setCursoIdCampus(int cursoIdCampus) {
        this.cursoIdCampus = cursoIdCampus;
    }

    public int getCursoIdDept() {
        return cursoIdDept;
    }

    public void setCursoIdDept(int cursoIdDept) {
        this.cursoIdDept = cursoIdDept;
    }

    public String getNomeCampus() { return nomeCampus; }

    public void setNomeCampus(String nomeCampus) { this.nomeCampus = nomeCampus;  }

    public String getNomeDept() { return nomeDept; }

    public void setNomeDept(String nomeDept) { this.nomeDept = nomeDept; }

    //PARCELABLE
    public Curso (Parcel parcel){
        setCursoNome(parcel.readString());
        setCursoNat(parcel.readString());
        setCursoTipo(parcel.readString());
        setCursoTurno(parcel.readString());
        setCursoSem(parcel.readString());
        setCursoPolo(parcel.readString());
        setCursoCod(parcel.readString());
        setCursoVagas(parcel.readString());
        setNomeCampus(parcel.readString());
        setNomeDept(parcel.readString());
        setIdCurso(parcel.readInt());
        setCursoIdCampus(parcel.readInt());
        setCursoIdDept(parcel.readInt());
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( getCursoNome());
        dest.writeString( getCursoNat());
        dest.writeString( getCursoTipo());
        dest.writeString( getCursoTurno());
        dest.writeString( getCursoSem());
        dest.writeString( getCursoPolo());
        dest.writeString( getCursoCod());
        dest.writeString( getCursoVagas());
        dest.writeString( getNomeCampus());
        dest.writeString( getNomeDept());
        dest.writeInt(getIdCurso());
        dest.writeInt(getCursoIdCampus());
        dest.writeInt(getCursoIdDept());
    }
    public static final Parcelable.Creator<Curso> CREATOR = new Parcelable.Creator<Curso>(){

        @Override
        public Curso createFromParcel(Parcel source) {
            return new Curso(source);
        }

        @Override
        public Curso[] newArray(int size) {
            return new Curso[size];
        }
    };

}
