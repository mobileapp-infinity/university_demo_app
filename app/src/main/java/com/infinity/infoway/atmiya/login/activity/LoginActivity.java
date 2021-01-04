package com.infinity.infoway.atmiya.login.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.custom_class.RecyclerItemTouchHelper;
import com.infinity.infoway.atmiya.custom_class.SwipeHelper;
import com.infinity.infoway.atmiya.login.adapter.LoginUserListAdapter;
import com.infinity.infoway.atmiya.login.pojo.RegisterStudentDetailsModel;
import com.infinity.infoway.atmiya.login.pojo.StudentLoginPojo;
import com.infinity.infoway.atmiya.student.forgot_password.activity.ForgotPasswordActivity;
import com.infinity.infoway.atmiya.student.student_dashboard.activity.StudentDashboardActivity;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.DialogUtil;
import com.infinity.infoway.atmiya.utils.IntentConstants;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener, LoginUserListAdapter.IOnLoggedInStudentItemClicked {

    AppCompatEditText edtLoginUserName, edtLoginUserPassword;
    LinearLayout llLogin, llForgotPassword;
    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    RecyclerView rvLoginUserList;
    LinearLayout llLoggedInStudentList;
    AppCompatCheckBox chk_show_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        try {
            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(LoginActivity.this, new OnSuccessListener<InstanceIdResult>() {
                @Override
                public void onSuccess(InstanceIdResult instanceIdResult) {
                    String fcmToken = instanceIdResult.getToken();
                    if (fcmToken != null &&
                            !fcmToken.isEmpty()) {
                        mySharedPreferences.setFCMToken(fcmToken);
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (mySharedPreferences.checkIsStudentCurrentlyLoggedIn() &&
                mySharedPreferences.getLoginUserType() == CommonUtil.LOGIN_TYPE_STUDENT) {
            redirectToStudentDashboard();
        }
        displayLoggedStudentList();
    }

    private void displayLoggedStudentList() {
        ArrayList<RegisterStudentDetailsModel> registerStudentDetailsModelArrayList = new ArrayList<>();
        HashMap<String, ArrayList<String>> hashMap = mySharedPreferences.getStudentIdAndName(LoginActivity.this);
        if (hashMap != null && hashMap.size() > 0 && hashMap.keySet() != null) {
            for (String key : hashMap.keySet()) {
                RegisterStudentDetailsModel registerStudentDetailsModel = new RegisterStudentDetailsModel();
                registerStudentDetailsModel.setStuEnrollmentNo(key);
                registerStudentDetailsModel.setStudentName(hashMap.get(key).get(0)); //0 For Student Name
                registerStudentDetailsModel.setStuPassword(hashMap.get(key).get(1));//1 For Student Password
                registerStudentDetailsModelArrayList.add(registerStudentDetailsModel);
            }
        }

        if (registerStudentDetailsModelArrayList.size() > 0) {
            llLoggedInStudentList.setVisibility(View.VISIBLE);
            rvLoginUserList.setAdapter(new LoginUserListAdapter(LoginActivity.this, registerStudentDetailsModelArrayList));
        }
    }


    private boolean isValid() {
        boolean isValid = true;
        if (edtLoginUserName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (edtLoginUserPassword.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        return isValid;
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(LoginActivity.this);
        connectionDetector = new ConnectionDetector(LoginActivity.this);
        edtLoginUserName = findViewById(R.id.edtLoginUserName);
        edtLoginUserPassword = findViewById(R.id.edtLoginUserPassword);
        llLogin = findViewById(R.id.llLogin);
        llLogin.setOnClickListener(this);
        llForgotPassword = findViewById(R.id.llForgotPassword);
        llForgotPassword.setOnClickListener(this);
        chk_show_password = findViewById(R.id.chk_show_password);
        llLoggedInStudentList = findViewById(R.id.llLoggedInStudentList);
        rvLoginUserList = findViewById(R.id.rvLoginUserList);


        chk_show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    edtLoginUserPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    edtLoginUserPassword.setSelection(edtLoginUserPassword.getText().toString().length());
                } else {
                    edtLoginUserPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    edtLoginUserPassword.setSelection(edtLoginUserPassword.getText().toString().length());
                }
            }
        });
    }

//    @Override
//    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
//
//    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.llLogin) {
            if (isValid()) {
                CommonUtil.hideKeyboardCommon(LoginActivity.this);
                checkLoginApiCall(edtLoginUserName.getText().toString().trim(), edtLoginUserPassword.getText().toString().trim());
            }
        } else if (v.getId() == R.id.llForgotPassword) {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_FORGOT_PASSWORD);
        }
    }

    private void redirectToStudentDashboard() {
        Intent intent = new Intent(LoginActivity.this, StudentDashboardActivity.class);
        startActivity(intent);
        finish();
    }

    private void checkLoginApiCall(String userName, String password) {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(LoginActivity.this, "");
            HashMap<String, String> mParams = new HashMap();
            mParams.put("username", userName);
            mParams.put("password", password);
            ApiImplementer.checkStudentLoginApiImplementer(mParams, new Callback<StudentLoginPojo>() {
                @Override
                public void onResponse(Call<StudentLoginPojo> call, Response<StudentLoginPojo> response) {
                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null) {
                        if (response.body().getStatus().equalsIgnoreCase("1")) {
                            StudentLoginPojo studentLoginPojo = response.body();
                            setStudentLoginData(userName, password, studentLoginPojo);
                            if (response.body().getName() != null && !response.body().getName().isEmpty()) {
                                mySharedPreferences.storeStudentIdAndName(userName, password, response.body().getName(), LoginActivity.this);
                            }
                            redirectToStudentDashboard();
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Some thing went wrong please try again later", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<StudentLoginPojo> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(LoginActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection, please try again later.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    private void setStudentLoginData(String userName, String password, StudentLoginPojo studentLoginPojo) {

        mySharedPreferences.setStudentUsername(userName);
        mySharedPreferences.setStudentPassword(password);

        if (studentLoginPojo.getLoginUserType() != null) {
            mySharedPreferences.setLoginUserType(studentLoginPojo.getLoginUserType());
        }

        if (studentLoginPojo.getStudId() != null) {
            mySharedPreferences.setStudentId(studentLoginPojo.getStudId());
        }

        if (studentLoginPojo.getDmId() != null) {
            mySharedPreferences.setDMId(studentLoginPojo.getDmId());
        }

        if (studentLoginPojo.getDmFullName() != null) {
            mySharedPreferences.setDmFullName(studentLoginPojo.getDmFullName());
        }

        if (studentLoginPojo.getCourseId() != null) {
            mySharedPreferences.setCourseId(studentLoginPojo.getCourseId());
        }

        if (studentLoginPojo.getCourseFullname() != null) {
            mySharedPreferences.setCourseFullName(studentLoginPojo.getCourseFullname());
        }

        if (studentLoginPojo.getSmId() != null) {
            mySharedPreferences.setSmId(studentLoginPojo.getSmId());
        }

        if (studentLoginPojo.getSmName() != null) {
            mySharedPreferences.setSmName(studentLoginPojo.getSmName());
        }

        if (studentLoginPojo.getAcId() != null) {
            mySharedPreferences.setAcId(studentLoginPojo.getAcId());
        }

        if (studentLoginPojo.getAcFullName() != null) {
            mySharedPreferences.setAcFullName(studentLoginPojo.getAcFullName());
        }

        if (studentLoginPojo.getSwdYearId() != null) {
            mySharedPreferences.setSwdYearId(studentLoginPojo.getSwdYearId());
        }

        if (studentLoginPojo.getAcCode() != null) {
            mySharedPreferences.setAcCode(studentLoginPojo.getAcCode());
        }

        if (studentLoginPojo.getHostelCode() != null) {
            mySharedPreferences.setHostelCode(studentLoginPojo.getHostelCode());
        }

        if (studentLoginPojo.getName() != null) {
            mySharedPreferences.setStudentName(studentLoginPojo.getName());
        }

        if (studentLoginPojo.getStudAdmissionNo() != null) {
            mySharedPreferences.setStudAdmissionNo(studentLoginPojo.getStudAdmissionNo());
        }

        if (studentLoginPojo.getStudEnrollmentNo() != null) {
            mySharedPreferences.setStudentEnrollmentNo(studentLoginPojo.getStudEnrollmentNo());
        }

        if (studentLoginPojo.getStudPhoto() != null) {
            mySharedPreferences.setStudentPhotoUrl(studentLoginPojo.getStudPhoto());
        }

        if (studentLoginPojo.getStatus() != null) {
            mySharedPreferences.setStatus(studentLoginPojo.getStatus());
        }

        if (studentLoginPojo.getSwdDivisionId() != null) {
            mySharedPreferences.setSwdDivisionId(studentLoginPojo.getSwdDivisionId());
        }

        if (studentLoginPojo.getSwdBatchId() != null) {
            mySharedPreferences.setSwdBatchId(studentLoginPojo.getSwdBatchId());
        }

        if (studentLoginPojo.getShiftId() != null) {
            mySharedPreferences.setShiftId(studentLoginPojo.getShiftId());
        }

        if (studentLoginPojo.getImDomainName() != null) {
            mySharedPreferences.setImDomainName(studentLoginPojo.getImDomainName());
        }

        if (studentLoginPojo.getIntituteId() != null) {
            mySharedPreferences.setInstituteId(studentLoginPojo.getIntituteId());
        }

        if (studentLoginPojo.getFcFile() != null) {
            mySharedPreferences.setFcFile(studentLoginPojo.getFcFile());
        }

        if (studentLoginPojo.getImExamDbName() != null) {
            mySharedPreferences.setImExamDbName(studentLoginPojo.getImExamDbName());
        }
    }


    @Override
    public void onStudentItemClick(String studentName, String studentUserName, String studentPassword) {
        edtLoginUserName.setText(studentUserName);
        edtLoginUserPassword.setText(studentPassword);
        checkLoginApiCall(studentUserName, studentPassword);
    }

    @Override
    public void onDeleteLoggedInStudentClick(String studentName, String studentUserName, String studentPassword) {
        mySharedPreferences.deleteStudentNameAndId(studentUserName, LoginActivity.this);
        ArrayList<RegisterStudentDetailsModel> registerStudentDetailsModelArrayList = new ArrayList<>();
        HashMap<String, ArrayList<String>> hashMap = mySharedPreferences.getStudentIdAndName(LoginActivity.this);
        if (hashMap != null && hashMap.size() > 0 && hashMap.keySet() != null) {
            for (String key : hashMap.keySet()) {
                RegisterStudentDetailsModel registerStudentDetailsModel = new RegisterStudentDetailsModel();
                registerStudentDetailsModel.setStuEnrollmentNo(key);
                registerStudentDetailsModel.setStudentName(hashMap.get(key).get(0)); //0 For Student Name
                registerStudentDetailsModel.setStuPassword(hashMap.get(key).get(1));//1 For Student Password
                registerStudentDetailsModelArrayList.add(registerStudentDetailsModel);
            }
        }

        if (registerStudentDetailsModelArrayList.size() > 0) {
            llLoggedInStudentList.setVisibility(View.VISIBLE);
            rvLoginUserList.setAdapter(new LoginUserListAdapter(LoginActivity.this, registerStudentDetailsModelArrayList));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK &&
                requestCode == IntentConstants.REQUEST_CODE_FOR_FORGOT_PASSWORD) {
            String userName = "";
            String password = "";

            if (data.hasExtra(IntentConstants.USERNAME_AFTER_FORGOT_PASS)) {
                userName = data.getStringExtra(IntentConstants.USERNAME_AFTER_FORGOT_PASS);
            }

            if (data.hasExtra(IntentConstants.PASSWORD_AFTER_FORGOT_PASS)) {
                password = data.getStringExtra(IntentConstants.PASSWORD_AFTER_FORGOT_PASS);
            }

            if (!CommonUtil.checkIsEmptyOrNullCommon(userName) &&
                    !CommonUtil.checkIsEmptyOrNullCommon(password)) {
                edtLoginUserName.setText(userName + "");
                edtLoginUserPassword.setText(password + "");
                llLogin.performClick();
            }
        }
    }
}