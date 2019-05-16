package com.sinapse.unebnoticias.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class DOMParsePremios {



    private RSSFeed _feed = new RSSFeed();

    public RSSFeed parsePremios(String html) {


        try {
            //criando as instancias necessárias
            Document doc = Jsoup.connect(html).get();
            doc.normalise();

            ArrayList<Elements> listaElem = new ArrayList<Elements>();

            Elements novoDoc = doc.getElementsByClass("entry");
            int length2 = novoDoc.size();

            for (int i = 0; i < length2; i++) {
                listaElem.add(novoDoc.get(i).getElementsByClass("entry"));
            }

            int length = listaElem.size();

            for (int i = 0; i < length; i++) {

                RSSItem _item = new RSSItem();
                String imagem, titulo, link, img;

                titulo   = listaElem.get(i).select("h3").text().toString();
                _item.setTitle(titulo);

                link     = listaElem.get(i).select("a").attr("href").toString();
                _item.setLink(link);

                img      = listaElem.get(i).select("img").attr("src").toString();

                if( !img.isEmpty() ){
                    imagem = img;
                    _item.setImage(imagem);
                }
                else  {
                    imagem = "Sem imagem";
                    _item.setImage("");
                }

                // adiciona o item na lista
                _feed.addItem(_item);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return _feed;

    }
}
