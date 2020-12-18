package com.infinity.infoway.atmiya.login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.atmiya.R;

public class LoginUserListAdapter extends RecyclerView.Adapter<LoginUserListAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;

    public LoginUserListAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_login_user_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout view_background, view_foreground;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view_background = itemView.findViewById(R.id.view_background);
            view_foreground = itemView.findViewById(R.id.view_foreground);
        }
    }
}
