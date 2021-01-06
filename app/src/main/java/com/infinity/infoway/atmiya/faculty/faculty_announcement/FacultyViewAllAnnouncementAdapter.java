package com.infinity.infoway.atmiya.faculty.faculty_announcement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.faculty.faculty_dashboard.pojo.FacultyAnnouncementPojo;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.DownloadPdfFromUrl;

import java.util.ArrayList;

public class FacultyViewAllAnnouncementAdapter extends RecyclerView.Adapter<FacultyViewAllAnnouncementAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<FacultyAnnouncementPojo> facultyAnnouncementPojoArrayList;

    public FacultyViewAllAnnouncementAdapter(Context context, ArrayList<FacultyAnnouncementPojo> facultyAnnouncementPojoArrayList) {
        this.context = context;
        this.facultyAnnouncementPojoArrayList = facultyAnnouncementPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_layout_view_all_faculty_announcement, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FacultyAnnouncementPojo facultyAnnouncementPojo = facultyAnnouncementPojoArrayList.get(position);

        if (facultyAnnouncementPojo.getNotifHead() != null && !facultyAnnouncementPojo.getNotifHead().isEmpty()) {
            holder.tvFacultyNotificationTitle.setText(facultyAnnouncementPojo.getNotifHead());
        }

        if (facultyAnnouncementPojo.getNotifDate() != null && !facultyAnnouncementPojo.getNotifDate().isEmpty()) {
            String date = "";
            try {
                if (facultyAnnouncementPojo.getNotifDate().contains("/")) {
                    String dateArray[] = facultyAnnouncementPojo.getNotifDate().split("/");
                    date = dateArray[0] + " " + CommonUtil.getMonthSortNameFromNumber(Integer.parseInt(dateArray[1])) + "," + "\n" + dateArray[2];
                }
                holder.tvFacultyNotificationDate.setText(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (facultyAnnouncementPojo.getNotifMsg() != null && !facultyAnnouncementPojo.getNotifMsg().isEmpty()) {
            holder.tvFacultyNotificationDesc.setText(facultyAnnouncementPojo.getNotifMsg());
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyAnnouncementPojo.getAppFilePath())) {
            holder.imgDownloadFacultyAnnouncementDoc.setVisibility(View.VISIBLE);

            holder.imgDownloadFacultyAnnouncementDoc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String fileUrl = facultyAnnouncementPojo.getAppFilePath();
                    String fileExtension = fileUrl.substring(fileUrl.lastIndexOf("."));
                    new DownloadPdfFromUrl(context, facultyAnnouncementPojo.getAppFilePath().trim(), fileExtension, "Faculty Announcement");
                }
            });


        } else {
            holder.imgDownloadFacultyAnnouncementDoc.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return facultyAnnouncementPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewRegularFont tvFacultyNotificationTitle;
        TextViewRegularFont tvFacultyNotificationDesc;
        TextViewRegularFont tvFacultyNotificationDate;
        AppCompatImageView imgDownloadFacultyAnnouncementDoc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFacultyNotificationTitle = itemView.findViewById(R.id.tvFacultyNotificationTitle);
            tvFacultyNotificationDesc = itemView.findViewById(R.id.tvFacultyNotificationDesc);
            tvFacultyNotificationDate = itemView.findViewById(R.id.tvFacultyNotificationDate);
            imgDownloadFacultyAnnouncementDoc = itemView.findViewById(R.id.imgDownloadFacultyAnnouncementDoc);
        }
    }

}
