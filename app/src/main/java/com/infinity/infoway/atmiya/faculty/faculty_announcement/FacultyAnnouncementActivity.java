package com.infinity.infoway.atmiya.faculty.faculty_announcement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.faculty.faculty_dashboard.activity.FacultyDashboardActivity;
import com.infinity.infoway.atmiya.student.news_or_notification.StudentNewsOrNotificationsPojo;
import com.infinity.infoway.atmiya.student.news_or_notification.ViewAllNewsOrNotificationAdapter;
import com.infinity.infoway.atmiya.student.student_dashboard.activity.StudentDashboardActivity;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyAnnouncementActivity extends AppCompatActivity implements View.OnClickListener, FacultyViewAllAnnouncementAdapter.IRemoveStudentNewsOrNotification {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    LinearLayout llAnnouncementProgressbar, llNoDataFoundFacultyAnnouncement, llFacultyAnnouncementList;
    public RecyclerView rvAnnouncement;
    AppCompatImageView ivCloseAnnouncement;
    FacultyViewAllAnnouncementAdapter viewAllNewsOrNotificationAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_announcement);
        initView();
        getAllNewsOrNotificationApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(FacultyAnnouncementActivity.this);
        connectionDetector = new ConnectionDetector(FacultyAnnouncementActivity.this);
        ivCloseNewsOrNotification = findViewById(R.id.ivCloseNewsOrNotification);
        ivCloseNewsOrNotification.setOnClickListener(this);
        llNewsOrNotificationProgressbar = findViewById(R.id.llNewsOrNotificationProgressbar);
        llNoDataFoundNewsOrNotification = findViewById(R.id.llNoDataFoundNewsOrNotification);
        llStudentNewsOrNotificationList = findViewById(R.id.llStudentNewsOrNotificationList);
        rvNewsOrNotification = findViewById(R.id.rvNewsOrNotification);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseNewsOrNotification) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FacultyAnnouncementActivity.this, FacultyDashboardActivity.class);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void getAllNewsOrNotificationApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llNewsOrNotificationProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundNewsOrNotification.setVisibility(View.GONE);
            llStudentNewsOrNotificationList.setVisibility(View.GONE);
            ApiImplementer.getStudentNewsOrNotificationImplementer(mySharedPreferences.getLoginUserType() + "", "0", mySharedPreferences.getStudentId(),
                    mySharedPreferences.getAcId(), mySharedPreferences.getDmId(),
                    mySharedPreferences.getCourseId(), mySharedPreferences.getSmId(),
                    mySharedPreferences.getInstituteId(), mySharedPreferences.getSwdYearId(), "0", new Callback<StudentNewsOrNotificationsPojo>() {
                        @Override
                        public void onResponse(Call<StudentNewsOrNotificationsPojo> call, Response<StudentNewsOrNotificationsPojo> response) {
                            llNewsOrNotificationProgressbar.setVisibility(View.GONE);
                            try {
                                if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                                    llStudentNewsOrNotificationList.setVisibility(View.VISIBLE);
                                    viewAllNewsOrNotificationAdapter = new ViewAllNewsOrNotificationAdapter(FacultyAnnouncementActivity.this, (ArrayList<StudentNewsOrNotificationsPojo.Data>) response.body().getTable());
                                    rvNewsOrNotification.setAdapter(viewAllNewsOrNotificationAdapter);
                                } else {
                                    llStudentNewsOrNotificationList.setVisibility(View.GONE);
                                    llNoDataFoundNewsOrNotification.setVisibility(View.VISIBLE);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<StudentNewsOrNotificationsPojo> call, Throwable t) {
                            llNewsOrNotificationProgressbar.setVisibility(View.GONE);
                            llStudentNewsOrNotificationList.setVisibility(View.GONE);
                            llNoDataFoundNewsOrNotification.setVisibility(View.VISIBLE);
                            Toast.makeText(FacultyAnnouncementActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onNotificationRemove(int removeIndex) {
        viewAllNewsOrNotificationAdapter.notifyItemRemoved(removeIndex);
    }

}