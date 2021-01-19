package com.infinity.infoway.atmiya.forgot_password.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import com.infinity.infoway.atmiya.R;

public class SelectUserTypeActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout llIAmAnEmployee;
    LinearLayout llIAmAStudent;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user_type);
        initView();
    }

    private void initView() {
        llIAmAnEmployee = findViewById(R.id.llIAmAnEmployee);
        llIAmAnEmployee.setOnClickListener(this);
        llIAmAStudent = findViewById(R.id.llIAmAStudent);
        llIAmAStudent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.llIAmAnEmployee) {
            llIAmAnEmployee.setBackground(ContextCompat.getDrawable(SelectUserTypeActivity.this, R.drawable.shape_select_employee_student_filled));
            llIAmAStudent.setBackground(ContextCompat.getDrawable(SelectUserTypeActivity.this, R.drawable.shape_select_employee_student_common));
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                }
            }, 80);
        } else if (v.getId() == R.id.llIAmAStudent) {
            llIAmAnEmployee.setBackground(ContextCompat.getDrawable(SelectUserTypeActivity.this, R.drawable.shape_select_employee_student_common));
            llIAmAStudent.setBackground(ContextCompat.getDrawable(SelectUserTypeActivity.this, R.drawable.shape_select_employee_student_filled));
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                }
            }, 80);
        }
    }
}