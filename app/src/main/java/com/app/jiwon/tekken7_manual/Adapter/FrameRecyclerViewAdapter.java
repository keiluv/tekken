package com.app.jiwon.tekken7_manual.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.jiwon.tekken7_manual.Items.FrameRecyclerViewItem;
import com.app.jiwon.tekken7_manual.Items.SelectRecyclerViewItem;
import com.app.jiwon.tekken7_manual.R;

import java.util.ArrayList;
import java.util.Locale;

public class FrameRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<FrameRecyclerViewItem> list = new ArrayList();
    private ArrayList<FrameRecyclerViewItem> saveList = new ArrayList();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_frame_recyclerview, parent, false);

        return new FrameRecyclerViewAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FrameRecyclerViewAdapterHolder customHolder = (FrameRecyclerViewAdapterHolder) holder;
        FrameRecyclerViewItem item = list.get(position);

        if (item.getSkillName().contains("이 밑부터")) {
            customHolder.command.setText(item.getSkillName());
            customHolder.skill_name.setText("");
            customHolder.hit_level.setText("");
            customHolder.damage.setText("");
            customHolder.start_frame.setText("");
            customHolder.block_frame.setText("");
            customHolder.hit_frame.setText("");
            customHolder.counter_frame.setText("");
            customHolder.note.setText("");
        } else {
            customHolder.skill_name.setText("기술명 : " + item.getSkillName());
            customHolder.command.setText("커맨드 : " + item.getCommand());
            customHolder.hit_level.setText(item.getHitLevel());
            customHolder.damage.setText(item.getDamage());
            customHolder.start_frame.setText(item.getStartFrame());
            customHolder.block_frame.setText(item.getBlockFrame());
            customHolder.hit_frame.setText(item.getHitFrame());
            customHolder.counter_frame.setText(item.getCounter_frame());
            customHolder.note.setText(item.getNote());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class FrameRecyclerViewAdapterHolder extends RecyclerView.ViewHolder {
        TextView skill_name;
        TextView command;
        TextView hit_level;
        TextView damage;
        TextView start_frame;
        TextView block_frame;
        TextView hit_frame;
        TextView counter_frame;
        TextView note;

        public FrameRecyclerViewAdapterHolder(View view) {
            super(view);

            skill_name = view.findViewById(R.id.item_frame_recyclerView_textView_skill_name);
            command = view.findViewById(R.id.item_frame_recyclerView_textView_command);
            hit_level = view.findViewById(R.id.item_frame_recyclerView_textView_hit_level);
            damage = view.findViewById(R.id.item_frame_recyclerView_textView_damage);
            start_frame = view.findViewById(R.id.item_frame_recyclerView_textView_start_frame);
            block_frame = view.findViewById(R.id.item_frame_recyclerView_textView_block_frame);
            counter_frame = view.findViewById(R.id.item_frame_recyclerView_textView_counter_frame);
            hit_frame = view.findViewById(R.id.item_frame_recyclerView_textView_hit_frame);
            note = view.findViewById(R.id.item_frame_recyclerView_textView_note);
        }
    }

    public void addItem(
            String skill_name,
            String command,
            String hit_level,
            String damage,
            String start_frame,
            String block_frame,
            String hit_frame,
            String counter_frame,
            String note) {

        FrameRecyclerViewItem item = new FrameRecyclerViewItem();

        item.setSkillName(skill_name);
        item.setCommand(command);
        item.setHitLevel(hit_level);
        item.setDamage(damage);
        item.setStartFrame(start_frame);
        item.setBlockFrame(block_frame);
        item.setHitFrame(hit_frame);
        item.setCounter_frame(counter_frame);
        item.setNote(note);

        list.add(item);
        saveList.add(item);
    }

    public void fillter(String searchText) {
        searchText = searchText.toLowerCase(Locale.getDefault());
        list.clear();

        if (searchText.length() == 0) {
            list.addAll(saveList);
        } else {
            for (FrameRecyclerViewItem item : saveList) {

                if (item.getCommand().toLowerCase().contains(searchText)
                        || item.getSkillName().toLowerCase().contains(searchText)
                        || item.getNote().toLowerCase().contains(searchText)) {
                    list.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

}
