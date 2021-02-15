package com.example.schedulechenk.Util;

import com.example.schedulechenk.adapters.ComplexAdapter;
import com.example.schedulechenk.adapters.CourseAdapter;
import com.example.schedulechenk.adapters.GroupAdapter;
import com.example.schedulechenk.databinding.ActivityChoiseBinding;
import com.example.schedulechenk.models.ComplexModel;
import com.example.schedulechenk.models.CourseModel;
import com.example.schedulechenk.models.GroupModel;
import com.example.schedulechenk.parser.Parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;


public class RecyclerViewContentInitialization {

    private List<ComplexModel> complexModels;
    private ComplexAdapter complexAdapter;

    private List<CourseModel> courseModels;
    private CourseAdapter courseAdapter;

    private List<GroupModel> groupModels;
    private GroupAdapter groupAdapter;

    public void ComplexInitialization(ActivityChoiseBinding activityChoiseBinding, ClickListeners complexListener) {
        complexModels = new Parser().getComplexWeb();
        activityChoiseBinding.choiceRecyclerView.setHasFixedSize(true);
        complexAdapter = new ComplexAdapter(complexModels,complexListener);
        activityChoiseBinding.choiceRecyclerView.setAdapter(new AlphaInAnimationAdapter(complexAdapter));
        complexAdapter.notifyDataSetChanged();
    }

    public void CourseInitialization(ActivityChoiseBinding activityChoiseBinding, ClickListeners courseListener) {
        courseModels = new Parser().getCourse();
        activityChoiseBinding.choiceRecyclerView.setHasFixedSize(true);
        courseAdapter = new CourseAdapter(courseModels,courseListener);
        activityChoiseBinding.choiceRecyclerView.setAdapter(new AlphaInAnimationAdapter(courseAdapter));

        courseAdapter.notifyDataSetChanged();
    }

    public void GroupInitialization(ActivityChoiseBinding activityChoiseBinding,int complexId, String course, ClickListeners groupListener){
        groupModels = new Parser().getGroupWeb(complexId);

        List<GroupModel> sortedGroupModels = new ArrayList<>();

        for(GroupModel group: groupModels){
            if(group.getYear().equals(course)){
                sortedGroupModels.add(group);
            }
        }




        activityChoiseBinding.choiceRecyclerView.setHasFixedSize(true);
        groupAdapter = new GroupAdapter(sortedGroupModels,groupListener);
        activityChoiseBinding.choiceRecyclerView.setAdapter(new AlphaInAnimationAdapter(groupAdapter));

        courseAdapter.notifyDataSetChanged();
    }

}
