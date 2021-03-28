package com.tarun.internassignment.ViewAdapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tarun.internassignment.Retrofits.Converter;
import com.tarun.internassignment.Models.RoomCrewModel;
import com.tarun.internassignment.R;

import java.util.List;

public class RoomCrewAdapter extends RecyclerView.Adapter<RoomCrewAdapter.RecyclerHolder> {

    List<RoomCrewModel> dataModelList;
    Context context;
    Bitmap bitmap;

    public RoomCrewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RoomCrewAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crew_card, parent, false);
        return new RoomCrewAdapter.RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomCrewAdapter.RecyclerHolder holder, int position) {
        holder.name_tv.setText(dataModelList.get(position).getName());
        holder.agency_tv.setText(dataModelList.get(position).getAgency());
        holder.status_tv.setText(dataModelList.get(position).getStatus());
        holder.wiki_tv.setText(dataModelList.get(position).getWiki());
        holder.image.setImageBitmap(Converter.ByteToBitMap(dataModelList.get(position).getImage()));
    }

    public void setCrews(List<RoomCrewModel> roomCrewModels){
        this.dataModelList = roomCrewModels;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder {

        TextView name_tv, agency_tv, status_tv, wiki_tv;
        ImageView image;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            name_tv = itemView.findViewById(R.id.name_tv);
            agency_tv = itemView.findViewById(R.id.agency_tv);
            status_tv = itemView.findViewById(R.id.status_tv);
            wiki_tv = itemView.findViewById(R.id.wiki_tv);
            image = itemView.findViewById(R.id.image_iv);
        }
    }
}
