package com.sinapse.unebnoticias.mapas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinapse.unebnoticias.MapasActivity;
import com.sinapse.unebnoticias.R;
import com.sinapse.unebnoticias.adapters.MapaAdapter;
import com.sinapse.unebnoticias.image.ZoomInZoomOut;
import com.sinapse.unebnoticias.interfaces.RecyclerViewOnClickListenerHack;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class MapaFragment extends Fragment implements RecyclerViewOnClickListenerHack {

    private RecyclerView mRecyclerView;
    private List<Mapa> mList;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                MapaAdapter adapter = (MapaAdapter) mRecyclerView.getAdapter();

                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Mapa> listAux = ((MapasActivity) getActivity()).getSetMapaList(0);

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mList.size());
                    }
                }
            }
        });


        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        mList = ((MapasActivity) getActivity()).getSetMapaList(13);
        MapaAdapter adapter = new MapaAdapter(getActivity(), mList);
        adapter.setRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClickListener(View view, int position) {

        int a = position +1;
        if (a == 1){

        }else {
            //Toast.makeText(getActivity(), "Position: "+a, Toast.LENGTH_SHORT).show();
            //Intent it = new Intent(getActivity(), ZoomInZoomOut.class);
            startMapaDetalhe(a);
        }
/*
        MapaAdapter adapter = (MapaAdapter) mRecyclerView.getAdapter();
        adapter.removeListItem(position);
        */
    }

    private void startMapaDetalhe(int pos){
        Bundle bundle = new Bundle();
        bundle.putSerializable("pos", pos);

        Intent intent = new Intent(getActivity(), ZoomInZoomOut.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
