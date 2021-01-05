package com.infinity.infoway.atmiya.faculty.faculty_profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.custom_class.TextViewMediumFont;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.faculty.faculty_dashboard.activity.FacultyDashboardActivity;
import com.infinity.infoway.atmiya.login.pojo.LogOutPojo;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.DialogUtil;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private MySharedPreferences mySharedPreferences;
    private ConnectionDetector connectionDetector;
    private AppCompatImageView ivCloseFacultyProfile;
    private CircleImageView faculty_profile_image;
    private AppCompatImageView imgFacultyLogout;

    private TextViewRegularFont tvFacultyName;
    private TextViewRegularFont tvFacultyEmail;

    private TextViewMediumFont tvEmpId;
    private TextViewMediumFont tvFacultyEmployeeNo;
    private TextViewMediumFont tvFacultyEducation;

    //personal Details
    private LinearLayout llExpandedHeaderFacultyPersonalDetailsProfile;
    private LinearLayout llExpandableLayoutFacultyPersonalDetailsProfile;
    private AppCompatImageView AppCompatImageView;

    private TextViewMediumFont tvFacultyGender;
    private TextViewMediumFont tvFacultyDOB;
    private TextViewMediumFont tvFacultyAadhaarCardNo;
    private TextViewMediumFont tvFacultyPanCardNo;
    private TextViewMediumFont tvFacultyMobileNo;

    //Official Details
    private LinearLayout llExpandedHeaderOfficialDetailsFacultyProfile;
    private LinearLayout llExpandableLayoutOfficialDetailsFacultyProfile;
    private AppCompatImageView ivViewMoreBtnOfficialDetailsFacultyProfile;

    private TextViewMediumFont tvFacultyDepartmentName;
    private TextViewMediumFont tvFacultyJobName;
    private TextViewMediumFont tvFacultyDateOfJoining;
    private TextViewMediumFont tvFacultyOfferLetterNo;
    private TextViewMediumFont tvFacultyCardNo;
    private TextViewMediumFont tvFacultyJobTimeFromToto;

    //Account Details
    private LinearLayout llExpandedHeaderAccountDetailsFaculty;
    private AppCompatImageView ivViewMoreBtnAccountDetails;
    private LinearLayout llExpandableLayoutAccountDetailsFaculty;

    private TextViewMediumFont tvFacultyBankName;
    private TextViewMediumFont tvFacultyAccountNo;
    private TextViewMediumFont tvFacultyBranchName;
    private TextViewMediumFont tvFacultyAccountType;

    //contact details
    private LinearLayout llExpandedHeaderContactDetailsFaculty;
    private AppCompatImageView ivViewMoreBtnContactDetails;
    private LinearLayout llExpandableLayoutContactDetailsFaculty;

    private TextViewMediumFont tvFacultyPermenantAddress;
    private TextViewMediumFont tvFacultyPresentAddress;
    private TextViewMediumFont tvFacultyHomeMobileNo;
    private TextViewMediumFont tvFacultyCity;
    private TextViewMediumFont tvFacultyState;
    private TextViewMediumFont tvFacultyCountry;

    //family details
    private LinearLayout llExpandedHeaderFamilyDetailsFaculty;
    private AppCompatImageView ivViewMoreBtnFamilyDetails;
    private LinearLayout llExpandableLayoutFamilyDetailsFaculty;

    private TextViewMediumFont tvFacultyFatherName;
    private TextViewMediumFont tvFacultyFatherOccupation;
    private TextViewMediumFont tvFacultyFathersAddress;
    private TextViewMediumFont tvFacultyMotherName;
    private TextViewMediumFont tvFacultyMotherOccupation;
    private TextViewMediumFont tvFacultySpouseName;
    private TextViewMediumFont tvFacultySppuseOccupation;
    private TextViewMediumFont tvFacultySonOrDoughterName;
    private TextViewMediumFont tvFacultySonOrDaughterDOF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_profile);
        initView();
        getFacultyProfileDetailsApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(FacultyProfileActivity.this);
        connectionDetector = new ConnectionDetector(FacultyProfileActivity.this);
        ivCloseFacultyProfile = findViewById(R.id.ivCloseFacultyProfile);
        ivCloseFacultyProfile.setOnClickListener(this);
        faculty_profile_image = findViewById(R.id.faculty_profile_image);
        imgFacultyLogout = findViewById(R.id.imgFacultyLogout);
        imgFacultyLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultyProfile) {
            onBackPressed();
        } else if (v.getId() == R.id.imgFacultyLogout) {
            new MaterialAlertDialogBuilder(FacultyProfileActivity.this)
                    .setTitle(Html.fromHtml("<b>" + "Logout" + " </b>"))
                    .setMessage("Do you wan to logout from this app ?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            logoutUserApiCall();
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getFacultyProfileDetailsApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(FacultyProfileActivity.this, "");
            ApiImplementer.getFacultyProfileDetailsApiImplementer(mySharedPreferences.getEmpId(), new Callback<ArrayList<FacultyProfilePojo>>() {
                @Override
                public void onResponse(Call<ArrayList<FacultyProfilePojo>> call, Response<ArrayList<FacultyProfilePojo>> response) {
                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null) {
                        FacultyProfilePojo facultyProfilePojo = response.body().get(0);

                        if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getEmpStudPhotoUrl())) {
                            Glide.with(FacultyProfileActivity.this)
                                    .asBitmap()
                                    .load(mySharedPreferences.getEmpStudPhotoUrl().trim())
                                    .override(70, 70)
                                    .placeholder(R.drawable.person_img)
                                    .error(R.drawable.person_img)
                                    .into(faculty_profile_image);
                        }
                    } else {
                        Toast.makeText(FacultyProfileActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<FacultyProfilePojo>> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(FacultyProfileActivity.this, "Request Failed,Please try again later", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void logoutUserApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(FacultyProfileActivity.this, "");
            ApiImplementer.logoutUserApiImplementer(mySharedPreferences.getLoginUserType(), mySharedPreferences.getEmpId(), new Callback<ArrayList<LogOutPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<LogOutPojo>> call, Response<ArrayList<LogOutPojo>> response) {
                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                        if (response.body().get(0).getStatus().equalsIgnoreCase("1")) {
                            mySharedPreferences.logoutStudentOrFaulty();
                            Intent intent = new Intent(FacultyProfileActivity.this, FacultyDashboardActivity.class);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            Toast.makeText(FacultyProfileActivity.this, "some thing went wrong please try again later", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(FacultyProfileActivity.this, "some thing went wrong please try again later", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<LogOutPojo>> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(FacultyProfileActivity.this, "some thing went wrong please try again later", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }

    }

}