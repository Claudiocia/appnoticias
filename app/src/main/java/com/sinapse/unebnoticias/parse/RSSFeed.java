package com.sinapse.unebnoticias.parse;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class RSSFeed implements Serializable {

    private static final long serialVersionUID = 1L;
    private int _itemcount = 0;
    private List<RSSItem> _itemlist;

    RSSFeed(){
        _itemlist = new Vector<RSSItem>(0);
    }

    public void addItem(RSSItem item){
        _itemlist.add(item);
        _itemcount++;
    }

    public RSSItem getItem(int location) {
        return _itemlist.get(location);
    }

    public int getItemCount() {
        return _itemcount;
    }
}
