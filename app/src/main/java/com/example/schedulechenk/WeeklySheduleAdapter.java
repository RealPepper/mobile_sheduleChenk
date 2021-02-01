package com.example.schedulechenk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeeklySheduleAdapter extends  RecyclerView.Adapter<WeeklySheduleAdapter.WeeklySheduleViewHolder>{

   // private List<WeeklyCheduleItem> weeklyCheduleItems;

    /*public WeeklySheduleAdapter(List<WeeklyCheduleItem> weeklyCheduleItems) {
        this.weeklyCheduleItems = weeklyCheduleItems;
    }*/

    @NonNull
    @Override
    public WeeklySheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WeeklySheduleViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_page,parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklySheduleViewHolder holder, int position) {
       // holder.setWeeklySheduleData(weeklyCheduleItems.get(position));
    }

    @Override
    public int getItemCount() {
        return 0; //weeklyCheduleItems.size();
    }

    //////////////////////////////////////////////////////////////////
    class WeeklySheduleViewHolder extends RecyclerView.ViewHolder{



        WeeklySheduleViewHolder(@NonNull View itemView/*, String dayOfWeek*/) {
            super(itemView);


        }
    }
}
