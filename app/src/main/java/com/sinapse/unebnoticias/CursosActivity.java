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
import android.widget.ListView;
import android.widget.Toast;

import com.sinapse.unebnoticias.bancodados.BDCampus;
import com.sinapse.unebnoticias.bancodados.BDCurso;
import com.sinapse.unebnoticias.bancodados.BDDept;
import com.sinapse.unebnoticias.bancodados.Campus;
import com.sinapse.unebnoticias.bancodados.Curso;
import com.sinapse.unebnoticias.bancodados.Departamento;
import com.sinapse.unebnoticias.provider.SearchableProvider;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by ClaudioSouza on 17/09/2016.
 */
public class CursosActivity extends AppCompatActivity {
    private Toolbar mTollbar;
    private Toolbar mTollbarBotton;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cursos_list);

        mTollbar = (Toolbar) findViewById(R.id.cursos_listView);
        mTollbar.setTitle(R.string.cursos);
        mTollbar.setPadding(15, 0, 0, 0);
        mTollbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mTollbar);

        mTollbarBotton = (Toolbar) findViewById(R.id.inc_tb_botton);
        mTollbarBotton.inflateMenu(R.menu.menu_bottom);

        mTollbarBotton.findViewById(R.id.ic_noticia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noticias = new Intent(CursosActivity.this, SplashActivity.class);
                startActivity(noticias);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_linhadir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lindir = new Intent(CursosActivity.this, LinhadirActivity.class);
                startActivity(lindir);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_webtv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webtv = new Intent(CursosActivity.this, SplashWebtv.class);
                startActivity(webtv);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_portal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent portal = new Intent(CursosActivity.this, PortalActivity.class);
                startActivity(portal);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_mapas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapas = new Intent(CursosActivity.this, MapasActivity.class);
                startActivity(mapas);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_agenda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agenda = new Intent(CursosActivity.this, AgendaActivity.class);
                startActivity(agenda);
            }
        });

        //Fragment
        CursosFragment frag = (CursosFragment) getSupportFragmentManager().findFragmentByTag("cursoFrag");
        if(frag == null){
            frag = new CursosFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container_cursos, frag, "cursoFrag");
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
        searchView.setQueryHint(getResources().getString(R.string.buscar));
        searchView.setOnQueryTextListener(onSearch());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings){
            Intent sobreApp = new Intent(CursosActivity.this, SobreAppActivity.class);
            startActivity(sobreApp);
        }
        else if(id == R.id.sobre){
            Intent sobre = new Intent(CursosActivity.this, SobreActivity.class);
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


                Intent intent = new Intent(CursosActivity.this, ResultCursoActivity.class);
                intent.putExtra("query", query);

                SearchRecentSuggestions searchRecentSuggestions = new SearchRecentSuggestions(CursosActivity.this,
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

    public List<Curso> getSetCursosList(int qtd, int pos){
        int x = 0;

        String posi = ""+pos;
        List<Curso> listAux = new ArrayList<>();

        BDCurso bdCurso = new BDCurso(this);
        BDCampus bdCampus = new BDCampus(this);
        Campus camp;
        BDDept bdDept = new BDDept(this);
        Departamento dep;
        List<Curso> list = bdCurso.buscarTodos();

        try {

            int y = list.size();
            if (y > pos ) {
                for (int i = pos; x <= qtd || i > y; i++) {
                    Curso curso = new Curso();
                    curso.setCursoNome(list.get(i).getCursoNome());
                    curso.setCursoTipo(list.get(i).getCursoTipo());
                    curso.setCursoNat(list.get(i).getCursoNat());
                    curso.setCursoTurno(list.get(i).getCursoTurno());
                    curso.setCursoSem(list.get(i).getCursoSem());

                    String id;
                    id = "" + list.get(i).getCursoIdCampus();
                    camp = bdCampus.buscarPorId(id);
                    curso.setNomeCampus(camp.getCampusNome());

                    String id2;
                    id2 = "" + list.get(i).getCursoIdDept();
                    dep = bdDept.buscarPorId(id2);
                    curso.setNomeDept(dep.getDeptNome() + " - " + dep.getDeptSigla()+" "+camp.getCampusNum());

                    listAux.add(curso);
                    x++;
                }
            }else {
                listAux.clear();
            }
        }catch (Exception e){

            Toast.makeText(CursosActivity.this, "Fim da lista ", Toast.LENGTH_LONG).show();
        }
        return (listAux);
    }
}
