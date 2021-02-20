package com.example.schedulechenk.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulechenk.R;
import com.example.schedulechenk.databinding.ItemContainerScheduleBinding;
import com.example.schedulechenk.models.PairModel;

import java.util.List;

public class WeeklyRecyclerScheduleAdapter extends RecyclerView.Adapter<WeeklyRecyclerScheduleAdapter.WeeklyRecyclerScheduleViewHolder> {

    private List<PairModel> pairModels;
    private LayoutInflater layoutInflater;

    public WeeklyRecyclerScheduleAdapter(List<PairModel> pairModels) {
        this.pairModels = pairModels;
    }

    @NonNull
    @Override
    public WeeklyRecyclerScheduleAdapter.WeeklyRecyclerScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ItemContainerScheduleBinding pairBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_container_schedule, parent, false
        );
        return new WeeklyRecyclerScheduleAdapter.WeeklyRecyclerScheduleViewHolder(pairBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyRecyclerScheduleAdapter.WeeklyRecyclerScheduleViewHolder holder, int position) {
        holder.bindGroup(pairModels.get(position));
    }

    @Override
    public int getItemCount() {
        return pairModels.size();
    }

    class WeeklyRecyclerScheduleViewHolder extends RecyclerView.ViewHolder {

        private ItemContainerScheduleBinding itemContainerScheduleBinding;

        public WeeklyRecyclerScheduleViewHolder(ItemContainerScheduleBinding itemContainerScheduleBinding) {
            super(itemContainerScheduleBinding.getRoot());
            this.itemContainerScheduleBinding = itemContainerScheduleBinding;
        }

        public void bindGroup(PairModel pairModel) {
            itemContainerScheduleBinding.setPair(pairModel);
            itemContainerScheduleBinding.executePendingBindings();
        }
    }
}
