package com.example.schedulechenk.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulechenk.GuideItem;
import com.example.schedulechenk.R;
import com.example.schedulechenk.Util.ClickListeners;
import com.example.schedulechenk.databinding.ItemContainerCourseBinding;
import com.example.schedulechenk.databinding.ItemContainerGroupBinding;
import com.example.schedulechenk.databinding.ItemContainerScheduleBinding;
import com.example.schedulechenk.databinding.ItemRecyclerContainerScheduleBinding;
import com.example.schedulechenk.models.GroupModel;
import com.example.schedulechenk.models.ScheduleModel;

import java.util.List;

public class WeeklyPagerScheduleAdapter extends RecyclerView.Adapter<WeeklyPagerScheduleAdapter.WeeklyPagerScheduleViewHolder>{

    private List<ScheduleModel> scheduleModels;
    private LayoutInflater layoutInflater;

    WeeklyRecyclerScheduleAdapter weeklyRecyclerScheduleAdapter;


    public WeeklyPagerScheduleAdapter(List<ScheduleModel> scheduleModels) {this.scheduleModels = scheduleModels;}

    @NonNull
    @Override
    public WeeklyPagerScheduleAdapter.WeeklyPagerScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ItemRecyclerContainerScheduleBinding scheduleBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_recycler_container_schedule, parent, false
        );
        return new WeeklyPagerScheduleAdapter.WeeklyPagerScheduleViewHolder(scheduleBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyPagerScheduleAdapter.WeeklyPagerScheduleViewHolder holder, int position) {
        holder.bindSchedule(scheduleModels.get(position));
    }

    @Override
    public int getItemCount() {
        return scheduleModels.size();
    }



    class WeeklyPagerScheduleViewHolder extends RecyclerView.ViewHolder {

        private ItemRecyclerContainerScheduleBinding itemRecyclerContainerScheduleBinding;

        WeeklyPagerScheduleViewHolder(ItemRecyclerContainerScheduleBinding itemRecyclerContainerScheduleBinding) {
            super(itemRecyclerContainerScheduleBinding.getRoot());
            this.itemRecyclerContainerScheduleBinding = itemRecyclerContainerScheduleBinding;

        }

        void  bindSchedule(ScheduleModel scheduleModel){
            itemRecyclerContainerScheduleBinding.setSchecdule(scheduleModel);
            itemRecyclerContainerScheduleBinding.executePendingBindings();

            weeklyRecyclerScheduleAdapter = new WeeklyRecyclerScheduleAdapter(scheduleModel.getPairModels());
            itemRecyclerContainerScheduleBinding.scheduleRecyclerView.setAdapter(weeklyRecyclerScheduleAdapter);

        }
    }

}
