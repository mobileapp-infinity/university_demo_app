package com.infinity.infoway.atmiya.student.student_dashboard.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.api.Urls;
import com.infinity.infoway.atmiya.custom_class.ProgressBarAnimation;
import com.infinity.infoway.atmiya.custom_class.TextViewBoldFont;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.student.student_dashboard.pojo.GetSliderImageUrlsPojo;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;

import net.seifhadjhassen.recyclerviewpager.PagerModel;
import net.seifhadjhassen.recyclerviewpager.RecyclerViewPager;

import java.util.ArrayList;

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
    LinearLayout llResultStudentSide;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //For day mode theme
        setContentView(R.layout.activity_student_dashboard);
        initView();
        llAttendanceStudentSide.startAnimation(AnimationUtils.loadAnimation(StudentDashboardActivity.this, R.anim.attendance_left_to_right));
        getSliderImagesApiCall();
        loadStudentAttendanceProgress();
    }

    private void loadStudentAttendanceProgress() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ProgressBarAnimation animCurrentMonth = new ProgressBarAnimation(cpCurrentMonthStudentSide, 0, 80, tvPercentageCurrentMonthStudentSide);
                animCurrentMonth.setDuration(3000);
                cpCurrentMonthStudentSide.startAnimation(animCurrentMonth);

                ProgressBarAnimation animPreviousMonth = new ProgressBarAnimation(cpPreviousMonthStudentSide, 0, 90, tvPercentagePreviousMonthStudentSide);
                animPreviousMonth.setDuration(3500);
                cpPreviousMonthStudentSide.startAnimation(animPreviousMonth);

                ProgressBarAnimation animAvg = new ProgressBarAnimation(cpAvgStudentSide, 0, 85, tvPercentageAvgStudentSide);
                animAvg.setDuration(4000);
                cpAvgStudentSide.startAnimation(animAvg);

            }
        }, 2000);
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(StudentDashboardActivity.this);
        cImgProfileStudentSide = findViewById(R.id.cImgProfileStudentSide);
        tvStudentName = findViewById(R.id.tvStudentName);
        tvStudentSemAndDesignation = findViewById(R.id.tvStudentSemAndDesignation);
        recyclerViewPagerStudentSideBanner = findViewById(R.id.recyclerViewPagerStudentSideBanner);
        llTimeTableStudentSide = findViewById(R.id.llTimeTableStudentSide);
        llLeaveApplicationStudentSide = findViewById(R.id.llLeaveApplicationStudentSide);
        llELearningStudentSide = findViewById(R.id.llELearningStudentSide);
        llAssignmentStudentSide = findViewById(R.id.llAssignmentStudentSide);
        llExamStudentSide = findViewById(R.id.llExamStudentSide);
        llResultStudentSide = findViewById(R.id.llResultStudentSide);
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

        cImgProfileStudentSide.setOnClickListener(this);

        llTimeTableStudentSide.setOnClickListener(this);
        llLeaveApplicationStudentSide.setOnClickListener(this);
        llELearningStudentSide.setOnClickListener(this);
        llAssignmentStudentSide.setOnClickListener(this);
        llExamStudentSide.setOnClickListener(this);
        llResultStudentSide.setOnClickListener(this);
        llSyllabusStudentSide.setOnClickListener(this);
        llLeassonPlanStudentSide.setOnClickListener(this);
        llHomeWorkStudentSide.setOnClickListener(this);
        llFeeDetailsStudentSide.setOnClickListener(this);
        llActivityStudentSide.setOnClickListener(this);
        llMessageHistoryStudentSide.setOnClickListener(this);

        llAttendanceStudentSide.setOnClickListener(this);

        btnViewAllStudentSide.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cImgProfileStudentSide) {

        } else if (v.getId() == R.id.llTimeTableStudentSide) {

        } else if (v.getId() == R.id.llLeaveApplicationStudentSide) {

        } else if (v.getId() == R.id.llELearningStudentSide) {

        } else if (v.getId() == R.id.llAssignmentStudentSide) {

        } else if (v.getId() == R.id.llExamStudentSide) {

        } else if (v.getId() == R.id.llResultStudentSide) {

        } else if (v.getId() == R.id.llSyllabusStudentSide) {

        } else if (v.getId() == R.id.llLeassonPlanStudentSide) {

        } else if (v.getId() == R.id.llHomeWorkStudentSide) {

        } else if (v.getId() == R.id.llFeeDetailsStudentSide) {

        } else if (v.getId() == R.id.llActivityStudentSide) {

        } else if (v.getId() == R.id.llMessageHistoryStudentSide) {

        } else if (v.getId() == R.id.llAttendanceStudentSide) {

        } else if (v.getId() == R.id.btnViewAllStudentSide) {

        }
    }


    private void getSliderImagesApiCall() {
        ApiImplementer.getSliderImagesApiImplementer(Urls.DOMAIN_NAME, mySharedPreferences.getImDomainName(), new Callback<GetSliderImageUrlsPojo>() {
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

}
