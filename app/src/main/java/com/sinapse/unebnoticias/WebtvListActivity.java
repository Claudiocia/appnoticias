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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sinapse.unebnoticias.image.ImageLoader;
import com.sinapse.unebnoticias.parse.DOMParseWebtv;
import com.sinapse.unebnoticias.parse.RSSFeed;

/**
 * Created by ClaudioSouza on 17/09/2016.
 */
public class WebtvListActivity extends AppCompatActivity {
    private Toolbar mTollbar;
    private Toolbar mTollbarBotton;

    RSSFeed feed;
    ListView lv;
    CustomListAdapter adapter;
    String feedLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webtv_list);

        //definir link para atualizar o feed
        feedLink = new SplashWebtv().RSSFEEDURL;

        //buscar o arquivo com o feed
        feed = (RSSFeed) getIntent().getExtras().get("feed");

        //Inicializando as variaveis
        lv = (ListView) findViewById(R.id.listView_webtv);
        lv.setVerticalFadingEdgeEnabled(true);

        //Definindo um adaptador para a listView
        adapter = new CustomListAdapter(this);
        lv.setAdapter(adapter);

        mTollbar = (Toolbar) findViewById(R.id.tb_listView);
        mTollbar.setTitle(R.string.webtv);
        mTollbar.setSubtitle("Vídeos");
        mTollbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mTollbar);

        mTollbarBotton = (Toolbar) findViewById(R.id.inc_tb_botton);
        mTollbarBotton.inflateMenu(R.menu.menu_bottom);

        mTollbarBotton.findViewById(R.id.ic_noticia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noticias = new Intent(WebtvListActivity.this, SplashActivity.class);
                startActivity(noticias);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_linhadir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lindir = new Intent(WebtvListActivity.this, LinhadirActivity.class);
                startActivity(lindir);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_webtv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webtv = new Intent(WebtvListActivity.this, SplashWebtv.class);
                startActivity(webtv);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_portal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent portal = new Intent(WebtvListActivity.this, PortalActivity.class);
                startActivity(portal);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_mapas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapas = new Intent(WebtvListActivity.this, MapasActivity.class);
                startActivity(mapas);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_agenda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agenda = new Intent(WebtvListActivity.this, AgendaActivity.class);
                startActivity(agenda);
            }
        });

        //definindo a ação do clique para o ListView
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,	int arg2, long arg3) {

                //ação para quando um item na lista é clicado
                int pos = arg2;

                Bundle bundle = new Bundle();
                bundle.putSerializable("feed", feed);
                Intent intent = new Intent(WebtvListActivity.this, WebtvActivity.class);
                intent.putExtras(bundle);
                intent.putExtra("pos", pos);

                startActivity(intent);

            }
        });

        mTollbar.findViewById(R.id.iv_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                ImageView ib = (ImageView) findViewById(R.id.iv_refresh);
                Animation rotation = AnimationUtils.loadAnimation(getApplication(), R.anim.refresh_rotate);
                rotation.setRepeatCount(Animation.INFINITE);
                ib.startAnimation(rotation);
                Toast.makeText(WebtvListActivity.this, "  Atualizando, \nfavor aguardar", Toast.LENGTH_SHORT).show();


                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DOMParseWebtv tmpDOMParser = new DOMParseWebtv();
                        feed = tmpDOMParser.parseWebtv(feedLink);

                        WebtvListActivity.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                if (feed != null && feed.getItemCount() > 0){
                                    adapter.notifyDataSetChanged();
                                    //originalmente a proxima linha esta comentada
                                    lv.setAdapter(adapter);
                                    view.getAnimation().cancel();
                                    Toast.makeText(WebtvListActivity.this, "Noticias Atualizadas!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                thread.start();
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
            Intent sobreApp = new Intent(WebtvListActivity.this, SobreAppActivity.class);
            startActivity(sobreApp);
        }
        else if(id == R.id.sobre){
            Intent sobre = new Intent(WebtvListActivity.this, SobreActivity.class);
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

    //class ListAdapter
    class CustomListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;
        public ImageLoader imageLoader;

        public CustomListAdapter(WebtvListActivity activity) {


            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            imageLoader = new ImageLoader(activity.getApplicationContext());

        }

        @Override
        public int getCount() {

            return feed.getItemCount();
        }


        @Override
        public Object getItem(int position) {

            return position;
        }

        @Override
        public long getItemId(int position) {

            return position;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // chamar o inflat dos itens e definit as views
            View listItem = convertView;
            int pos = position;

            if(listItem == null){
                listItem = layoutInflater.inflate(R.layout.item_list_webtv, null);
            }

            //Colocando na tela os elementos da busca
            ImageView iv = (ImageView) listItem.findViewById(R.id.thumb_web);
            TextView tvTitle = (TextView) listItem.findViewById(R.id.title_web);


            //aplicando as views no form_contato
            imageLoader.DisplayImage(feed.getItem(pos).getImage(), iv);
            tvTitle.setText(feed.getItem(pos).getTitle());


            return listItem;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.imageLoader.clearCache();
        adapter.notifyDataSetChanged();
    }
}
