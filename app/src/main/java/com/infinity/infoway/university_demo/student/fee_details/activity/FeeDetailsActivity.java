package com.infinity.infoway.university_demo.student.fee_details.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.infoway.university_demo.R;
import com.infinity.infoway.university_demo.api.ApiImplementer;
import com.infinity.infoway.university_demo.student.fee_details.pojo.PaySlipOfAxisPojo;
import com.infinity.infoway.university_demo.utils.CommonUtil;
import com.infinity.infoway.university_demo.utils.ConnectionDetector;
import com.infinity.infoway.university_demo.utils.DialogUtil;
import com.infinity.infoway.university_demo.utils.GeneratePDFFileFromBase64String;
import com.infinity.infoway.university_demo.utils.MySharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeeDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseFeeDetails;
    LinearLayout llStudentFeeReceipt;
    //    ProgressDialog progressDialog;
    LinearLayout llPaySlipOfAxisFeeDetails, llDownloadPaySlipOfHdfc, llFeeCircularFeeDetails, llPayFee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_details);
        initView();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(FeeDetailsActivity.this);
        connectionDetector = new ConnectionDetector(FeeDetailsActivity.this);
        llStudentFeeReceipt = findViewById(R.id.llStudentFeeReceipt);
        llStudentFeeReceipt.setOnClickListener(this);
        ivCloseFeeDetails = findViewById(R.id.ivCloseFeeDetails);
        ivCloseFeeDetails.setOnClickListener(this);

        llPaySlipOfAxisFeeDetails = findViewById(R.id.llPaySlipOfAxisFeeDetails);
        llPaySlipOfAxisFeeDetails.setOnClickListener(this);
        llDownloadPaySlipOfHdfc = findViewById(R.id.llDownloadPaySlipOfHdfc);
        llDownloadPaySlipOfHdfc.setOnClickListener(this);
        llFeeCircularFeeDetails = findViewById(R.id.llFeeCircularFeeDetails);
        llFeeCircularFeeDetails.setOnClickListener(this);

        llPayFee = findViewById(R.id.llPayFee);
        llPayFee.setOnClickListener(this);

//        progressDialog = new ProgressDialog(FeeDetailsActivity.this);
//        progressDialog.setMessage("Please wait....");
//        progressDialog.setCancelable(false);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFeeDetails) {
            onBackPressed();
        } else if (v.getId() == R.id.llStudentFeeReceipt) {
            Intent intent = new Intent(FeeDetailsActivity.this, FeeReciptActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llPaySlipOfAxisFeeDetails) {
            downloadPayslipOfAxis();
        } else if (v.getId() == R.id.llDownloadPaySlipOfHdfc) {

        } else if (v.getId() == R.id.llFeeCircularFeeDetails) {
            if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getFcFile())) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mySharedPreferences.getFcFile().trim()));
                startActivity(browserIntent);
            } else {
                Toast.makeText(this, "Circular Not Found!", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.llPayFee) {
            Intent intent = new Intent(FeeDetailsActivity.this, PayFeeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void downloadPayslipOfAxis() {
        DialogUtil.showProgressDialogNotCancelable(FeeDetailsActivity.this, "downloading... ");
//        progressDialog.show();
        ApiImplementer.downloadPaySlipOfAxisApiImplementer(mySharedPreferences.getStudentId(), new Callback<PaySlipOfAxisPojo>() {
            @Override
            public void onResponse(Call<PaySlipOfAxisPojo> call, Response<PaySlipOfAxisPojo> response) {
                DialogUtil.hideProgressDialog();
                if (response.isSuccessful() && response.body() != null && response.body().getBase64string() != null &&
                        !response.body().getBase64string().isEmpty()) {
                    new GeneratePDFFileFromBase64String(FeeDetailsActivity.this, "Pay Slip Of Axis", response.body().getFilename(),
                            response.body().getBase64string());
                } else {
//                    progressDialog.hide();
                    Toast.makeText(FeeDetailsActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PaySlipOfAxisPojo> call, Throwable t) {
                DialogUtil.hideProgressDialog();
//                progressDialog.hide();
                Toast.makeText(FeeDetailsActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}