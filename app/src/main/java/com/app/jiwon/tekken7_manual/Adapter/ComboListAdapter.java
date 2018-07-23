package com.app.jiwon.tekken7_manual.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.jiwon.tekken7_manual.R;

import java.util.ArrayList;

public class ComboListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String[] list = null;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_combo_list, parent, false);

        return new ComboListAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ComboListAdapterHolder customHolder = (ComboListAdapterHolder) holder;

        customHolder.textView.setText(list[position]);
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public void addItem(String[] comboList) {
        this.list = comboList;
    }

    private class ComboListAdapterHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ComboListAdapterHolder(View view) {
            super(view);

            textView = view.findViewById(R.id.item_combo_list_textView);
        }
    }
}
