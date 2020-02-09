package com.sinapse.unebnoticias;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sinapse.unebnoticias.bancodados.BDEcgu;
import com.sinapse.unebnoticias.bancodados.BDUsuario;
import com.sinapse.unebnoticias.bancodados.NomeEcgu;
import com.sinapse.unebnoticias.bancodados.Usuario;
import com.sinapse.unebnoticias.util.AndroidSystemUtil;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ClaudioSouza on 17/09/2016.
 */
public class CadastrarUser extends AppCompatActivity {
    private Toolbar mTollbar;
    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtMat;
    private EditText edtTel;
    private Spinner sp;
    private int pos;
    private String regId;
    String nomeDept;
    private RequestQueue rq;
    private Map<String, String> params;
    private String url;
    String JTF_hora;
    String JTF_data;
    String dataHora;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caduser);

        regId = AndroidSystemUtil.getRegistrationId(CadastrarUser.this);

        BDEcgu bdEcgu = new BDEcgu(this);

        List<NomeEcgu> list = bdEcgu.buscarSigla();
        List<String> siglas = new ArrayList<>();
        siglas.add("Selecione o setor!");

        siglas.add("Usuário externo");
        siglas.add("Estudante - Discente");
        for(NomeEcgu unid : list ){
            siglas.add(unid.getNomeEcgu() + " - " + unid.getSiglaEcgu());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, siglas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp = (Spinner) findViewById(R.id.spinner);
        sp.setAdapter(adapter);
        sp.setEnabled(true);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(CadastrarUser.this, "Nome do Item = "+ position, Toast.LENGTH_LONG).show();
                pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        url = "https://www.smartenem.com.br/server/server.php";
        //pega hora
        JTF_hora = (new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
        //pega data
        JTF_data = (new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        dataHora = JTF_data +" - "+ JTF_hora;

        edtNome  = (EditText) findViewById(R.id.edt_nome);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtMat   = (EditText) findViewById(R.id.edt_mat);
        edtTel   = (EditText) findViewById(R.id.edt_tel);

        rq = Volley.newRequestQueue(CadastrarUser.this);

        mTollbar = (Toolbar) findViewById(R.id.tb_listView);
        mTollbar.setTitle(R.string.caduser);
        mTollbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mTollbar);


    }
    public final boolean isValidadeEmail(String email){
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void salvarDados(View view){

        if(edtNome.getText().toString().length()<= 3){
            edtNome.setError("Preencha o campo nome, com pelo menos 4 letras");
            edtNome.requestFocus();
            //int pass = 33;
        }else {
            edtNome.setError(null);

            if (isValidadeEmail(edtEmail.getText().toString().trim()) == false) {
                edtEmail.setError("Informe um email válido!");
                edtEmail.requestFocus();
            } else {
                edtEmail.setError(null);

                if (pos <= 0) {
                    Toast.makeText(CadastrarUser.this, "Favor selecionar o setor", Toast.LENGTH_SHORT).show();
                } else {
                    nomeDept = sp.getSelectedItem().toString();
                    Log.d("Volta do Spinner", nomeDept);

                    if (edtTel.getText().toString().length() <= 10 || edtTel.getText().toString().length() >= 12) {
                        edtTel.setError("Preencha corretamente o número do celular, inclusive o ddd no formato dd9xxxxxxxx");
                        edtTel.requestFocus();
                    } else {
                        edtTel.setError(null);

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
                                        Toast.makeText(CadastrarUser.this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }) {
                            @Override
                            public Map<String, String> getParams() throws AuthFailureError {
                                params = new HashMap<String, String>();
                                params.put("nome", edtNome.getText().toString());
                                params.put("email", edtEmail.getText().toString());
                                params.put("matricula", edtMat.getText().toString());
                                params.put("telefone", edtTel.getText().toString());
                                params.put("setor", nomeDept);
                                params.put("data_msg", dataHora );
                                params.put("header", getHeaders().toString());
                                params.put("regid",regId );

                                return (params);
                            }

                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                HashMap<String, String> header = new HashMap<String, String>();
                                header.put("apiKey", "AIzaSyCrR3k-LewTrS8sW0Fj40d0nr7ZkoP4vYs");
                                return (header);
                            }

                            @Override
                            public Priority getPriority() {
                                return (Priority.NORMAL);
                            }
                        };
                        request.setTag("tag");
                        rq.add(request);

                        Usuario user = new Usuario();

                        user.setUserNome(edtNome.getText().toString());
                        user.setUserEmail(edtEmail.getText().toString());
                        user.setUserMat(edtMat.getText().toString());
                        user.setUserTel(edtTel.getText().toString());
                        user.setUserDept(nomeDept);

                        BDUsuario bdUser = new BDUsuario(this);

                        bdUser.inserir(user);

                        Intent it = new Intent(CadastrarUser.this, SplashInicial.class);
                        startActivity(it);
                        finish();
                    }
                }
            }
        }
    }
    @Override
    public void onStop () {
        super.onStop();

        rq.cancelAll("tag");
    }


    public void doBefore() {
        //não usarei por enquanto
    }

    public void doAfter(String answer) {

        try {
            String resposta = new String(answer);

            if (!resposta.isEmpty()) {
                Toast.makeText(CadastrarUser.this, "Resposta: " + resposta, Toast.LENGTH_LONG);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
