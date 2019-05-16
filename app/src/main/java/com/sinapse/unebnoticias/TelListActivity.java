package com.sinapse.unebnoticias;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sinapse.unebnoticias.bancodados.BDUnidade;
import com.sinapse.unebnoticias.bancodados.BDUsuario;
import com.sinapse.unebnoticias.bancodados.Telefone;
import com.sinapse.unebnoticias.bancodados.Unidade;
import com.sinapse.unebnoticias.bancodados.Usuario;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by ClaudioSouza on 16/09/2016.
 */
public class TelListActivity extends AppCompatActivity {
    private Toolbar mTollbar;
    private Toolbar mTollbarBotton;
    int id;
    Unidade unid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telefone_rel);

        id = getIntent().getExtras().getInt("pos");

        String posi = ""+id;

        BDUnidade bdUnid = new BDUnidade(this);
        Unidade unid = bdUnid.buscarPorId(posi);


        //Populando os TextViews com Nome e setor
        TextView tvUnid = (TextView) findViewById(R.id.unid_tel);
        TextView tvTitu = (TextView) findViewById(R.id.titular_tel);

        tvUnid.setText(unid.getUnidSigla() + " - " + unid.getUnidNome());
        tvTitu.setText(unid.getUnidTit());

        mTollbar = (Toolbar) findViewById(R.id.tel_listView);
        mTollbar.setTitle(R.string.telunico);
        mTollbar.setPadding(15, 0, 0, 0);
        mTollbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mTollbar);

        mTollbarBotton = (Toolbar) findViewById(R.id.inc_tb_botton);
        mTollbarBotton.inflateMenu(R.menu.menu_bottom);

        mTollbarBotton.findViewById(R.id.ic_noticia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noticias = new Intent(TelListActivity.this, SplashActivity.class);
                startActivity(noticias);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_linhadir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lindir = new Intent(TelListActivity.this, LinhadirActivity.class);
                startActivity(lindir);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_webtv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webtv = new Intent(TelListActivity.this, SplashWebtv.class);
                startActivity(webtv);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_portal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent portal = new Intent(TelListActivity.this, PortalActivity.class);
                startActivity(portal);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_mapas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapas = new Intent(TelListActivity.this, MapasActivity.class);
                startActivity(mapas);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_agenda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agenda = new Intent(TelListActivity.this, AgendaActivity.class);
                startActivity(agenda);
            }
        });

        //Fragment
        RelFragment frag = (RelFragment) getSupportFragmentManager().findFragmentByTag("relFrag");
        if(frag == null){
            frag = new RelFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rel_fragment_container_tel, frag, "relFrag");
            ft.commit();
        }
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
            Intent sobreApp = new Intent(TelListActivity.this, SobreAppActivity.class);
            startActivity(sobreApp);
        }
        else if(id == R.id.sobre){
            Intent sobre = new Intent(TelListActivity.this, SobreActivity.class);
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


    public List<Telefone> getSetTelList(){

        List<Telefone> listAux = new ArrayList<>();

        String posi = ""+id;

        BDUnidade bdUnid = new BDUnidade(this);
        Unidade unid = bdUnid.buscarPorId(posi);

        BDUsuario bdUser = new BDUsuario(this);
        int id = 1;
        Usuario user = bdUser.buscarPorArg(id);


        if(user.getUserMat().equals("19452015")) {

            String[] telAux = {unid.getUnidTel().toString(), unid.getUnidTel2().toString(), unid.getUnidTel3().toString(),
                    unid.getUnidCelInst().toString(), unid.getUnidCelPart().toString(), unid.getUnidEmailInst().toString(),
                    unid.getUnidEmailPart().toString()};

            int qtd = telAux.length;

            for (int i = 0; i < qtd; i++) {
                Telefone tele = new Telefone(telAux[i % telAux.length]);
                if(tele.getTel().isEmpty()){

                }else {
                    listAux.add(tele);
                }
            }


        }else{
            String[] telAux = {unid.getUnidTel(), unid.getUnidTel2(), unid.getUnidTel3(),unid.getUnidEmailInst()};

            int qtd = telAux.length;

            for (int i = 0; i < qtd; i++) {
                Telefone tele = new Telefone(telAux[i % telAux.length]);
                if(tele.getTel().isEmpty()){

                }else {
                    listAux.add(tele);
                }
            }
        }



        return (listAux);
    }

}
