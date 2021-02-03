package com.example.schedulechenk;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.GuideViewHolder>{

    private List<GuideItem> guideItems;

    public GuideAdapter(List<GuideItem> guideItems) {
        this.guideItems = guideItems;
    }

    @NonNull
    @Override
    public GuideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GuideViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_guide,parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull GuideViewHolder holder, int position) {
        holder.setGuideData((guideItems.get(position)));
    }

    @Override
    public int getItemCount() {
        return guideItems.size();
    }



    class GuideViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageGuide;
        private TextView titleText;
        private TextView descriptionText;


        GuideViewHolder(@NonNull View itemView) {
            super(itemView);
            imageGuide = itemView.findViewById(R.id.imageGuide);
            titleText = itemView.findViewById(R.id.guide_item_textTitle);
            descriptionText = itemView.findViewById(R.id.guide_item_textDescription);
        }

        void  setGuideData(GuideItem guideItem){
            imageGuide.setImageResource(guideItem.getImage());
            titleText.setText(guideItem.getTitleText());
            descriptionText.setText(guideItem.getDescriptionText());
        }


    }
}
