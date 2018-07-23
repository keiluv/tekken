package com.app.jiwon.tekken7_manual.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.jiwon.tekken7_manual.Items.ShortSutsRecyclerViewItem;
import com.app.jiwon.tekken7_manual.R;
import com.app.jiwon.tekken7_manual.Util.Value;

import java.util.ArrayList;
import java.util.HashMap;

public class ShortCutsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<ShortSutsRecyclerViewItem> list;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shortcuts_recyclerview, parent, false);
        context = view.getContext();

        return new ShortCutsRecyclerViewAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ShortCutsRecyclerViewAdapterHolder customHolder = (ShortCutsRecyclerViewAdapterHolder) holder;
        ShortSutsRecyclerViewItem item = list.get(position);

        customHolder.imageView.setImageResource(item.getImage());
        customHolder.name.setText(item.getName());
        customHolder.type.setText(item.getType());

        customHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(position).getLink()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ShortCutsRecyclerViewAdapterHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView type;
        ImageView imageView;
        public ShortCutsRecyclerViewAdapterHolder(View view) {
            super(view);

            name = view.findViewById(R.id.item_shortCuts_recyclerView_textView_name);
            type = view.findViewById(R.id.item_shortCuts_recyclerView_textView_type);
            imageView = view.findViewById(R.id.item_shortCuts_recyclerView_imageView);
        }
    }

    public void addItem(ArrayList<ShortSutsRecyclerViewItem> list){
        this.list = list;
    }
}
