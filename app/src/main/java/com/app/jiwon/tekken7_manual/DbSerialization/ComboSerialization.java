package com.app.jiwon.tekken7_manual.DbSerialization;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jiwon on 2018-05-27.
 */

public class ComboSerialization {
    @SerializedName("name")
    public String name;

//    @SerializedName("link")
//    public String link;

    @SerializedName("combo")
    public List<ComboSerialization.combo> combo;

    public class combo {
        @SerializedName("starter")
        public String starter;

        @SerializedName("combo_list")
        public String [] combo_list;

    }
}
