package com.example.schedulechenk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeeklySheduleAdapter extends  RecyclerView.Adapter<WeeklySheduleAdapter.WeeklySheduleViewHolder>{

    private List<WeeklyCheduleItem> weeklyCheduleItems;

    public WeeklySheduleAdapter(List<WeeklyCheduleItem> weeklyCheduleItems) {
        this.weeklyCheduleItems = weeklyCheduleItems;
    }

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
        holder.setWeeklySheduleData(weeklyCheduleItems.get(position));
    }

    @Override
    public int getItemCount() {
        return weeklyCheduleItems.size();
    }

    //////////////////////////////////////////////////////////////////
    class WeeklySheduleViewHolder extends RecyclerView.ViewHolder{

        private TextView dayOfWeek;
        private TextView dayOfWeekData;


        private TextView coupeOne;
        private TextView coupeTwo;
        private TextView coupeThree;
        private TextView coupeFour;


        private TextView cabinetOne;
        private TextView cabinetTwo;
        private TextView cabinetThree;
        private TextView cabinetFour;

        private TextView replaceOrCancelOne;
        private TextView replaceOrCancelTwo;
        private TextView replaceOrCancelThree;
        private TextView replaceOrCancelFour;

        WeeklySheduleViewHolder(@NonNull View itemView/*, String dayOfWeek*/) {
            super(itemView);

            dayOfWeek = itemView.findViewById(R.id.today);
            dayOfWeekData = itemView.findViewById(R.id.todayData);

            coupeOne = itemView.findViewById(R.id.coupeOne);
            coupeTwo = itemView.findViewById(R.id.coupeTwo);
            coupeThree = itemView.findViewById(R.id.coupeThree);
            coupeFour = itemView.findViewById(R.id.coupeFour);

            cabinetOne = itemView.findViewById(R.id.cabinetOne);
            cabinetTwo = itemView.findViewById(R.id.cabinetTwo);
            cabinetThree = itemView.findViewById(R.id.cabinetThree);
            cabinetFour = itemView.findViewById(R.id.cabinetFour);

            replaceOrCancelOne= itemView.findViewById(R.id.replaceOrCancelOne);
            replaceOrCancelTwo= itemView.findViewById(R.id.replaceOrCancelTwo);
            replaceOrCancelThree= itemView.findViewById(R.id.replaceOrCancelThree);
            replaceOrCancelFour = itemView.findViewById(R.id.replaceOrCancelFour);
            //this.dayOfWeek = dayOfWeek;
        }

        void setWeeklySheduleData(WeeklyCheduleItem weeklyCheduleItem){
            dayOfWeek.setText(weeklyCheduleItem.getDayOfWeek());
            dayOfWeekData.setText(weeklyCheduleItem.getDayOfWeekData());
            coupeOne.setText(weeklyCheduleItem.getCoupeOne());
            coupeTwo.setText(weeklyCheduleItem.getCoupeTwo());
            coupeThree.setText(weeklyCheduleItem.getCoupeThree());
            coupeFour.setText(weeklyCheduleItem.getCoupeFour());
            cabinetOne.setText(weeklyCheduleItem.getCabinetOne());
            cabinetTwo.setText(weeklyCheduleItem.getCabinetTwo());
            cabinetThree.setText(weeklyCheduleItem.getCabinetThree());
            cabinetFour.setText(weeklyCheduleItem.getCabinetFour());
            replaceOrCancelOne.setText(weeklyCheduleItem.getReplaceOrCancelOne());
            replaceOrCancelTwo.setText(weeklyCheduleItem.getReplaceOrCancelTwo());
            replaceOrCancelThree.setText(weeklyCheduleItem.getReplaceOrCancelThree());
            replaceOrCancelFour.setText(weeklyCheduleItem.getReplaceOrCancelFour());
        }
    }
}
