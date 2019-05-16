package com.sinapse.unebnoticias.image;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sinapse.unebnoticias.R;
import com.sinapse.unebnoticias.bancodados.BDCurso;
import com.sinapse.unebnoticias.bancodados.BDMapaInfo;
import com.sinapse.unebnoticias.bancodados.BDPrograma;
import com.sinapse.unebnoticias.bancodados.Curso;
import com.sinapse.unebnoticias.bancodados.MapaInfo;
import com.sinapse.unebnoticias.bancodados.Programa;

import java.util.List;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class ZoomInZoomOut extends AppCompatActivity implements View.OnTouchListener {

    private static final String TAG = "Touch";
    private Toolbar mTollbar;
    private static final float MIN_ZOOM = 0.5f, MAX_ZOOM = 5f;

    // These matrices will be used to scale points of the image
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();

    // The 3 states (events) which the user is trying to perform
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;
    int pos;
    String arg1, arg2, arg3, arg4;
    int arg5;

    // these PointF objects are used to record the point(s) the user is touching
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 0.5f;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private GoogleApiClient client;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa_imagem);

        mTollbar = (Toolbar) findViewById(R.id.tb_mapa);
        mTollbar.setTitle(R.string.app_name);
        mTollbar.setPadding(15, 0, 0, 0);
        mTollbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mTollbar);

        TextView titulo = (TextView) findViewById(R.id.tv_title_mapa);
        ImageView view = (ImageView) findViewById(R.id.iv_mapa);

        pos = (int) getIntent().getExtras().get("pos");
        switch (pos) {
            case 2:
                titulo.setText("Campi - Graduação Presencial");
                view.setImageResource(R.drawable.mapa_1_alta);
                arg1 = "mapasinfo_campus = ?";
                arg2 = "s";
                arg5 = 1;
                //Chamada do banco com os dados acima para compor o popup
                BDMapaInfo mapaInfo = new BDMapaInfo(getBaseContext());
                List<MapaInfo> list = mapaInfo.buscarDetalhe(arg1, arg2);

                BDCurso cursoInfo = new BDCurso(getBaseContext());
                List<Curso> listCurso = cursoInfo.buscarTodos();
                List<Curso> listConta = cursoInfo.contarTodos();

                BDPrograma programInfo = new BDPrograma(getBaseContext());
                Programa mProgram = programInfo.buscarPorId(arg5);

                arg3 = "São 29 departamentos distribuídos em " + list.size() + " Campi em toda a Bahia";
                arg4 = "Atualmente a UNEB possui " + listConta.size() + " cursos de graduação presencial, que atendem mais de " + mProgram.getProgramBenef() + " estudantes.";
                break;
            case 3:
                titulo.setText("Licenciatura Intercultural em Educação Escolar Indígena (LICEEI)");
                view.setImageResource(R.drawable.mapa_2_alta);
                arg1 = "mapasinfo_liceei_polo = ? OR ?";
                arg2 = "Paulo_Afon";
                arg5 = 2;
                //Chamada do banco com os dados acima para compor o popup
                BDMapaInfo mapaInfo2 = new BDMapaInfo(getBaseContext());
                List<MapaInfo> list2 = mapaInfo2.buscarDetalhe(arg1, arg2);
                BDPrograma programInfo2 = new BDPrograma(getBaseContext());
                Programa mProgram2 = programInfo2.buscarPorId(arg5);
                arg3 = "O Programa LICEEI está presente em " + list2.size() + " municípios baianos.";
                arg4 = "Os pólos coordenadores do Programa estão localizados nos Campi de Paulo Afonso, no Norte da Bahia e " +
                        "de Teixeira de Freitas, no Extremo-Sul do Estado - regiões que tem forte presença de comunidades indígenas.\n " +
                        "Atualmente o Programa tem " + mProgram2.getProgramBenef() + " professores em formação.";
                break;
            case 4:
                titulo.setText("Plano Nacional de Formação dos Professores da Educação Básica (PARFOR) / MEC");
                view.setImageResource(R.drawable.mapa_3_alta);
                arg1 = "mapasinfo_parfor = ?";
                arg2 = "s";
                arg5 = 3;
                //Chamada do banco com os dados acima para compor o popup
                BDMapaInfo mapaInfo3 = new BDMapaInfo(getBaseContext());
                List<MapaInfo> list3 = mapaInfo3.buscarDetalhe(arg1, arg2);
                BDPrograma programInfo3 = new BDPrograma(getBaseContext());
                Programa mProgram3 = programInfo3.buscarPorId(arg5);
                arg3 = "O PARFOR está presente em " + list3.size() + " municípios baianos.";
                arg4 = "Atualmente, o Programa atende " + mProgram3.getProgramBenef() + " professores das redes estaduais e municipais de ensino.";
                break;
            case 5:
                titulo.setText("Graduação EAD");
                view.setImageResource(R.drawable.mapa_4_alta);
                arg1 = "mapasinfo_grad_ead = ?";
                arg2 = "s";
                arg5 = 4;
                //Chamada do banco com os dados acima para compor o popup
                BDMapaInfo mapaInfo4 = new BDMapaInfo(getBaseContext());
                List<MapaInfo> list4 = mapaInfo4.buscarDetalhe(arg1, arg2);
                BDPrograma programInfo4 = new BDPrograma(getBaseContext());
                Programa mProgram4 = programInfo4.buscarPorId(arg5);
                arg3 = "A UNEB atualmente tem turmas de Graduação EAD em " + list4.size() + " municípios baianos.";
                arg4 = "Atualmente, a universidade atende " + mProgram4.getProgramBenef() + " estudantes em todo o estado.";
                break;
            case 6:
                titulo.setText("Especialização presencial");
                view.setImageResource(R.drawable.mapa_5_alta);
                arg1 = "mapasinfo_pos_pres = ?";
                arg2 = "s";
                arg5 = 5;
                //Chamada do banco com os dados acima para compor o popup
                BDMapaInfo mapaInfo5 = new BDMapaInfo(getBaseContext());
                List<MapaInfo> list5 = mapaInfo5.buscarDetalhe(arg1, arg2);
                BDPrograma programInfo5 = new BDPrograma(getBaseContext());
                Programa mProgram5 = programInfo5.buscarPorId(arg5);
                arg3 = "A UNEB oferece cursos presenciais de Pós-Graduação Lato Sensu em " + list5.size() + " municípios baianos.";
                arg4 = "Atualmente, o Programa atende " + mProgram5.getProgramBenef() + " profissionais das mais diversas áreas.";
                break;
            case 7:
                titulo.setText("Especialização EAD");
                view.setImageResource(R.drawable.mapa_6_alta);
                arg1 = "mapasinfo_pos_ead = ?";
                arg2 = "s";
                arg5 = 6;
                //Chamada do banco com os dados acima para compor o popup
                BDMapaInfo mapaInfo6 = new BDMapaInfo(getBaseContext());
                List<MapaInfo> list6 = mapaInfo6.buscarDetalhe(arg1, arg2);
                BDPrograma programInfo6 = new BDPrograma(getBaseContext());
                Programa mProgram6 = programInfo6.buscarPorId(arg5);
                arg3 = "A UNEB oferece cursos EAD de Pós-Graduação Lato Sensu em " + list6.size() + " municípios baianos.";
                arg4 = "Atualmente, o Programa atende " + mProgram6.getProgramBenef() + " profissionais das mais diversas áreas.";
                break;
            case 8:
                titulo.setText("Pós-graduação stricto sensu");
                view.setImageResource(R.drawable.mapa_7_alta);
                arg1 = "mapasinfo_mestre = ?";
                arg2 = "s";
                String arg7 = "mapasinfo_doutor = ?";
                arg5 = 7;
                int arg6 = 8;
                //Chamada do banco com os dados acima para compor o popup
                BDMapaInfo mapaInfo7 = new BDMapaInfo(getBaseContext());
                List<MapaInfo> list7 = mapaInfo7.buscarDetalhe(arg1, arg2);//mestre
                List<MapaInfo> list8 = mapaInfo7.buscarDetalhe(arg7, arg2);//doutor
                BDPrograma programInfo7 = new BDPrograma(getBaseContext());
                Programa mProgram7 = programInfo7.buscarPorId(arg5);//mestre
                Programa mProgram8 = programInfo7.buscarPorId(arg6);//doutor
                int a = 0;
                int b = 0;
                b = a + Integer.parseInt(mProgram7.getProgramBenef()) + Integer.parseInt(mProgram8.getProgramBenef());
                arg3 = "A UNEB oferece cursos Pós-Graduação Strictu Sensu no grau de Doutorado em " + list8.size() + " municípios baianos e de mestrado" +
                        " em outros " + list7.size() + ".";
                arg4 = "Atualmente, o Programa de Pós-Graduação Strictu Sensu atende " + b + " profissionais das mais diversas áreas.";
                break;
            case 9:
                titulo.setText("Todos pela Alfabetização (TOPA) / SEC");
                view.setImageResource(R.drawable.mapa_8_alta);
                arg1 = "mapasinfo_topa = ?";
                arg2 = "s";
                arg5 = 9;
                //Chamada do banco com os dados acima para compor o popup
                BDMapaInfo mapaInfo9 = new BDMapaInfo(getBaseContext());
                List<MapaInfo> list9 = mapaInfo9.buscarDetalhe(arg1, arg2);
                BDPrograma programInfo9 = new BDPrograma(getBaseContext());
                Programa mProgram9 = programInfo9.buscarPorId(arg5);
                arg3 = "O TOPA, através da UNEB, está presente em " + list9.size() + " municípios baianos.";
                arg4 = "Atualmente, o Programa atende " + mProgram9.getProgramBenef() + " pessoas em fase de alfabetização.";
                break;
            case 10:
                titulo.setText("Universidade para Todos (UPT) / SEC");
                view.setImageResource(R.drawable.mapa_9_alta);
                arg1 = "mapasinfo_upt = ?";
                arg2 = "s";
                arg5 = 10;
                //Chamada do banco com os dados acima para compor o popup
                BDMapaInfo mapaInfo10 = new BDMapaInfo(getBaseContext());
                List<MapaInfo> list10 = mapaInfo10.buscarDetalhe(arg1, arg2);
                BDPrograma programInfo10 = new BDPrograma(getBaseContext());
                Programa mProgram10 = programInfo10.buscarPorId(arg5);
                arg3 = "A UNEB oferece cursos de Pré-Vestibular pelo Programa UPT em " + list10.size() + " municípios baianos.";
                arg4 = "Atualmente, o UPT atende " + mProgram10.getProgramBenef() + " estudantes.";
                break;
            case 11:
                titulo.setText("Pacto Nacional pela Alfabetização na Idade Certa (PNAIC) / SEC, UNDIME, UNCME");
                view.setImageResource(R.drawable.mapa_10_alta);
                arg1 = "mapasinfo_pnaic = ?";
                arg2 = "s";
                arg5 = 11;
                //Chamada do banco com os dados acima para compor o popup
                BDMapaInfo mapaInfo11 = new BDMapaInfo(getBaseContext());
                List<MapaInfo> list11 = mapaInfo11.buscarDetalhe(arg1, arg2);
                BDPrograma programInfo11 = new BDPrograma(getBaseContext());
                Programa mProgram11 = programInfo11.buscarPorId(arg5);
                arg3 = "O PNAIC está presente em " + list11.size() + " municípios baianos.";
                arg4 = "O Programa atende " + mProgram11.getProgramBenef() + " estudantes.";
                break;
            case 12:
                titulo.setText("Programa Institucional de Bolsas em Iniciação à Docência (PIBID) / CAPES");
                view.setImageResource(R.drawable.mapa_11_alta);
                arg1 = "mapasinfo_pibid = ?";
                arg2 = "s";
                arg5 = 12;
                //Chamada do banco com os dados acima para compor o popup
                BDMapaInfo mapaInfo12 = new BDMapaInfo(getBaseContext());
                List<MapaInfo> list12 = mapaInfo12.buscarDetalhe(arg1, arg2);
                BDPrograma programInfo12 = new BDPrograma(getBaseContext());
                Programa mProgram12 = programInfo12.buscarPorId(arg5);
                arg3 = "O PIBID tem professores em " + list12.size() + " municípios baianos.";
                arg4 = "Atualmente, o Programa atende " + mProgram12.getProgramBenef() + " professores das mais diversas áreas.";
                break;
            case 13:
                titulo.setText("Especializações em Gestão da Educação e Metodologia do Ensino – SEC / SUPROF");
                view.setImageResource(R.drawable.mapa_12_alta);
                arg1 = "mapasinfo_suprof_met = ?";
                String arg9 = "mapasinfo_suprof_gest = ?";
                arg2 = "s";
                arg5 = 13;
                int arg10 = 14;
                //Chamada do banco com os dados acima para compor o popup
                BDMapaInfo mapaInfo13 = new BDMapaInfo(getBaseContext());
                List<MapaInfo> list13 = mapaInfo13.buscarDetalhe(arg1, arg2);
                List<MapaInfo> list16 = mapaInfo13.buscarDetalhe(arg9, arg2);
                BDPrograma programInfo13 = new BDPrograma(getBaseContext());
                Programa mProgram13 = programInfo13.buscarPorId(arg5);
                Programa mProgram14 = programInfo13.buscarPorId(arg10);
                String benefA = mProgram13.getProgramBenef();
                String benefB = mProgram14.getProgramBenef();
                int mun = list13.size() + list16.size();
                int benef = Integer.parseInt(benefA) + Integer.parseInt(benefB);

                arg3 = "O Programa SUPROF está presente em " + mun + " municípios baianos.";
                arg4 = "Atualmente, estão sendo formados " + benef + " entre professores e gestores da Educação.";
                break;
            case 14:
                titulo.setText("Universidade Aberta à Terceira Idade (UATI)");
                view.setImageResource(R.drawable.mapa_13_alta);
                arg1 = "mapasinfo_uati = ?";
                arg2 = "s";
                arg5 = 15;
                //Chamada do banco com os dados acima para compor o popup
                BDMapaInfo mapaInfo15 = new BDMapaInfo(getBaseContext());
                List<MapaInfo> list15 = mapaInfo15.buscarDetalhe(arg1, arg2);
                BDPrograma programInfo15 = new BDPrograma(getBaseContext());
                Programa mProgram15 = programInfo15.buscarPorId(arg5);
                arg3 = "A UNEB oferece cursos para turmas da Terceira Idade em " + list15.size() + " municípios baianos.";
                arg4 = "Atualmente, o Programa atende " + mProgram15.getProgramBenef() + " estudantes dos mais diversos cursos.";
                break;

        }
        //view.setImageResource(R.drawable.mapa1_multicampia);
        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
        view.setOnTouchListener(this);

        final Button btnOpenPopup = (Button) findViewById(R.id.openpopup);
        btnOpenPopup.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.mapa_popup, null);
                final PopupWindow popupWindow = new PopupWindow(popupView, Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);

                TextView tv1 = (TextView) popupView.findViewById(R.id.tv_pop1);
                TextView tv2 = (TextView) popupView.findViewById(R.id.tv_pop2);

                tv1.setText(arg3);
                tv2.setText(arg4);

                Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
                btnDismiss.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //resetZoom();
                        popupWindow.dismiss();

                    }
                });

                int tamWidth = getResources().getDisplayMetrics().widthPixels;
                int tamHeight = getResources().getDisplayMetrics().heightPixels;
                int posW = -tamWidth;
                int posH = -tamHeight + 120;
                popupWindow.showAsDropDown(btnOpenPopup, posW, posH);

            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ImageView view = (ImageView) v;
        view.setScaleType(ImageView.ScaleType.MATRIX);
        float scale;

        //dumpEvent(event);
        // Handle touch events here...

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:   // first finger down only
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                Log.d(TAG, "Tico mode=DRAG"); // write to LogCat
                mode = DRAG;
                break;

            case MotionEvent.ACTION_UP: // first finger lifted

            case MotionEvent.ACTION_POINTER_UP: // second finger lifted

                mode = NONE;
                Log.d(TAG, "Tico mode=NONE");
                break;

            case MotionEvent.ACTION_POINTER_DOWN: // first and second finger down

                oldDist = 0;
                Log.d(TAG, "oldDist=" + oldDist);
                if (oldDist > 5f) {
                    savedMatrix.set(matrix);
                   // midPoint(mid, event);
                    mode = ZOOM;
                    Log.d(TAG, "Tico mode=ZOOM");
                }
                break;

            case MotionEvent.ACTION_MOVE:

                if (mode == DRAG) {
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - start.x, event.getY() - start.y); // create the transformation in the matrix  of points
                } else if (mode == ZOOM) {
                    // pinch zooming
                    float newDist = 0;
                    Log.d(TAG, "newDist=" + newDist);
                    if (newDist > 5f) {
                        matrix.set(savedMatrix);
                        scale = newDist / oldDist; // setting the scaling of the
                        // matrix...if scale > 1 means
                        // zoom in...if scale < 1 means
                        // zoom out
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    } else {
                       // resetZoom();
                    }
                }
                break;
        }

        limitZoom(matrix);
        limitDrag(matrix);

        view.setImageMatrix(matrix); // display the transformation on screen

        return true; // indicate event was handled
    }

    private void limitDrag(Matrix m) {
        float[] values = new float[9];
        m.getValues(values);
        float transX = values[Matrix.MTRANS_X];
        float transY = values[Matrix.MTRANS_Y];
        float scaleX = values[Matrix.MSCALE_X];
        float scaleY = values[Matrix.MSCALE_Y];

        ImageView iv = (ImageView) findViewById(R.id.iv_mapa);
        Rect bounds = iv.getDrawable().getBounds();
        int viewWidth = getResources().getDisplayMetrics().widthPixels;
        int viewHeight = getResources().getDisplayMetrics().heightPixels;

        int width = bounds.right - bounds.left;
        int height = bounds.bottom - bounds.top;

        float minX = (-width + 20) * scaleX;
        float minY = (-height + 20) * scaleY;

        if (transX > (viewWidth - 20)) {
            transX = viewWidth - 20;
        } else if (transX < minX) {
            transX = minX;
        }

        if (transY > (viewHeight - 80)) {
            transY = viewHeight - 80;
        } else if (transY < minY) {
            transY = minY;
        }

        values[Matrix.MTRANS_X] = transX;
        values[Matrix.MTRANS_Y] = transY;
        m.setValues(values);
    }

    private void limitZoom(Matrix m) {

        float[] values = new float[9];
        m.getValues(values);
        float scaleX = values[Matrix.MSCALE_X];
        float scaleY = values[Matrix.MSCALE_Y];
        if (scaleX > MAX_ZOOM) {
            scaleX = MAX_ZOOM;
        } else if (scaleX < MIN_ZOOM) {
            scaleX = MIN_ZOOM;
        }

        if (scaleY > MAX_ZOOM) {
            scaleY = MAX_ZOOM;
        } else if (scaleY < MIN_ZOOM) {
            scaleY = MIN_ZOOM;
        }

        values[Matrix.MSCALE_X] = scaleX;
        values[Matrix.MSCALE_Y] = scaleY;
        m.setValues(values);
    }

    /*
     * --------------------------------------------------------------------------
     * Method: spacing Parameters: MotionEvent Returns: float Description:
     * checks the spacing between the two fingers on touch
     * ----------------------------------------------------
     */
