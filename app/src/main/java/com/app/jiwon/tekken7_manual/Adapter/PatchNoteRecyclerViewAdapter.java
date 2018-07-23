package com.app.jiwon.tekken7_manual.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.jiwon.tekken7_manual.Fragment.PopupFragment;
import com.app.jiwon.tekken7_manual.Items.PatchNoteRecyclerViewItem;
import com.app.jiwon.tekken7_manual.R;

import java.util.ArrayList;

public class PatchNoteRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private ArrayList<PatchNoteRecyclerViewItem> list = new ArrayList();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patchnote_recyclerview, parent, false);
        context = view.getContext();

        return new PatchNoteRecyclerViewAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PatchNoteRecyclerViewAdapterHolder customHolder = (PatchNoteRecyclerViewAdapterHolder) holder;
        final PatchNoteRecyclerViewItem item = list.get(position);

        customHolder.mVersion.setText(item.getVersion());
        customHolder.mDate.setText(item.getDate());

        customHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupFragment popup = new PopupFragment(item.getDate(), item.getContents());
                popup.show(((FragmentActivity) context).getSupportFragmentManager().beginTransaction(), popup.FRAGMENT_TAG);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class PatchNoteRecyclerViewAdapterHolder extends RecyclerView.ViewHolder {

        TextView mVersion;
        TextView mDate;

        public PatchNoteRecyclerViewAdapterHolder(View view) {
            super(view);

            mVersion = view.findViewById(R.id.item_patchNote_recyclerView_textView_version);
            mDate = view.findViewById(R.id.item_patchNote_recyclerView_textView_date);
        }
    }

    public void addItem(String version, String date, String contents) {
        PatchNoteRecyclerViewItem item = new PatchNoteRecyclerViewItem();
        item.setVersion(version);
        item.setDate(date);
        item.setContents(contents);

        list.add(item);
    }

}
