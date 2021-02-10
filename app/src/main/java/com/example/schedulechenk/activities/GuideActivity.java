package com.example.schedulechenk.activities;

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

import com.example.schedulechenk.adapters.GuideAdapter;
import com.example.schedulechenk.GuideItem;
import com.example.schedulechenk.R;

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
                    startActivity(new Intent(getApplicationContext(), ChoiceActivity.class));
                    finish();
                }
            }
        });
    }


    private void  setupGuideItems(){
        List<GuideItem> guideItems = new ArrayList<>();

        GuideItem itemHowStart = new GuideItem();
        itemHowStart.setImage(R.drawable.frst_guide_image);
        itemHowStart.setTitleText("Для начала работы");
        itemHowStart.setDescriptionText("Выберите учебный комплекс, после выберите вашу группу");

        GuideItem itemHowUse = new GuideItem();
        itemHowUse.setImage(R.drawable.second_guide_image);
        itemHowUse.setTitleText("Как пользоваться");
        itemHowUse.setDescriptionText("После запуска приложения, вы увидите расписание на день. Нажав на вкладу слева, вы увидите расписание на неделю, для перелистывания используются свайпы");

        GuideItem itemHowSetting = new GuideItem();
        itemHowSetting.setImage(R.drawable.thrd_guide_image);
        itemHowSetting.setTitleText("Настройки");
        itemHowSetting.setDescriptionText("В настройках можно изменить группу, а так же учебный комплекс");

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
            buttonGuideAction.setText("Далее");
        }
    }
}
