package com.infinity.infoway.atmiya.faculty.faculty_dashboard.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiClientForStudentAndEmployeeFcmApi;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.api.Urls;
import com.infinity.infoway.atmiya.faculty.faculty_dashboard.pojo.UpdateFaultyFCMTokenPojo;
import com.infinity.infoway.atmiya.faculty.faculty_profile.FacultyProfileActivity;
import com.infinity.infoway.atmiya.login.activity.LoginActivity;
import com.infinity.infoway.atmiya.student.profile.StudentProfileActivity;
import com.infinity.infoway.atmiya.student.student_dashboard.activity.StudentDashboardActivity;
import com.infinity.infoway.atmiya.student.student_dashboard.pojo.GetSliderImageUrlsPojo;
import com.infinity.infoway.atmiya.student.student_dashboard.pojo.UpdateStudentFCMTokenPojo;
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

    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_dashboard);
        initView();
        sendFacultyFCMTokenToServer();
        getSliderImagesApiCall();
    }


    private void initView() {
        mySharedPreferences = new MySharedPreferences(FacultyDashboardActivity.this);
        connectionDetector = new ConnectionDetector(FacultyDashboardActivity.this);
        cImgProfileFacultySide = findViewById(R.id.cImgProfileFacultySide);
        cImgProfileFacultySide.setOnClickListener(this);
        recyclerViewPagerFacultySideBanner = findViewById(R.id.recyclerViewPagerFacultySideBanner);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cImgProfileFacultySide) {
            Intent profileActivityStudentSide = new Intent(this, FacultyProfileActivity.class);
            startActivityForResult(profileActivityStudentSide, IntentConstants.REQUEST_CODE_FOR_FACULTY_LOGOUT);
            overridePendingTransition(R.anim.slide_in_left, 0);
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
        }
    }
}