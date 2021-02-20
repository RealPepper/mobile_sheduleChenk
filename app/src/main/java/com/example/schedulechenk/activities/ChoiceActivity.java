package com.example.schedulechenk.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
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
import com.example.schedulechenk.fragments.Weekly;
import com.example.schedulechenk.models.ComplexModel;
import com.example.schedulechenk.models.CourseModel;
import com.example.schedulechenk.models.GroupModel;

import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import jp.wasabeef.recyclerview.animators.FadeInDownAnimator;


public class ChoiceActivity extends AppCompatActivity implements ClickListeners {


    private int complexId;

    public boolean isComplex = false,
                   isCourse = false,
                   isGroup = false;

    private static final String PREFERENCES_NAME = "user_choice";
    SharedPreferences sharedPreferences;

    private ActivityChoiseBinding activityChoiseBinding;
    private RecyclerViewContentInitialization recyclerInitialization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choise);

        recyclerInitialization = new RecyclerViewContentInitialization();
        activityChoiseBinding = DataBindingUtil.setContentView(this, R.layout.activity_choise);
        recyclerInitialization.ComplexInitialization(activityChoiseBinding,this,this);
    }

    @Override
    public void onComplexClick(ComplexModel complexModel) {
        complexId = complexModel.getComplexID();
        recyclerInitialization.CourseInitialization(activityChoiseBinding,this, this);

        setSharedPreferences("complexId",complexId);
    }

    @Override
    public void onCourseClick(CourseModel courseModel) {
        recyclerInitialization.GroupInitialization(activityChoiseBinding, complexId, courseModel.getCourse(), this,this);
    }

    @Override
    public void onGroupClick(GroupModel groupModel) {
        setSharedPreferences("group",groupModel.getGroupId());
        Intent intent= new Intent(this, Weekly.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        if(isComplex){
            this.finish();
        }
        if (isCourse){
            recyclerInitialization.ComplexInitialization(activityChoiseBinding,this,this);
        }

        if(isGroup){
            recyclerInitialization.CourseInitialization(activityChoiseBinding,this, this);
        }
    }

    public void setSharedPreferences(String name,String value){
        sharedPreferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name, value);
        editor.apply();
    }
    public void setSharedPreferences(String name,int value){
        sharedPreferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(name, value);
        editor.apply();
    }

}