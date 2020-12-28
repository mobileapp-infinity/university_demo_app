package com.infinity.infoway.atmiya.student.student_timetable.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.student.leave_application.activity.LeaveApplicationActivity;
import com.infinity.infoway.atmiya.student.leave_application.adapter.LeaveApplicationViewPagerAdapter;
import com.infinity.infoway.atmiya.student.leave_application.fragment.ApplyILeaveFragment;
import com.infinity.infoway.atmiya.student.leave_application.fragment.LeaveHistoryFragment;
import com.infinity.infoway.atmiya.student.student_timetable.adapter.TimeTableViewPagerAdapter;
import com.infinity.infoway.atmiya.student.student_timetable.fragment.TimeTableFragment;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;

public class StudentTimeTableActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseStudentTimeTable;
    TabLayout tlTimeTable;
    ViewPager vpTimeTable;
    TabLayout.Tab tabItem;

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

        tlTimeTable = findViewById(R.id.tlTimeTable);
        vpTimeTable = findViewById(R.id.vpTimeTable);


        tabItem = tlTimeTable.newTab();
       tlTimeTable.addTab(tabItem);
        tlTimeTable.setupWithViewPager(vpTimeTable);

        setupViewPager(vpTimeTable);

    }

    private void setupViewPager(ViewPager viewPager) {
        TimeTableViewPagerAdapter adapter = new TimeTableViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(TimeTableFragment.newInstance(), " Mon ");
        adapter.addFragment(TimeTableFragment.newInstance(), " Tue ");
        viewPager.setAdapter(adapter);
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