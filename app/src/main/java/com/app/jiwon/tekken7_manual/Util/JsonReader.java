package com.app.jiwon.tekken7_manual.Util;

import android.os.Environment;
import android.util.Log;

import com.app.jiwon.tekken7_manual.Adapter.ComboRecyclerViewAdapter;
import com.app.jiwon.tekken7_manual.Adapter.DictionaryRecyclerViewAdapter;
import com.app.jiwon.tekken7_manual.Adapter.FrameRecyclerViewAdapter;
import com.app.jiwon.tekken7_manual.Adapter.PatchNoteRecyclerViewAdapter;
import com.app.jiwon.tekken7_manual.DbSerialization.ComboSerialization;
import com.app.jiwon.tekken7_manual.DbSerialization.DictionarySerialization;
import com.app.jiwon.tekken7_manual.DbSerialization.FrameSerialization;
import com.app.jiwon.tekken7_manual.DbSerialization.PatchnoteSerialization;
import com.app.jiwon.tekken7_manual.DbSerialization.ProfileSerialization;
import com.app.jiwon.tekken7_manual.Items.ProfileFragmentItem;
import com.app.jiwon.tekken7_manual.R;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class JsonReader {

    public static void frameDbSet(String name, String filePath, FrameRecyclerViewAdapter adapter) {
        FrameSerialization frameSerialization = new Gson().fromJson(getJson(name, filePath), FrameSerialization.class);

        for (FrameSerialization.skillList value : frameSerialization.skill) {
            adapter.addItem(
                    value.skill_Name,
                    value.command,
                    value.hit_level,
                    value.damage,
                    value.start_up_frame,
                    value.block_frame,
                    value.hit_frame,
                    value.counter_hit_frame,
                    value.notes);
        }

        adapter.notifyDataSetChanged();
    }

    public static void DictionaryDbSet(String name, String filePath, DictionaryRecyclerViewAdapter adapter) {
        DictionarySerialization dictionarySerialization = new Gson().fromJson(getJson(name, filePath), DictionarySerialization.class);

        for (DictionarySerialization.Value value : dictionarySerialization.list) {
            adapter.addItem(value.name, value.number, value.explanation);
        }
    }

    public static void ComboDbSet(String name, String filePath, ComboRecyclerViewAdapter adapter) {
        ComboSerialization comboSerialization = new Gson().fromJson(getJson(name, filePath), ComboSerialization.class);

        for (ComboSerialization.combo value : comboSerialization.combo) {
            adapter.addItem(
                    value.starter,
                    value.combo_list);
        }

        adapter.notifyDataSetChanged();
    }

    public static void PatchnoteSet(String name, String filePath, PatchNoteRecyclerViewAdapter adapter) {
        PatchnoteSerialization patchnoteSerialization = new Gson().fromJson(getJson(name, filePath), PatchnoteSerialization.class);

        for (PatchnoteSerialization.value value : patchnoteSerialization.patch) {
            adapter.addItem(
                    value.version,
                    value.date,
                    value.content);
        }

        adapter.notifyDataSetChanged();
    }

    public static ProfileFragmentItem ProfileDbSet(String name, String filePath) {
        ProfileFragmentItem item = new ProfileFragmentItem();

        ProfileSerialization profileSerialization = new Gson().fromJson(getJson(name, filePath), ProfileSerialization.class);

        for (ProfileSerialization.Value value : profileSerialization.profile) {
            if (value.name.equals(name)) {
                item.setCharacterName(value.name);
                item.setKoreanName(value.display_name);
                item.setAge(value.age);
                item.setBloodType(value.blood_type);
                item.setFightStyle(value.fighting_style);
                item.setHeight(value.height);
                item.setWeight(value.weight);
                item.setGender(value.gender);
                item.setNationality(value.nationality);
                item.setLink(value.link);

                return item;
            }
        }

        return null;
    }

    private static String getJson(String name, String filePath) {
        final File localFile;

        String sdcard = Environment.getExternalStorageState();

        if (!sdcard.equals(Environment.MEDIA_MOUNTED)) {
            localFile = Environment.getRootDirectory();
        } else {
            localFile = Environment.getExternalStorageDirectory();
        }

        String dir;

        if (filePath.equals("frame") || filePath.equals("combo")) {
            dir = localFile.getAbsolutePath() + String.format("/.tekken_manual/%1$s/%1$s_%2$s.json", filePath, name);
        } else {
            dir = localFile.getAbsolutePath() + String.format("/.tekken_manual/%1$s/%1$s.json", filePath);
        }

        File file = new File(dir);
        String path = readJson(file.getAbsolutePath());

        return path;
    }

    private static String readJson(String path) {
        String Json = null;
        InputStream is = null;

        try {
            is = new FileInputStream(path);

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            Json = new String(buffer, "UTF-8");

            return Json;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
