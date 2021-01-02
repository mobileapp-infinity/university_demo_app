package com.infinity.infoway.atmiya.student.forgot_password.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;
import com.mukesh.OtpListener;
import com.mukesh.OtpView;

public class VerifyOTPActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseVerifyOTP;
    OtpView otp_view;
    TextViewRegularFont btnVerifyOtpAndProceed;
//    String otpText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_o_t_p);
        initView();
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
            } else {
                Toast.makeText(this, "Please enter 6 digit OTP.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}