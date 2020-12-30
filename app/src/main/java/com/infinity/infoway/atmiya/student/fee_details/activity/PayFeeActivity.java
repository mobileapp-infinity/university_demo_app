package com.infinity.infoway.atmiya.student.fee_details.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.card.MaterialCardView;
import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.custom_class.TextViewMediumFont;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.student.fee_details.adapter.AllPendingFeeStudentAdapter;
import com.infinity.infoway.atmiya.student.fee_details.pojo.FeeUrlPojo;
import com.infinity.infoway.atmiya.student.fee_details.pojo.GetAllPendingFeeStudentPojo;
import com.infinity.infoway.atmiya.student.fee_details.pojo.GetPaymentButtonHideShowPojo;
import com.infinity.infoway.atmiya.student.fee_details.pojo.GetPaymentSingleButtonHideShowPojo;
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

public class PayFeeActivity extends AppCompatActivity implements View.OnClickListener, AllPendingFeeStudentAdapter.ISelectedPendingFeeListItem {

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
    RequestQueue queue;

    private static String IP = "";
    private LinearLayout llPaymentButtons;
    TextViewRegularFont btnPayWithHDFC, btnPayWithAxis, btnPayWithPaytm, btnPayNow;
    LinearLayout llContent;
    MaterialCardView cvPaymentDetails;
    LinearLayout llPendingFeeList;
    RecyclerView rvPendingFeeList;
    ArrayList<GetAllPendingFeeStudentPojo> getAllPendingFeeStudentPojoArrayListSelected;
    private boolean isMultiplePendingFeePayEnabled = false;
    private String multiplePendingFeePayListItemIds = "";
    private boolean isMultiplePendingFeePaySelected = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_fee);
        initView();
        getIp();
        getPendingFeeTypeApiCall();
    }

    private void initView() {
        queue = Volley.newRequestQueue(PayFeeActivity.this);
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
        llPaymentButtons = findViewById(R.id.llPaymentButtons);

        btnPayWithHDFC = findViewById(R.id.btnPayWithHDFC);
        btnPayWithHDFC.setOnClickListener(PayFeeActivity.this);
        btnPayWithAxis = findViewById(R.id.btnPayWithAxis);
        btnPayWithAxis.setOnClickListener(PayFeeActivity.this);
        btnPayWithPaytm = findViewById(R.id.btnPayWithPaytm);
        btnPayWithPaytm.setOnClickListener(PayFeeActivity.this);
        btnPayNow = findViewById(R.id.btnPayNow);
        btnPayNow.setOnClickListener(PayFeeActivity.this);
        llContent = findViewById(R.id.llContent);
        cvPaymentDetails = findViewById(R.id.cvPaymentDetails);
        llPendingFeeList = findViewById(R.id.llPendingFeeList);
        rvPendingFeeList = findViewById(R.id.rvPendingFeeList);

        spFeeTypePayFee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    String feeType = feeTypeNameArrayList.get(position).trim();
                    String feeTypeId = feeTypeAndIdHashMap.get(feeType).trim();
                    llContent.setVisibility(View.VISIBLE);
                    getPaymentButtonHideShowApiCall(feeTypeId);
                    getAllPendingFeeStudentListApiCall(feeType, feeTypeId);
                } else {
                    llPaymentButtons.setVisibility(View.GONE);
                    llContent.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getIp() {
        String GET_IP_URL = "http://checkip.amazonaws.com";
        StringRequest req = new StringRequest(Request.Method.GET, GET_IP_URL,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            if (!CommonUtil.checkIsEmptyOrNullCommon(response)) {
                                IP = response.trim() + "";
                            } else {
                                IP = "";
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                IP = "";
            }
        });
        queue.add(req);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivClosePayFee) {
            onBackPressed();
        } else if (v.getId() == R.id.btnPayWithHDFC) {

        } else if (v.getId() == R.id.btnPayWithAxis) {

            if (isValid()) {
                String feeType = feeTypeNameArrayList.get(spFeeTypePayFee.getSelectedItemPosition()).trim() + "";
                String feeTypeId = feeTypeAndIdHashMap.get(feeType).trim() + "";
                if (isMultiplePendingFeePayEnabled && feeTypeId.equalsIgnoreCase("1")) {
                    if (!CommonUtil.checkIsEmptyOrNullCommon(multiplePendingFeePayListItemIds)) {
                        //Send Fee Data Api Call
                    } else {
                        Toast.makeText(this, "Please Select Fee", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (feeTypeId.equalsIgnoreCase("0")) {

                    }
                }
            }
        } else if (v.getId() == R.id.btnPayWithPaytm) {

        } else if (v.getId() == R.id.btnPayNow) {

        }
    }

    private boolean isValid() {
        boolean isValidationCorrect = true;
        if (feeTypeNameArrayList == null || feeTypeNameArrayList.size() == 0 || feeTypeAndIdHashMap == null || feeTypeAndIdHashMap.size() == 0) {
            isValidationCorrect = false;
        }
        return isValidationCorrect;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getPendingFeeTypeApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llPayFeeProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundPayFee.setVisibility(View.GONE);
            llPayFeeDetails.setVisibility(View.GONE);
            ApiImplementer.getPendingFeeTypeApiImplementer(mySharedPreferences.getStudentId(), mySharedPreferences.getSwdYearId(),
                    mySharedPreferences.getStudAdmissionNo(), mySharedPreferences.getAcCode(), mySharedPreferences.getHostelCode(),
                    new Callback<ArrayList<PayFeeTypePojoList>>() {
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

    private void sendFeeDataTerm(String examId, String feeSource, String feeId, String createdIp) {
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

    private void sendFeeData(String examId, String feeSource, String feeId, String createdIp) {
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

    private void getPaymentButtonHideShowApiCall(String feeType) {
        ApiImplementer.getPaymentButtonHideShowApiImplementer(mySharedPreferences.getStudentId(), feeType, new Callback<ArrayList<GetPaymentButtonHideShowPojo>>() {
            @Override
            public void onResponse(Call<ArrayList<GetPaymentButtonHideShowPojo>> call, Response<ArrayList<GetPaymentButtonHideShowPojo>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                    if (response.body().get(0).getStatus().equalsIgnoreCase("0")) {
                        llPaymentButtons.setVisibility(View.GONE);
                        Toast.makeText(PayFeeActivity.this, "" + response.body().get(0).getMsg(), Toast.LENGTH_SHORT).show();
                    } else if (response.body().get(0).getStatus().equalsIgnoreCase("1")) {
                        llPaymentButtons.setVisibility(View.VISIBLE);
                        getPaymentSingleButtonHideShowApiCall();
                    } else {
                        llPaymentButtons.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetPaymentButtonHideShowPojo>> call, Throwable t) {

            }
        });
    }

    private void getPaymentSingleButtonHideShowApiCall() {
        ApiImplementer.getPaymentSingleButtonHideShowApiImplementer(new Callback<ArrayList<GetPaymentSingleButtonHideShowPojo>>() {
            @Override
            public void onResponse(Call<ArrayList<GetPaymentSingleButtonHideShowPojo>> call, Response<ArrayList<GetPaymentSingleButtonHideShowPojo>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                    GetPaymentSingleButtonHideShowPojo getPaymentSingleButtonHideShowPojo = response.body().get(0);

                    if (getPaymentSingleButtonHideShowPojo.getHDFC().equalsIgnoreCase("0")) {
                        btnPayWithHDFC.setVisibility(View.GONE);

                    } else {
                        btnPayWithHDFC.setVisibility(View.VISIBLE);
                    }


                    if (getPaymentSingleButtonHideShowPojo.getAXIS().equalsIgnoreCase("0")) {

                        btnPayWithAxis.setVisibility(View.GONE);
                    } else {
                        btnPayWithAxis.setVisibility(View.VISIBLE);
                    }


                    if (getPaymentSingleButtonHideShowPojo.getPAYTM().equalsIgnoreCase("0")) {

                        btnPayWithPaytm.setVisibility(View.GONE);
                    } else {
                        btnPayWithPaytm.setVisibility(View.VISIBLE);
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetPaymentSingleButtonHideShowPojo>> call, Throwable t) {

            }
        });
    }

    private void getAllPendingFeeStudentListApiCall(String feeTypeText, String feeTypeId) {
        DialogUtil.showProgressDialogNotCancelable(PayFeeActivity.this, "");
        ApiImplementer.getAllPendingFeeStudentApiImplementer(mySharedPreferences.getStudentId(),
                mySharedPreferences.getSwdYearId(), mySharedPreferences.getStudAdmissionNo(), "0",
                mySharedPreferences.getAcCode(), feeTypeText, mySharedPreferences.getHostelCode(), new Callback<ArrayList<GetAllPendingFeeStudentPojo>>() {
                    @Override
                    public void onResponse(Call<ArrayList<GetAllPendingFeeStudentPojo>> call, Response<ArrayList<GetAllPendingFeeStudentPojo>> response) {
                        DialogUtil.hideProgressDialog();
                        if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {

                            GetAllPendingFeeStudentPojo getAllPendingFeeStudentPojo = response.body().get(0);

                            if (feeTypeId.equalsIgnoreCase("1")) {
                                isMultiplePendingFeePayEnabled = true;
                                cvPaymentDetails.setVisibility(View.GONE);
                                llPendingFeeList.setVisibility(View.VISIBLE);
                                rvPendingFeeList.setAdapter(new AllPendingFeeStudentAdapter(PayFeeActivity.this, response.body()));
                            } else {
                                isMultiplePendingFeePayEnabled = false;
                                cvPaymentDetails.setVisibility(View.VISIBLE);
                                llPendingFeeList.setVisibility(View.GONE);

                                if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeeFeeDate())) {
                                    tvFeeDatePayFee.setText(getAllPendingFeeStudentPojo.getFeeFeeDate() + "");
                                }

                                if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeeFeeType())) {
                                    tvFeeTypePayFee.setText(getAllPendingFeeStudentPojo.getFeeFeeType() + "");
                                }

                                if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeeTotalFee())) {
                                    tvTotalFeePayFee.setText(getAllPendingFeeStudentPojo.getFeeTotalFee() + "");
                                }

                                if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeePaidFee())) {
                                    tvPaidFeePayFee.setText(getAllPendingFeeStudentPojo.getFeePaidFee() + "");
                                }

                                if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeePendingFee())) {
                                    tvPendingFeePayFee.setText(getAllPendingFeeStudentPojo.getFeePendingFee() + "");
                                }

                                if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeePendingFeeRefund())) {
                                    tvPendingFeeRefundPayFee.setText(getAllPendingFeeStudentPojo.getFeePendingFeeRefund() + "");
                                }
                                if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeeDueDate())) {
                                    tvDueDatePayFee.setText(getAllPendingFeeStudentPojo.getFeeDueDate() + "");
                                }
                            }
                        } else {
                            isMultiplePendingFeePayEnabled = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<GetAllPendingFeeStudentPojo>> call, Throwable t) {
                        DialogUtil.hideProgressDialog();
                        isMultiplePendingFeePayEnabled = false;
                    }
                });
    }

    @Override
    public void onPendingFeeListItemSelected(ArrayList<GetAllPendingFeeStudentPojo> getAllPendingFeeStudentPojoArrayList) {
        multiplePendingFeePayListItemIds = "";
        this.getAllPendingFeeStudentPojoArrayListSelected = getAllPendingFeeStudentPojoArrayList;
        for (int i = 0; i < getAllPendingFeeStudentPojoArrayListSelected.size(); i++) {
            if (getAllPendingFeeStudentPojoArrayListSelected.get(i).getIsPendingFeeItemIsCheckOrNot()) {
                multiplePendingFeePayListItemIds = multiplePendingFeePayListItemIds + "," + getAllPendingFeeStudentPojoArrayListSelected.get(i).getFeeExId() + "";
            }
        }
        multiplePendingFeePayListItemIds = multiplePendingFeePayListItemIds.replaceFirst(",", "");
        if (CommonUtil.checkIsEmptyOrNullCommon(multiplePendingFeePayListItemIds)) {
            isMultiplePendingFeePaySelected = false;
        } else {
            isMultiplePendingFeePaySelected = true;
        }

    }
}