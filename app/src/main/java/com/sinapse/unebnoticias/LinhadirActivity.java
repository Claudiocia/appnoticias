package com.sinapse.unebnoticias;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sinapse.unebnoticias.bancodados.BDUsuario;
import com.sinapse.unebnoticias.bancodados.Usuario;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ClaudioSouza on 16/09/2016.
 */
public class LinhadirActivity extends AppCompatActivity implements Transaction {
    private Toolbar mTollbar;
    private Toolbar mTollbarBotton;
    private RequestQueue rq;
    private Map<String, String> params;
    private TextView edtNome;
    private TextView edtEmail;
    private EditText edtMsg;
    private String destino;
    private String url;
    private Transaction transaction;
    String JTF_hora;
    String JTF_data;
    String dataHora;
    String setor;
    String tel;
    Usuario user;
    private String[] nomesECGU = new String[]{"Selecione o destinatário",
            "Chefia de Gabinete",
            "PROGRAD - Pró-Reitoria de Ensino de Graduação",
            "PPG - Pró-Reitoria de Pesquisa e Ensino de Pós-Graduação",
            "PROEX - Pró-Reitoria de Pesquisa e Extensão",
            "PRAES  - Pró-Reitoria  de Assistência Estudantil",
            "PROAF - Pró-Reitoria de Ações Afirmativas",
            "UNEAD - Unidade Acadêmica de Educação a Distância",
            "PROPLAN - Pró-Reitoria de Planejamento",
            "PROAD - Pró-Reitoria de Administração",
            "PGDP - Pró-Reitoria de Gestão e Desenvolvimento de Pessoas",
            "PROINFRA - Pró-Reitoria de Infraestrutura",
            "SEAVI - Secretaria de Avaliação Institucional",
            "SERINT - Secretaria de Relações Internacionais",
            "SEAI - Secretaria de Articulação Interinstitucional",
            "SELCC - Secretaria de Licitações, Contratos e Convênios",
            "SECONF - Secretaria de Contabilidade e Finanças",
            "UDO - Unidade de Desenvolvimento Organizacional",
            "ASCULT - Assessoria Especial de Cultura e Artes",
            "ASCOM - Assessoria de Comunicação",
            "EDUNEB - Editora UNEB",
            "SMOS - Serviço Médico Odontológico e Social",
            "SISB  - Sistema de Bibliotecas",
            "Ouvidoria",
            "Cerimonial"};

    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fale_comreitor);

        BDUsuario bdUser = new BDUsuario(this);
        user = bdUser.buscarPorArg(1);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomesECGU);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp = (Spinner) findViewById(R.id.spinner);
        sp.setAdapter(adapter);
        sp.setEnabled(true);


        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(LinhadirActivity.this, "Nome do Item = "+ sp.getSelectedItem(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        url = "http://www.informa.uneb.br/server/server.php";

        //pega hora
        JTF_hora = (new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
        //pega data
        JTF_data = (new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));

        edtNome  = (TextView) findViewById(R.id.edt_nome);
        edtEmail = (TextView) findViewById(R.id.edt_email);
        edtMsg   = (EditText) findViewById(R.id.edt_msg);
        dataHora = JTF_data +" - "+ JTF_hora;
        setor = user.getUserDept().toString();
        tel = user.getUserTel().toString();

        edtNome.setText(user.getUserNome().toString());
        edtEmail.setText(user.getUserEmail().toString());
        //edtMat.setText(user.getUserMat().toString());

        rq = Volley.newRequestQueue(LinhadirActivity.this);

        mTollbar = (Toolbar) findViewById(R.id.tb_listView);
        mTollbar.setTitle(R.string.linhadir);
        mTollbar.setSubtitle("Sua linha direta");
        mTollbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mTollbar);

        mTollbarBotton = (Toolbar) findViewById(R.id.inc_tb_botton);
        mTollbarBotton.inflateMenu(R.menu.menu_bottom);

        mTollbarBotton.findViewById(R.id.ic_noticia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noticias = new Intent(LinhadirActivity.this, SplashActivity.class);
                startActivity(noticias);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_linhadir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //no action
            }
        });
        mTollbarBotton.findViewById(R.id.ic_webtv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webtv = new Intent(LinhadirActivity.this, SplashWebtv.class);
                startActivity(webtv);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_portal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent portal = new Intent(LinhadirActivity.this, PortalActivity.class);
                startActivity(portal);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_mapas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapas = new Intent(LinhadirActivity.this, MapasActivity.class);
                startActivity(mapas);
            }
        });
        mTollbarBotton.findViewById(R.id.ic_agenda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agenda = new Intent(LinhadirActivity.this, AgendaActivity.class);
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
            Intent sobreApp = new Intent(LinhadirActivity.this, SobreAppActivity.class);
            startActivity(sobreApp);
        }
        else if(id == R.id.sobre){
            Intent sobre = new Intent(LinhadirActivity.this, SobreActivity.class);
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


    public void enviarDados(View view) {
        destino  = (String) sp.getSelectedItem();
        if(destino == "Selecione o destinatário"){
            Toast.makeText(LinhadirActivity.this, "Error: Favor selecionar um destinatário", Toast.LENGTH_LONG).show();

        }else {

            if (edtMsg.getText().toString().length() <= 0) {
                edtMsg.setError("Falta digitar a mensagem com até 350 toques");
                edtMsg.requestFocus();
            } else {
                edtMsg.setError(null);
                StringRequest request = new StringRequest(Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                doAfter(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(LinhadirActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    public Map<String, String> getParams() throws AuthFailureError {
                        params = new HashMap<String, String>();
                        params.put("nome", edtNome.getText().toString());
                        params.put("email", edtEmail.getText().toString());
                        params.put("telefone", tel);
                        params.put("setor", setor);
                        params.put("destino", destino);
                        params.put("mensagem", edtMsg.getText().toString());
                        params.put("data_msg", dataHora);
                        params.put("header", getHeaders().toString());

                        return (params);
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> header = new HashMap<String, String>();
                        header.put("apiKey", "Essa e minha API KEY: sdvkjbsdjvkbskdv");

                        return (header);
                    }

                    @Override
                    public Priority getPriority() {
                        return (Priority.NORMAL);
                    }
                };
                request.setTag("tag Tico");
                rq.add(request);



                Intent intent = new Intent(LinhadirActivity.this, SplashActivity.class);
                startActivity(intent);

                //mata a activity
                finish();

            }
        }

    }
    @Override
    public void onStop () {
        super.onStop();

        rq.cancelAll("tag");
    }


    @Override
    public void doBefore() {
        //não usarei por enquanto
    }

    @Override
    public void doAfter(String answer) {

        try {
            String resposta = new String(answer);

            if (!resposta.isEmpty()) {
                Toast.makeText(LinhadirActivity.this, "Resposta: " + resposta, Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
