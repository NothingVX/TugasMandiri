package com.example.buangan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.UserViewHolder> {
    Context context;
    OnUserClickListener listener;
    List<ItemBean> itemBeanList;
    private SQLiteDatabase db;

    public RecyclerviewAdapter(Context context, List<ItemBean> itemBeanList, OnUserClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.itemBeanList = itemBeanList;
    }

    public	interface OnUserClickListener{
        void onUserClick(ItemBean currentPost, String action);
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_item,parent,false);
        UserViewHolder postViewHolder = new UserViewHolder(view);

        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        final ItemBean currentPost = itemBeanList.get(position);
        holder.judul.setText(currentPost.getJudul());
        holder.desk.setText(currentPost.getDesc());
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(currentPost, "Update");
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            listener.onUserClick(currentPost,"Delete");
        }
        });

    }

    @Override
    public int getItemCount() {
        return itemBeanList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView judul,desk;
        ImageView imgDelete,imgEdit;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            judul=itemView.findViewById(R.id.judul);
            desk=itemView.findViewById(R.id.desk);
            imgEdit=itemView.findViewById(R.id.imgedit);
            imgDelete=itemView.findViewById(R.id.imgdelete);
        }
    }

}
