package com.sinapse.unebnoticias.parse;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class DOMParseHtml {


    private RSSFeed _feed = new RSSFeed();

    public RSSFeed parseHtml(String html) {


        //for (int pg = 1; pg <= 3; pg++) {

            String fHtml = html; // + pg + "/";

            try {
                //criando as instancias necessárias
                Document doc = Jsoup.connect(fHtml).get();
                doc.normalise();


                ArrayList<Elements> listaElem = new ArrayList<Elements>();

                Elements novoDoc = doc.select("li.media");
                //Elements novoDoc = doc.getAllElements();

                int length2 = novoDoc.size();

                Log.d("claudio", "o doc recebido é: /n"+ novoDoc.html());

                for (int i = 0; i < length2; i++) {
                    listaElem.add(novoDoc.get(i).getElementsByClass("media"));
                }

                int length = listaElem.size();

                for (int i = 0; i < length; i++) {

                    RSSItem _item = new RSSItem();
                    String imagem, data, titulo, conteudo, link, img;

                    data = listaElem.get(i).select("cite").text().toString();
                    _item.setDate(data);

                    titulo = listaElem.get(i).select("h5").text().toString();
                    _item.setTitle(titulo);

                    link = listaElem.get(i).select("a").attr("href").toString();
                    _item.setLink(link);

                    conteudo = listaElem.get(i).select("p").text().toString();
                    _item.setDescription(conteudo);

                    img = listaElem.get(i).select("img").attr("src").toString();
                    Log.d("claudio", "o que vem na tag img é: " +img);

                    if (!img.isEmpty()) {
                        imagem = img;
                        _item.setImage(imagem);
                    } else {
                        imagem = "Sem imagem";
                        _item.setImage("");
                    }

                    // adiciona o item na lista
                    _feed.addItem(_item);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        //}
        return _feed;
    }
}
