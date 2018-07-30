package com.app.jiwon.tekken7_manual.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.jiwon.tekken7_manual.Adapter.MainViewPagerAdapter;
import com.app.jiwon.tekken7_manual.Fragment.DictionaryFragment;
import com.app.jiwon.tekken7_manual.Fragment.SelectFragment;
import com.app.jiwon.tekken7_manual.R;
import com.app.jiwon.tekken7_manual.Util.SharedPreferencesUtil;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.gun0912.tedpermission.TedPermission;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    private Context context = MainActivity.this;

    private boolean favoriteCheck = false;
    private boolean isPagerCheck = true;

    private MenuItem mFavoriteMenu;

    private MainViewPagerAdapter mainViewPagerAdapter;

    private ArrayList items = new ArrayList();

    @BindArray(R.array.characterName)
    String[] characterList;

    @BindString(R.string.mainActivity_alert_message)
    String alertMessage;

    @BindString(R.string.actionBar_character_search_hint)
    String characterSearchHint;

    @BindString(R.string.actionBar_dictionary_search_hint)
    String dictionarySearchHint;

    @BindView(R.id.mainActivity_viewPager)
    ViewPager viewPager;

    @BindView(R.id.mainActivity_toolBar)
    Toolbar toolbar;

    @BindView(R.id.toolBar_searchView)
    MaterialSearchView searchView;

    @BindView(R.id.mainActivity_tabLayout)
    AHBottomNavigation bottomNavigation;

    AHBottomNavigationItem navItem1 = new AHBottomNavigationItem(R.string.main_tab_select, R.drawable.ic_tab_character, R.color.black);
    AHBottomNavigationItem navItem2 = new AHBottomNavigationItem(R.string.main_tab_dictionary, R.drawable.ic_tab_dictionary, R.color.black);
    AHBottomNavigationItem navItem3 = new AHBottomNavigationItem(R.string.main_tab_shortcuts, R.drawable.ic_tab_shortcuts, R.color.black);
    AHBottomNavigationItem navItem4 = new AHBottomNavigationItem(R.string.main_tab_patchnote, R.drawable.ic_tab_patchnote, R.color.black);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        checkData();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.main_tab_select);

        mainViewPagerAdapter = new MainViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(mainViewPagerAdapter);

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
                    ((SelectFragment) mainViewPagerAdapter.getItem(viewPager.getCurrentItem())).adapter.fillter(newText);
                } else {
                    ((DictionaryFragment) mainViewPagerAdapter.getItem(viewPager.getCurrentItem())).adapter.fillter(newText);
                }

                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                if (isPagerCheck) {
                    ((SelectFragment) mainViewPagerAdapter.getItem(viewPager.getCurrentItem())).adapter.listChange();
                    mFavoriteMenu.setIcon(R.drawable.ic_star_empty);
                    favoriteCheck = false;
                }
            }

            @Override
            public void onSearchViewClosed() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int position = viewPager.getCurrentItem();

        if (position == 0) {
            toolbar.inflateMenu(R.menu.select_menu);
            MenuItem mSearchMenu = menu.findItem(R.id.action_search);
            mFavoriteMenu = menu.findItem(R.id.action_favorite);
            searchView.setMenuItem(mSearchMenu);
            searchView.setHint(characterSearchHint);

            if (favoriteCheck) {
                mFavoriteMenu.setIcon(R.drawable.ic_star);
            }

        } else if (position == 1) {
            toolbar.inflateMenu(R.menu.dictionary_menu);
            MenuItem mSearchMenu = menu.findItem(R.id.action_search);
            searchView.setMenuItem(mSearchMenu);
            searchView.setHint(dictionarySearchHint);
        } else {
            toolbar.getMenu().clear();
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.action_favorite:

                mFavoriteCheck(item);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }

    public void mFavoriteCheck(MenuItem menuItem) {
        if (favoriteCheck) {
            menuItem.setIcon(R.drawable.ic_star_empty);

            ((SelectFragment) mainViewPagerAdapter.getItem(viewPager.getCurrentItem())).favoriteBack();
            favoriteCheck = !favoriteCheck;
        } else {
            menuItem.setIcon(R.drawable.ic_star);

            ((SelectFragment) mainViewPagerAdapter.getItem(viewPager.getCurrentItem())).favoriteChange();
            favoriteCheck = !favoriteCheck;
        }
    }


    private void checkData() {
        if (!getPath("profile", "").exists()) {
            FinishApp();
            return;
        }

        if (!getPath("dictionary", "").exists()) {
            FinishApp();
            return;
        }

        if (!getPath("patchnote", "").exists()) {
            FinishApp();
            return;
        }

        for (int i = 0; i < characterList.length; i++) {
            if (!getPath("combo", characterList[i]).exists()) {
                FinishApp();
                return;
            }

            if (!getPath("frame", characterList[i]).exists()) {
                FinishApp();
                return;
            }
        }

    }

    private File getPath(String downPath, String characterName) {
        String sdCard = Environment.getExternalStorageState();
        String path;
        File file;

        if (!sdCard.equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getRootDirectory().getAbsolutePath() + "/.tekken_manual/" + downPath;
        } else {
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/.tekken_manual/" + downPath;
        }

        if (downPath.equals("dictionary") || downPath.equals("patchnote") || downPath.equals("profile")) {
            file = new File(path, downPath + ".json");
            return file;
        } else {
            file = new File(path, String.format(downPath + "_%s.json", characterName));
            return file;
        }
    }

    private void FinishApp() {
        Toast.makeText(context, alertMessage, Toast.LENGTH_SHORT).show();
        finish();
    }
}
