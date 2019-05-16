package com.sinapse.unebnoticias.bancodados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class BDCurso {

    public static final String TAG = "Tico BD";
    private SQLiteDatabase bd;

    public BDCurso(Context context){
        super();
        BDPlunge auxBD = new BDPlunge(context);

        bd = auxBD.getWritableDatabase();

    }


    public void inserir(Curso curso) {
        ContentValues valores = new ContentValues();
        valores.put("campi_idcampus", curso.getCursoIdCampus() );
        valores.put("departamentos_iddept", curso.getCursoIdDept());
        valores.put("curso_cod", curso.getCursoCod());
        valores.put("curso_nome", curso.getCursoNome());
        valores.put("curso_nat", curso.getCursoNat());
        valores.put("curso_tipo", curso.getCursoTipo());
        valores.put("curso_turno", curso.getCursoTurno());
        valores.put("curso_sem", curso.getCursoSem());
        valores.put("curso_polo", curso.getCursoPolo());
        valores.put("curso_vagas", curso.getCursoVagas());

        bd.insert("cursos", null, valores);
    }

    public void atualiza(Curso curso) {
        ContentValues valores = new ContentValues();
        valores.put("campi_idcampus", curso.getCursoIdCampus() );
        valores.put("departamentos_iddept", curso.getCursoIdDept());
        valores.put("curso_cod", curso.getCursoCod());
        valores.put("curso_nome", curso.getCursoNome());
        valores.put("curso_nat", curso.getCursoNat());
        valores.put("curso_tipo", curso.getCursoTipo());
        valores.put("curso_turno", curso.getCursoTurno());
        valores.put("curso_sem", curso.getCursoSem());
        valores.put("curso_polo", curso.getCursoPolo());
        valores.put("curso_vagas", curso.getCursoVagas());

        bd.update("cursos", valores, "_idcurso = ?", new String[]{"" + curso.getIdCurso()});
    }

    public void deletar(Curso curso){
        bd.delete("cursos", "_idcurso =" + curso.getIdCurso(), null);
    }

    public List<Curso> buscarTodos(){
        List<Curso> list = new ArrayList<Curso>();
        String[] colunas = new String[]{"_idcurso", "campi_idcampus", "departamentos_iddept", "curso_cod", "curso_nome", "curso_nat",
                "curso_tipo", "curso_turno", "curso_sem", "curso_polo", "curso_vagas"};

        //Cursor cursor = bd.query("cursos", colunas, null, null, null, null, null);
        Cursor cursor = bd.query("cursos", colunas, null, null, null, null, "curso_nome ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Curso curso = new Curso();
                curso.setIdCurso(cursor.getInt(0));
                curso.setCursoIdCampus(cursor.getInt(1));
                curso.setCursoIdDept(cursor.getInt(2));
                curso.setCursoCod(cursor.getString(3));
                curso.setCursoNome(cursor.getString(4));

                String nat;
                if(cursor.getString(5).equalsIgnoreCase("bac")){
                    nat = "Bacharelado";
                }
                else if (cursor.getString(5).equalsIgnoreCase("lic")){
                    nat = "Licenciatura";
                }
                else{
                    nat = "Tecnólogo";
                }
                curso.setCursoNat(nat);

                String tipo;
                if(cursor.getString(6).equalsIgnoreCase("ead")){
                    tipo = "EAD";
                }
                else{
                    tipo = "Presencial";
                }
                curso.setCursoTipo(tipo);
                String turno;
                if (cursor.getString(7).equalsIgnoreCase("diurno")){
                    turno = "Diurno";
                }
                else if (cursor.getString(7).equalsIgnoreCase("diurno / mat")){
                    turno = "Diurno ou Matutino";
                }
                else if (cursor.getString(7).equalsIgnoreCase("diurno / notu")){
                    turno =  "Diurno ou Noturno";
                }
                else if (cursor.getString(7).equalsIgnoreCase("diurno / vesp")){
                    turno = "Diurno ou Vespertino";
                }
                else if (cursor.getString(7).equalsIgnoreCase("mat")){
                    turno =  "Matutino";
                }
                else if (cursor.getString(7).equalsIgnoreCase("mat / notu")){
                    turno = "Matutino ou Noturno";
                }
                else if (cursor.getString(7).equalsIgnoreCase("mat / vesp")){
                    turno = "Matutino ou Vespertino";
                }
                else if (cursor.getString(7).equalsIgnoreCase("mat / vesp / notu")){
                    turno = "Matutino ou Vespertino ou Noturno";
                }
                else if (cursor.getString(7).equalsIgnoreCase("notu")){
                    turno = "Noturno";
                }
                else if (cursor.getString(7).equalsIgnoreCase("vesp")){
                    turno = "Vespertino";
                }
                else if (cursor.getString(7).equalsIgnoreCase("vesp / notu")){
                    turno = "Vespertino ou Noturno";
                }
                else{
                    turno = "Cursos EaD não tem turno";
                }
                curso.setCursoTurno(turno);

                String sem;
                String tico = cursor.getString(8);
                //Log.v(TAG, "o que vem do banco é: "+ tico);
                if (tico.equalsIgnoreCase("1")){
                    sem = "1º Semestre";
                }
                else if (tico.equalsIgnoreCase("2")){
                    sem = "2º Semestre";
                }
                else {
                    sem = "1º e 2º Semestres";
                }
                curso.setCursoSem(sem);

                curso.setCursoPolo(cursor.getString(9));
                curso.setCursoVagas(cursor.getString(10));

                list.add(curso);

            }while (cursor.moveToNext());
        }

        if (cursor != null){
            cursor.close();
        }
        return (list);
    }

    public List<Curso> contarTodos(){
        List<Curso> list = new ArrayList<Curso>();
        String[] colunas = new String[]{"curso_nome"};
        String[] arg = new String[]{"pres"};

        //"_idcurso = ?", new String[]{"" + curso.getIdCurso()}

        Cursor cursor = bd.query(false, "cursos", colunas, "curso_tipo = ?", arg, null, null, null, null, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Curso curso = new Curso();
                curso.setCursoNome(cursor.getString(0));
                list.add(curso);

            }while (cursor.moveToNext());
        }

        if (cursor != null){
            cursor.close();
        }

        return (list);
    }

    public List<Curso> searchCurso(String search){
        List<Curso> slist = new ArrayList<Curso>();

        String[] colunas = new String[]{"_idcurso", "campi_idcampus", "departamentos_iddept", "curso_cod", "curso_nome", "curso_nat",
                "curso_tipo", "curso_turno", "curso_sem", "curso_polo", "curso_vagas"};

        Cursor cursor = bd.rawQuery("SELECT * FROM cursos WHERE curso_cod LIKE '%" + search + "%' OR curso_nome LIKE '%" + search + "%' OR curso_nat LIKE '%" + search
                + "%' OR curso_tipo LIKE '%" + search + "%' OR curso_polo LIKE '%" + search + "%' ORDER BY curso_nome ", null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Curso curso = new Curso();
                curso.setIdCurso(cursor.getInt(0));
                curso.setCursoIdCampus(cursor.getInt(1));
                curso.setCursoIdDept(cursor.getInt(2));
                curso.setCursoCod(cursor.getString(3));
                curso.setCursoNome(cursor.getString(4));

                String nat;
                if(cursor.getString(5).equalsIgnoreCase("bac")){
                    nat = "Bacharelado";
                }
                else if (cursor.getString(5).equalsIgnoreCase("lic")){
                    nat = "Licenciatura";
                }
                else{
                    nat = "Tecnólogo";
                }
                curso.setCursoNat(nat);

                String tipo;
                if(cursor.getString(6).equalsIgnoreCase("ead")){
                    tipo = "EaD";
                }
                else{
                    tipo = "Presencial";
                }
                curso.setCursoTipo(tipo);
                curso.setCursoTurno(cursor.getString(7));
                curso.setCursoSem(cursor.getString(8));
                curso.setCursoPolo(cursor.getString(9));
                curso.setCursoVagas(cursor.getString(10));

                slist.add(curso);

            }while (cursor.moveToNext());
        }

        return (slist);
    }
}
