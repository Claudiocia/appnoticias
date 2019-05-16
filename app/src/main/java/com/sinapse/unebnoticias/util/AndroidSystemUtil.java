package com.sinapse.unebnoticias.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.sinapse.unebnoticias.GcmCloudActivity;

import java.util.Random;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class AndroidSystemUtil {
    public static final String TAG = "Script";
    public static final String PROPERTY_REG_ID = "registration_id";
    public static final String PROPERTY_APP_VERSION = "appVersion";


    public static int getAppVersion(Context context){
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return(pi.versionCode);
        }
        catch (PackageManager.NameNotFoundException e) {
            Log.i(TAG, "Package name not found");
        }
        return(0);
    }


    public static int randInt() {
        Random rand = new Random();
        int randomNum = 1; //rand.nextInt((50000 - 0) + 1) + 0;
        return randomNum;
    }


    // SHARED PREFERENCES
    public static String getRegistrationId(Context context){
        SharedPreferences prefs = getGCMPreferences(context);
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");

        if(registrationId.trim().length() == 0){
            Log.i(TAG, "Registration not found.");
            return("");
        }

        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = AndroidSystemUtil.getAppVersion(context);

        if(registeredVersion != currentVersion){
            Log.i(TAG, "App Version has changed");
            return("");
        }

        return(registrationId);
    }

    public static void storeRegistrationId(Context context, String regId){
        SharedPreferences prefs = getGCMPreferences(context);
        int appVersion = AndroidSystemUtil.getAppVersion(context);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.commit();
    }

    public static SharedPreferences getGCMPreferences(Context context){
        return(context.getSharedPreferences(GcmCloudActivity.class.getSimpleName(), Context.MODE_PRIVATE));
    }
}