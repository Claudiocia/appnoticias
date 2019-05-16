package com.sinapse.unebnoticias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sinapse.unebnoticias.bancodados.BDCampus;
import com.sinapse.unebnoticias.bancodados.BDCurso;
import com.sinapse.unebnoticias.bancodados.BDDept;
import com.sinapse.unebnoticias.bancodados.Campus;
import com.sinapse.unebnoticias.bancodados.Curso;
import com.sinapse.unebnoticias.bancodados.Departamento;

import java.util.List;

/**
 * Created by ClaudioSouza on 17/09/2016.
 */
public class ResultCursoActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar mTollbar;
    private Toolbar mTollbarBotton;
    String query;
    CustomListAdapter adapter;
    ListView lv;
    List<Curso> mList;
    Campus campus;
    Departamento depart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        query = getIntent().getExtras().getString("query");

        BDCurso bdCurso = new BDCurso(this);
        mList = bdCurso.searchCurso(query);

        if (mList.isEmpty()){

            Toast.makeText(ResultCursoActivity.this, "Nenhum resultado encontrado!!", Toast.LENGTH_LONG).show();
            Intent it = new Intent(ResultCursoActivity.this, CursosActivity.class);
            startActivity(it);
            finish();

        }else {
            setContentView(R.layout.result_list);

            lv = (ListView) findViewById(R.id.res_listView);
            lv.setVerticalFadingEdgeEnabled(true);

            //Definindo um adaptador para a listView
            adapter = new CustomListAdapter(this);
            lv.setAdapter(adapter);


            mTollbar = (Toolbar) findViewById(R.id.tel_result_list);
            mTollbar.setTitle(R.string.cursos);
            mTollbar.setPadding(15, 0, 0, 0);
            mTollbar.setLogo(R.drawable.ic_launcher);
            setSupportActionBar(mTollbar);

            mTollbarBotton = (Toolbar) findViewById(R.id.inc_tb_botton);
            mTollbarBotton.inflateMenu(R.menu.menu_bottom);

            mTollbarBotton.findViewById(R.id.ic_noticia).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent noticias = new Intent(ResultCursoActivity.this, SplashActivity.class);
                    startActivity(noticias);
                }
            });
            mTollbarBotton.findViewById(R.id.ic_linhadir).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent lindir = new Intent(ResultCursoActivity.this, LinhadirActivity.class);
                    startActivity(lindir);
                }
            });
            mTollbarBotton.findViewById(R.id.ic_webtv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent webtv = new Intent(ResultCursoActivity.this, SplashWebtv.class);
                    startActivity(webtv);
                }
            });
            mTollbarBotton.findViewById(R.id.ic_portal).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent portal = new Intent(ResultCursoActivity.this, PortalActivity.class);
                    startActivity(portal);
                }
            });
            mTollbarBotton.findViewById(R.id.ic_mapas).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mapas = new Intent(ResultCursoActivity.this, MapasActivity.class);
                    startActivity(mapas);
                }
            });
            mTollbarBotton.findViewById(R.id.ic_agenda).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent agenda = new Intent(ResultCursoActivity.this, AgendaActivity.class);
                    startActivity(agenda);
                }
            });

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    int pos = arg2;
                    //int id =
                    //Toast.makeText(ResultCursoActivity.this, "Voce clicou no item número " + pos, Toast.LENGTH_SHORT).show();

                }
            });
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
            Intent sobreApp = new Intent(ResultCursoActivity.this, SobreAppActivity.class);
            startActivity(sobreApp);
        }
        else if(id == R.id.sobre){
            Intent sobre = new Intent(ResultCursoActivity.this, SobreActivity.class);
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

    @Override
    public void onClick(View v) {

    }

    //class ListAdapter
    class CustomListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        public CustomListAdapter(ResultCursoActivity activity) {

            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {

            return mList.size();
        }

        @Override
        public Object getItem(int position) {

            return position;
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        public Campus adicionarCampus(String x){

            BDCampus bdCampus = new BDCampus(ResultCursoActivity.this);
            campus = bdCampus.buscarPorId(x);

            return campus;
        }

        public Departamento adicionarDept(String x){

            BDDept bdDept = new BDDept(ResultCursoActivity.this);
            depart = bdDept.buscarPorId(x);

            return depart;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // chamar o inflat dos itens e definir as views
            View listItem = convertView;
            int pos = position;

            if(listItem == null){
                listItem = layoutInflater.inflate(R.layout.item_list_resultcurso, null);
            }
            //Colocando na tela os elementos da busca
            TextView tvCurso = (TextView) listItem.findViewById(R.id.curso_nome);
            TextView tvDept = (TextView) listItem.findViewById(R.id.dept_nome);
            TextView tvCampus = (TextView) listItem.findViewById(R.id.campus_nome);
            TextView tvNat = (TextView) listItem.findViewById(R.id.nat);
            TextView tvPolo = (TextView) listItem.findViewById(R.id.polo);


            String end = "" + mList.get(pos).getCursoIdCampus();
            adicionarCampus(end);
            String campusf = campus.getCampusNome();
            String dep = "" + mList.get(pos).getCursoIdDept();
            adicionarDept(dep);
            String dept = depart.getDeptNome()+ " - "+depart.getDeptSigla()+" "+campus.getCampusNum();
            String nat = mList.get(pos).getCursoNat()+ " - Tipo: "+mList.get(pos).getCursoTipo()+ " - "+mList.get(pos).getCursoTurno();
            String polo = "Polo: "+ mList.get(pos).getCursoPolo()+" - "+mList.get(pos).getCursoSem()+" Semestre, com "+mList.get(pos).getCursoVagas()+" vagas.";


            //aplicando as views no form_contato
            tvCurso.setText(mList.get(pos).getCursoNome().toString());
            tvDept.setText(dept);
            tvCampus.setText(campusf);
            tvNat.setText(nat);
            tvPolo.setText(polo);

            return listItem;
        }

    }

}
