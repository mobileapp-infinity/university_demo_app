package com.infinity.infoway.atmiya.student.news_or_notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.utils.CommonUtil;

import java.util.ArrayList;

public class ViewAllNewsOrNotificationAdapter extends RecyclerView.Adapter<ViewAllNewsOrNotificationAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<StudentNewsOrNotificationsPojo.Data> dataArrayList;

    public ViewAllNewsOrNotificationAdapter(Context context, ArrayList<StudentNewsOrNotificationsPojo.Data> dataArrayList) {
        this.context = context;
        this.dataArrayList = dataArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_view_all_news_or_notification, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StudentNewsOrNotificationsPojo.Data data = dataArrayList.get(position);

        if (data.getNt_head() != null && !data.getNt_head().isEmpty()) {
            holder.tvNotificationTitle.setText(data.getNt_head());
        }

        if (data.getNt_date() != null && !data.getNt_date().isEmpty()) {
            String date = "";
            try {
                if (data.getNt_date().contains("/")) {
                    String dateArray[] = data.getNt_date().split("/");
                    date = dateArray[0] + " " + CommonUtil.getMonthSortNameFromNumber(Integer.parseInt(dateArray[1])) + "," + dateArray[2];
                }
                holder.tvNotificationDate.setText(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (data.getNt_desc() != null && !data.getNt_desc().isEmpty()) {
            holder.tvNotificationDesc.setText(data.getNt_desc());
        }
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewRegularFont tvNotificationDate;
        TextViewRegularFont tvNotificationTitle;
        TextViewRegularFont tvNotificationDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNotificationDate = itemView.findViewById(R.id.tvNotificationDate);
            tvNotificationTitle = itemView.findViewById(R.id.tvNotificationTitle);
            tvNotificationDesc = itemView.findViewById(R.id.tvNotificationDesc);
        }
    }

}
