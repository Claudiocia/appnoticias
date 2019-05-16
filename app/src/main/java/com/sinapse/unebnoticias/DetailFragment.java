package com.sinapse.unebnoticias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.sinapse.unebnoticias.parse.RSSFeed;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by ClaudioSouza on 17/09/2016.
 */
public class DetailFragment extends Fragment {

    private int fPos;
    RSSFeed fFeed;
    String html = null;
    String docFinal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fFeed = (RSSFeed)getArguments().getSerializable("feed");
        fPos = getArguments().getInt("pos");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);

        //Inicializando as views
        TextView title = (TextView)view.findViewById(R.id.title);
        WebView desc = (WebView)view.findViewById(R.id.desc);

        //configurar as propriedades do webview
        WebSettings ws = desc.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setUseWideViewPort(true);
        ws.setLoadWithOverviewMode(true);
        ws.setSupportZoom(true);
        ws.setBuiltInZoomControls(true);


        /*ws.setLightTouchEnabled(false);
		ws.setPluginState(PluginState.ON);
		ws.setJavaScriptEnabled(true);
		ws.setLoadWithOverviewMode(true);
		ws.setUseWideViewPort(true);
		ws.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		ws.setSupportZoom(true);
		ws.setBuiltInZoomControls(true);*/
        //Mozilla/5.0 (Linux; U; Android 2.0; pt-br; Droid Build/ESD20) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17
        ws.setUserAgentString("Mozilla/5.0 (Linux; U; Android 2.0; pt-br; Droid Build/ESD20) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17");
        // definir as views
        title.setText(fFeed.getItem(fPos).getTitle());
        desc.loadUrl(fFeed.getItem(fPos).getLink());
        //desc.loadData(docFinal, "text/html", "UTF-8");

        return view;

    }
}
