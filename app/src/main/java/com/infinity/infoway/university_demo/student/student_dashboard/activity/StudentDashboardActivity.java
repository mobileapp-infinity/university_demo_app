package com.infinity.infoway.university_demo.student.student_dashboard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.infinity.infoway.university_demo.R;
import com.infinity.infoway.university_demo.api.ApiClientForStudentAndEmployeeFcmApi;
import com.infinity.infoway.university_demo.api.ApiImplementer;
import com.infinity.infoway.university_demo.api.Urls;
import com.infinity.infoway.university_demo.custom_class.ProgressBarAnimation;
import com.infinity.infoway.university_demo.custom_class.TextViewBoldFont;
import com.infinity.infoway.university_demo.custom_class.TextViewRegularFont;
import com.infinity.infoway.university_demo.login.activity.LoginActivity;
import com.infinity.infoway.university_demo.student.assignment.AssignmentActivity;
import com.infinity.infoway.university_demo.student.attendance.activity.StudentAttendanceActivity;
import com.infinity.infoway.university_demo.student.e_learning.activity.ELearningActivity;
import com.infinity.infoway.university_demo.student.exam.activity.ExamActivity;
import com.infinity.infoway.university_demo.student.fee_details.activity.FeeDetailsActivity;
import com.infinity.infoway.university_demo.student.holiday.HolidayActivity;
import com.infinity.infoway.university_demo.student.home_work.activity.StudentHomeWorkActivity;
import com.infinity.infoway.university_demo.student.leave_application.activity.LeaveApplicationActivity;
import com.infinity.infoway.university_demo.student.lesson_plan.StudentLessonPlanActivity;
import com.infinity.infoway.university_demo.student.message_history.MessageHistoryActivity;
import com.infinity.infoway.university_demo.student.news_or_notification.FacultyOrStudentNewsOrNotificationsPojo;
import com.infinity.infoway.university_demo.student.news_or_notification.ViewAllNewsOrNotificationStudentActivity;
import com.infinity.infoway.university_demo.student.profile.StudentProfileActivity;
import com.infinity.infoway.university_demo.student.profile.StudentProfilePojo;
import com.infinity.infoway.university_demo.student.student_activity.StudentActivity;
import com.infinity.infoway.university_demo.student.student_dashboard.adapter.NewsOrNotificationListAdapter;
import com.infinity.infoway.university_demo.student.student_dashboard.pojo.GetSliderImageUrlsPojo;
import com.infinity.infoway.university_demo.student.student_dashboard.pojo.UpdateStudentFCMTokenPojo;
import com.infinity.infoway.university_demo.student.student_syllabus.StudentSyllabusActivity;
import com.infinity.infoway.university_demo.student.student_timetable.activity.StudentTimeTableActivity;
import com.infinity.infoway.university_demo.utils.CommonUtil;
import com.infinity.infoway.university_demo.utils.ConnectionDetector;
import com.infinity.infoway.university_demo.utils.IntentConstants;
import com.infinity.infoway.university_demo.utils.MySharedPreferences;

