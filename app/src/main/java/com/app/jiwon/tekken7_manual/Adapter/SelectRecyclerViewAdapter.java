package com.app.jiwon.tekken7_manual.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.jiwon.tekken7_manual.Activity.CharacterActivity;
import com.app.jiwon.tekken7_manual.Items.SelectRecyclerViewItem;
import com.app.jiwon.tekken7_manual.R;

import java.util.ArrayList;
import java.util.Locale;

public class SelectRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    public ArrayList<SelectRecyclerViewItem> list;
    private ArrayList<SelectRecyclerViewItem> saveList;

    private boolean isFirst = true;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_recyclerview, parent, false);
        context = parent.getContext();

        return new SelectRecyclerViewAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        SelectRecyclerViewAdapterHolder customHolder = (SelectRecyclerViewAdapterHolder) holder;

        final SelectRecyclerViewItem item = list.get(position);

        customHolder.imageView.setImageResource(item.getImage());
        customHolder.textView.setText(item.getKoreanName());

        customHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CharacterActivity.class);
                intent.putExtra("characterName", item.getEnglishName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class SelectRecyclerViewAdapterHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView imageView;
        TextView textView;

        public SelectRecyclerViewAdapterHolder(View view) {
            super(view);

            cardView = view.findViewById(R.id.item_select_recyclerView_cardView);
            imageView = view.findViewById(R.id.item_select_recyclerView_cardView_imageView);
            textView = view.findViewById(R.id.item_select_recyclerView_cardView_textView);
        }
    }

    public void fillter(String searchText) {
        searchText = searchText.toLowerCase(Locale.getDefault());
        list.clear();

        if (searchText.length() == 0) {
            list = new ArrayList<>(saveList);
        } else {
            for (SelectRecyclerViewItem item : saveList) {

                if (item.getEnglishName().toLowerCase().contains(searchText) ||
                        item.getKoreanName().contains(searchText)) {

                    list.add(item);
                }
            }
        }

        notifyDataSetChanged();
    }

    public void listChange() {
        list = new ArrayList<>(saveList);
        notifyDataSetChanged();
    }

    public void setItem(ArrayList list) {
        this.list = new ArrayList<>(list);

        if (isFirst) {
            this.saveList = new ArrayList<>(list);
            isFirst = !isFirst;
        }
    }

    public void deleteItem(int position) {
        if (!list.isEmpty()) {
            this.list.remove(position);
        }
    }
}
