package com.infinity.infoway.atmiya.faculty.faculty_announcement;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.student.news_or_notification.FacultyOrStudentNewsOrNotificationsPojo;
import com.infinity.infoway.atmiya.student.news_or_notification.UpdateNotificationStatusPojo;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.DialogUtil;
import com.infinity.infoway.atmiya.utils.DownloadPdfFromUrl;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyViewAllAnnouncementAdapter extends RecyclerView.Adapter<FacultyViewAllAnnouncementAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<FacultyOrStudentNewsOrNotificationsPojo.Data> facultyAnnouncementPojoArrayList;
    FacultyViewAllAnnouncementAdapter.IRemoveStudentNewsOrNotification iRemoveStudentNewsOrNotification;
    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;

    public FacultyViewAllAnnouncementAdapter(Context context, ArrayList<FacultyOrStudentNewsOrNotificationsPojo.Data> facultyAnnouncementPojoArrayList) {
        this.context = context;
        this.facultyAnnouncementPojoArrayList = facultyAnnouncementPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
        iRemoveStudentNewsOrNotification = (FacultyAnnouncementActivity) context;
        mySharedPreferences = new MySharedPreferences(context);
        connectionDetector = new ConnectionDetector(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_layout_view_all_faculty_announcement, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FacultyOrStudentNewsOrNotificationsPojo.Data data = facultyAnnouncementPojoArrayList.get(position);

        holder.cbMarkAsReadStudentNotification.setChecked(false);
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

        if (data.getNt_is_notif() == 1) {
            holder.cbMarkAsReadStudentNotification.setVisibility(View.VISIBLE);
        } else {
            holder.cbMarkAsReadStudentNotification.setVisibility(View.GONE);
        }

        holder.cbMarkAsReadStudentNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    new MaterialAlertDialogBuilder(context)
                            .setMessage("Are you sure you want to mark as read ?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    updateNewsOrNotificationStatus(data, holder.cbMarkAsReadStudentNotification, position);
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.cbMarkAsReadStudentNotification.setChecked(false);
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }
            }
        });

        if (!CommonUtil.checkIsEmptyOrNullCommon(data.getNt_file_path())) {
            holder.imgDownloadAttachFileStudent.setVisibility(View.VISIBLE);
        } else {
            holder.imgDownloadAttachFileStudent.setVisibility(View.GONE);
        }

        holder.imgDownloadAttachFileStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileUrl = data.getNt_file_path();
                String fileExtension = fileUrl.substring(fileUrl.lastIndexOf("."));
                new DownloadPdfFromUrl(context, data.getNt_file_path(), fileExtension, "Faculty Announcement");
            }
        });
    }


    private void updateNewsOrNotificationStatus(FacultyOrStudentNewsOrNotificationsPojo.Data data, AppCompatCheckBox cbMarkAsReadStudentNotification, int position) {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(context, "");
            ApiImplementer.updateStudentOrEmployeeNotificationStatusApiImplementer(mySharedPreferences.getLoginUserType() + "",
                    data.getNt_id() + "", mySharedPreferences.getStudentId(), "1", new Callback<UpdateNotificationStatusPojo>() {
                        @Override
                        public void onResponse(Call<UpdateNotificationStatusPojo> call, Response<UpdateNotificationStatusPojo> response) {
                            DialogUtil.hideProgressDialog();
                            if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                                facultyAnnouncementPojoArrayList.remove(position);
                                iRemoveStudentNewsOrNotification.onNotificationRemove(position);
                            } else {
                                cbMarkAsReadStudentNotification.setChecked(false);
                                Toast.makeText(context, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UpdateNotificationStatusPojo> call, Throwable t) {
                            DialogUtil.hideProgressDialog();
                            cbMarkAsReadStudentNotification.setChecked(false);
                            Toast.makeText(context, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(context, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public int getItemCount() {
        return facultyAnnouncementPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView imgDownloadAttachFileStudent;
        AppCompatCheckBox cbMarkAsReadStudentNotification;

        TextViewRegularFont tvNotificationDate;
        TextViewRegularFont tvNotificationTitle;
        TextViewRegularFont tvNotificationDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNotificationDate = itemView.findViewById(R.id.tvNotificationDate);
            tvNotificationTitle = itemView.findViewById(R.id.tvNotificationTitle);
            tvNotificationDesc = itemView.findViewById(R.id.tvNotificationDesc);
            imgDownloadAttachFileStudent = itemView.findViewById(R.id.imgDownloadAttachFileStudent);
            cbMarkAsReadStudentNotification = itemView.findViewById(R.id.cbMarkAsReadStudentNotification);
        }
    }

    public interface IRemoveStudentNewsOrNotification {
        void onNotificationRemove(int removeIndex);
    }


}
