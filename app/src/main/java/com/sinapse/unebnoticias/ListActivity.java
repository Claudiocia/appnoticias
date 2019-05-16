package com.sinapse.unebnoticias;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sinapse.unebnoticias.image.ImageLoader;
import com.sinapse.unebnoticias.parse.DOMParseHtml;
import com.sinapse.unebnoticias.parse.RSSFeed;

public class ListActivity extends AppCompatActivity implements AbsListView.OnScrollListener {
    private Toolbar mTollbar;
    private Toolbar mTollbarBotton;

    RSSFeed feed;
    ListView lv;
    CustomListAdapter adapter;
    String feedLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_list);

        //definir link para atualizar o feed
        feedLink = new SplashActivity().RSSFEEDURL;

        //buscar o arquivo com o feed
        feed = (RSSFeed) getIntent().getExtras().get("feed");

        //Inicializando as variaveis
        lv = (ListView) findViewById(R.id.listView);
        lv.setVerticalFadingEdgeEnabled(true);
        lv.setOnScrollListener(this);

        //Definindo um adaptador para a listView
        adapter = new CustomListAdapter(this);
        lv.setAdapter(adapter);

        mTollbar = (Toolbar) findViewById(R.id.tb_listView);
        mTollbar.setTitle(R.string.app_name);
        mTollbar.setPadding(15, 0, 0, 0);
        mTollbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mTollbar);

        mTollbarBotton = (Toolbar) findViewById(R.id.inc_tb_botton);
        mTollbarBotton.inflateMenu(R.menu.menu_bottom);

        mTollbarBotton.findViewById(R.id.ic_noticia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noticias = new Intent(ListActivity.this, SplashActivity.class);
                startActivity(noticias);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_linhadir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lindir = new Intent(ListActivity.this, LinhadirActivity.class);
                startActivity(lindir);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_webtv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webtv = new Intent(ListActivity.this, SplashWebtv.class);
                startActivity(webtv);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_portal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent portal = new Intent(ListActivity.this, PortalActivity.class);
                startActivity(portal);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_mapas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapas = new Intent(ListActivity.this, MapasActivity.class);
                startActivity(mapas);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_agenda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agenda = new Intent(ListActivity.this, AgendaActivity.class);
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
                Intent intent = new Intent(ListActivity.this,  DetailActivity.class);
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
                Toast.makeText(ListActivity.this, "  Atualizando, \nfavor aguardar", Toast.LENGTH_SHORT).show();


                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DOMParseHtml tmpDOMParser = new DOMParseHtml();
                        feed = tmpDOMParser.parseHtml(feedLink);

                        ListActivity.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                if (feed != null && feed.getItemCount() > 0){
                                    adapter.notifyDataSetChanged();
                                    //originalmente a proxima linha esta comentada
                                    //lv.setAdapter(adapter);
                                    view.getAnimation().cancel();
                                    Toast.makeText(ListActivity.this, "Noticias Atualizadas!!", Toast.LENGTH_SHORT).show();
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
            Intent sobreApp = new Intent(ListActivity.this, SobreAppActivity.class);
            startActivity(sobreApp);
        }
        else if(id == R.id.sobre){
            Intent sobre = new Intent(ListActivity.this, SobreActivity.class);
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


    //Listener
    @Override
    public void onScrollStateChanged(final AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    //class ListAdapter
    class CustomListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;
        public ImageLoader imageLoader;

        public CustomListAdapter(ListActivity activity) {

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
                listItem = layoutInflater.inflate(R.layout.item_list, null);
            }

            //Colocando na tela os elementos da busca
            ImageView iv = (ImageView) listItem.findViewById(R.id.thumb);
            TextView tvTitle = (TextView) listItem.findViewById(R.id.title);
            TextView tvDate = (TextView) listItem.findViewById(R.id.date);

            //aplicando as views no form_contato
            imageLoader.DisplayImage(feed.getItem(pos).getImage(), iv);
            tvTitle.setText(feed.getItem(pos).getTitle());
            tvDate.setText(feed.getItem(pos).getDate());

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
