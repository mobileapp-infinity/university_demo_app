package com.infinity.infoway.atmiya.student.student_timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.view.View;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;

public class StudentTimeTableActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseStudentTimeTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_time_table);
        initView();
    }


    private void initView() {
        mySharedPreferences = new MySharedPreferences(StudentTimeTableActivity.this);
        connectionDetector = new ConnectionDetector(StudentTimeTableActivity.this);
        ivCloseStudentTimeTable = findViewById(R.id.ivCloseStudentTimeTable);
        ivCloseStudentTimeTable.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivCloseStudentTimeTable) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}