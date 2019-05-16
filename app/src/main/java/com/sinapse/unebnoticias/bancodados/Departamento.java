package com.sinapse.unebnoticias.bancodados;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class Departamento {
    private String deptNome;
    private String deptSigla;
    private long   idDept;

    public String getDeptNome() {
        return deptNome;
    }

    public void setDeptNome(String deptNome) {
        this.deptNome = deptNome;
    }

    public String getDeptSigla() {
        return deptSigla;
    }

    public void setDeptSigla(String deptSigla) {
        this.deptSigla = deptSigla;
    }

    public long getIdDept() {
        return idDept;
    }

    public void setIdDept(long idDept) {
        this.idDept = idDept;
    }
}
