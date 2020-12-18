package com.infinity.infoway.atmiya.login.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.custom_class.RecyclerItemTouchHelper;
import com.infinity.infoway.atmiya.login.adapter.LoginUserListAdapter;
import com.infinity.infoway.atmiya.login.pojo.StudentLoginPojo;
import com.infinity.infoway.atmiya.student.student_dashboard.activity.StudentDashboardActivity;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, View.OnClickListener {

    AppCompatEditText edtLoginUserName, edtLoginUserPassword;
    LinearLayout llLogin, llForgotPassword;
    MySharedPreferences mySharedPreferences;

    RecyclerView rvLoginUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
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
        edtLoginUserName = findViewById(R.id.edtLoginUserName);
        edtLoginUserPassword = findViewById(R.id.edtLoginUserPassword);
        llLogin = findViewById(R.id.llLogin);
        llLogin.setOnClickListener(this);
        llForgotPassword = findViewById(R.id.llForgotPassword);
        llForgotPassword.setOnClickListener(this);

        rvLoginUserList = findViewById(R.id.rvLoginUserList);
        rvLoginUserList.setAdapter(new LoginUserListAdapter(LoginActivity.this));

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.RIGHT, LoginActivity.this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rvLoginUserList);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.llLogin) {
            if (isValid()) {
                checkLoginApiCall(edtLoginUserName.getText().toString().trim(), edtLoginUserPassword.getText().toString().trim());
            }
        } else if (v.getId() == R.id.llForgotPassword) {

        }
    }


    private void checkLoginApiCall(String userName, String password) {
        Map<String, String> mParams = new HashMap<String, String>();
        mParams.put("username", userName);
        mParams.put("password", password);
        ApiImplementer.checkStudentLoginApiImplementer(null, new Callback<StudentLoginPojo>() {
            @Override
            public void onResponse(Call<StudentLoginPojo> call, Response<StudentLoginPojo> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getStatus().equalsIgnoreCase("1")) {
                    StudentLoginPojo studentLoginPojo = response.body();
                    setStudentLoginData(userName, password, studentLoginPojo);
                    Intent intent = new Intent(LoginActivity.this, StudentDashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StudentLoginPojo> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setStudentLoginData(String userName, String password, StudentLoginPojo studentLoginPojo) {

        mySharedPreferences.setStudentUsername(userName);
        mySharedPreferences.setStudentPassword(password);

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


}