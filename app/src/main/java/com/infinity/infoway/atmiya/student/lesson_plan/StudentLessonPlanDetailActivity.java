package com.infinity.infoway.atmiya.student.lesson_plan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;

public class StudentLessonPlanDetailActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseLessonPlanDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_lesson_plan_detail);
        initView();
    }


    private void initView() {
        mySharedPreferences = new MySharedPreferences(StudentLessonPlanDetailActivity.this);
        connectionDetector = new ConnectionDetector(StudentLessonPlanDetailActivity.this);
        ivCloseLessonPlanDetails = findViewById(R.id.ivCloseLessonPlanDetails);
        ivCloseLessonPlanDetails.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseLessonPlanDetails) {
            onBackPressed();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}