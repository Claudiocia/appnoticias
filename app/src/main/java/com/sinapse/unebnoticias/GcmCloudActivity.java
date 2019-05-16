package com.sinapse.unebnoticias;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.sinapse.unebnoticias.bancodados.BDUsuario;
import com.sinapse.unebnoticias.bancodados.Usuario;
import com.sinapse.unebnoticias.util.AndroidSystemUtil;
import com.sinapse.unebnoticias.util.HttpConnectionUtil;

import java.io.IOException;

/**
 * Created by ClaudioSouza on 17/09/2016.
 */
public class GcmCloudActivity extends AppCompatActivity {
    public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static final String TAG = "Script";

    private String SENDER_ID = "152945944898";
    private String regId;
    private GoogleCloudMessaging gcm;
    private TextView tvRegistrationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gcmcloud_layout);

        tvRegistrationId = (TextView) findViewById(R.id.tvRegistrationId);
        regId = AndroidSystemUtil.getRegistrationId(GcmCloudActivity.this);

        if(checkPlayServices()){
            gcm = GoogleCloudMessaging.getInstance(GcmCloudActivity.this);

            if(regId.trim().length()==0){
                BDUsuario bdUser = new BDUsuario(this);
                String arg = "1";
                boolean regUser = bdUser.buscaRegId(arg);

                registerIdInBackground();
                Intent it = new Intent(GcmCloudActivity.this, SplashActivity.class);
                startActivity(it);
                finish();
            }
        }

    }

    @Override
    public void onResume(){
        super.onResume();
        checkPlayServices();
    }

    //UTIL
    private boolean checkPlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);

        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {

                googleAPI.getErrorDialog(this, result, PLAY_SERVICES_RESOLUTION_REQUEST).show();

            }
            Toast.makeText(GcmCloudActivity.this, "Aparelho sem suporte a PlayService", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void registerIdInBackground(){
        new AsyncTask(){
            @Override
            protected Object doInBackground(Object... params) {
                String msg = "";

                try {
                    if(gcm == null){
                        gcm = GoogleCloudMessaging.getInstance(GcmCloudActivity.this);
                    }
                    regId = gcm.register(SENDER_ID);
                    msg = "Register Id: "+regId;

                    //Chamada para criar o banco e popular os campos do usuario
                    BDUsuario uBd = new BDUsuario(GcmCloudActivity.this);
                    int arg = 1;

                    Usuario user = uBd.buscarPorArg(arg);

                    if(regId != user.getRegId()) {

                        user.setIdUser(arg);
                        user.setRegId(regId);
                        uBd.atualizar(user);
                    }

                    String feedback = HttpConnectionUtil.sendRegistrationIdToBackend(regId);
                    Log.i(TAG, feedback);
                }catch(IOException e){
                    Log.i(TAG, e.getMessage());
                }
                return msg;
            }
            @Override
            public void onPostExecute(Object msg){

                tvRegistrationId.setText((String)msg);
            }

        }.execute(null, null, null);
    }
}
