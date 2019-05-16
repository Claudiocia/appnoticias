package com.sinapse.unebnoticias.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinapse.unebnoticias.R;
import com.sinapse.unebnoticias.image.ImageLoader;
import com.sinapse.unebnoticias.parse.RSSFeed;
import com.sinapse.unebnoticias.parse.RSSItem;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    RSSFeed mList;
    private LayoutInflater mLayoutInflater;
    public ImageLoader imageLoader;


    public ListAdapter(Context c, RSSFeed l){
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = new ImageLoader(c.getApplicationContext());
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_list, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int pos) {
        ImageView ivItem;
        ivItem = null;

        imageLoader.DisplayImage(mList.getItem(pos).getImage(), ivItem);
        myViewHolder.tvTitle.setText(mList.getItem(pos).getTitle());
        myViewHolder.tvData.setText(mList.getItem(pos).getDate());

    }

    @Override
    public int getItemCount() {
        return mList.getItemCount();
    }

    public void addListItem(RSSItem f, int pos){
        mList.addItem(f);
        notifyItemInserted(pos);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView ivItem;
        public TextView tvTitle;
        public TextView tvData;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivItem = (ImageView) itemView.findViewById(R.id.thumb);
            tvTitle = (TextView) itemView.findViewById(R.id.title);
            tvData = (TextView) itemView.findViewById(R.id.date);

        }
    }
}
