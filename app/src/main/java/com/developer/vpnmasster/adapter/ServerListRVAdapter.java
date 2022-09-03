package com.developer.vpnmasster.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.developer.vpnmasster.interfaces.NavItemClickListener;
import com.developer.vpnmasster.model.Server;
import com.developer.vpnmasster.R;

import java.util.ArrayList;

public class ServerListRVAdapter extends RecyclerView.Adapter<ServerListRVAdapter.MyViewHolder> {

    private final ArrayList<Server> serverLists;
    private final Context mContext;
    private final NavItemClickListener listener;

    public ServerListRVAdapter(ArrayList<Server> serverLists, Context context) {
        this.serverLists = serverLists;
        this.mContext = context;
        listener = (NavItemClickListener) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.server_list_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.serverCountry.setText(serverLists.get(position).getCountry());
        Glide.with(mContext)
                .load(serverLists.get(position).getFlagUrl())
                .into(holder.serverIcon);

        holder.serverItemLayout.setOnClickListener(v -> listener.clickedItem(position));
    }

    @Override
    public int getItemCount() {
        return serverLists.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        final LinearLayout serverItemLayout;
        final ImageView serverIcon;
        final TextView serverCountry;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            serverItemLayout = itemView.findViewById(R.id.serverItemLayout);
            serverIcon = itemView.findViewById(R.id.iconImg);
            serverCountry = itemView.findViewById(R.id.countryTv);
        }
    }
}
