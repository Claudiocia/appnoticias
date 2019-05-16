package com.sinapse.unebnoticias.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinapse.unebnoticias.R;
import com.sinapse.unebnoticias.bancodados.Telefone;
import com.sinapse.unebnoticias.interfaces.RecyclerViewOnClickListenerHack;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class RelAdapter extends RecyclerView.Adapter<RelAdapter.MyViewHolder> {

    private List<Telefone> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public RelAdapter(Context c, List<Telefone> l){
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_list_rel, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        if(mList.get(position).getTel().equalsIgnoreCase("Aqui vai a data")){

        }else {
            myViewHolder.tvRel.setText(mList.get(position).getTel());
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r){
        mRecyclerViewOnClickListenerHack = r;
    }

    public void addListItem(Telefone u, int position){
        mList.add(u);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvRel;


        public MyViewHolder(View itemView) {
            super(itemView);

            tvRel = (TextView) itemView.findViewById(R.id.tel_rel);

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
