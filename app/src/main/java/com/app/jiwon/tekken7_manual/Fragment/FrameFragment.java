package com.app.jiwon.tekken7_manual.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.app.jiwon.tekken7_manual.Adapter.FrameRecyclerViewAdapter;
import com.app.jiwon.tekken7_manual.R;
import com.app.jiwon.tekken7_manual.Util.JsonReader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FrameFragment extends Fragment {

    private Context context;
    private String characterName;
    public FrameRecyclerViewAdapter adapter = new FrameRecyclerViewAdapter();

    public FrameFragment() {
    }

    @BindView(R.id.fragment_frame_recyclerView)
    RecyclerView recyclerView;

    public static FrameFragment newInstance(String characterName) {

        Bundle args = new Bundle();

        FrameFragment fragment = new FrameFragment();
        fragment.setArguments(args);
        args.putString("characterName", characterName);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frame, container, false);
        context = view.getContext();
        ButterKnife.bind(this, view);

        this.characterName = getArguments().getString("characterName");

        JsonReader.frameDbSet(characterName, "frame", adapter);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }
}
