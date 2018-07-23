package com.app.jiwon.tekken7_manual.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.jiwon.tekken7_manual.Activity.CharacterActivity;
import com.app.jiwon.tekken7_manual.Items.ProfileFragmentItem;
import com.app.jiwon.tekken7_manual.R;
import com.app.jiwon.tekken7_manual.Util.JsonReader;
import com.app.jiwon.tekken7_manual.Util.Value;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {

    private String characterName;
    private Context context;

    private int[] characterImage = Value.portraitImages;

    @BindView(R.id.fragment_profile_imageView)
    ImageView imageView;

    @BindView(R.id.fragment_profile_textView_name)
    TextView name;

    @BindView(R.id.fragment_profile_textView_age_blood_type)
    TextView age;

    @BindView(R.id.fragment_profile_textView_gender)
    TextView gender;

    @BindView(R.id.fragment_profile_textView_nationality)
    TextView nationality;

    @BindView(R.id.fragment_profile_textView_height)
    TextView height;

    @BindView(R.id.fragment_profile_textView_weight)
    TextView weight;

    @BindView(R.id.fragment_profile_textView_fight_style)
    TextView fightStyle;

    @BindView(R.id.fragment_profile_button_shortcut)
    Button shortcut;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(String characterName) {
        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        args.putString("characterName", characterName);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        context = view.getContext();

        this.characterName = getArguments().getString("characterName");

        final ProfileFragmentItem item = JsonReader.ProfileDbSet(characterName, "profile");

        for (int image : characterImage) {
            if (getResources().getResourceEntryName(image).contains(characterName)) {
                imageView.setImageResource(image);
            }
        }

        name.setText(item.getKoreanName());
        age.setText("나이 : " + item.getAge() + ", 혈액형 : " + item.getBloodType());
        gender.setText(item.getGender());
        nationality.setText(item.getNationality());
        height.setText(item.getHeight());
        weight.setText(item.getWeight());
        fightStyle.setText("전투스타일 : " + item.getFightStyle());

        shortcut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NetworkInfo info = getNetworkInfo();

                if (info != null && info.isConnected()) {
                    if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                                .setMessage(R.string.data_alert_message)
                                .setPositiveButton(R.string.alert_agree_button, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dlg, int value) {
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(Uri.parse(item.getLink()));
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton(R.string.alert_refuse_button, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(item.getLink()));
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(item.getLink()));
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    private NetworkInfo getNetworkInfo() {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        return info;
    }
}
