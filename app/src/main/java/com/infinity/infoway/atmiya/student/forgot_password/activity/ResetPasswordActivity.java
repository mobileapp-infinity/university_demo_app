package com.infinity.infoway.atmiya.student.forgot_password.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.ResetEmployeePasswordPojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.ResetStudentPasswordPojo;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.DialogUtil;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseResetPassword;
    AppCompatEditText edtNewPassword;
    AppCompatEditText edtConfirmPassword;
    TextViewRegularFont btnUpdatePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initView();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(ResetPasswordActivity.this);
        connectionDetector = new ConnectionDetector(ResetPasswordActivity.this);
        ivCloseResetPassword = findViewById(R.id.ivCloseResetPassword);
        ivCloseResetPassword.setOnClickListener(this);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnUpdatePassword = findViewById(R.id.btnUpdatePassword);
        btnUpdatePassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseResetPassword) {
            onBackPressed();
        } else if (v.getId() == R.id.btnUpdatePassword) {
            if (isValid()) {

            }
        }
    }

    private boolean isValid() {
        boolean isValid = true;
        if (CommonUtil.checkIsEmptyOrNullCommon(edtNewPassword.getText().toString().trim())) {
            isValid = false;
            Toast.makeText(this, "Please enter new password.", Toast.LENGTH_SHORT).show();
        } else if (CommonUtil.checkIsEmptyOrNullCommon(edtConfirmPassword.getText().toString().trim())) {
            isValid = false;
            Toast.makeText(this, "Please enter confirm password.", Toast.LENGTH_SHORT).show();
        } else if (!edtNewPassword.getText().toString().trim().equalsIgnoreCase(edtConfirmPassword.getText().toString())) {
            isValid = false;
            Toast.makeText(this, "Password not match", Toast.LENGTH_SHORT).show();
        }
        return isValid;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void resetStudentPasswordApiCall(String studId, String instituteId, String password, String modifyBy, String modifyIp) {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(ResetPasswordActivity.this, "");
            ApiImplementer.resetStudentPasswordApiImplementer(studId, instituteId, password, modifyBy, modifyIp, new Callback<ResetStudentPasswordPojo>() {
                @Override
                public void onResponse(Call<ResetStudentPasswordPojo> call, Response<ResetStudentPasswordPojo> response) {
                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0 &&
                            response.body().getTable().get(0).getErrorCode() == 1) {
                        Toast.makeText(ResetPasswordActivity.this, "Password reset successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ResetPasswordActivity.this, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResetStudentPasswordPojo> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(ResetPasswordActivity.this, "Request failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


    private void resetEmployeePasswordApiCall(String emp_id, String institute_id, String password, String modify_by, String modify_ip) {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(ResetPasswordActivity.this, "");
            ApiImplementer.resetEmployeePasswordApiImplementer(emp_id, institute_id, password, modify_by, modify_ip, new Callback<ResetEmployeePasswordPojo>() {
                @Override
                public void onResponse(Call<ResetEmployeePasswordPojo> call, Response<ResetEmployeePasswordPojo> response) {
                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0 &&
                            response.body().getTable().get(0).getErrorCode() == 1) {
                        Toast.makeText(ResetPasswordActivity.this, "Password reset successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ResetPasswordActivity.this, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResetEmployeePasswordPojo> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(ResetPasswordActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


}