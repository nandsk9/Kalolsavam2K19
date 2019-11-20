package com.wish.kalolsavam2k19;

import android.content.Intent;
import android.content.SharedPreferences;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IntroSlideActivity extends AppCompatActivity {
    int  position,pos;
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    Button btnPrev,btnNext,btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Make activity in fullScreen

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();
        //When this activity is about to be launch we need to check if it is opened before or not

        //Default value is given false,when openend once it changes to true

        if(restorePrefData()){
            //old main activity
            Intent mainActivity =new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainActivity);
            finish();//Used to remove IntroSlideActivity from the Androids Activity stack
        }

        setContentView(R.layout.activity_intro_slide);

        btnPrev = findViewById(R.id.btn_Prev);
        btnNext = findViewById(R.id.btn_Next);
        btnStart = findViewById(R.id.btn_start);


        //On button click Actions

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position=mSlideViewPager.getCurrentItem();
                position--;
                mSlideViewPager.setCurrentItem(position);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position =mSlideViewPager.getCurrentItem();
                if(position<SliderAdapter.slide_headings.length){
                    position++;
                    mSlideViewPager.setCurrentItem(position);
                }
                if(position==(SliderAdapter.slide_headings.length-1)){

                    //Hides all buttons except Get start button

                    btnNext.setVisibility(View.INVISIBLE);
                    btnPrev.setVisibility(View.INVISIBLE);
                    btnStart.setVisibility(View.VISIBLE);
                }
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent mainActivity =new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
                savePrefsData();//Set before opened to true
                finish();//Remove the introSlideActivity from backstack
            }
        });


        mSlideViewPager =(ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout =(LinearLayout) findViewById(R.id.dotsLayout);

        sliderAdapter =new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
    }

    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        mDotLayout.removeAllViews();//otherwise no. of dots will increase
        for(int i=0;i<mDots.length;i++){
            mDots[i] =new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mDots[i]);
        }

        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }

    }

    ViewPager.OnPageChangeListener viewListener =new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);

            pos =mSlideViewPager.getCurrentItem();
            if(pos==0)
            {
                btnPrev.setVisibility(View.INVISIBLE);
                btnNext.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.INVISIBLE);
            }
            if(pos>0&&pos<SliderAdapter.slide_headings.length){
                btnNext.setVisibility(View.VISIBLE);
                btnPrev.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.INVISIBLE);
            }
            if(pos==(SliderAdapter.slide_headings.length-1)) {


                btnNext.setVisibility(View.INVISIBLE);
                btnPrev.setVisibility(View.INVISIBLE);
                btnStart.setVisibility(View.VISIBLE);

            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    //Methods to access preference datas[used in managing intro sliders,...]

    private boolean restorePrefData(){
        SharedPreferences pref=getApplicationContext().getSharedPreferences("myAppPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore =pref.getBoolean("isIntroOpened",false);//Default is false
        return isIntroActivityOpenedBefore;
    }

    private void savePrefsData(){
        SharedPreferences pref =getApplicationContext().getSharedPreferences("myAppPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putBoolean("isIntroOpened",true);
        editor.commit();
    }



}
