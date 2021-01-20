package com.infinity.infoway.atmiya.forgot_password.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.custom_class.TextViewMediumFont;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.forgot_password.pojo.CheckLoginByOTPAndUsernamePojo;
import com.infinity.infoway.atmiya.forgot_password.pojo.CheckOTPVerificationForEmployeePojo;
import com.infinity.infoway.atmiya.forgot_password.pojo.CheckOTPVerificationForStudentPojo;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.DialogUtil;
import com.infinity.infoway.atmiya.utils.IntentConstants;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;
import com.mukesh.OtpView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOTPActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseVerifyOTP;
    OtpView otp_view;
    TextViewRegularFont btnVerifyOtpAndProceed;
    String instituteId = "";
    String userName = "";
    String userType = "";
    String userId = "";
    String mobileNo = "";
    TextViewMediumFont tvEnteredMobileNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_o_t_p);
        initView();

        if (getIntent().hasExtra(IntentConstants.INSTITUTE_ID_FOR_VERIFY_OTP)) {
            instituteId = getIntent().getStringExtra(IntentConstants.INSTITUTE_ID_FOR_VERIFY_OTP);
        }

        if (getIntent().hasExtra(IntentConstants.USERNAME_FOR_VERIFY_OTP)) {
            userName = getIntent().getStringExtra(IntentConstants.USERNAME_FOR_VERIFY_OTP);
        }

        if (getIntent().hasExtra(IntentConstants.FACULTY_OR_STUDENT)) {
            userType = getIntent().getStringExtra(IntentConstants.FACULTY_OR_STUDENT);
        }

        if (getIntent().hasExtra(IntentConstants.USER_ID_VERIFY_OTP)) {
            userId = getIntent().getStringExtra(IntentConstants.USER_ID_VERIFY_OTP);
        }

        if (getIntent().hasExtra(IntentConstants.ENTERED_MOBILE_NO)) {
            mobileNo = getIntent().getStringExtra(IntentConstants.ENTERED_MOBILE_NO);
            tvEnteredMobileNo.setText("+91  - " + mobileNo);
        } else {
            tvEnteredMobileNo.setText("+91  - " + "xxxxxxxxxx");
        }


    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(VerifyOTPActivity.this);
        connectionDetector = new ConnectionDetector(VerifyOTPActivity.this);
        ivCloseVerifyOTP = findViewById(R.id.ivCloseVerifyOTP);
        ivCloseVerifyOTP.setOnClickListener(this);
        otp_view = findViewById(R.id.otp_view);
        btnVerifyOtpAndProceed = findViewById(R.id.btnVerifyOtpAndProceed);
        btnVerifyOtpAndProceed.setOnClickListener(this);
        tvEnteredMobileNo = findViewById(R.id.tvEnteredMobileNo);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivCloseVerifyOTP) {
            onBackPressed();
        } else if (view.getId() == R.id.btnVerifyOtpAndProceed) {
            String otpText = otp_view.getOTP();
            if (!CommonUtil.checkIsEmptyOrNullCommon(otpText) &&
                    otpText.length() == 6) {
                //new Call
                checkLoginByOTPAndUsernameAPI(userId, userType, userName, instituteId, otpText, "1");

            } else {
                Toast.makeText(this, "Please enter 6 digit OTP.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void checkLoginByOTPAndUsernameAPI(String userId, String user_type, String user_name, String institute_id, String otp, String ip_addr) {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(VerifyOTPActivity.this, "");
            ApiImplementer.checkLoginByOTPAndUsernameAPIImplementer(user_type, user_name, institute_id, otp, ip_addr, new Callback<CheckLoginByOTPAndUsernamePojo>() {
                @Override
                public void onResponse(Call<CheckLoginByOTPAndUsernamePojo> call, Response<CheckLoginByOTPAndUsernamePojo> response) {
                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                        CheckLoginByOTPAndUsernamePojo checkLoginByOTPAndUsernamePojo = response.body();
                        if (userType.compareToIgnoreCase(String.valueOf(CommonUtil.SELECTED_USER_TYPE_FACULTY)) == 0) {
                            setEmpNewLoginDate(checkLoginByOTPAndUsernamePojo);
                            Toast.makeText(VerifyOTPActivity.this, "OTP verified Successfully ", Toast.LENGTH_SHORT).show();
                            redirectToResetPasswordActivity(userId, institute_id, user_type);
                        } else if (userType.compareToIgnoreCase(String.valueOf(CommonUtil.SELECTED_USER_TYPE_STUDENT)) == 0) {
                            setStudentNewLoginData(checkLoginByOTPAndUsernamePojo);
                            Toast.makeText(VerifyOTPActivity.this, "OTP verified Successfully ", Toast.LENGTH_SHORT).show();
                            redirectToResetPasswordActivity(userId, institute_id, user_type);
                        }


                    } else {
                        Toast.makeText(VerifyOTPActivity.this, "Incorrect OTP,Please enter correct OTP.", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<CheckLoginByOTPAndUsernamePojo> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(VerifyOTPActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }


    }


    private void redirectToResetPasswordActivity(String userId,
                                                 String instituteId, String userType) {
        Intent intent = new Intent(VerifyOTPActivity.this, ResetPasswordActivity.class);
        intent.putExtra(IntentConstants.RESET_PASS_USER_ID, userId);
        intent.putExtra(IntentConstants.FACULTY_OR_STUDENT, userType);
        intent.putExtra(IntentConstants.RESET_PASS_INSTITUTE_ID, instituteId);
        startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_RESET_PASSWORD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK &&
                requestCode == IntentConstants.REQUEST_CODE_FOR_RESET_PASSWORD) {
            Intent intent = new Intent(VerifyOTPActivity.this, ForgotPasswordActivity.class);
            boolean isOTPVerifiedAndResetPass = false;
            if (data.hasExtra(IntentConstants.IS_OTP_VERIFIED_AND_RESENT_PASS)) {
                isOTPVerifiedAndResetPass = data.getBooleanExtra(IntentConstants.IS_OTP_VERIFIED_AND_RESENT_PASS, false);
            }
            intent.putExtra(IntentConstants.IS_OTP_VERIFIED_AND_RESENT_PASS, isOTPVerifiedAndResetPass);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    /**
     * emp_id: 2270,
     * emp_number: "123456",
     * name: "CMS Admin Infinity",
     * emp_main_school_id: 6,
     * college_code: "11",
     * college_name: "Atmiya Institute of Technology & Science (MCA)",
     * ac_logo: "458afea2_3f9f_45d6_8ccb_acb765678fff.png",
     * stud_photo: "https://cms.atmiya.edu.in/images/Emp_photo/2270.jpg",
     * status: 1,
     * is_director: 0,
     * emp_year_id: 5,
     * institute_id: 1,
     * im_domain_name: "https://cms.atmiya.edu.in",
     * emp_user_status: 1,
     * emp_permenant_college: null,
     * emp_department_id: 4,
     * emp_username: "infinitysupport@gmail.com",
     * emp_password: null
     **/

    private void setEmpNewLoginDate(CheckLoginByOTPAndUsernamePojo checkLoginByOTPAndUsernamePojo) {


        //emp_username
        if (checkLoginByOTPAndUsernamePojo.getTable().get(0).getEmp_username() != null) {
            mySharedPreferences.setEmpUserName(checkLoginByOTPAndUsernamePojo.getTable().get(0).getEmp_username());
        }
        mySharedPreferences.setLoginUserType(1);

        // emp_id: 2270,
        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsernamePojo.getTable().get(0).getEmp_id())) {
            mySharedPreferences.setEmpId(checkLoginByOTPAndUsernamePojo.getTable().get(0).getEmp_id() + "");
        }

        //emp_number: "123456",
        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsernamePojo.getTable().get(0).getEmp_number())) {
            mySharedPreferences.setEmpNumber(checkLoginByOTPAndUsernamePojo.getTable().get(0).getEmp_number() + "");
        }

        //name: "CMS Admin Infinity",
        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsernamePojo.getTable().get(0).getName())) {
            mySharedPreferences.setEmpName(checkLoginByOTPAndUsernamePojo.getTable().get(0).getName() + "");
        }

        // emp_main_school_id: 6,
        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsernamePojo.getTable().get(0).getEmp_main_school_id())) {
            mySharedPreferences.setEmpMainSchoolId(checkLoginByOTPAndUsernamePojo.getTable().get(0).getEmp_main_school_id() + "");
        }

        /*if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsernamePojo.getTable().get(0).get)) {
            mySharedPreferences.setAcFullName(checkOTPVerificationForEmployeePojo.getAcFullName() + "");
        }*/

        //ac_logo: "458afea2_3f9f_45d6_8ccb_acb765678fff.png",
        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsernamePojo.getTable().get(0).getAc_logo())) {
            mySharedPreferences.setEmpAcLogo(checkLoginByOTPAndUsernamePojo.getTable().get(0).getAc_logo() + "");
        }

        //stud_photo: "https://cms.atmiya.edu.in/images/Emp_photo/2270.jpg",
        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsernamePojo.getTable().get(0).getStud_photo())) {
            mySharedPreferences.setStudentPhotoUrl(checkLoginByOTPAndUsernamePojo.getTable().get(0).getStud_photo() + "");
        }

      /*  if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsernamePojo.getTable().get(0).getStatus())) {
            mySharedPreferences.setEmpS(checkLoginByOTPAndUsernamePojo.getTable().get(0).getStatus() + "");
        }*/

        //not available in response
      /*  if (!CommonUtil.checkIsEmptyOrNullCommon(checkOTPVerificationForEmployeePojo.getAcCode())) {
            mySharedPreferences.setEmpAcCode(checkOTPVerificationForEmployeePojo.getAcCode() + "");
        }*/

        //is_director
        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsernamePojo.getTable().get(0).getIs_director())) {
            mySharedPreferences.setEmpIsDirectory(checkLoginByOTPAndUsernamePojo.getTable().get(0).getIs_director() + "");
        }

        //emp_year_id
        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsernamePojo.getTable().get(0).getEmp_year_id())) {
            mySharedPreferences.setEmpYearId(checkLoginByOTPAndUsernamePojo.getTable().get(0).getEmp_year_id() + "");
        }

        //institute_id
        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsernamePojo.getTable().get(0).getInstitute_id())) {
            mySharedPreferences.setEmpInstituteId(checkLoginByOTPAndUsernamePojo.getTable().get(0).getInstitute_id() + "");
        }

        //im_domain_name
        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsernamePojo.getTable().get(0).getIm_domain_name())) {
            mySharedPreferences.setEmpImDomainName(checkLoginByOTPAndUsernamePojo.getTable().get(0).getIm_domain_name() + "");
        }

        //emp_user_status
        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsernamePojo.getTable().get(0).getEmp_user_status())) {
            mySharedPreferences.setEmpUserStatus(checkLoginByOTPAndUsernamePojo.getTable().get(0).getEmp_user_status() + "");
        }

        //emp_permenant_college
        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsernamePojo.getTable().get(0).getEmp_permenant_college())) {
            mySharedPreferences.setEmpPermanentCollege(checkLoginByOTPAndUsernamePojo.getTable().get(0).getEmp_permenant_college() + "");
        }

        //emp_department_id
        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsernamePojo.getTable().get(0).getEmp_department_id())) {
            mySharedPreferences.setEmpDepartmentId(checkLoginByOTPAndUsernamePojo.getTable().get(0).getEmp_department_id() + "");
        }



        /*if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsernamePojo.getTable().get(0).getCollege_name())) {
            mySharedPreferences.setColl(checkLoginByOTPAndUsernamePojo.getTable().get(0).getCollege_name() + "");
        }*/


    }


    /**
     * stud_id: 5547,
     * dm_id: 5,
     * dm_full_name: "Information Technology",
     * course_id: 36,
     * course_fullname: "B. E. (Information Tech.)",
     * sm_id: 2019,
     * sm_name: "Semester - 8",
     * college_code: 3,
     * college_name: "Atmiya Institute of Technology & Science",
     * swd_year_id: 3,
     * status: 1,
     * hostel_code: "0",
     * name: "Vekaria Vaibhav Pravinbhai",
     * stud_admission_no: "1030416061",
     * Stud_Enrollment_no: "160033116004",
     * stud_photo: "https://cms.atmiya.edu.in/images/stud_photo/1030416061.jpg",
     * swd_division_id: 2347,
     * swd_batch_id: 1524,
     * shift_id: 1,
     * im_domain_name: "https://cms.atmiya.edu.in",
     * intitute_id: 1,
     * fc_file: "https://cms.atmiya.edu.in/images/Fee_Circular/3_36/5b0e8eba_bf91_4841_9ef4_608d49e99da1.pdf",
     * Stud_user_name: "1030416061",
     * Stud_password: ""
     **/
    private void setStudentNewLoginData(CheckLoginByOTPAndUsernamePojo CheckLoginByOTPAndUsernamePojo) {

        //stud_id: 5547,
        if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getStud_id())) {
            mySharedPreferences.setStudentId(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getStud_id() + "");
        }

        //dm_id: 5,
        if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getDm_id())) {
            mySharedPreferences.setDMId(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getDm_id() + "");
        }

        //dm_full_name: "Information Technology",
        if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getDm_full_name())) {
            mySharedPreferences.setDmFullName(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getDm_full_name() + "");
        }
        //course_id: 36,
        if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getCourse_id())) {
            mySharedPreferences.setCourseId(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getCourse_id() + "");
        }
        //sm_id: 2019,
        if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getSm_id())) {
            mySharedPreferences.setSmId(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getSm_id() + "");
        }

        //sm_name: "Semester - 8",
        if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getSm_name())) {
            mySharedPreferences.setSmName(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getSm_name() + "");
        }

        mySharedPreferences.setLoginUserType(2);

        //college_code: 3,
       /* if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getSm_name())) {
            mySharedPreferences.setStu(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getSm_name()+ "");
        }*/

        //college_name: "Atmiya Institute of Technology & Science",
        /*if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getCollege_name())) {
            mySharedPreferences.setC(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getCollege_name()+ "");
        }*/

        //swd_year_id: 3,
        if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getSwd_year_id())) {
            mySharedPreferences.setSwdYearId(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getSwd_year_id() + "");
        }
        //hostel_code: "0"
        if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getHostel_code())) {
            mySharedPreferences.setHostelCode(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getHostel_code() + "");
        }

        //name
        if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getName())) {
            mySharedPreferences.setStudentName(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getName() + "");
        }

        //stud_admission_no
        if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getStud_admission_no())) {
            mySharedPreferences.setStudAdmissionNo(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getStud_admission_no() + "");
        }

        //Stud_Enrollment_no
        if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getStud_Enrollment_no())) {
            mySharedPreferences.setStudentEnrollmentNo(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getStud_Enrollment_no() + "");
        }

        //stud_photo
        if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getStud_photo())) {
            mySharedPreferences.setStudentPhotoUrl(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getStud_photo() + "");
        }
        //swd_division_id
        if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getSwd_division_id())) {
            mySharedPreferences.setSwdDivisionId(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getSwd_division_id() + "");
        }

        //swd_batch_id
        if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getSwd_batch_id())) {
            mySharedPreferences.setSwdBatchId(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getSwd_batch_id() + "");
        }

        //shift_id
        if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getShift_id())) {
            mySharedPreferences.setShiftId(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getShift_id() + "");
        }
        //im_domain_name
        if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getIm_domain_name())) {
            mySharedPreferences.setImDomainName(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getIm_domain_name() + "");
        }
        //intitute_id
        if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getIntitute_id())) {
            mySharedPreferences.setInstituteId(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getIntitute_id() + "");
        }

        //fc_file
        if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getFc_file())) {
            mySharedPreferences.setFcFile(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getFc_file() + "");
        }

        //Stud_user_name
        if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getStud_user_name())) {
            mySharedPreferences.setStudentUsername(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getStud_user_name() + "");
        }

        //course_fullname
        if (!CommonUtil.checkIsEmptyOrNullCommon(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getCourse_fullname())) {
            mySharedPreferences.setCourseFullName(CheckLoginByOTPAndUsernamePojo.getTable().get(0).getCourse_fullname() + "");
        }


    }


}