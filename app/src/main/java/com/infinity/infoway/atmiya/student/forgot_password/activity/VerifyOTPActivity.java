package com.infinity.infoway.atmiya.student.forgot_password.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
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


    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(VerifyOTPActivity.this);
        connectionDetector = new ConnectionDetector(VerifyOTPActivity.this);
        ivCloseVerifyOTP = findViewById(R.id.ivCloseVerifyOTP);
        ivCloseVerifyOTP.setOnClickListener(this);
        otp_view = findViewById(R.id.otp_view);
        btnVerifyOtpAndProceed = findViewById(R.id.btnVerifyOtpAndProceed);
        btnVerifyOtpAndProceed.setOnClickListener(this);
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
                        Toast.makeText(VerifyOTPActivity.this, "OTP verified Successfully ", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(VerifyOTPActivity.this, "OTP verified Successfully ", Toast.LENGTH_SHORT).show();
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


}