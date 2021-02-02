package com.example.schedulechenk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {


    private GuideAdapter guideAdapter;
    private LinearLayout layoutGuideIndicators;
    private Button buttonGuideAction;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_guide));

        layoutGuideIndicators = findViewById(R.id.layoutGuideIndicators);
        buttonGuideAction = findViewById(R.id.guideActionButton);
        setupGuideItems();

        final ViewPager2 guideViewPager = findViewById(R.id.guideViewPager);
        guideViewPager.setAdapter(guideAdapter);


        setupGuideIndicators();
        setCurrentGuideIndicators(0);

        guideViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentGuideIndicators(position);
            }
        });


        buttonGuideAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(guideViewPager.getCurrentItem() +1 < guideAdapter.getItemCount()){
                    guideViewPager.setCurrentItem(guideViewPager.getCurrentItem() + 1);
                }else{
                    startActivity(new Intent(getApplicationContext(), EnterActivity.class));
                    finish();
                }
            }
        });
    }


    private void  setupGuideItems(){
        List<GuideItem> guideItems = new ArrayList<>();

        GuideItem itemHowStart = new GuideItem();
        itemHowStart.setImage(R.drawable.frst_guide_image);

        GuideItem itemHowUse = new GuideItem();
        itemHowStart.setImage(R.drawable.second_guide_image);

        GuideItem itemHowSetting = new GuideItem();
        itemHowStart.setImage(R.drawable.thrd_guide_image);

        guideItems.add(itemHowStart);
        guideItems.add(itemHowUse);
        guideItems.add(itemHowSetting);

        guideAdapter = new GuideAdapter(guideItems);
    }

    private  void setupGuideIndicators(){
        ImageView[] indicators = new ImageView[guideAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for(int i = 0; i< indicators.length;i++){
            indicators[i] = new ImageView((getApplicationContext()));
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.guide_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutGuideIndicators.addView(indicators[i]);
        }
    }

    private  void  setCurrentGuideIndicators(int index){
        int childCount = layoutGuideIndicators.getChildCount();
        for(int i = 0; i < childCount;i++){
            ImageView imageView = (ImageView) layoutGuideIndicators.getChildAt(i);
            if(i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.guide_indicator_active)
                );
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.guide_indicator_inactive)
                );
            }
        }
        if(index == guideAdapter.getItemCount() - 1){
            buttonGuideAction.setText("Начать");
        } else {
            buttonGuideAction.setText("Продолжить");
        }
    }
}
