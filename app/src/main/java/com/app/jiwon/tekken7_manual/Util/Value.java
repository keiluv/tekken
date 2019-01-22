package com.app.jiwon.tekken7_manual.Util;

import android.content.Context;

import com.app.jiwon.tekken7_manual.Items.SelectRecyclerViewItem;
import com.app.jiwon.tekken7_manual.Items.ShortSutsRecyclerViewItem;
import com.app.jiwon.tekken7_manual.R;

import java.util.ArrayList;

public class Value {

    public static final int cardViewMargin = 12;

    public static final String comboLink[] = {
            "https://www.youtube.com/watch?v=Q7PM1odSK3I",                  //고우키
            "https://www.youtube.com/watch?v=jQprNV-cHFI&t=1s",             //알리사
            "https://www.youtube.com/watch?v=-qeXaycTdQE&t=269s",           //아스카
            "https://www.youtube.com/watch?v=qpchv5DGk-k",                  //안나
            "https://www.youtube.com/watch?v=x9lRn3PdqHY",                  //아머킹
            "https://www.youtube.com/watch?v=axYpISGZRI4",                  //밥
            "https://www.youtube.com/watch?v=8PHoaFomTpI",                  //브라이언
            "https://www.youtube.com/watch?v=p-0xXFY0cUE&t=99s",            //클라
            "https://www.youtube.com/watch?v=AGyvY2EIMCo",                  //데빌진
            "https://www.youtube.com/watch?v=EQ_zDhGUQWQ",                  //드라그
            "https://www.youtube.com/watch?v=pvC6uBtfZ30",                  //에디
            "https://www.youtube.com/watch?v=4Q7Bg5KStyQ",                  //엘리자
            "https://www.youtube.com/watch?v=ko5UDkViyD8",                  //펭
            "https://www.youtube.com/watch?v=SIT6-0JZFBE",                  //기스
            "https://www.youtube.com/watch?v=4cInR0osyiA",                  //기가스
            "https://www.youtube.com/watch?v=_RmckqJmNms&t=236s",           //헤이아치
            "https://www.youtube.com/watch?v=ITp8-rkkDzk",                  //화랑
            "https://www.youtube.com/watch?v=WGh61lKta7o",                  //잭
            "https://www.youtube.com/watch?v=Y87FRrEQ7v8",                  //진
            "https://www.youtube.com/watch?v=20_oOzBGrGQ",                  //조시
//            "",                                                             //줄리아
            "https://www.youtube.com/watch?v=v7Uze4mfFuc",                  //카타리나
            "https://www.youtube.com/watch?v=GjeOIo4ez7M",                  // 카즈미
            "https://www.youtube.com/watch?v=gNlz5LxW7Fk",                  // 카즈야
            "https://www.youtube.com/watch?v=lBfo2LYwmJE",                  //킹
            "https://www.youtube.com/watch?v=lSiaLyPMQbM",                  //쿠마
            "https://www.youtube.com/watch?v=B-6WfM4ijvg",                  //라스
            "https://www.youtube.com/watch?v=HrOoWSgyBR4",                  //로우
            "https://www.youtube.com/watch?v=9n-GPmArobw",                  //리
            "https://www.youtube.com/watch?v=UPnCuN1Qdtg",                  //레오
            "https://www.youtube.com/watch?v=Uutjq1cyFfw",                  //리리
            "https://www.youtube.com/watch?v=i8Er-Y6tFJA",                  //럭키
            "https://www.youtube.com/watch?v=ju7XMxOyr-M",                  //레이우롱
            "https://www.youtube.com/watch?v=NOPy3bKqqQM",                  //미겔
            "https://www.youtube.com/watch?v=W3lzeiz5Knk",                  //머독
            "https://www.youtube.com/watch?v=hve6HvxlBC8",                  //니나
            "https://www.youtube.com/watch?v=ZSrzvsAt5l0",                  //녹티스
//            "",                                                             //네간
            "https://www.youtube.com/watch?v=lSiaLyPMQbM&t=10s",            //판다
            "https://www.youtube.com/watch?v=3L9JRL5XzLU&t=495s",           //폴
            "https://www.youtube.com/watch?v=6SoIs-sNaLA",                  //레이븐
            "https://www.youtube.com/watch?v=OTPwivhUUrE",                  //샤힌
            "https://www.youtube.com/watch?v=bfUsjd4fH2w",                  //스티브
            "https://www.youtube.com/watch?v=CRBQ4IcdBh4",                  //샤오유
            "https://www.youtube.com/watch?v=q86m8FVSRWA",                  //요시미츠
    };

    public static final int portraitImages[] = {
            R.drawable.portrait_akuma, R.drawable.portrait_alisa, R.drawable.portrait_asuka, R.drawable.portrait_anna, R.drawable.portrait_a_king,
            R.drawable.portrait_bob, R.drawable.portrait_bryan,
            R.drawable.portrait_claudio,
            R.drawable.portrait_devil_jin, R.drawable.portrait_dragunov,
            R.drawable.portrait_eddy, R.drawable.portrait_eliza,
            R.drawable.portrait_feng,
            R.drawable.portrait_geese, R.drawable.portrait_gigas,
            R.drawable.portrait_hei, R.drawable.portrait_hwoarang,
            R.drawable.portrait_jack, R.drawable.portrait_jin, R.drawable.portrait_josie,
            R.drawable.portrait_katarina, R.drawable.portrait_kazumi, R.drawable.portrait_kazuya, R.drawable.portrait_king, R.drawable.portrait_kuma,
            R.drawable.portrait_lars, R.drawable.portrait_law, R.drawable.portrait_lee, R.drawable.portrait_leo, R.drawable.portrait_lili, R.drawable.portrait_lucky, R.drawable.portrait_lei,
            R.drawable.portrait_miguel, R.drawable.portrait_marduk,
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
            R.drawable.shortcut_youtube_jkim,
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
