package com.app.jiwon.tekken7_manual.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.jiwon.tekken7_manual.Fragment.PopupFragment;
import com.app.jiwon.tekken7_manual.Items.DictionaryRecyclerViewItem;
import com.app.jiwon.tekken7_manual.R;

import java.util.ArrayList;
import java.util.Locale;

public class DictionaryRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<DictionaryRecyclerViewItem> list = new ArrayList();
    private ArrayList<DictionaryRecyclerViewItem> saveList = new ArrayList();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dictionary_recyclerview, parent, false);
        context = parent.getContext();

        return new DictionaryRecyclerViewAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DictionaryRecyclerViewAdapterHolder customHolder = (DictionaryRecyclerViewAdapterHolder) holder;
        final DictionaryRecyclerViewItem item = list.get(position);

        customHolder.textView.setText(item.getSkillName());
        customHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupFragment popup = new PopupFragment(item.getSkillName(), item.getExplanation());
                popup.show(((FragmentActivity) context).getSupportFragmentManager().beginTransaction(), popup.FRAGMENT_TAG);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class DictionaryRecyclerViewAdapterHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public DictionaryRecyclerViewAdapterHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.item_dictionary_recyclerView_textView);
        }
    }

    public void addItem(String name, int number, String explanation) {
        DictionaryRecyclerViewItem item = new DictionaryRecyclerViewItem();
        item.setSkillName(name);
        item.setNumber(number);
        item.setExplanation(explanation);

        list.add(item);
        saveList.add(item);
    }

    public void fillter(String searchText) {
        searchText = searchText.toLowerCase(Locale.getDefault());
        list.clear();

        if (searchText.length() == 0) {
            list.addAll(saveList);
        } else {
            for (DictionaryRecyclerViewItem item : saveList) {

                if (item.getSkillName().toLowerCase().contains(searchText)) {
                    list.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
