package com.sinapse.unebnoticias;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinapse.unebnoticias.adapters.TelAdapter;
import com.sinapse.unebnoticias.bancodados.Unidade;
import com.sinapse.unebnoticias.interfaces.RecyclerViewOnClickListenerHack;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ClaudioSouza on 16/09/2016.
 */
public class TelFragment extends Fragment implements RecyclerViewOnClickListenerHack {

    private RecyclerView mRecyclerView;
    private List<Unidade> mList;

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tel, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list_tel);
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
                TelAdapter adapter = (TelAdapter) mRecyclerView.getAdapter();


                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Unidade> listAux = ((TelefoneActivity) getActivity()).getSetUnidList(13, llm.findLastCompletelyVisibleItemPosition() + 1);
                    if (!listAux.isEmpty()) {
                        for (int i = 0; i < listAux.size(); i++) {
                            adapter.addListItem(listAux.get(i), mList.size());
                        }
                    }else {

                    }
                }

            }
        });
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        mList = ((TelefoneActivity)getActivity()).getSetUnidList(13, 0);
        TelAdapter adapter = new TelAdapter(getActivity(), mList);
        adapter.setRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClickListener(View view, int position) {

        int x = mList.get(position).getIdUnid();
        //Toast.makeText(getActivity(), "Voce clicou no item número "+ position + " ID nº: "+ x, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getActivity(), TelListActivity.class);
        intent.putExtra("pos", x);

        startActivity(intent);

        /*
        String telefone = mList.get(position).getUnidTel().toString();
        Uri uri = Uri.parse("tel:0" + telefone);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
        */
    }
}
