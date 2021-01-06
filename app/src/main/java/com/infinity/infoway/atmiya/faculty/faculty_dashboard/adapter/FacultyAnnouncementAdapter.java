package com.infinity.infoway.atmiya.faculty.faculty_dashboard.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.faculty.faculty_announcement.FacultyAnnouncementActivity;
import com.infinity.infoway.atmiya.faculty.faculty_dashboard.pojo.FacultyAnnouncementPojo;
import com.infinity.infoway.atmiya.student.news_or_notification.ViewAllNewsOrNotificationActivity;
import com.infinity.infoway.atmiya.utils.CommonUtil;

import java.util.ArrayList;

public class FacultyAnnouncementAdapter extends RecyclerView.Adapter<FacultyAnnouncementAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<FacultyAnnouncementPojo> facultyAnnouncementPojoArrayList;

    public FacultyAnnouncementAdapter(Context context, ArrayList<FacultyAnnouncementPojo> facultyAnnouncementPojoArrayList) {
        this.context = context;
        this.facultyAnnouncementPojoArrayList = facultyAnnouncementPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_faculty_announcement_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        FacultyAnnouncementPojo facultyAnnouncementPojo = facultyAnnouncementPojoArrayList.get(position);

        if (facultyAnnouncementPojo.getNotifHead() != null && !facultyAnnouncementPojo.getNotifHead().isEmpty()) {
            holder.tvNotificationHeadFacultySide.setText(facultyAnnouncementPojo.getNotifHead());
        }

        if (facultyAnnouncementPojo.getNotifDate() != null && !facultyAnnouncementPojo.getNotifDate().isEmpty()) {
            String date = "";
            try {
                if (facultyAnnouncementPojo.getNotifDate().contains("/")) {
                    String dateArray[] = facultyAnnouncementPojo.getNotifDate().split("/");
                    date = dateArray[0] + " " + CommonUtil.getMonthSortNameFromNumber(Integer.parseInt(dateArray[1])) + "," + "\n" + dateArray[2];
                }
                holder.tvNotificationDateFacultySide.setText(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (facultyAnnouncementPojo.getNotifMsg() != null && !facultyAnnouncementPojo.getNotifMsg().isEmpty()) {
            holder.tvnotificationDescriptionFacultySide.setText(facultyAnnouncementPojo.getNotifMsg());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FacultyAnnouncementActivity.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return facultyAnnouncementPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewRegularFont tvNotificationHeadFacultySide;
        TextViewRegularFont tvNotificationDateFacultySide;
        TextViewRegularFont tvnotificationDescriptionFacultySide;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNotificationHeadFacultySide = itemView.findViewById(R.id.tvNotificationHeadFacultySide);
            tvNotificationDateFacultySide = itemView.findViewById(R.id.tvNotificationDateFacultySide);
            tvnotificationDescriptionFacultySide = itemView.findViewById(R.id.tvnotificationDescriptionFacultySide);
        }
    }

}
