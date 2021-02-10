package com.example.schedulechenk.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulechenk.R;
import com.example.schedulechenk.databinding.ItemContainerComplexBinding;
import com.example.schedulechenk.models.ComplexModel;

import java.util.List;

public class ComplexAdapter extends RecyclerView.Adapter<ComplexAdapter.ComplexViewHolder> {

    private List<ComplexModel> complexModels;
    private LayoutInflater layoutInflater;

    public ComplexAdapter(List<ComplexModel> complexModels) {
        this.complexModels = complexModels;
    }


    @NonNull
    @Override
    public ComplexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ItemContainerComplexBinding complexBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_container_complex, parent, false
        );
        return new ComplexViewHolder(complexBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplexViewHolder holder, int position) {
        holder.bindComplex(complexModels.get(position));
    }

    @Override
    public int getItemCount() {
        return complexModels.size();
    }

    static class ComplexViewHolder extends RecyclerView.ViewHolder {

        private ItemContainerComplexBinding itemContainerComplexBinding;

        public ComplexViewHolder(ItemContainerComplexBinding itemContainerComplexBinding) {
            super(itemContainerComplexBinding.getRoot());
            this.itemContainerComplexBinding = itemContainerComplexBinding;
        }

        public void bindComplex(ComplexModel complexModel) {
            itemContainerComplexBinding.setComplex(complexModel);
            itemContainerComplexBinding.executePendingBindings();
        }
    }
}
