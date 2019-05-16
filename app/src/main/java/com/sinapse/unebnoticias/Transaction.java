package com.sinapse.unebnoticias;

/**
 * Created by ClaudioSouza on 16/09/2016.
 */
public interface Transaction {
    public void doBefore();
    public void doAfter(String answer);
    //public RequestData getRequestData();
}