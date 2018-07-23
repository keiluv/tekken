package com.app.jiwon.tekken7_manual.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.jiwon.tekken7_manual.R;

public class ManualFragment extends Fragment {

    public ManualFragment() {
    }

    public static ManualFragment newInstance() {

        Bundle args = new Bundle();

        ManualFragment fragment = new ManualFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_manual, container, false);

        return view;
    }
}