import net.seifhadjhassen.recyclerviewpager.PagerModel;
import net.seifhadjhassen.recyclerviewpager.RecyclerViewPager;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentDashboardActivity extends AppCompatActivity implements View.OnClickListener {


    MySharedPreferences mySharedPreferences;

    CircleImageView cImgProfileStudentSide;
    TextViewBoldFont tvStudentName;
    TextViewRegularFont tvStudentSemAndDesignation;

    RecyclerViewPager recyclerViewPagerStudentSideBanner;

    LinearLayout llTimeTableStudentSide;
    LinearLayout llLeaveApplicationStudentSide;
    LinearLayout llELearningStudentSide;
    LinearLayout llAssignmentStudentSide;
    LinearLayout llExamStudentSide;
    LinearLayout llHolidayStudentSide;
    LinearLayout llSyllabusStudentSide;
    LinearLayout llLeassonPlanStudentSide;
    LinearLayout llHomeWorkStudentSide;
    LinearLayout llFeeDetailsStudentSide;
    LinearLayout llActivityStudentSide;
    LinearLayout llMessageHistoryStudentSide;

    LinearLayout llAttendanceStudentSide;
    ProgressBar cpCurrentMonthStudentSide;
    TextViewRegularFont tvPercentageCurrentMonthStudentSide;
    ProgressBar cpPreviousMonthStudentSide;
    TextViewRegularFont tvPercentagePreviousMonthStudentSide;
    ProgressBar cpAvgStudentSide;
    TextViewRegularFont tvPercentageAvgStudentSide;

    AppCompatButton btnViewAllStudentSide;
    RecyclerView rvNewsOrNotificationListStudentSide;

    ScrollView svStudentDashboard;
    LinearLayout llStudentDashboradProgressbar;
    LinearLayout llNewsOrNotificationListStudentDashboard;

    ConnectionDetector connectionDetector;
    private Boolean exit = false;
//    private AppCompatImageView imgNotificationBell;

    FrameLayout flNotification;
    TextViewRegularFont tvNotificationCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //For day mode theme
        setContentView(R.layout.activity_student_dashboard);
        initView();
        getStudentProfileAndAttendanceData();
//        sendStudentFCMTokenToServer();
    }

    private void loadStudentAttendanceProgress(int currentMonthAttendance, int previousMonthAttendance, int avgPercentage) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ProgressBarAnimation animCurrentMonth = new ProgressBarAnimation(cpCurrentMonthStudentSide, 0, currentMonthAttendance, tvPercentageCurrentMonthStudentSide);
                animCurrentMonth.setDuration(3000);
                cpCurrentMonthStudentSide.startAnimation(animCurrentMonth);

                ProgressBarAnimation animPreviousMonth = new ProgressBarAnimation(cpPreviousMonthStudentSide, 0, previousMonthAttendance, tvPercentagePreviousMonthStudentSide);
                animPreviousMonth.setDuration(3500);
                cpPreviousMonthStudentSide.startAnimation(animPreviousMonth);

                ProgressBarAnimation animAvg = new ProgressBarAnimation(cpAvgStudentSide, 0, avgPercentage, tvPercentageAvgStudentSide);
                animAvg.setDuration(4000);
                cpAvgStudentSide.startAnimation(animAvg);

            }
        }, 2000);
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(StudentDashboardActivity.this);
        connectionDetector = new ConnectionDetector(StudentDashboardActivity.this);
        cImgProfileStudentSide = findViewById(R.id.cImgProfileStudentSide);
        tvStudentName = findViewById(R.id.tvStudentName);
        tvStudentSemAndDesignation = findViewById(R.id.tvStudentSemAndDesignation);
        recyclerViewPagerStudentSideBanner = findViewById(R.id.recyclerViewPagerStudentSideBanner);
        llTimeTableStudentSide = findViewById(R.id.llTimeTableStudentSide);
        llLeaveApplicationStudentSide = findViewById(R.id.llLeaveApplicationStudentSide);
        llELearningStudentSide = findViewById(R.id.llELearningStudentSide);
        llAssignmentStudentSide = findViewById(R.id.llAssignmentStudentSide);
        llExamStudentSide = findViewById(R.id.llExamStudentSide);
        llHolidayStudentSide = findViewById(R.id.llHolidayStudentSide);
        llSyllabusStudentSide = findViewById(R.id.llSyllabusStudentSide);
        llLeassonPlanStudentSide = findViewById(R.id.llLeassonPlanStudentSide);
        llHomeWorkStudentSide = findViewById(R.id.llHomeWorkStudentSide);
        llFeeDetailsStudentSide = findViewById(R.id.llFeeDetailsStudentSide);
        llActivityStudentSide = findViewById(R.id.llActivityStudentSide);
        llMessageHistoryStudentSide = findViewById(R.id.llMessageHistoryStudentSide);
        btnViewAllStudentSide = findViewById(R.id.btnViewAllStudentSide);
        rvNewsOrNotificationListStudentSide = findViewById(R.id.rvNewsOrNotificationListStudentSide);
        llAttendanceStudentSide = findViewById(R.id.llAttendanceStudentSide);
        cpCurrentMonthStudentSide = findViewById(R.id.cpCurrentMonthStudentSide);
        tvPercentageCurrentMonthStudentSide = findViewById(R.id.tvPercentageCurrentMonthStudentSide);
        cpPreviousMonthStudentSide = findViewById(R.id.cpPreviousMonthStudentSide);
        tvPercentagePreviousMonthStudentSide = findViewById(R.id.tvPercentagePreviousMonthStudentSide);
        cpAvgStudentSide = findViewById(R.id.cpAvgStudentSide);
        tvPercentageAvgStudentSide = findViewById(R.id.tvPercentageAvgStudentSide);
