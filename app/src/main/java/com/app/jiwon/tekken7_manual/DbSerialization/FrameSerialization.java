package com.app.jiwon.tekken7_manual.DbSerialization;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jiwon on 2018-02-13.
 */

public class FrameSerialization {
    @SerializedName("name")
    public String name;

    @SerializedName("skill")
    public List<skillList> skill;

    public class skillList {
        @SerializedName("skill_Name")
        public String skill_Name;

        @SerializedName("command")
        public String command;

        @SerializedName("hit_level")
        public String hit_level;

        @SerializedName("damage")
        public String damage;

        @SerializedName("start_up_frame")
        public String start_up_frame;

        @SerializedName("block_frame")
        public String block_frame;

        @SerializedName("hit_frame")
        public String hit_frame;

        @SerializedName("counter_hit_frame")
        public String counter_hit_frame;

        @SerializedName("notes")
        public String notes;
    }
}
