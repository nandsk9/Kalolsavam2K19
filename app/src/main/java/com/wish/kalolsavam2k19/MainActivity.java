package com.wish.kalolsavam2k19;

import android.graphics.PorterDuff;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import 	androidx.core.content.ContextCompat;

import com.google.android.material.tabs.TabItem;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
//import android.widget.Toolbar;
//import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    //tab layout
    Toolbar toolbartab,toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;

    PageAdapter pageAdapter;

    //setting tab icon
    private int[] tabIcons = {
            R.drawable.others_tab_icon,
            R.drawable.locate_tab_icon,
            R.drawable.others_tab_icon
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Make activity in fullScreen

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //change background color of status bar
        //Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        //window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        //window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorWhite));

        setContentView(R.layout.activity_main);
        /*
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);*/



        toolbartab=findViewById(R.id.toolbartab);
        //toolbar=findViewById(R.id.toolbar);
        viewPager =findViewById(R.id.viewpager);
        tabLayout =findViewById(R.id.tablayout);

        //setSupportActionBar(toolbar);
        pageAdapter = new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new HomeFragment(),"Home");
        pageAdapter.addFragment(new LocateFragment(),"Locate");
        pageAdapter.addFragment(new OthersFragment(),"Others");

        //to make the tabs swipe
        viewPager.setAdapter(pageAdapter);
        //to make the tabIndicator move according to swipe
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();;
        //set color of already selected selected icon
        tabLayout.getTabAt(0).getIcon().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.tabSelectedIconColor),PorterDuff.Mode.SRC_IN);
        //change icon color according to selection
        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.tabSelectedIconColor);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }


                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.tabUnselectedIconColor);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                });
    }

    //user defined functions
    //set icons for the tab
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[1]);
        tabLayout.getTabAt(1).setIcon(tabIcons[0]);
        tabLayout.getTabAt(2).setIcon(tabIcons[1]);
    }
}