//        imgNotificationBell = findViewById(R.id.imgNotificationBell);
//        imgNotificationBell.setOnClickListener(this);

        cImgProfileStudentSide.setOnClickListener(this);

        llTimeTableStudentSide.setOnClickListener(this);
        llLeaveApplicationStudentSide.setOnClickListener(this);
        llELearningStudentSide.setOnClickListener(this);
        llAssignmentStudentSide.setOnClickListener(this);
        llExamStudentSide.setOnClickListener(this);
        llHolidayStudentSide.setOnClickListener(this);
        llSyllabusStudentSide.setOnClickListener(this);
        llLeassonPlanStudentSide.setOnClickListener(this);
        llHomeWorkStudentSide.setOnClickListener(this);
        llFeeDetailsStudentSide.setOnClickListener(this);
        llActivityStudentSide.setOnClickListener(this);
        llMessageHistoryStudentSide.setOnClickListener(this);
        flNotification = findViewById(R.id.flNotification);
        flNotification.setOnClickListener(this);

        llAttendanceStudentSide.setOnClickListener(this);

        btnViewAllStudentSide.setOnClickListener(this);

        svStudentDashboard = findViewById(R.id.svStudentDashboard);
        llStudentDashboradProgressbar = findViewById(R.id.llStudentDashboradProgressbar);
        llNewsOrNotificationListStudentDashboard = findViewById(R.id.llNewsOrNotificationListStudentDashboard);
        tvNotificationCount = findViewById(R.id.tvNotificationCount);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cImgProfileStudentSide) {
            Intent profileActivityStudentSide = new Intent(this, StudentProfileActivity.class);
            startActivityForResult(profileActivityStudentSide, IntentConstants.REQUEST_CODE_STUDENT_LOGOUT);
            overridePendingTransition(R.anim.slide_in_left, 0);
        } else if (v.getId() == R.id.llTimeTableStudentSide) {
            Intent intent = new Intent(StudentDashboardActivity.this, StudentTimeTableActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llLeaveApplicationStudentSide) {
            Intent intent = new Intent(StudentDashboardActivity.this, LeaveApplicationActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llELearningStudentSide) {
            Intent intent = new Intent(StudentDashboardActivity.this, ELearningActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llAssignmentStudentSide) {
            Intent intent = new Intent(StudentDashboardActivity.this, AssignmentActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llExamStudentSide) {
            Intent intent = new Intent(StudentDashboardActivity.this, ExamActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llHolidayStudentSide) {
            Intent intent = new Intent(StudentDashboardActivity.this, HolidayActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llSyllabusStudentSide) {
            Intent intent = new Intent(StudentDashboardActivity.this, StudentSyllabusActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llLeassonPlanStudentSide) {
            Intent intent = new Intent(StudentDashboardActivity.this, StudentLessonPlanActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llHomeWorkStudentSide) {
            Intent intent = new Intent(StudentDashboardActivity.this, StudentHomeWorkActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llFeeDetailsStudentSide) {
            Intent feeDetailsIntent = new Intent(StudentDashboardActivity.this, FeeDetailsActivity.class);
            startActivity(feeDetailsIntent);
        } else if (v.getId() == R.id.llActivityStudentSide) {
            Intent intent = new Intent(StudentDashboardActivity.this, StudentActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llMessageHistoryStudentSide) {
            Intent intent = new Intent(StudentDashboardActivity.this, MessageHistoryActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llAttendanceStudentSide) {
            if (connectionDetector.isConnectingToInternet()) {
                Intent studentAttendanceIntent = new Intent(StudentDashboardActivity.this, StudentAttendanceActivity.class);
                startActivity(studentAttendanceIntent);
            } else {
                Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.btnViewAllStudentSide) {
//            final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
//            btnViewAllStudentSide.startAnimation(animation);

//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
            Intent intent = new Intent(StudentDashboardActivity.this, ViewAllNewsOrNotificationStudentActivity.class);
            startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_VIEW_ALL_NEWS_OR_NOTIFICATION);
//                }
//            }, 400);
        } else if (v.getId() == R.id.flNotification) {
            Intent intent = new Intent(StudentDashboardActivity.this, ViewAllNewsOrNotificationStudentActivity.class);
            startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_VIEW_ALL_NEWS_OR_NOTIFICATION);
        }
    }


    private void getSliderImagesApiCall() {
        ApiImplementer.getSliderImagesApiImplementer(Urls.DOMAIN_NAME, mySharedPreferences.getInstituteId(), new Callback<GetSliderImageUrlsPojo>() {
            @Override
            public void onResponse(Call<GetSliderImageUrlsPojo> call, Response<GetSliderImageUrlsPojo> response) {
                if (response.isSuccessful() && response.body().getUrl().size() > 0) {
                    ArrayList<String> bannerUrls = (ArrayList<String>) response.body().getUrl();
                    for (int i = 0; i < bannerUrls.size(); i++) {
                        if (bannerUrls.get(i) != null && !bannerUrls.get(i).isEmpty() && bannerUrls.get(i).length() > 7) {
                            recyclerViewPagerStudentSideBanner.addItem(new PagerModel(bannerUrls.get(i)));
                        }
                    }
                    recyclerViewPagerStudentSideBanner.start();
                }
            }

            @Override
            public void onFailure(Call<GetSliderImageUrlsPojo> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getStudentProfileAndAttendanceData() {
        if (connectionDetector.isConnectingToInternet()) {
            llStudentDashboradProgressbar.setVisibility(View.VISIBLE);
            svStudentDashboard.setVisibility(View.GONE);
            HashMap<String, String> mParams = new HashMap<>();
            mParams.put("stud_id", mySharedPreferences.getStudentId());
            mParams.put("year_id", mySharedPreferences.getSwdYearId());
            mParams.put("school_id", mySharedPreferences.getAcId());
            ApiImplementer.getStudentProfileImplementer(mParams, new Callback<StudentProfilePojo>() {
                @Override
                public void onResponse(Call<StudentProfilePojo> call, Response<StudentProfilePojo> response) {
                    try {
                        llStudentDashboradProgressbar.setVisibility(View.GONE);
                        if (response.isSuccessful() && response.body() != null) {
                            StudentProfilePojo studentProfilePojo = response.body();

                            if (!CommonUtil.checkIsEmptyOrNullCommon(studentProfilePojo.getUnread_notif_count())) {
                                tvNotificationCount.setText(studentProfilePojo.getUnread_notif_count() + "");
                            }

                            if (studentProfilePojo.getStudName() != null && !studentProfilePojo.getStudName().isEmpty()) {
                                tvStudentName.setText("Hello, " + studentProfilePojo.getStudName());
                            }
//                            String studentSemAndDesignation = "Sem - ";
                            String studentSemAndDesignation = "";
                            if (studentProfilePojo.getSmName() != null && !studentProfilePojo.getSmName().isEmpty()) {
//                                studentSemAndDesignation += studentProfilePojo.getSmName().split("-")[1];
                                studentSemAndDesignation = studentProfilePojo.getSmName();
                            }

                            if (studentProfilePojo.getCourseFullname() != null && !studentProfilePojo.getCourseFullname().isEmpty()) {
                                studentSemAndDesignation += ", " + studentProfilePojo.getCourseFullname();
                            }
                            tvStudentSemAndDesignation.setText(studentSemAndDesignation);

                            Glide.with(StudentDashboardActivity.this)
                                    .asBitmap()
                                    .load(studentProfilePojo.getStudPhoto())
                                    .override(46, 46)
                                    .placeholder(R.drawable.person_img)
                                    .error(R.drawable.person_img)
                                    .into(cImgProfileStudentSide);

                            svStudentDashboard.setVisibility(View.VISIBLE);
                            llAttendanceStudentSide.startAnimation(AnimationUtils.loadAnimation(StudentDashboardActivity.this, R.anim.attendance_left_to_right));
                            getSliderImagesApiCall();
                            loadStudentAttendanceProgress(studentProfilePojo.getCurrentMonthAvgAtt(),
                                    studentProfilePojo.getPreviousMonthAvgAtt(),
                                    studentProfilePojo.getSemesterAvgAtt());
                            getStudentNewsOrNotificationListApiCall();
                        } else {
                            Toast.makeText(StudentDashboardActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<StudentProfilePojo> call, Throwable t) {
                    llStudentDashboradProgressbar.setVisibility(View.GONE);
                    svStudentDashboard.setVisibility(View.GONE);
                    Toast.makeText(StudentDashboardActivity.this, "Request Failed,Please try again later", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void getStudentNewsOrNotificationListApiCall() {
        llNewsOrNotificationListStudentDashboard.setVisibility(View.GONE);
        ApiImplementer.getFacultyOrStudentNewsOrNotificationImplementer(mySharedPreferences.getLoginUserType() + "", "0",
                mySharedPreferences.getStudentId(),
                mySharedPreferences.getAcId(), mySharedPreferences.getDmId(),
                mySharedPreferences.getCourseId(), mySharedPreferences.getSmId(),
                mySharedPreferences.getInstituteId(), mySharedPreferences.getSwdYearId(), "8", new Callback<FacultyOrStudentNewsOrNotificationsPojo>() {
                    @Override
                    public void onResponse(Call<FacultyOrStudentNewsOrNotificationsPojo> call, Response<FacultyOrStudentNewsOrNotificationsPojo> response) {
                        if (response.isSuccessful() && response.body() != null &&
                                response.body().getTable().size() > 0) {
                            llNewsOrNotificationListStudentDashboard.setVisibility(View.VISIBLE);
                            rvNewsOrNotificationListStudentSide.setLayoutManager(new LinearLayoutManager(StudentDashboardActivity.this, LinearLayoutManager.HORIZONTAL, false));
                            rvNewsOrNotificationListStudentSide.setAdapter(new NewsOrNotificationListAdapter(StudentDashboardActivity.this, response.body()));
                        }
                    }

                    @Override
                    public void onFailure(Call<FacultyOrStudentNewsOrNotificationsPojo> call, Throwable t) {
                        llNewsOrNotificationListStudentDashboard.setVisibility(View.GONE);
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IntentConstants.REQUEST_CODE_STUDENT_LOGOUT) {
            Intent intent = new Intent(StudentDashboardActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else if (resultCode == RESULT_OK && requestCode == IntentConstants.REQUEST_CODE_FOR_VIEW_ALL_NEWS_OR_NOTIFICATION) {
            getStudentNewsOrNotificationListApiCall();
        }
    }


//    private void sendStudentFCMTokenToServer() {
//        if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getFCMToken())) {
//            if (connectionDetector.isConnectingToInternet()) {
//                ApiImplementer.updateStudentFcmTokenApiImplementer(mySharedPreferences.getStudentId(), mySharedPreferences.getFCMToken(),
//                        ApiClientForStudentAndEmployeeFcmApi.ENCODED_KEY_FOR_STUDENT_FCM_REGISTRATION, new Callback<UpdateStudentFCMTokenPojo>() {
//                            @Override
//                            public void onResponse(Call<UpdateStudentFCMTokenPojo> call, Response<UpdateStudentFCMTokenPojo> response) {
////                                if (response.isSuccessful() && response.body() != null) {
////                                    Toast.makeText(StudentDashboardActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
////                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<UpdateStudentFCMTokenPojo> call, Throwable t) {
//
//                            }
//                        });
//            }
//        }
//    }


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
}