/*
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);

        return  (float)Math.sqrt(x * x + y * y);

        //return FloatMath.sqrt(x * x + y * y);
    }
*/
    /*
     * --------------------------------------------------------------------------
     * Method: midPoint Parameters: PointF object, MotionEvent Returns: void
     * Description: calculates the midpoint between the two fingers
     * ------------------------------------------------------------
     */
/*
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }
*/
    /**
     * Show an event in the LogCat view, for debugging
     */
    /*
    private void dumpEvent(MotionEvent event) {
        String names[] = {"DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE", "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?"};
        StringBuilder sb = new StringBuilder();
        int action = event.getAction();
        int actionCode = action & MotionEvent.ACTION_MASK;
        sb.append("event ACTION_").append(names[actionCode]);

        if (actionCode == MotionEvent.ACTION_POINTER_DOWN || actionCode == MotionEvent.ACTION_POINTER_UP) {
            sb.append("(pid ").append(action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
            sb.append(")");
        }

        sb.append("[");
        for (int i = 0; i < event.getPointerCount(); i++) {
            sb.append("#").append(i);
            sb.append("(pid ").append(event.getPointerId(i));
            sb.append(")=").append((int) event.getX(i));
            sb.append(",").append((int) event.getY(i));
            if (i + 1 < event.getPointerCount())
                sb.append(";");
        }

        sb.append("]");
        Log.d("Touch Events ---------", sb.toString());

    }

    public void resetZoom() {
        mode = 0;
    }

*/
}
