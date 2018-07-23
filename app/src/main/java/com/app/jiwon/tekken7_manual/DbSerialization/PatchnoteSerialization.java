package com.app.jiwon.tekken7_manual.DbSerialization;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jiwon on 2018-02-16.
 */

public class PatchnoteSerialization {

    @SerializedName("patch")
    public List<value> patch;

    public class value{
        @SerializedName("version")
        public String version;

        @SerializedName("date")
        public String date;

        @SerializedName("content")
        public String content;
    }

}
