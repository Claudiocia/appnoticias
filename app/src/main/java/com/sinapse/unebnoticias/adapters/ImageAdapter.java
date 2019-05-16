package com.sinapse.unebnoticias.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.sinapse.unebnoticias.R;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private String TAG = "Tico";
    private int width;
    private int height;

    public ImageAdapter(Context c){
        mContext = c;
        width = (mContext.getResources().getDisplayMetrics().widthPixels /2 )- 10;
        height = width;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            Log.v(TAG, "Largura da tela é =" + width);
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(width, height));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 10, 8, 10);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.agenda, R.drawable.premios,
            R.drawable.cursos, R.drawable.telefone
    };
}
