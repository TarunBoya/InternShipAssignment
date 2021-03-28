package com.tarun.internassignment.ViewAdapters;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tarun.internassignment.Database.CrewDao;
import com.tarun.internassignment.Database.CrewViewModel;
import com.tarun.internassignment.Retrofits.Converter;
import com.tarun.internassignment.Models.CrewDataModel;
import com.tarun.internassignment.Models.RoomCrewModel;
import com.tarun.internassignment.R;

import java.io.IOException;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerHolder> {

    Application application;
    RoomCrewModel roomCrewModel;
    CrewViewModel crewRep;
    List<CrewDataModel> dataModelList;
    Context context;
    public RecyclerViewAdapter(List<CrewDataModel> dataModelList, Context context, Application application) {
        this.dataModelList = dataModelList;
        this.context = context;
        this.application = application;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crew_card, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        holder.name_tv.setText(dataModelList.get(position).getName());
        holder.agency_tv.setText(dataModelList.get(position).getAgency());
        holder.status_tv.setText(dataModelList.get(position).getStatus());
        holder.wiki_tv.setText(dataModelList.get(position).getWikipedia());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.build().load(dataModelList.get(position).getImage()).into(holder.image);


        crewRep = new CrewViewModel(application);
        byte[] bytes = new byte[0];
        try {
            bytes = Converter.BitMapToByte(MediaStore.Images.Media.getBitmap(context.getContentResolver() , Uri.parse(dataModelList.get(position).getImage())));
        } catch (IOException e) {
            e.printStackTrace();
        }
            roomCrewModel = new RoomCrewModel(dataModelList.get(position), bytes);
            crewRep.insert(roomCrewModel);
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder{

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
