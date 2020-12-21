package com.infinity.infoway.atmiya.student.fee_details.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.infinity.infoway.atmiya.R;

public class FeeDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageView ivCloseFeeDetails;
    LinearLayout llStudentFeeReceipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_details);
        initView();
    }

    private void initView() {
        llStudentFeeReceipt = findViewById(R.id.llStudentFeeReceipt);
        llStudentFeeReceipt.setOnClickListener(this);
        ivCloseFeeDetails = findViewById(R.id.ivCloseFeeDetails);
        ivCloseFeeDetails.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFeeDetails) {
            onBackPressed();
        } else if (v.getId() == R.id.llStudentFeeReceipt) {
            Intent intent = new Intent(FeeDetailsActivity.this, FeeReciptActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}