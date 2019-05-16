package com.sinapse.unebnoticias.image;

import android.content.Context;

import java.io.File;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class FileCache {

    private File cacheDir;

    public FileCache(Context context) {
        // procurar o diretoria para salvar o cache das imagens com as informações recuperadas
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), "UNEBNoticias");
        } else {
            cacheDir = context.getCacheDir();
        }
        if(!cacheDir.exists()){
            cacheDir.mkdirs();
        }
    }

    public File getFile(String url){
        //O tutorial deu como opção para identificar as imagens geradas com as informações
        // via hashcode
        String filename = String.valueOf(url.hashCode());
        File f = new File(cacheDir, filename);
        return f;
    }

    public void clear(){
        File[] files = cacheDir.listFiles();
        if (files == null)
            return;
        for (File f : files)
            f.delete();
    }
}
