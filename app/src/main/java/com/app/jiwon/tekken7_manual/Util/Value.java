package com.app.jiwon.tekken7_manual.Util;

import android.content.Context;

import com.app.jiwon.tekken7_manual.Items.SelectRecyclerViewItem;
import com.app.jiwon.tekken7_manual.Items.ShortSutsRecyclerViewItem;
import com.app.jiwon.tekken7_manual.R;

import java.util.ArrayList;

public class Value {

    public static final int cardViewMargin = 12;

    public static final String comboLink[] = {
            "https://www.youtube.com/watch?v=Q7PM1odSK3I",
            "https://www.youtube.com/watch?v=jQprNV-cHFI&t=1s",
            "https://www.youtube.com/watch?v=-qeXaycTdQE&t=269s",
            "https://www.youtube.com/watch?v=axYpISGZRI4",
            "https://www.youtube.com/watch?v=8PHoaFomTpI",
            "https://www.youtube.com/watch?v=p-0xXFY0cUE&t=99s",
            "https://www.youtube.com/watch?v=AGyvY2EIMCo",
            "https://www.youtube.com/watch?v=EQ_zDhGUQWQ",
            "https://www.youtube.com/watch?v=pvC6uBtfZ30",
            "https://www.youtube.com/watch?v=4Q7Bg5KStyQ",
            "https://www.youtube.com/watch?v=ko5UDkViyD8",
            "https://www.youtube.com/watch?v=SIT6-0JZFBE",
            "https://www.youtube.com/watch?v=4cInR0osyiA",
            "https://www.youtube.com/watch?v=_RmckqJmNms&t=236s",
            "https://www.youtube.com/watch?v=ITp8-rkkDzk",
            "https://www.youtube.com/watch?v=WGh61lKta7o",
            "https://www.youtube.com/watch?v=Y87FRrEQ7v8",
            "https://www.youtube.com/watch?v=20_oOzBGrGQ",
            "https://www.youtube.com/watch?v=v7Uze4mfFuc",
            "https://www.youtube.com/watch?v=GjeOIo4ez7M",
            "https://www.youtube.com/watch?v=gNlz5LxW7Fk",
            "https://www.youtube.com/watch?v=lBfo2LYwmJE",
            "https://www.youtube.com/watch?v=lSiaLyPMQbM",
            "https://www.youtube.com/watch?v=B-6WfM4ijvg",
            "https://www.youtube.com/watch?v=HrOoWSgyBR4",
            "https://www.youtube.com/watch?v=9n-GPmArobw",
            "https://www.youtube.com/watch?v=UPnCuN1Qdtg",
            "https://www.youtube.com/watch?v=Uutjq1cyFfw",
            "https://www.youtube.com/watch?v=i8Er-Y6tFJA",
            "https://www.youtube.com/watch?v=NOPy3bKqqQM",
            "https://www.youtube.com/watch?v=hve6HvxlBC8",
            "https://www.youtube.com/watch?v=ZSrzvsAt5l0",
            "https://www.youtube.com/watch?v=lSiaLyPMQbM&t=10s",
            "https://www.youtube.com/watch?v=3L9JRL5XzLU&t=495s",
            "https://www.youtube.com/watch?v=6SoIs-sNaLA",
            "https://www.youtube.com/watch?v=OTPwivhUUrE",
            "https://www.youtube.com/watch?v=bfUsjd4fH2w",
            "https://www.youtube.com/watch?v=CRBQ4IcdBh4",
            "https://www.youtube.com/watch?v=q86m8FVSRWA",
    };

    public static final int portraitImages[] = {
            R.drawable.portrait_akuma, R.drawable.portrait_alisa, R.drawable.portrait_asuka,
            R.drawable.portrait_bob, R.drawable.portrait_bryan,
            R.drawable.portrait_claudio,
            R.drawable.portrait_devil_jin, R.drawable.portrait_dragunov,
            R.drawable.portrait_eddy, R.drawable.portrait_eliza,
            R.drawable.portrait_feng,
            R.drawable.portrait_geese, R.drawable.portrait_gigas,
            R.drawable.portrait_hei, R.drawable.portrait_hwoarang,
            R.drawable.portrait_jack, R.drawable.portrait_jin, R.drawable.portrait_josie,
            R.drawable.portrait_katarina, R.drawable.portrait_kazumi, R.drawable.portrait_kazuya, R.drawable.portrait_king, R.drawable.portrait_kuma,
            R.drawable.portrait_lars, R.drawable.portrait_law, R.drawable.portrait_lee, R.drawable.portrait_leo, R.drawable.portrait_lili, R.drawable.portrait_lucky,
            R.drawable.portrait_miguel,
            R.drawable.portrait_nina, R.drawable.portrait_noctis,
            R.drawable.portrait_panda, R.drawable.portrait_paul,
            R.drawable.portrait_raven,
            R.drawable.portrait_shaheen, R.drawable.portrait_steve,
            R.drawable.portrait_xiaoyu,
            R.drawable.portrait_yoshi
    };

    public static final int shortCutsImages[] = {
            R.drawable.shortcut_community_dc, R.drawable.shortcut_community_pt, R.drawable.shortcut_community_rb, R.drawable.shortcut_community_tc, R.drawable.shortcut_community_tw,
            R.drawable.shortcut_youtube_jurry,
            R.drawable.shortcut_youtube_ebs,
            R.drawable.shortcut_youtube_whirlwind,
            R.drawable.shortcut_youtube_knee,
            R.drawable.shortcut_youtube_dad,
            R.drawable.shortcut_youtube_narak,
            R.drawable.shortcut_youtube_lowhigh,
            R.drawable.shortcut_youtube_jeongdding,
            R.drawable.shortcut_youtube_mupaper,
            R.drawable.shortcut_youtube_74,
            R.drawable.shortcut_youtube_cherry
    };

    public static ArrayList getShortCutsImages(Context context) {
        ArrayList<ShortSutsRecyclerViewItem> list = new ArrayList();

        for (int i = 0; i < shortCutsImages.length; i++) {
            ShortSutsRecyclerViewItem item = new ShortSutsRecyclerViewItem();

            item.setImage(shortCutsImages[i]);
            item.setName(context.getResources().getStringArray(R.array.shortCutsName)[i]);
            item.setLink(context.getResources().getStringArray(R.array.shortCutsLink)[i]);

            if (i < 5)
                item.setType("커뮤니티");
            else
                item.setType("유튜브");

            list.add(item);
        }

        return list;
    }

    public static ArrayList getPortraitImages(Context context) {
        ArrayList<SelectRecyclerViewItem> list = new ArrayList<>();

        for (int i = 0; i < portraitImages.length; i++) {
            SelectRecyclerViewItem item = new SelectRecyclerViewItem();

            String key = context.getResources().getResourceEntryName(portraitImages[i]).substring(9);

            item.setImage(portraitImages[i]);
            item.setKoreanName(context.getResources().getStringArray(R.array.selectName)[i]);
            item.setEnglishName(context.getResources().getStringArray(R.array.characterName)[i]);

            boolean check = SharedPreferencesUtil.getBooleanPreferences("checkFavorite", key, false, context);

            if (check)
                item.setFavorite(true);
            else
                item.setFavorite(false);

            list.add(item);
        }

        return list;
    }
}
