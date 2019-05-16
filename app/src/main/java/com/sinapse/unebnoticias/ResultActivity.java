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
import com.sinapse.unebnoticias.bancodados.BDUnidade;
import com.sinapse.unebnoticias.bancodados.Campus;
import com.sinapse.unebnoticias.bancodados.Unidade;

import java.util.List;

/**
 * Created by ClaudioSouza on 16/09/2016.
 */
public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar mTollbar;
    private Toolbar mTollbarBotton;
    String query;
    CustomListAdapter adapter;
    ListView lv;
    List<Unidade> mList;
    Campus campus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        query = getIntent().getExtras().getString("query");

        BDUnidade bdUnid = new BDUnidade(this);
        mList = bdUnid.searchTelef(query);

        if (mList.isEmpty()){

            Toast.makeText(ResultActivity.this, "Nenhum resultado encontrado!!", Toast.LENGTH_LONG).show();
            Intent it = new Intent(ResultActivity.this, TelefoneActivity.class);
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
            mTollbar.setTitle(R.string.telunid);
            mTollbar.setPadding(15, 0, 0, 0);
            mTollbar.setLogo(R.drawable.ic_launcher);
            setSupportActionBar(mTollbar);

            mTollbarBotton = (Toolbar) findViewById(R.id.inc_tb_botton);
            mTollbarBotton.inflateMenu(R.menu.menu_bottom);

            mTollbarBotton.findViewById(R.id.ic_noticia).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent noticias = new Intent(ResultActivity.this, SplashActivity.class);
                    startActivity(noticias);
                }
            });
            mTollbarBotton.findViewById(R.id.ic_linhadir).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent lindir = new Intent(ResultActivity.this, LinhadirActivity.class);
                    startActivity(lindir);
                }
            });
            mTollbarBotton.findViewById(R.id.ic_webtv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent webtv = new Intent(ResultActivity.this, SplashWebtv.class);
                    startActivity(webtv);
                }
            });
            mTollbarBotton.findViewById(R.id.ic_portal).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent portal = new Intent(ResultActivity.this, PortalActivity.class);
                    startActivity(portal);
                }
            });
            mTollbarBotton.findViewById(R.id.ic_mapas).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mapas = new Intent(ResultActivity.this, MapasActivity.class);
                    startActivity(mapas);
                }
            });
            mTollbarBotton.findViewById(R.id.ic_agenda).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent agenda = new Intent(ResultActivity.this, AgendaActivity.class);
                    startActivity(agenda);
                }
            });

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    int pos = arg2;

                    int x = mList.get(pos).getIdUnid();

                    Intent intent = new Intent(ResultActivity.this, TelListActivity.class);
                    intent.putExtra("pos", x);

                    startActivity(intent);
                    //int id =
                    //Toast.makeText(ResultActivity.this, "Voce clicou no item número " + pos, Toast.LENGTH_SHORT).show();

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
            Intent sobreApp = new Intent(ResultActivity.this, SobreAppActivity.class);
            startActivity(sobreApp);
        }
        else if(id == R.id.sobre){
            Intent sobre = new Intent(ResultActivity.this, SobreActivity.class);
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

        public CustomListAdapter(ResultActivity activity) {

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

        public String adicionarEnd(String x){
            String endComp = "";

            BDCampus bdCampus = new BDCampus(ResultActivity.this);
            campus = bdCampus.buscarPorId(x);

            endComp = campus.getCampusEnd() + " - " +
                    campus.getCampusBairro() + " CEP: " +
                    campus.getCampusCep() + " - " +
                    campus.getCampusCidade() + "/" +
                    campus.getCampusUf();
            return endComp;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // chamar o inflat dos itens e definir as views
            View listItem = convertView;
            int pos = position;

            if(listItem == null){
                listItem = layoutInflater.inflate(R.layout.item_list_result, null);
            }
            //Colocando na tela os elementos da busca
            TextView tvUnid = (TextView) listItem.findViewById(R.id.unid_res);
            TextView tvTitular = (TextView) listItem.findViewById(R.id.titular_res);
            TextView tvEnd = (TextView) listItem.findViewById(R.id.end_res);
            TextView tvEmail = (TextView) listItem.findViewById(R.id.email_res);
            TextView tvTel = (TextView) listItem.findViewById(R.id.telefone_res);
            TextView tvTel2 = (TextView) listItem.findViewById(R.id.telefone2_res);

            String end = "" + mList.get(pos).getCampiIdCampus();
            String endere = adicionarEnd(end);
            String tvtitu;
            String email;
            String tele2;
            if(mList.get(pos).getUnidTit()== null){
                tvtitu = "";
            }else {
                tvtitu = mList.get(pos).getUnidTit().toString();
            }
            if (mList.get(pos).getUnidEmailInst() == null){
                email = "";
            }else{
                email = mList.get(pos).getUnidEmailInst().toString();
            }
            if (mList.get(pos).getUnidTel2() == null){
                tele2 = "";
            }else{
                tele2 = mList.get(pos).getUnidTel2().toString();
            }


            //aplicando as views no form_contato
            tvUnid.setText(mList.get(pos).getUnidSigla().toString()+" - "+ mList.get(pos).getUnidNome().toString());
            tvTitular.setText(tvtitu);
            tvEnd.setText(endere);
            tvEmail.setText(email);
            tvTel.setText(mList.get(pos).getUnidTel().toString());
            tvTel2.setText(tele2);

            return listItem;
        }

    }

    public void voltarLista(View view){
        Intent it = new Intent(ResultActivity.this, TelefoneActivity.class);
        startActivity(it);
        finish();
    }

}
