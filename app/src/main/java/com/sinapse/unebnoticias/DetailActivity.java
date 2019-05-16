package com.sinapse.unebnoticias;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sinapse.unebnoticias.parse.RSSFeed;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by ClaudioSouza on 17/09/2016.
 */
public class DetailActivity extends FragmentActivity {
    private Toolbar mTollbar;
    private Toolbar mTollbarBotton;
    RSSFeed feed;
    int pos;
    private DescAdapter adapter;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        //Obter o objeto feed e a posição na tela
        feed = (RSSFeed) getIntent().getExtras().get("feed");
        pos = getIntent().getExtras().getInt("pos");

        //Inicializando as views
        adapter = new DescAdapter(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.pager);

        //definindo a view para o pager
        pager.setAdapter(adapter);
        pager.setCurrentItem(pos);
        mTollbar = (Toolbar) findViewById(R.id.tb_listView);
        mTollbar.setTitle(R.string.app_name);
        mTollbar.setLogo(R.drawable.ic_launcher);
        //setSupportActionBar(mTollbar);

        mTollbar.findViewById(R.id.iv_compat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt();
            }

            private void shareIt() {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "UNEB Noticias");
                String shareBody = feed.getItem(pos).getTitle() + " - link:\n" + feed.getItem(pos).getLink();
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

                startActivity(Intent.createChooser(shareIntent, "Compartilhar via"));
            }
        });

        mTollbarBotton = (Toolbar) findViewById(R.id.inc_tb_botton);
        mTollbarBotton.inflateMenu(R.menu.menu_bottom);

        mTollbarBotton.findViewById(R.id.ic_noticia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noticias = new Intent(DetailActivity.this, SplashActivity.class);
                startActivity(noticias);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_linhadir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lindir = new Intent(DetailActivity.this, LinhadirActivity.class);
                startActivity(lindir);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_webtv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webtv = new Intent(DetailActivity.this, SplashWebtv.class);
                startActivity(webtv);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_portal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent portal = new Intent(DetailActivity.this, PortalActivity.class);
                startActivity(portal);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_mapas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapas = new Intent(DetailActivity.this, MapasActivity.class);
                startActivity(mapas);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_agenda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agenda = new Intent(DetailActivity.this, AgendaActivity.class);
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
            Intent sobreApp = new Intent(DetailActivity.this, SobreAppActivity.class);
            startActivity(sobreApp);
        }
        else if(id == R.id.sobre){
            Intent sobre = new Intent(DetailActivity.this, SobreActivity.class);
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

    public class DescAdapter extends FragmentStatePagerAdapter {
        public DescAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return feed.getItemCount();
        }

        @Override
        public Fragment getItem(int position) {

            DetailFragment frag = new DetailFragment();

            Bundle bundle = new Bundle();
            bundle.putSerializable("feed", feed);
            bundle.putInt("pos", position);
            frag.setArguments(bundle);

            return frag;
        }

    }
}
