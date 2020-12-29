package com.infinity.infoway.atmiya.student.fee_details.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.custom_class.TextViewMediumFont;
import com.infinity.infoway.atmiya.student.exam.activity.ExaminationScheduleActivity;
import com.infinity.infoway.atmiya.student.fee_details.pojo.FeeUrlPojo;
import com.infinity.infoway.atmiya.student.fee_details.pojo.PayFeeTypePojoList;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.DialogUtil;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayFeeActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivClosePayFee;

    TextViewMediumFont tvFeeDatePayFee;
    TextViewMediumFont tvFeeTypePayFee;
    TextViewMediumFont tvTotalFeePayFee;
    TextViewMediumFont tvPaidFeePayFee;
    TextViewMediumFont tvPendingFeePayFee;
    TextViewMediumFont tvPendingFeeRefundPayFee;
    TextViewMediumFont tvDueDatePayFee;

    LinearLayout llPayFeeDetails, llPayFeeProgressbar, llNoDataFoundPayFee;

    SearchableSpinner spFeeTypePayFee;
    ArrayList<String> feeTypeNameArrayList;
    HashMap<String, String> feeTypeAndIdHashMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_fee);
        initView();
        getFeeDetailsApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(PayFeeActivity.this);
        connectionDetector = new ConnectionDetector(PayFeeActivity.this);
        ivClosePayFee = findViewById(R.id.ivClosePayFee);
        ivClosePayFee.setOnClickListener(this);

        tvFeeDatePayFee = findViewById(R.id.tvFeeDatePayFee);
        tvFeeTypePayFee = findViewById(R.id.tvFeeTypePayFee);
        tvTotalFeePayFee = findViewById(R.id.tvTotalFeePayFee);
        tvPaidFeePayFee = findViewById(R.id.tvPaidFeePayFee);
        tvPendingFeePayFee = findViewById(R.id.tvPendingFeePayFee);
        tvPendingFeeRefundPayFee = findViewById(R.id.tvPendingFeeRefundPayFee);
        tvDueDatePayFee = findViewById(R.id.tvDueDatePayFee);

        llPayFeeDetails = findViewById(R.id.llPayFeeDetails);
        llPayFeeProgressbar = findViewById(R.id.llPayFeeProgressbar);
        llNoDataFoundPayFee = findViewById(R.id.llNoDataFoundPayFee);

        spFeeTypePayFee = findViewById(R.id.spFeeTypePayFee);


        spFeeTypePayFee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String feeType = feeTypeNameArrayList.get(position).trim();
                String feeTypeId = feeTypeAndIdHashMap.get(feeType).trim();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivClosePayFee) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void getFeeDetailsApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llPayFeeProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundPayFee.setVisibility(View.GONE);
            llPayFeeDetails.setVisibility(View.GONE);
            ApiImplementer.getPendingFeeTypeApiImplementer(mySharedPreferences.getStudentId(), mySharedPreferences.getSwdYearId(),
                    mySharedPreferences.getStudAdmissionNo(), mySharedPreferences.getAcCode(), mySharedPreferences.getHostelCode(), new Callback<ArrayList<PayFeeTypePojoList>>() {
                        @Override
                        public void onResponse(Call<ArrayList<PayFeeTypePojoList>> call, Response<ArrayList<PayFeeTypePojoList>> response) {
                            llPayFeeProgressbar.setVisibility(View.GONE);
                            try {
                                if (response.isSuccessful() && response.body() != null &&
                                        response.body().size() > 0) {
                                    ArrayList<PayFeeTypePojoList> payFeeTypePojoListArrayList = response.body();
                                    llPayFeeDetails.setVisibility(View.VISIBLE);
                                    feeTypeNameArrayList = new ArrayList<>();
                                    feeTypeAndIdHashMap = new HashMap<>();

                                    for (int i = 0; i < payFeeTypePojoListArrayList.size(); i++) {
                                        PayFeeTypePojoList payFeeTypePojoList = payFeeTypePojoListArrayList.get(i);
                                        if (!CommonUtil.checkIsEmptyOrNullCommon(payFeeTypePojoList.getFeeType()) &&
                                                !CommonUtil.checkIsEmptyOrNullCommon(payFeeTypePojoList.getFeeTypeId())) {
                                            feeTypeNameArrayList.add(payFeeTypePojoList.getFeeType() + "");
                                            feeTypeAndIdHashMap.put(payFeeTypePojoList.getFeeType(), payFeeTypePojoList.getFeeTypeId() + "");
                                        }
                                    }

                                    ArrayAdapter<String> bankAdapter = new ArrayAdapter<String>
                                            (PayFeeActivity.this, R.layout.layout_dropdown_row,
                                                    feeTypeNameArrayList);
                                    bankAdapter.setDropDownViewResource(R.layout.layout_dropdown_row);
                                    spFeeTypePayFee.setTitle("Select Fee Type");
                                    spFeeTypePayFee.setAdapter(bankAdapter);

                                } else {
                                    llPayFeeDetails.setVisibility(View.GONE);
                                    llNoDataFoundPayFee.setVisibility(View.VISIBLE);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<PayFeeTypePojoList>> call, Throwable t) {
                            llPayFeeProgressbar.setVisibility(View.GONE);
                            llPayFeeDetails.setVisibility(View.GONE);
                            llNoDataFoundPayFee.setVisibility(View.VISIBLE);
                            Toast.makeText(PayFeeActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getFeeUrlApiCall(String examId, String feeSource, String feeId, String createdIp) {
        DialogUtil.showProgressDialogNotCancelable(PayFeeActivity.this, "");
        ApiImplementer.feeUrlPojoApiImplementer(mySharedPreferences.getStudentId(), mySharedPreferences.getSwdYearId(), examId,
                mySharedPreferences.getSmId(), feeSource, feeId, createdIp, new Callback<FeeUrlPojo>() {
                    @Override
                    public void onResponse(Call<FeeUrlPojo> call, Response<FeeUrlPojo> response) {
                        DialogUtil.hideProgressDialog();
                        if (response.isSuccessful() && response.body() != null) {

                        } else {
                            Toast.makeText(PayFeeActivity.this, "No Data Fond!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<FeeUrlPojo> call, Throwable t) {
                        DialogUtil.hideProgressDialog();
                        Toast.makeText(PayFeeActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}