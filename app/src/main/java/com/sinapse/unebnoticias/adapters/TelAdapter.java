package com.sinapse.unebnoticias.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinapse.unebnoticias.R;
import com.sinapse.unebnoticias.bancodados.Unidade;
import com.sinapse.unebnoticias.interfaces.RecyclerViewOnClickListenerHack;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class TelAdapter extends RecyclerView.Adapter<TelAdapter.MyViewHolder> {

    private List<Unidade> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public TelAdapter(Context c, List<Unidade> l){
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_list_tel, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.tvNome.setText(mList.get(position).getUnidSigla()+" - " +mList.get(position).getUnidNome() );
        myViewHolder.tvTitu.setText(mList.get(position).getUnidTit());
        myViewHolder.tvTele.setText(mList.get(position).getUnidTel());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r){
        mRecyclerViewOnClickListenerHack = r;
    }

    public void addListItem(Unidade u, int position){
        mList.add(u);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvNome;
        public TextView tvTitu;
        public TextView tvTele;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvNome = (TextView) itemView.findViewById(R.id.unid_tel);
            tvTitu = (TextView) itemView.findViewById(R.id.titular_tel);
            tvTele = (TextView) itemView.findViewById(R.id.telefone);

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
