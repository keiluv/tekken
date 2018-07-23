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
import android.widget.Toast;

import com.app.jiwon.tekken7_manual.Adapter.SelectRecyclerViewAdapter;
import com.app.jiwon.tekken7_manual.Custom.RecyclerViewMarginDecoration;
import com.app.jiwon.tekken7_manual.Items.SelectRecyclerViewItem;
import com.app.jiwon.tekken7_manual.R;
import com.app.jiwon.tekken7_manual.Util.Value;
import com.app.jiwon.tekken7_manual.Util.ConvertPx;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectFragment extends Fragment {

    private Context context;
    public SelectRecyclerViewAdapter adapter = new SelectRecyclerViewAdapter();

    @BindView(R.id.fragment_select_recyclerView)
    RecyclerView recyclerView;

    public SelectFragment() {
    }

    public static SelectFragment newInstance() {

        Bundle args = new Bundle();

        SelectFragment fragment = new SelectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select, container, false);
        context = view.getContext();
        ButterKnife.bind(this, view);

        adapter.setItem(Value.getPortraitImages(context));

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerView.addItemDecoration(new RecyclerViewMarginDecoration(3, ConvertPx.dpToPx(Value.cardViewMargin), true));

        return view;
    }

    public void favoriteChange() {
        ArrayList<SelectRecyclerViewItem> temp = Value.getPortraitImages(context);

        for (int i = temp.size() - 1; i >= 0; i--) {
            if (!temp.get(i).isFavorite()) {
                adapter.deleteItem(i);
                adapter.notifyItemRemoved(i);
            }
        }

        adapter.notifyItemRangeChanged(0, adapter.getItemCount());

        if (adapter.list.isEmpty())
            Toast.makeText(context, R.string.selectFragment_alart_message, Toast.LENGTH_SHORT).show();
    }

    public void favoriteBack() {
        adapter.setItem(Value.getPortraitImages(context));
        adapter.notifyItemRangeChanged(0, adapter.getItemCount());
    }
}
