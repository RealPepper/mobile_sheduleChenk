package com.example.schedulechenk.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulechenk.R;
import com.example.schedulechenk.Util.ClickListeners;
import com.example.schedulechenk.databinding.ItemContainerGroupBinding;
import com.example.schedulechenk.models.GroupModel;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {

    private List<GroupModel> groupModels;
    private LayoutInflater layoutInflater;

    private ClickListeners clickListener;

    public GroupAdapter(List<GroupModel> groupModels, ClickListeners clickListener) {
        this.groupModels = groupModels;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public GroupAdapter.GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ItemContainerGroupBinding groupBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_container_group, parent, false
        );
        return new GroupAdapter.GroupViewHolder(groupBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.GroupViewHolder holder, int position) {
        holder.bindGroup(groupModels.get(position));
    }

    @Override
    public int getItemCount() {
        return groupModels.size();
    }

     class GroupViewHolder extends RecyclerView.ViewHolder {

        private ItemContainerGroupBinding itemContainerGroupBinding;

        public GroupViewHolder(ItemContainerGroupBinding itemContainerGroupBinding) {
            super(itemContainerGroupBinding.getRoot());
            this.itemContainerGroupBinding = itemContainerGroupBinding;
        }

        public void bindGroup(GroupModel groupModel) {
            itemContainerGroupBinding.setGroup(groupModel);
            itemContainerGroupBinding.executePendingBindings();
            itemContainerGroupBinding.getRoot().setOnClickListener(View-> clickListener.onGroupClick(groupModel));
        }
    }
}
