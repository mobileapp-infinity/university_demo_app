package com.infinity.infoway.atmiya.faculty.faculty_dashboard.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiClientForStudentAndEmployeeFcmApi;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.api.Urls;
import com.infinity.infoway.atmiya.custom_class.TextViewBoldFont;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.faculty.faculty_announcement.FacultyAnnouncementActivity;
import com.infinity.infoway.atmiya.faculty.faculty_dashboard.adapter.FacultyAnnouncementAdapter;
import com.infinity.infoway.atmiya.faculty.faculty_dashboard.pojo.UpdateFaultyFCMTokenPojo;
import com.infinity.infoway.atmiya.faculty.faculty_profile.FacultyProfileActivity;
import com.infinity.infoway.atmiya.faculty.faculty_profile.FacultyProfilePojo;
import com.infinity.infoway.atmiya.faculty.faculty_timetable.activity.FacultyTimeTableActivity;
import com.infinity.infoway.atmiya.login.activity.LoginActivity;
import com.infinity.infoway.atmiya.student.news_or_notification.FacultyOrStudentNewsOrNotificationsPojo;
import com.infinity.infoway.atmiya.student.student_dashboard.pojo.GetSliderImageUrlsPojo;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.IntentConstants;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;

import net.seifhadjhassen.recyclerviewpager.PagerModel;
import net.seifhadjhassen.recyclerviewpager.RecyclerViewPager;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyDashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private MySharedPreferences mySharedPreferences;
    private ConnectionDetector connectionDetector;

    private CircleImageView cImgProfileFacultySide;
    private RecyclerViewPager recyclerViewPagerFacultySideBanner;

    AppCompatImageView imgNotificationBellFacultySide;

    TextViewBoldFont tvFacultyName;
    TextViewRegularFont tvFacultyDesignation;

    LinearLayout llRemAttendanceFacultySide;
    LinearLayout llAttendanceFacultySide;
    LinearLayout llPendingAttendanceFacultySide;
    LinearLayout llLeaveFacultySide;
    LinearLayout llTimetableFacultySide;
    LinearLayout llLecturePlanFacultySide;
    LinearLayout llNewsFacultySide;

    AppCompatButton btnViewAllAnnouncementFacultySide;
    RecyclerView rvAnnouncementFacultySide;

    ScrollView svFacultyDashboard;
    LinearLayout llFacultyDashboradProgressbar;
    LinearLayout llAnnouncementFacultyDashboard;

    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_dashboard);
        initView();
        sendFacultyFCMTokenToServer();
        getFacultyProfileDetailsApiCall();
    }


    private void initView() {
        mySharedPreferences = new MySharedPreferences(FacultyDashboardActivity.this);
        connectionDetector = new ConnectionDetector(FacultyDashboardActivity.this);
        cImgProfileFacultySide = findViewById(R.id.cImgProfileFacultySide);
        cImgProfileFacultySide.setOnClickListener(this);
        recyclerViewPagerFacultySideBanner = findViewById(R.id.recyclerViewPagerFacultySideBanner);

        tvFacultyName = findViewById(R.id.tvFacultyName);
        tvFacultyDesignation = findViewById(R.id.tvFacultyDesignation);

        svFacultyDashboard = findViewById(R.id.svFacultyDashboard);
        llFacultyDashboradProgressbar = findViewById(R.id.llFacultyDashboradProgressbar);
        llAnnouncementFacultyDashboard = findViewById(R.id.llAnnouncementFacultyDashboard);

        imgNotificationBellFacultySide = findViewById(R.id.imgNotificationBellFacultySide);
        imgNotificationBellFacultySide.setOnClickListener(this);

        llRemAttendanceFacultySide = findViewById(R.id.llRemAttendanceFacultySide);
        llRemAttendanceFacultySide.setOnClickListener(this);
        llAttendanceFacultySide = findViewById(R.id.llAttendanceFacultySide);
        llAttendanceFacultySide.setOnClickListener(this);
        llPendingAttendanceFacultySide = findViewById(R.id.llPendingAttendanceFacultySide);
        llPendingAttendanceFacultySide.setOnClickListener(this);
        llLeaveFacultySide = findViewById(R.id.llLeaveFacultySide);
        llLeaveFacultySide.setOnClickListener(this);
        llTimetableFacultySide = findViewById(R.id.llTimetableFacultySide);
        llTimetableFacultySide.setOnClickListener(this);
        llLecturePlanFacultySide = findViewById(R.id.llLecturePlanFacultySide);
        llLecturePlanFacultySide.setOnClickListener(this);
        llNewsFacultySide = findViewById(R.id.llNewsFacultySide);
        llNewsFacultySide.setOnClickListener(this);

        btnViewAllAnnouncementFacultySide = findViewById(R.id.btnViewAllAnnouncementFacultySide);
        btnViewAllAnnouncementFacultySide.setOnClickListener(this);
        rvAnnouncementFacultySide = findViewById(R.id.rvAnnouncementFacultySide);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cImgProfileFacultySide) {
            Intent profileActivityStudentSide = new Intent(this, FacultyProfileActivity.class);
            startActivityForResult(profileActivityStudentSide, IntentConstants.REQUEST_CODE_FOR_FACULTY_LOGOUT);
            overridePendingTransition(R.anim.slide_in_left, 0);
        } else if (v.getId() == R.id.llRemAttendanceFacultySide) {

        } else if (v.getId() == R.id.llAttendanceFacultySide) {

        } else if (v.getId() == R.id.llPendingAttendanceFacultySide) {

        } else if (v.getId() == R.id.llLeaveFacultySide) {

        } else if (v.getId() == R.id.llTimetableFacultySide) {
            Intent intent = new Intent(FacultyDashboardActivity.this, FacultyTimeTableActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llLecturePlanFacultySide) {

        } else if (v.getId() == R.id.llNewsFacultySide) {

        } else if (v.getId() == R.id.btnViewAllAnnouncementFacultySide) {
            Intent intent = new Intent(FacultyDashboardActivity.this, FacultyAnnouncementActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.imgNotificationBellFacultySide) {
            Intent intent = new Intent(FacultyDashboardActivity.this, FacultyAnnouncementActivity.class);
            startActivity(intent);
        }
    }

    private void getSliderImagesApiCall() {
        ApiImplementer.getSliderImagesApiImplementer(Urls.DOMAIN_NAME, mySharedPreferences.getEmpInstituteId(), new Callback<GetSliderImageUrlsPojo>() {
            @Override
            public void onResponse(Call<GetSliderImageUrlsPojo> call, Response<GetSliderImageUrlsPojo> response) {
                if (response.isSuccessful() && response.body().getUrl().size() > 0) {
                    ArrayList<String> bannerUrls = (ArrayList<String>) response.body().getUrl();
                    for (int i = 0; i < bannerUrls.size(); i++) {
                        if (bannerUrls.get(i) != null && !bannerUrls.get(i).isEmpty() && bannerUrls.get(i).length() > 7) {
                            recyclerViewPagerFacultySideBanner.addItem(new PagerModel(bannerUrls.get(i)));
                        }
                    }
                    recyclerViewPagerFacultySideBanner.start();
                }
            }

            @Override
            public void onFailure(Call<GetSliderImageUrlsPojo> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }

    private void getFacultyProfileDetailsApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llFacultyDashboradProgressbar.setVisibility(View.VISIBLE);
            svFacultyDashboard.setVisibility(View.GONE);
            ApiImplementer.getFacultyProfileDetailsApiImplementer(mySharedPreferences.getEmpId(), new Callback<ArrayList<FacultyProfilePojo>>() {
                @Override
                public void onResponse(Call<ArrayList<FacultyProfilePojo>> call, Response<ArrayList<FacultyProfilePojo>> response) {
                    try {
                        if (response.isSuccessful() && response.body() != null) {
                            llFacultyDashboradProgressbar.setVisibility(View.GONE);
                            FacultyProfilePojo facultyProfilePojo = response.body().get(0);

                            if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getName())) {
                                tvFacultyName.setText(facultyProfilePojo.getName() + "");
                            }

                            if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEdName())) {
                                tvFacultyDesignation.setText(facultyProfilePojo.getEdName() + "");
                            }

                            if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getEmpStudPhotoUrl())) {
                                Glide.with(FacultyDashboardActivity.this)
                                        .asBitmap()
                                        .load(mySharedPreferences.getEmpStudPhotoUrl().trim())
                                        .override(70, 70)
                                        .placeholder(R.drawable.person_img)
                                        .error(R.drawable.person_img)
                                        .into(cImgProfileFacultySide);
                            }
                            svFacultyDashboard.setVisibility(View.VISIBLE);
                            getSliderImagesApiCall();
                            getFacultyAnnouncementApiCall();
                        } else {
                            Toast.makeText(FacultyDashboardActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<FacultyProfilePojo>> call, Throwable t) {
                    llFacultyDashboradProgressbar.setVisibility(View.GONE);
                    svFacultyDashboard.setVisibility(View.GONE);
                    Toast.makeText(FacultyDashboardActivity.this, "Request Failed,Please try again later", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendFacultyFCMTokenToServer() {
        if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getFCMToken())) {
            if (connectionDetector.isConnectingToInternet()) {
                ApiImplementer.updateFacultyFcmTokenApiImplementer(mySharedPreferences.getEmpId(), mySharedPreferences.getFCMToken(),
                        ApiClientForStudentAndEmployeeFcmApi.ENCODED_KEY_FOR_FACULTY_FCM_REGISTRATION, new Callback<UpdateFaultyFCMTokenPojo>() {
                            @Override
                            public void onResponse(Call<UpdateFaultyFCMTokenPojo> call, Response<UpdateFaultyFCMTokenPojo> response) {

                            }

                            @Override
                            public void onFailure(Call<UpdateFaultyFCMTokenPojo> call, Throwable t) {

                            }
                        });
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IntentConstants.REQUEST_CODE_FOR_FACULTY_LOGOUT) {
            Intent intent = new Intent(FacultyDashboardActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else if (resultCode == RESULT_OK && requestCode == IntentConstants.REQUEST_CODE_FOR_VIEW_ALL_NEWS_OR_NOTIFICATION_FACULTY_SIDE) {
            getFacultyAnnouncementApiCall();
        }
    }

    private void getFacultyAnnouncementApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llAnnouncementFacultyDashboard.setVisibility(View.GONE);
            ApiImplementer.getFacultyOrStudentNewsOrNotificationImplementer(mySharedPreferences.getLoginUserType() + "",
                    mySharedPreferences.getEmpUserStatus(), mySharedPreferences.getEmpId(), "0", "0",
                    "0", "0", mySharedPreferences.getEmpInstituteId(),
                    mySharedPreferences.getEmpYearId(), "8", new Callback<FacultyOrStudentNewsOrNotificationsPojo>() {
                        @Override
                        public void onResponse(Call<FacultyOrStudentNewsOrNotificationsPojo> call, Response<FacultyOrStudentNewsOrNotificationsPojo> response) {
                            if (response.isSuccessful() && response.body() != null &&
                                    response.body().getTable().size() > 0) {
                                llAnnouncementFacultyDashboard.setVisibility(View.VISIBLE);
                                rvAnnouncementFacultySide.setLayoutManager(new LinearLayoutManager(FacultyDashboardActivity.this, LinearLayoutManager.HORIZONTAL, false));
                                rvAnnouncementFacultySide.setAdapter(new FacultyAnnouncementAdapter(FacultyDashboardActivity.this, (ArrayList<FacultyOrStudentNewsOrNotificationsPojo.Data>) response.body().getTable()));
                            }
                        }

                        @Override
                        public void onFailure(Call<FacultyOrStudentNewsOrNotificationsPojo> call, Throwable t) {
                            llAnnouncementFacultyDashboard.setVisibility(View.GONE);
                        }
                    });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }

    }

}