package com.srfl.gibi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class AdapterUser extends RecyclerView.Adapter<AdapterUser.MyHolder> {
    Context context;
    List<ModelUser> userList;
    AdapterUser adapterUser;

    public AdapterUser(Context context, List<ModelUser> userList) {
        this.context = context;
        this.userList = userList;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_users, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String userImage = userList.get(position).getImage();
        String userName = userList.get(position).getName();
        String userEmail = userList.get(position).getEmail();
        String userId = userList.get(position).getId();
        Log.e("User Name", userName);
        holder.tvName.setText(userName);
        holder.tvEmail.setText(userEmail);
        holder.tvId.setText(userId);
        try {
            Picasso.get().load(userImage).placeholder(R.mipmap.ic_row_avatar).into(holder.ivAvatar);

        } catch (Exception e){

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+userEmail, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        ImageView ivAvatar;
        TextView tvName, tvEmail, tvId;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.avatar_iv);
            tvName = itemView.findViewById(R.id.name_tv);
            tvEmail = itemView.findViewById(R.id.email_tv);
            tvId = itemView.findViewById(R.id.id_tv);
        }
    }



}
