package com.app.jiwon.tekken7_manual.DbSerialization;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jiwon on 2018-02-09.
 */

public class DictionarySerialization {

    @SerializedName("MyJson")
    public List<Value> list;

    public class Value {
        @SerializedName("skill_name")
        public String name;

        @SerializedName("skill_number")
        public int number;

        @SerializedName("explanation")
        public String explanation;
    }
}
