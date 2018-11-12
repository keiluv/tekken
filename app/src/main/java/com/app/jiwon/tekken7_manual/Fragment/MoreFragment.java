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

import com.app.jiwon.tekken7_manual.Adapter.MoreRecyclerViewAdapter;
import com.app.jiwon.tekken7_manual.Custom.RecyclerViewMarginDecoration;
import com.app.jiwon.tekken7_manual.R;
import com.app.jiwon.tekken7_manual.Util.JsonReader;
import com.app.jiwon.tekken7_manual.Util.Value;
import com.app.jiwon.tekken7_manual.Util.ConvertPx;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreFragment extends Fragment {

    private Context context;
    private MoreRecyclerViewAdapter adapter = new MoreRecyclerViewAdapter();

    @BindView(R.id.fragment_patchNote_recyclerView)
    RecyclerView recyclerView;

    public static MoreFragment newInstance() {

        Bundle args = new Bundle();

        MoreFragment fragment = new MoreFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        context = inflater.getContext();
        ButterKnife.bind(this, view);

        JsonReader.PatchnoteSet(null, "patchnote", adapter);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerView.addItemDecoration(new RecyclerViewMarginDecoration(2, ConvertPx.dpToPx(Value.cardViewMargin), true));

        return view;
    }

}
