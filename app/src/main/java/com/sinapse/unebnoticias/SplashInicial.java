package com.sinapse.unebnoticias;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.sinapse.unebnoticias.bancodados.BDPlunge;
import com.sinapse.unebnoticias.bancodados.BDUsuario;

import java.io.IOException;

/**
 * Created by ClaudioSouza on 16/09/2016.
 */
public class SplashInicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        //opção de criar o BD a partir do arquivo já populado.

        BDPlunge bd = new BDPlunge(this);
        try {
            bd.criarDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BDUsuario bdUser = new BDUsuario(this);
        String arg = "1";
        boolean regUser = bdUser.buscaPorId(arg);
        if(regUser == false) {
            Intent it = new Intent(SplashInicial.this, CadastrarUser.class);
            startActivity(it);
            finish();
        }
        else {
            boolean regId = bdUser.buscaRegId(arg);
            if (regId == true) {
                Intent it = new Intent(SplashInicial.this, GcmCloudActivity.class);
                startActivity(it);
                finish();
            }
        }

    }
}
