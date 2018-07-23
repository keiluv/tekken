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

import com.app.jiwon.tekken7_manual.Adapter.ComboRecyclerViewAdapter;
import com.app.jiwon.tekken7_manual.Custom.RecyclerViewMarginDecoration;
import com.app.jiwon.tekken7_manual.R;
import com.app.jiwon.tekken7_manual.Util.ConvertPx;
import com.app.jiwon.tekken7_manual.Util.JsonReader;
import com.app.jiwon.tekken7_manual.Util.Value;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComboFragment extends Fragment {

    private Context context;
    private String characterName;

    private ComboRecyclerViewAdapter adapter = new ComboRecyclerViewAdapter();

    public ComboFragment() {
    }

    @BindView(R.id.fragment_combo_recyclerView)
    RecyclerView recyclerView;

    public static ComboFragment newInstance(String characterName) {

        Bundle args = new Bundle();

        ComboFragment fragment = new ComboFragment();
        fragment.setArguments(args);
        args.putString("characterName", characterName);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_combo, container, false);
        context = view.getContext();
        ButterKnife.bind(this, view);

        this.characterName = getArguments().getString("characterName");

        JsonReader.ComboDbSet(characterName, "combo", adapter);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new RecyclerViewMarginDecoration(1, ConvertPx.dpToPx(Value.cardViewMargin), true));

        return view;
    }
}
