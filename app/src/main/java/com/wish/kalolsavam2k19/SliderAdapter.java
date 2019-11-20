package com.wish.kalolsavam2k19;

import android.content.Context;
//import android.support.v4.view.PagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public  class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context=context;
    }

    //Arrays

    public int[] slide_images = {
            R.drawable.intro_slide_img1,
            R.drawable.intro_slide_img2,
            R.drawable.intro_slide_img3

    };

    public static String[] slide_headings = {
            "Welcome",
            "Navigate",
            "All the best"
    };

    public String[] slide_descs={
            "Edit something in future 1",
            "Navigation to different dias is made at your finger tip ",
            "Edit something in future 3"

    };
    @Override
    public  int getCount(){
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view,Object o){
        return view==(RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container,int position){
        layoutInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView slideImageView =(ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);

        return view;
    }

    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView((RelativeLayout)object); //Removes the slides when we reach the end of slides


    }
}
