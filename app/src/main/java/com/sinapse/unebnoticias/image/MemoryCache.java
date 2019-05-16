package com.sinapse.unebnoticias.image;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class MemoryCache {
    private static final String TAG = "MemoryCache";
    //Ultimo argumento verdadeiro para LRU ordenaçao
    private Map<String, Bitmap> cache = Collections.synchronizedMap(new LinkedHashMap<String, Bitmap>(10, 1.5f, true));
    private long size = 0; // atual tamanho alocado para o cache
    private long limit = 1000000;//maximo de memoria em bytes

    public MemoryCache(){
        //usar 25% do tamanho da memoria disponível
        setLimit(Runtime.getRuntime().maxMemory() / 4);
    }

    public void setLimit(long new_limit) {
        limit = new_limit;

        Log.i(TAG, "MemoryCache vai usar " + limit / 1024. / 1024. + "MB");
    }

    public Bitmap get(String id){
        try {
            if(!cache.containsKey(id))
                return null;
            return cache.get(id);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void put(String id, Bitmap bitmap){
        try {
            if(cache.containsKey(id))
                size -= getSizeInBytes(cache.get(id));
            cache.put(id, bitmap);
            size += getSizeInBytes(bitmap);
            checkSize();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    long getSizeInBytes(Bitmap bitmap) {
        if(bitmap == null)
            return 0;
        return bitmap.getRowBytes() * bitmap.getHeight();

    }

    private void checkSize() {
        Log.i(TAG, "tamanho do cache=" + size + " comprimento=" + cache.size());
        if (size > limit) {
            Iterator<Map.Entry<String, Bitmap>> iter = cache.entrySet().iterator();

            while (iter.hasNext()) {
                Map.Entry<String, Bitmap> entry = iter.next();
                size -= getSizeInBytes(entry.getValue());
                iter.remove();
                if(size <= limit)
                    break;
            }
            Log.i(TAG, "cache limpo. Novo tamanho " + cache.size());
        }
    }

    public void clear(){
        try {
            cache.clear();
            size = 0;
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
}
