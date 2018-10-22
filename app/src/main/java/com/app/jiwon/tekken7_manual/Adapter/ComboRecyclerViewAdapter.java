package com.app.jiwon.tekken7_manual.Adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.jiwon.tekken7_manual.Custom.RecyclerViewDividerDecoration;
import com.app.jiwon.tekken7_manual.Items.ComboRecyclerViewItem;
import com.app.jiwon.tekken7_manual.R;

import java.util.ArrayList;

public class ComboRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ComboRecyclerViewItem> list = new ArrayList<>();

    private Context context;
    private ComboListAdapter listAdapter = new ComboListAdapter();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_combo_recyclerview, parent, false);
        context = parent.getContext();

        return new ComboRecyclerViewAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ComboRecyclerViewAdapterHolder customHolder = (ComboRecyclerViewAdapterHolder) holder;
        ComboRecyclerViewItem item = list.get(position);
        RecyclerView.ItemDecoration dividerItemDecoration = new RecyclerViewDividerDecoration(ContextCompat.getDrawable(context, R.drawable.divider));

        if (item.getStarter().equals("벽꽝이후") || item.getStarter().contains("사원맵 바닥") || item.getStarter().contains("벽 바운드"))
            customHolder.textView.setText(item.getStarter());
        else
            customHolder.textView.setText("시동기 : " + item.getStarter());

        customHolder.recyclerView.setAdapter(listAdapter);
        customHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context));

        customHolder.recyclerView.addItemDecoration(dividerItemDecoration);

        listAdapter.addItem(item.getComboList());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(String starter, String[] comboList) {
        ComboRecyclerViewItem item = new ComboRecyclerViewItem();

        item.setStarter(starter);
        item.setComboList(comboList);

        list.add(item);
    }

    private class ComboRecyclerViewAdapterHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;

        public ComboRecyclerViewAdapterHolder(View view) {
            super(view);

            textView = view.findViewById(R.id.item_combo_recyclerView_textView);
            recyclerView = view.findViewById(R.id.item_combo_recyclerView_recyclerView);
        }
    }
}
