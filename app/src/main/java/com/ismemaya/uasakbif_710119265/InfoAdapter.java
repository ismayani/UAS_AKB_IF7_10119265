package com.ismemaya.uasakbif_710119265;

// 10119265_IsmayaniSetyaningrum_IF7

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {

    ArrayList<InfoObject> infoObjects;
    public InfoAdapter(ArrayList<InfoObject> infoObjects){
        this.infoObjects = infoObjects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_items,
                parent,
                false);
        return new ViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InfoObject infoObjects = this.infoObjects.get(position);
        holder.inObject.setImageResource(infoObjects.imgObject);
        holder.inInfo.setText(infoObjects.txInfo);
    }

    @Override
    public int getItemCount() {
        return infoObjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView inObject;
        TextView inInfo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            inObject = itemView.findViewById(R.id.info_list);
            inInfo = itemView.findViewById(R.id.penjelasan_list);
        }
    }
}
