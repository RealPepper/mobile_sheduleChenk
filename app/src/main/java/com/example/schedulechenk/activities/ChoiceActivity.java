package com.example.schedulechenk.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.schedulechenk.Util.ClickListeners;
import com.example.schedulechenk.Util.RecyclerViewContentInitialization;
import com.example.schedulechenk.databinding.ActivityChoiseBinding;

import com.example.schedulechenk.R;
import com.example.schedulechenk.models.ComplexModel;
import com.example.schedulechenk.models.CourseModel;
import com.example.schedulechenk.models.GroupModel;

import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import jp.wasabeef.recyclerview.animators.FadeInDownAnimator;


public class ChoiceActivity extends AppCompatActivity implements ClickListeners {

    //initialization
    private static final String PREFERENCES_NAME = "check_launch";
    private int complexId;

    Dialog firstLaunchDialog;

    public boolean isComplex = false,
                   isCourse = false,
                   isGroup = false;

    private ActivityChoiseBinding activityChoiseBinding;
    private RecyclerViewContentInitialization recyclerInitialization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choise);

        //проверка на первый вход в приложение
        //checkFirstLaunch();

        recyclerInitialization = new RecyclerViewContentInitialization();

        activityChoiseBinding = DataBindingUtil.setContentView(this, R.layout.activity_choise);

        recyclerInitialization.ComplexInitialization(activityChoiseBinding,this,this);
    }

    @Override
    public void onComplexClick(ComplexModel complexModel) {
        complexId = complexModel.getComplexID();
        recyclerInitialization.CourseInitialization(activityChoiseBinding,this, this);

    }

    @Override
    public void onCourseClick(CourseModel courseModel) {
        recyclerInitialization.GroupInitialization(activityChoiseBinding, complexId, courseModel.getCourse(), this,this);
    }

    @Override
    public void onGroupClick(GroupModel groupModel) {

    }

    @Override
    public void onBackPressed(){
        if(isComplex == true){
            this.finish();

        }
        if (isCourse == true){
            recyclerInitialization.ComplexInitialization(activityChoiseBinding,this,this);

        }

        if(isGroup == true){
            recyclerInitialization.CourseInitialization(activityChoiseBinding,this, this);

        }

    }
    private void checkFirstLaunch() {
        SharedPreferences sp = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);

        boolean hasVisited = sp.getBoolean("hasVisited", false);

        if (!hasVisited) {

            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.commit();
        }

    }
}