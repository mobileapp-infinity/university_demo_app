package com.infinity.infoway.atmiya.faculty.faculty_fill_attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.view.View;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;

public class FacultyFillAttendanceActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseFacultyFillAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_fill_attendance);
        initView();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(FacultyFillAttendanceActivity.this);
        connectionDetector = new ConnectionDetector(FacultyFillAttendanceActivity.this);
        ivCloseFacultyFillAttendance = findViewById(R.id.ivCloseFacultyFillAttendance);
        ivCloseFacultyFillAttendance.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultyFillAttendance) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}