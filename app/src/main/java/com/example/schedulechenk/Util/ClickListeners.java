package com.example.schedulechenk.Util;

import com.example.schedulechenk.models.ComplexModel;
import com.example.schedulechenk.models.CourseModel;
import com.example.schedulechenk.models.GroupModel;

public interface ClickListeners {
    void onComplexClick(ComplexModel complexModel);
    void onCourseClick(CourseModel courseModel);
    void onGroupClick(GroupModel groupModel);
}
