package com.sinapse.unebnoticias;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by ClaudioSouza on 16/09/2016.
 */
public class SobreActivity extends AppCompatActivity {

    private Toolbar mTollbar;
    private Toolbar mTollbarBotton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sobre);

        mTollbar = (Toolbar) findViewById(R.id.tb_listView);
        mTollbar.setTitle(R.string.sobre);
        mTollbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mTollbar);


        mTollbarBotton = (Toolbar) findViewById(R.id.inc_tb_botton);
        mTollbarBotton.inflateMenu(R.menu.menu_bottom);


        mTollbarBotton.findViewById(R.id.ic_noticia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noticias = new Intent(SobreActivity.this, SplashActivity.class);
                startActivity(noticias);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_linhadir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lindir = new Intent(SobreActivity.this, LinhadirActivity.class);
                startActivity(lindir);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_webtv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webtv = new Intent(SobreActivity.this, SplashWebtv.class);
                startActivity(webtv);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_portal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent portal = new Intent(SobreActivity.this, PortalActivity.class);
                startActivity(portal);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_mapas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapas = new Intent(SobreActivity.this, MapasActivity.class);
                startActivity(mapas);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_agenda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agenda = new Intent(SobreActivity.this, AgendaActivity.class);
                startActivity(agenda);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings){
            Intent sobreApp = new Intent(SobreActivity.this, SobreAppActivity.class);
            startActivity(sobreApp);
        }
        else if(id == R.id.sobre){
            Intent sobre = new Intent(SobreActivity.this, SobreActivity.class);
            startActivity(sobre);
        }
        else if(id == R.id.fechar){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}
