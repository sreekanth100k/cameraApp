package com.app.openweather.mygateapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailsListAdapter extends RecyclerView.Adapter<DetailsListAdapter.DetailsViewHolder> {

    ArrayList<String> mMyDetailsList;
    Context mContext;

    public DetailsListAdapter(ArrayList<String> iMyDetailsList, Context iContext) {
        this.mMyDetailsList =   iMyDetailsList;
        this.mContext       =   iContext;

    }

    @Override
    public DetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v                              =   LayoutInflater.from(mContext).inflate(R.layout.my_details_list_item,parent,false);
        DetailsViewHolder viewHolder        =   new DetailsViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DetailsListAdapter.DetailsViewHolder holder, int position) {
        holder.imageIv.setImageResource(R.drawable.ic_launcher_background);
        String userName     =    mMyDetailsList.get(position);
        holder.idTv.setText(userName);
    }

    @Override
    public int getItemCount() {
        return mMyDetailsList.size();
    }

    public static class DetailsViewHolder extends RecyclerView.ViewHolder{

        protected ImageView imageIv;
        protected TextView idTv;

        public DetailsViewHolder(View itemView) {
            super(itemView);

            imageIv     = (ImageView)   itemView.findViewById(R.id.id_user_iv);
            idTv        = (TextView)    itemView.findViewById(R.id.id_passcode_tv);

        }
    }
}
