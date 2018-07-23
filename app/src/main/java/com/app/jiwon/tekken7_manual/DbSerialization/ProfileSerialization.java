package com.app.jiwon.tekken7_manual.DbSerialization;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jiwon on 2018-02-13.
 */

public class ProfileSerialization {
    @SerializedName("profile")
    public List<Value> profile;

    public class Value {
        @SerializedName("name")
        public String name;

        @SerializedName("display_name")
        public String display_name;

        @SerializedName("gender")
        public String gender;

        @SerializedName("age")
        public String age;

        @SerializedName("weight")
        public String weight;

        @SerializedName("blood_type")
        public String blood_type;

        @SerializedName("height")
        public String height;

        @SerializedName("nationality")
        public String nationality;

        @SerializedName("fighting_style")
        public String fighting_style;

        @SerializedName("link")
        public String link;
    }
}
