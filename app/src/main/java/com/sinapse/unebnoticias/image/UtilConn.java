package com.sinapse.unebnoticias.image;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class UtilConn {
    public static boolean verififyConneccao(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnected()){
            return true;
        }else {
            return false;
        }
    }
}
