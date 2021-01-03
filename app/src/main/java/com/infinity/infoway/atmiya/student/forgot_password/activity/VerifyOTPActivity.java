package com.infinity.infoway.atmiya.student.forgot_password.activity;

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
import com.infinity.infoway.atmiya.login.activity.LoginActivity;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.CheckOTPVerificationForEmployeePojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.CheckOTPVerificationForStudentPojo;
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
    //    String otpText = "";
    String instituteId = "";
    String userName = "";
    String IS_EMPLOYEE_FORGOT_PASSWORD;
    String IS_STUDENT_FORGOT_PASSWORD;
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

        if (getIntent().hasExtra(IntentConstants.IS_EMPLOYEE_FORGOT_PASSWORD)) {
            IS_EMPLOYEE_FORGOT_PASSWORD = getIntent().getStringExtra(IntentConstants.IS_EMPLOYEE_FORGOT_PASSWORD);
        }

        if (getIntent().hasExtra(IntentConstants.IS_STUDENT_FORGOT_PASSWORD)) {
            IS_STUDENT_FORGOT_PASSWORD = getIntent().getStringExtra(IntentConstants.IS_STUDENT_FORGOT_PASSWORD);
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
                if (IS_STUDENT_FORGOT_PASSWORD.equalsIgnoreCase("1")) {
                    verifyStudentOTPApiCall(userName, otpText, instituteId);
                } else if (IS_EMPLOYEE_FORGOT_PASSWORD.equalsIgnoreCase("1")) {
                    verifyEmployeeOTPApiCall(userName, otpText, instituteId);
                } else {
                    Toast.makeText(this, "Something went wrong please try again later.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please enter 6 digit OTP.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void verifyStudentOTPApiCall(String username, String otp, String institute_id) {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(VerifyOTPActivity.this, "");
            ApiImplementer.checkOTPVerificationForStudentApiImplementer(username, otp, institute_id, "1", new Callback<CheckOTPVerificationForStudentPojo>() {
                @Override
                public void onResponse(Call<CheckOTPVerificationForStudentPojo> call, Response<CheckOTPVerificationForStudentPojo> response) {
                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                        CheckOTPVerificationForStudentPojo.TableBean checkOTPVerificationForStudentPojo = response.body().getTable().get(0);
                        String userName = checkOTPVerificationForStudentPojo.getStudUserName().toString().trim();
                        String userId = checkOTPVerificationForStudentPojo.getStudId() + "";
                        String password = checkOTPVerificationForStudentPojo.getStudPassword().toString().trim();
                        String isEmp = "0";
                        String isStudent = "1";
                        String instituteId = institute_id;
                        Toast.makeText(VerifyOTPActivity.this, "OTP verified Successfully ", Toast.LENGTH_SHORT).show();
                        redirectToResetPasswordActivity(userName, userId, password, isEmp, isStudent, instituteId);
                    } else {
                        Toast.makeText(VerifyOTPActivity.this, "Incorrect OTP,Please enter correct OTP.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CheckOTPVerificationForStudentPojo> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(VerifyOTPActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


    private void verifyEmployeeOTPApiCall(String username, String otp, String institute_id) {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(VerifyOTPActivity.this, "");
            ApiImplementer.checkOTPVerificationForEmployeeApiImplementer(username, otp, institute_id, "1", new Callback<CheckOTPVerificationForEmployeePojo>() {
                @Override
                public void onResponse(Call<CheckOTPVerificationForEmployeePojo> call, Response<CheckOTPVerificationForEmployeePojo> response) {
                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                        CheckOTPVerificationForEmployeePojo.TableBean checkOTPVerificationForEmployeePojo = response.body().getTable().get(0);
                        Toast.makeText(VerifyOTPActivity.this, "OTP verified Successfully ", Toast.LENGTH_SHORT).show();

                        String userName = checkOTPVerificationForEmployeePojo.getEmpUsername().trim();
                        String userId = checkOTPVerificationForEmployeePojo.getEmpId() + "";
                        String password = checkOTPVerificationForEmployeePojo.getEmpPassword().toString().trim();
                        String isEmp = "1";
                        String isStudent = "0";
                        String instituteId = institute_id;
                        redirectToResetPasswordActivity(userName, userId, password, isEmp, isStudent, instituteId);
                    } else {
                        Toast.makeText(VerifyOTPActivity.this, "Incorrect OTP,Please enter correct OTP.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CheckOTPVerificationForEmployeePojo> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(VerifyOTPActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


    private void redirectToResetPasswordActivity(String userName, String userId, String password,
                                                 String isEmp, String isStudent, String instituteId) {
        Intent intent = new Intent(VerifyOTPActivity.this, ResetPasswordActivity.class);
        intent.putExtra(IntentConstants.USERNAME_AFTER_FORGOT_PASS, userName);
        intent.putExtra(IntentConstants.RESET_PASS_USER_ID, userId);
        intent.putExtra(IntentConstants.PASSWORD_AFTER_FORGOT_PASS, password);
        intent.putExtra(IntentConstants.IS_EMPLOYEE_RESET_PASSWORD, isEmp);
        intent.putExtra(IntentConstants.IS_STUDENT_RESET_PASSWORD, isStudent);
        intent.putExtra(IntentConstants.RESET_PASS_INSTITUTE_ID, instituteId);
        startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_RESET_PASSWORD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK &&
                requestCode == IntentConstants.REQUEST_CODE_FOR_RESET_PASSWORD) {
            Intent intent = new Intent(VerifyOTPActivity.this, ForgotPasswordActivity.class);
            String userName = "";
            String password = "";

            if (data.hasExtra(IntentConstants.USERNAME_AFTER_FORGOT_PASS)) {
                userName = data.getStringExtra(IntentConstants.USERNAME_AFTER_FORGOT_PASS);
            }

            if (data.hasExtra(IntentConstants.PASSWORD_AFTER_FORGOT_PASS)) {
                password = data.getStringExtra(IntentConstants.PASSWORD_AFTER_FORGOT_PASS);
            }
            intent.putExtra(IntentConstants.USERNAME_AFTER_FORGOT_PASS, userName);
            intent.putExtra(IntentConstants.PASSWORD_AFTER_FORGOT_PASS, password);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}