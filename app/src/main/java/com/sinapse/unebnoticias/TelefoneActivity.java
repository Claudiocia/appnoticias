package com.sinapse.unebnoticias;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sinapse.unebnoticias.bancodados.BDUnidade;
import com.sinapse.unebnoticias.bancodados.Unidade;
import com.sinapse.unebnoticias.provider.SearchableProvider;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by ClaudioSouza on 16/09/2016.
 */
public class TelefoneActivity extends AppCompatActivity {
    private Toolbar mTollbar;
    private Toolbar mTollbarBotton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telefone_list);

        mTollbar = (Toolbar) findViewById(R.id.tel_listView);
        mTollbar.setTitle(R.string.telunid);
        mTollbar.setPadding(15, 0, 0, 0);
        mTollbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mTollbar);

        mTollbarBotton = (Toolbar) findViewById(R.id.inc_tb_botton);
        mTollbarBotton.inflateMenu(R.menu.menu_bottom);

        mTollbarBotton.findViewById(R.id.ic_noticia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noticias = new Intent(TelefoneActivity.this, SplashActivity.class);
                startActivity(noticias);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_linhadir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lindir = new Intent(TelefoneActivity.this, LinhadirActivity.class);
                startActivity(lindir);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_webtv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webtv = new Intent(TelefoneActivity.this, SplashWebtv.class);
                startActivity(webtv);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_portal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent portal = new Intent(TelefoneActivity.this, PortalActivity.class);
                startActivity(portal);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_mapas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapas = new Intent(TelefoneActivity.this, MapasActivity.class);
                startActivity(mapas);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_agenda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agenda = new Intent(TelefoneActivity.this, AgendaActivity.class);
                startActivity(agenda);
            }
        });

        //Fragment
        TelFragment frag = (TelFragment) getSupportFragmentManager().findFragmentByTag("telFrag");
        if(frag == null){
            frag = new TelFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container_tel, frag, "telFrag");
            ft.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_searchable_activity, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView;
        MenuItem item = menu.findItem(R.id.action_searchable_activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            searchView = (SearchView) item.getActionView();
        }
        else{
            searchView = (SearchView) MenuItemCompat.getActionView( item );
        }

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.setOnQueryTextListener(onSearch());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings){
            Intent sobreApp = new Intent(TelefoneActivity.this, SobreAppActivity.class);
            startActivity(sobreApp);
        }
        else if(id == R.id.sobre){
            Intent sobre = new Intent(TelefoneActivity.this, SobreActivity.class);
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

    private SearchView.OnQueryTextListener onSearch(){
        return new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                //  Toast.makeText(TelefoneActivity.this, "Usuario fez a busca " + query, Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(TelefoneActivity.this, ResultActivity.class);
                intent.putExtra("query", query);

                SearchRecentSuggestions searchRecentSuggestions = new SearchRecentSuggestions(TelefoneActivity.this,
                        SearchableProvider.AUTHORITY,
                        SearchableProvider.MODE);
                searchRecentSuggestions.saveRecentQuery(query, null);

                startActivity(intent);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Toast.makeText(TelefoneActivity.this, "Usuario alterou o texto " + newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        };
    }

    public List<Unidade> getSetUnidList(int qtd, int pos){
        int x = 0;

        String posi = ""+pos;
        List<Unidade> listAux = new ArrayList<>();

        BDUnidade bdUnid = new BDUnidade(this);
        List<Unidade> list = bdUnid.buscarTodos();

        try {

            int y = list.size();
            if (y > pos) {
                for (int i = pos; x <= qtd; i++) {
                    Unidade unid = new Unidade();
                    unid.setIdUnid(list.get(i).getIdUnid());
                    unid.setUnidNome(list.get(i).getUnidNome());
                    unid.setUnidSigla(list.get(i).getUnidSigla());
                    unid.setUnidTit(list.get(i).getUnidTit());
                    unid.setUnidTel(list.get(i).getUnidTel());
                    //unid.setUnidNome(nomeUnid[i % nomeUnid.length]);
                    //unid.setUnidTit(tituUnid[i % tituUnid.length]);
                    //unid.setUnidTel(teleUnid[i % teleUnid.length]);
                    listAux.add(unid);
                    x++;
                }
            } else {
                listAux.clear();
                Toast.makeText(TelefoneActivity.this, "Fim da lista ", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Toast.makeText(TelefoneActivity.this, "Fim da lista ", Toast.LENGTH_LONG).show();
        }
        return (listAux);
    }

    public List<Unidade> getListaCompleta(){

        List<Unidade> mlist = new ArrayList<>();

        BDUnidade bdUnid = new BDUnidade(this);
        List<Unidade> list = bdUnid.buscarTodos();
        int tamList = list.size();
        for(int i = 0; i <= tamList; i++) {
            Unidade unid = new Unidade();
            unid.setUnidNome(list.get(i).getUnidNome());
            unid.setUnidSigla(list.get(i).getUnidSigla());
            unid.setUnidTit(list.get(i).getUnidTit());
            unid.setUnidTel(list.get(i).getUnidTel());
            //unid.setUnidNome(nomeUnid[i % nomeUnid.length]);
            //unid.setUnidTit(tituUnid[i % tituUnid.length]);
            //unid.setUnidTel(teleUnid[i % teleUnid.length]);
            mlist.add(unid);
        }
        return (mlist);
    }
}
