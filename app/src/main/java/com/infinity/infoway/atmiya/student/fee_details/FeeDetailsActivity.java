package com.infinity.infoway.atmiya.student.fee_details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.view.View;

import com.infinity.infoway.atmiya.R;

public class FeeDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageView ivCloseFeeDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_details);
        initView();
    }

    private void initView() {
        ivCloseFeeDetails = findViewById(R.id.ivCloseFeeDetails);
        ivCloseFeeDetails.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFeeDetails) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}