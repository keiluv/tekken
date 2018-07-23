package com.app.jiwon.tekken7_manual.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.jiwon.tekken7_manual.Adapter.CharacterViewPagerAdapter;
import com.app.jiwon.tekken7_manual.Fragment.FrameFragment;
import com.app.jiwon.tekken7_manual.R;
import com.app.jiwon.tekken7_manual.Util.SharedPreferencesUtil;
import com.app.jiwon.tekken7_manual.Util.Value;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CharacterActivity extends AppCompatActivity {

    Context context = CharacterActivity.this;

    private String characterName;
    private String thisComboLink;

    private CharacterViewPagerAdapter characterViewPagerAdapter;
    private ArrayList items = new ArrayList();

    private boolean isPagerCheck = true;

    String[] comboLink = Value.comboLink;

    @BindString(R.string.alert_agree_button)
    String agree;

    @BindString(R.string.alert_refuse_button)
    String refuse;

    @BindString(R.string.data_alert_message)
    String alertMessage;

    @BindArray(R.array.characterName)
    String[] characterNameList;

    @BindView(R.id.characterActivity_viewPager)
    ViewPager viewPager;

    @BindView(R.id.characterActivity_toolBar)
    Toolbar toolbar;

    @BindString(R.string.actionBar_frame_search_hint)
    String frameHint;

    @BindView(R.id.toolBar_searchView)
    MaterialSearchView searchView;

    @BindView(R.id.characterActivity_tabLayout)
    AHBottomNavigation bottomNavigation;

    AHBottomNavigationItem navItem1 = new AHBottomNavigationItem(R.string.character_tab_frame, R.drawable.ic_tab_frame, R.color.black);
    AHBottomNavigationItem navItem2 = new AHBottomNavigationItem(R.string.character_tab_profile, R.drawable.ic_tab_profile, R.color.black);
    AHBottomNavigationItem navItem3 = new AHBottomNavigationItem(R.string.character_tab_manual, R.drawable.ic_tab_manual, R.color.black);
    AHBottomNavigationItem navItem4 = new AHBottomNavigationItem(R.string.character_tab_combo, R.drawable.ic_tab_combo, R.color.black);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        ButterKnife.bind(this);

        characterName = getIntent().getStringExtra("characterName").toString();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.character_tab_frame);

        characterViewPagerAdapter = new CharacterViewPagerAdapter(getFragmentManager(), characterName);
        viewPager.setAdapter(characterViewPagerAdapter);

        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(0);

        items.add(navItem1);
        items.add(navItem2);
        items.add(navItem3);
        items.add(navItem4);

        bottomNavigation.addItems(items);
        bottomNavigation.setTranslucentNavigationEnabled(true);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                invalidateOptionsMenu();

                if (searchView.isSearchOpen())
                    searchView.closeSearch();

                if (position == 1)
                    isPagerCheck = false;
                else if (position == 0)
                    isPagerCheck = true;

                viewPager.setCurrentItem(position, true);
                getSupportActionBar().setTitle(bottomNavigation.getItem(position).getTitle(context));

                return true;
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (isPagerCheck) {
                    ((FrameFragment) characterViewPagerAdapter.getItem(viewPager.getCurrentItem())).adapter.fillter(newText);
                }

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int position = viewPager.getCurrentItem();

        if (position == 0) {
            toolbar.inflateMenu(R.menu.character_menu);
            MenuItem mSearchMenu = menu.findItem(R.id.action_search);
            MenuItem mFavoriteMenu = menu.findItem(R.id.action_favorite);
            searchView.setMenuItem(mSearchMenu);
            searchView.setHint(frameHint);

            if (SharedPreferencesUtil.getBooleanPreferences("checkFavorite", characterName, false, context))
                mFavoriteMenu.setIcon(R.drawable.ic_star);
            else
                mFavoriteMenu.setIcon(R.drawable.ic_star_empty);

        } else if (position == 3) {
            toolbar.inflateMenu(R.menu.combo_menu);
        } else {
            toolbar.getMenu().clear();
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        int id = menuItem.getItemId();

        switch (id) {
            case R.id.action_favorite:
                if (SharedPreferencesUtil.getBooleanPreferences("checkFavorite", characterName, false, context)) {
                    menuItem.setIcon(R.drawable.ic_star_empty);
                    SharedPreferencesUtil.saveBooleanPreferences("checkFavorite", characterName, false, context);
                    Toast.makeText(context, R.string.characterActivity_favorite_release, Toast.LENGTH_SHORT).show();
                } else {
                    menuItem.setIcon(R.drawable.ic_star);
                    SharedPreferencesUtil.saveBooleanPreferences("checkFavorite", characterName, true, context);
                    Toast.makeText(context, R.string.characterActivity_favorite_enrollment, Toast.LENGTH_SHORT).show();
                }

                return true;

            case R.id.action_shortcut:

                for (int i = 0; i < comboLink.length; i++) {
                    if (characterNameList[i].equals(characterName)) {
                        thisComboLink = comboLink[i];
                    }
                }

                NetworkInfo info = getNetworkInfo();

                if (info != null && info.isConnected()) {
                    if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CharacterActivity.this)
                                .setMessage(alertMessage)
                                .setPositiveButton(agree, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dlg, int value) {
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(Uri.parse(thisComboLink));
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton(refuse, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(thisComboLink));
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(thisComboLink));
                    startActivity(intent);
                }

                return true;

            case R.id.action_help:

                startActivity(new Intent(CharacterActivity.this, HelpActivity.class));

                return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }

    private NetworkInfo getNetworkInfo() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        return info;
    }
}
