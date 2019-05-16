package com.sinapse.unebnoticias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinapse.unebnoticias.adapters.CursoAdapter;
import com.sinapse.unebnoticias.bancodados.Curso;
import com.sinapse.unebnoticias.interfaces.RecyclerViewOnClickListenerHack;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ClaudioSouza on 17/09/2016.
 */
public class CursosFragment extends Fragment implements RecyclerViewOnClickListenerHack {

    private RecyclerView mRecyclerView;
    private List<Curso> mList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cursos, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list_cursos);
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
                CursoAdapter adapter = (CursoAdapter) mRecyclerView.getAdapter();

                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Curso> listAux = ((CursosActivity) getActivity()).getSetCursosList(12, llm.findLastCompletelyVisibleItemPosition() + 1 );
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

        mList = ((CursosActivity)getActivity()).getSetCursosList(13, 0);
        CursoAdapter adapter = new CursoAdapter(getActivity(), mList);
        adapter.setRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClickListener(View view, int position) {
        //Toast.makeText(getActivity(), "Voce clicou no item número " + position, Toast.LENGTH_SHORT).show();
        //Aqui posso trabalhar o clique no curso tipo abrir uma página com detalhes sobre os cursos
        /*
        String telefone = mList.get(position).getUnidTel().toString();
        Uri uri = Uri.parse("tel:0" + telefone);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
        */
    }
}
