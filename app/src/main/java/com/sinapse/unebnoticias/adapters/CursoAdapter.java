package com.sinapse.unebnoticias.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinapse.unebnoticias.R;
import com.sinapse.unebnoticias.bancodados.BDCampus;
import com.sinapse.unebnoticias.bancodados.Curso;
import com.sinapse.unebnoticias.interfaces.RecyclerViewOnClickListenerHack;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.MyViewHolder> {

    private List<Curso> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
    private BDCampus bdCampus;

    public CursoAdapter(Context c, List<Curso> l){
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_list_curso, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.tvCurso.setText(mList.get(position).getCursoNome());
        myViewHolder.tvDept.setText(mList.get(position).getNomeDept());
        myViewHolder.tvCampus.setText(mList.get(position).getNomeCampus());
        myViewHolder.tvTipo.setText(mList.get(position).getCursoNat()+" - " +mList.get(position).getCursoTipo());
        myViewHolder.tvTurno.setText(mList.get(position).getCursoSem()+ " - " + mList.get(position).getCursoTurno());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r){
        mRecyclerViewOnClickListenerHack = r;
    }

    public void addListItem(Curso c, int position){
        mList.add(c);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvCurso;
        public TextView tvDept;
        public TextView tvCampus;
        public TextView tvTipo;
        public TextView tvTurno;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvCurso = (TextView) itemView.findViewById(R.id.title_c);
            tvDept = (TextView) itemView.findViewById(R.id.depart);
            tvCampus = (TextView) itemView.findViewById(R.id.campus);
            tvTipo = (TextView) itemView.findViewById(R.id.tipo);
            tvTurno = (TextView) itemView.findViewById(R.id.turno);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mRecyclerViewOnClickListenerHack != null){
                mRecyclerViewOnClickListenerHack.onClickListener(v, getPosition());
            }

        }
    }
}
