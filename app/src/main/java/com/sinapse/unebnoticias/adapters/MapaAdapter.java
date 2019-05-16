package com.sinapse.unebnoticias.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinapse.unebnoticias.R;
import com.sinapse.unebnoticias.extras.ImageHelper;
import com.sinapse.unebnoticias.interfaces.RecyclerViewOnClickListenerHack;
import com.sinapse.unebnoticias.mapas.Mapa;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class MapaAdapter extends RecyclerView.Adapter<MapaAdapter.MyViewHolder> {
    private Context mContext;
    private List<Mapa> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
    private float scale;
    private int width;
    private int height;

    public MapaAdapter(Context c, List<Mapa> l){
        mContext = c;
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        scale = mContext.getResources().getDisplayMetrics().density;
        width = mContext.getResources().getDisplayMetrics().widthPixels - (int)(14 * scale + 0.5f);
        height = (width / 16) * 14;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = mLayoutInflater.inflate(R.layout.item_mapa_card, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myholder, int position) {

        myholder.tvTitulo.setText(mList.get(position).getNomeMapa());
        myholder.tvDescri.setText(mList.get(position).getDescrMapa());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            myholder.ivMapa.setImageResource(mList.get(position).getIdMapa());

        }
        else{
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), mList.get(position).getIdMapa());
            bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);

            bitmap = ImageHelper.getRoundedCornerBitmap(mContext, bitmap, 10, width, height, false, false, true, true);
            myholder.ivMapa.setImageBitmap(bitmap);
        }

    }

    @Override
    public int getItemCount() {

        return mList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r){
        mRecyclerViewOnClickListenerHack = r;
    }

    public void addListItem(Mapa m, int position){
        mList.add(m);
        notifyItemInserted(position);
    }

    public void removeListItem(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView ivMapa;
        public TextView tvTitulo;
        public TextView tvDescri;

        public MyViewHolder(View itemView){
            super(itemView);

            ivMapa = (ImageView) itemView.findViewById(R.id.iv_mapa);
            tvTitulo = (TextView) itemView.findViewById(R.id.tv_titMapa);
            tvDescri = (TextView) itemView.findViewById(R.id.tv_descrMapa);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mRecyclerViewOnClickListenerHack != null){
                mRecyclerViewOnClickListenerHack.onClickListener(v, getPosition());
            }

        }

    }
}