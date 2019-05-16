package com.sinapse.unebnoticias;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinapse.unebnoticias.adapters.RelAdapter;
import com.sinapse.unebnoticias.bancodados.Telefone;
import com.sinapse.unebnoticias.interfaces.RecyclerViewOnClickListenerHack;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ClaudioSouza on 16/09/2016.
 */
public class RelFragment extends Fragment implements RecyclerViewOnClickListenerHack {

    private RecyclerView mRecyclerView;
    private List<Telefone> mList;

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
                RelAdapter adapter = (RelAdapter) mRecyclerView.getAdapter();
                /*

                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Telefone> listAux = ((TelListActivity) getActivity()).getSetTelList();
                    if (!listAux.isEmpty()) {
                        for (int i = 0; i < listAux.size(); i++) {
                            adapter.addListItem(listAux.get(i), mList.size());
                        }
                    }else {

                    }
                }
                */
            }
        });
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        mList = ((TelListActivity)getActivity()).getSetTelList();
        RelAdapter adapter = new RelAdapter(getActivity(), mList);
        adapter.setRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClickListener(View view, int position) {

        String telefone = mList.get(position).getTel();
        if(telefone.contains("@")){
            /* Create the Intent */
            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

            /* Fill it with Data */
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{telefone});
            //emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
            //emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Text");

            /* Send it off to the Activity-Chooser */
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        }
        else {
            Uri uri = Uri.parse("tel:0" + telefone);
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(intent);
        }
    }
}
