package com.infinity.infoway.atmiya.common_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.IntentConstants;

public class NoInternetConnectionActivity extends AppCompatActivity implements View.OnClickListener {

    ConnectionDetector connectionDetector;
    TextViewRegularFont btnTryAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet_connection);
        initView();
    }

    private void initView() {
        connectionDetector = new ConnectionDetector(NoInternetConnectionActivity.this);
        btnTryAgain = findViewById(R.id.btnTryAgain);
        btnTryAgain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnTryAgain) {
            if (!connectionDetector.isConnectingToInternet()) {
                Toast.makeText(this, "No internet connection!", Toast.LENGTH_SHORT).show();
            } else {
                setResult(RESULT_OK);
                finish();
            }
        }
    }
}