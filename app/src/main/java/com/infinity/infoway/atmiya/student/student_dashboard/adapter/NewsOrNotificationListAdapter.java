package com.infinity.infoway.atmiya.student.student_dashboard.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.student.news_or_notification.StudentNewsOrNotificationsPojo;
import com.infinity.infoway.atmiya.student.news_or_notification.ViewAllNewsOrNotificationActivity;
import com.infinity.infoway.atmiya.student.student_dashboard.activity.StudentDashboardActivity;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.IntentConstants;

public class NewsOrNotificationListAdapter extends RecyclerView.Adapter<NewsOrNotificationListAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    StudentNewsOrNotificationsPojo studentNewsOrNotificationList;

    public NewsOrNotificationListAdapter(Context context, StudentNewsOrNotificationsPojo studentNewsOrNotificationList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.studentNewsOrNotificationList = studentNewsOrNotificationList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_student_news_or_notifications_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StudentNewsOrNotificationsPojo.Data data = studentNewsOrNotificationList.getTable().get(position);

        if (data.getNt_head() != null && !data.getNt_head().isEmpty()) {
            holder.tvNewsOrNotificationTitle.setText(data.getNt_head());
        }

        if (data.getNt_date() != null && !data.getNt_date().isEmpty()) {
            String date = "";
            try {
                if (data.getNt_date().contains("/")) {
                    String dateArray[] = data.getNt_date().split("/");
                    date = dateArray[0] + " " + CommonUtil.getMonthSortNameFromNumber(Integer.parseInt(dateArray[1])) + "," + "\n" + dateArray[2];
                }
                holder.tvNewsOrNotificationDate.setText(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (data.getNt_desc() != null && !data.getNt_desc().isEmpty()) {
            holder.tvNewsOrNotificationDescription.setText(data.getNt_desc());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewAllNewsOrNotificationActivity.class);
                ((StudentDashboardActivity)context).startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_VIEW_ALL_NEWS_OR_NOTIFICATION);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentNewsOrNotificationList.getTable().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewRegularFont tvNewsOrNotificationTitle;
        TextViewRegularFont tvNewsOrNotificationDate;
        TextViewRegularFont tvNewsOrNotificationDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNewsOrNotificationTitle = itemView.findViewById(R.id.tvNewsOrNotificationTitle);
            tvNewsOrNotificationDate = itemView.findViewById(R.id.tvNewsOrNotificationDate);
            tvNewsOrNotificationDescription = itemView.findViewById(R.id.tvNewsOrNotificationDescription);
        }
    }

}
