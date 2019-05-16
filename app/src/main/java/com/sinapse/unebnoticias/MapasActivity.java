package com.sinapse.unebnoticias;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sinapse.unebnoticias.mapas.Mapa;
import com.sinapse.unebnoticias.mapas.MapaFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by ClaudioSouza on 16/09/2016.
 */
public class MapasActivity extends AppCompatActivity {

    private static String TAG = "LOG";
    private Toolbar mTollbar;
    private Toolbar mTollbarBotton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapas);

        mTollbar = (Toolbar) findViewById(R.id.tb_main);
        mTollbar.setTitle(R.string.mapas);
        mTollbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mTollbar);

        mTollbarBotton = (Toolbar) findViewById(R.id.inc_tb_botton_mp);
        mTollbarBotton.inflateMenu(R.menu.menu_bottom_mapa);

        mTollbarBotton.findViewById(R.id.ic_noticia_mp).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent noticias = new Intent(MapasActivity.this, SplashActivity.class);
                startActivity(noticias);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_linhadir_mp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lindir = new Intent(MapasActivity.this, LinhadirActivity.class);
                startActivity(lindir);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_webtv_mp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webtv = new Intent(MapasActivity.this, SplashWebtv.class);
                startActivity(webtv);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_portal_mp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent portal = new Intent(MapasActivity.this, PortalActivity.class);
                startActivity(portal);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_mapas_mp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //no action
            }
        });
        mTollbarBotton.findViewById(R.id.ic_agenda_mp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agenda = new Intent(MapasActivity.this, AgendaActivity.class);
                startActivity(agenda);
            }
        });

        //FRAGMENT
        MapaFragment frag = (MapaFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
        if(frag == null){
            frag = new MapaFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
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
            Intent sobreApp = new Intent(MapasActivity.this, SobreAppActivity.class);
            startActivity(sobreApp);
        }
        else if(id == R.id.sobre){
            Intent sobre = new Intent(MapasActivity.this, SobreActivity.class);
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

    public List<Mapa> getSetMapaList(int qtd){
        String[] nomesMapas = new String[]{"Uneb Multicampi nos Territorios de Identidade da Bahia", "Campi - Graduação Presencial", "Licenciatura Intercultural em Educação Escolar Indígena (LICEEI)", "Plano Nacional de Formação dos Professores da Educação Básica (PARFOR) / MEC", "Graduação EAD", "Especialização presencial",
                "Especialização EAD", "Pós-graduação stricto sensu", "Todos pela Alfabetização (TOPA) / SEC", "Universidade para Todos (UPT) / SEC", "Pacto Nacional pela Alfabetização na Idade Certa (PNAIC) / SEC, UNDIME, UNCME",
                "Programa Institucional de Bolsas em Iniciação à Docência (PIBID) / CAPES", "Especializações em Gestão da Educação e Metodologia do Ensino – SEC / SUPROF",  "Universidade Aberta à Terceira Idade (UATI)"};
        String[] descriMapas = new String[]{"A Uneb está presente em todas as regiões da Bahia, alcançando com suas ações mais de 300 municípios", "A UNEB oferece curso de graduação presencial em 24 municípios de 19 territórios de identidade do Estado da Bahia.", "Curso específico destinado à formação em nível superior de professores de escolas indígenas.",
                "Destinado aos professores em exercício das escolas públicas estaduais e municipais sem formação adequada à LDB, oferecendo cursos superiores públicos, gratuitos e de qualidade.",
                "Cursos de graduação oferecidos na modalidade de ensino a distância.", "Cursos de pós-graduação lato sensu oferecidos na modalidade presencial.", "Cursos de pós-graduação lato sensu oferecidos na modalidade de ensino a distância.",
                "Cursos de pós-graduação stricto sensu (Mestrado e Doutorado) oferecidos na modalidade presencial.", "Tem como objetivos assegurar o acesso e a permanência de jovens, adultos e idosos na escola, garantir oportunidades para desenvolvimento da leitura e escrita, " +
                "e criar condições para a inclusão social, política, econômica e cultural dos beneficiados.", "Curso pré-vestibular voltado aos estudantes do ensino médio da rede pública de ensino municipal e/ou estadual.",
                "Tem por objetivo assegurar a plena alfabetização de todas as crianças até os oito anos de idade, ao final do 3º ano do ensino fundamental.", "Tem por objetivo fomentar a formação inicial e continuada de profissionais do magistério básico," +
                " numa ação que articula a participação de estudantes dos cursos de licenciatura das nas escolas da Educação Básica sob a supervisão de professores da Universidade.",
                "Visa a valorização e melhoria da qualificação profissional dos professores das Escolas e Centros de Educação profissional da Rede Pública de Ensino do Estado da Bahia, nos 27 Territórios de Identidade.",
                "Programa de extensão universitária que atende pessoas cuja faixa etária seja igual ou superior a 60 anos, objetivando a sua reinserção psicossocial para o pleno exercício da cidadania."};
        int[] idMapas = new int[]{R.drawable.mapa_3_app, R.drawable.mapa1_multicampia, R.drawable.mapa2_liceei, R.drawable.mapa3_parfor, R.drawable.mapa4_grad_ead,
                R.drawable.mapa5_espec_presen, R.drawable.mapa6_espec_ead, R.drawable.mapa7_ppg, R.drawable.mapa8_topa, R.drawable.mapa9_upt,
                R.drawable.mapa10_pnaic, R.drawable.mapa11_pibid, R.drawable.mapa12_suprof, R.drawable.mapa13_uati};
        List<Mapa> listAux = new ArrayList<>();

        for(int i = 0; i < qtd; i++){
            Mapa map = new Mapa(nomesMapas[i % nomesMapas.length], descriMapas[i % descriMapas.length], idMapas[i % nomesMapas.length]);
            listAux.add(map);
        }

        return (listAux);
    }


}
