package com.example.schedulechenk.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulechenk.R;
import com.example.schedulechenk.Util.ClickListeners;
import com.example.schedulechenk.databinding.ItemContainerCourseBinding;
import com.example.schedulechenk.models.CourseModel;
import com.example.schedulechenk.models.GroupModel;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<CourseModel> courseModels;
    private LayoutInflater layoutInflater;

    private ClickListeners clickListener;

    public CourseAdapter(List<CourseModel> courseModels, ClickListeners clickListener) {
        this.courseModels = courseModels;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ItemContainerCourseBinding courseBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_container_course, parent, false
        );
        return new CourseViewHolder(courseBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        holder.bindCourse(courseModels.get(position));
    }

    @Override
    public int getItemCount() {
        return courseModels.size();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {

        private ItemContainerCourseBinding itemContainerCourseBinding;

        public CourseViewHolder(ItemContainerCourseBinding itemContainerCourseBinding) {
            super(itemContainerCourseBinding.getRoot());
            this.itemContainerCourseBinding = itemContainerCourseBinding;
        }

        public void bindCourse(CourseModel courseModel) {
            itemContainerCourseBinding.setCourse(courseModel);
            itemContainerCourseBinding.executePendingBindings();
            itemContainerCourseBinding.getRoot().setOnClickListener(view -> clickListener.onCourseClick(courseModel));
        }
    }
}
