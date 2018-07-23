package com.app.jiwon.tekken7_manual.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.jiwon.tekken7_manual.Adapter.ShortCutsRecyclerViewAdapter;
import com.app.jiwon.tekken7_manual.Custom.RecyclerViewMarginDecoration;
import com.app.jiwon.tekken7_manual.R;
import com.app.jiwon.tekken7_manual.Util.Value;
import com.app.jiwon.tekken7_manual.Util.ConvertPx;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShortCutsFragment extends Fragment {

    private Context context;
    private ShortCutsRecyclerViewAdapter shortCutsRecyclerViewAdapter;

    @BindView(R.id.fragment_shortCuts_RecyclerView)
    RecyclerView recyclerView;

    public ShortCutsFragment() {
    }

    public static ShortCutsFragment newInstance() {

        Bundle args = new Bundle();

        ShortCutsFragment fragment = new ShortCutsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shortcuts, container, false);
        context = inflater.getContext();
        ButterKnife.bind(this, view);

        shortCutsRecyclerViewAdapter = new ShortCutsRecyclerViewAdapter();
        shortCutsRecyclerViewAdapter.addItem(Value.getShortCutsImages(context));

        recyclerView.setAdapter(shortCutsRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new RecyclerViewMarginDecoration(1, ConvertPx.dpToPx(Value.cardViewMargin), true));

        return view;
    }
}
