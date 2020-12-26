package com.infinity.infoway.atmiya.student.e_learning.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.student.e_learning.adapter.ELearningViewPagerAdapter;
import com.infinity.infoway.atmiya.student.e_learning.fragment.EnrollToGroupFragment;
import com.infinity.infoway.atmiya.student.e_learning.fragment.JoinGroupFragment;
import com.infinity.infoway.atmiya.student.leave_application.activity.LeaveApplicationActivity;
import com.infinity.infoway.atmiya.student.leave_application.adapter.LeaveApplicationViewPagerAdapter;
import com.infinity.infoway.atmiya.student.leave_application.fragment.ApplyILeaveFragment;
import com.infinity.infoway.atmiya.student.leave_application.fragment.LeaveHistoryFragment;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.IntentConstants;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;
import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.model.MediaFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ELearningActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageView ivBackELearning;
    TabLayout tlELearning;
    ViewPager vpELearning;
    JoinGroupFragment joinGroupFragment;
    EnrollToGroupFragment enrollToGroupFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_learning);
        initView();
    }


    private void initView() {

        ivBackELearning = findViewById(R.id.ivBackELearning);
        ivBackELearning.setOnClickListener(this);

        tlELearning = findViewById(R.id.tlELearning);
        vpELearning = findViewById(R.id.vpELearning);
        tlELearning.setupWithViewPager(vpELearning);

        joinGroupFragment = JoinGroupFragment.newInstance();
        enrollToGroupFragment = enrollToGroupFragment.newInstance();
        setupViewPager(vpELearning);
    }

    private void setupViewPager(ViewPager viewPager) {
        ELearningViewPagerAdapter eLearningViewPagerAdapter = new ELearningViewPagerAdapter(getSupportFragmentManager());
        eLearningViewPagerAdapter.addFragment(joinGroupFragment, "JOIN GROUP");
        eLearningViewPagerAdapter.addFragment(enrollToGroupFragment, "ENROLL TO GROUP");
        viewPager.setAdapter(eLearningViewPagerAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivBackELearning) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}