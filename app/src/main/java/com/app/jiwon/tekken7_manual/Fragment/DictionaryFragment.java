package com.app.jiwon.tekken7_manual.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.jiwon.tekken7_manual.Adapter.DictionaryRecyclerViewAdapter;
import com.app.jiwon.tekken7_manual.Custom.RecyclerViewMarginDecoration;
import com.app.jiwon.tekken7_manual.R;
import com.app.jiwon.tekken7_manual.Util.JsonReader;
import com.app.jiwon.tekken7_manual.Util.Value;
import com.app.jiwon.tekken7_manual.Util.ConvertPx;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DictionaryFragment extends Fragment {

    private Context context;
    public DictionaryRecyclerViewAdapter adapter = new DictionaryRecyclerViewAdapter();

    public DictionaryFragment() {
    }

    @BindView(R.id.fragment_dictionary_recyclerView)
    RecyclerView recyclerView;

    public static DictionaryFragment newInstance() {

        Bundle args = new Bundle();

        DictionaryFragment fragment = new DictionaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dictionary, container, false);
        context = view.getContext();
        ButterKnife.bind(this, view);

        JsonReader.DictionaryDbSet(null, "dictionary", adapter);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
        recyclerView.addItemDecoration(new RecyclerViewMarginDecoration(4, ConvertPx.dpToPx(Value.cardViewMargin), true));

        return view;
    }
}
