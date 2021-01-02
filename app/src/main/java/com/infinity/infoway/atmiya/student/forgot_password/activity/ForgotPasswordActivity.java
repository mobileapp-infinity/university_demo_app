package com.infinity.infoway.atmiya.student.forgot_password.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.api.Urls;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.student.forgot_password.adapter.RegisterUserListAdapter;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.GetForgetPasswordDetailsByStudentEmployeeIdPojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.GetInstituteFromDomainPojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.GetSMSApiForApplicationPojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.GetStudentForgotPasswordDetailsPojo;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.DialogUtil;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener, RegisterUserListAdapter.IUserListDialog {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseForgotPassword;
    AppCompatEditText edtEnterRegisterMobileNo;
    TextViewRegularFont btnSendMsg;
    WebView webview;
    private String SMS_URL = "";
    ArrayList<GetForgetPasswordDetailsByStudentEmployeeIdPojo> getForgetPasswordDetailsByStudentEmployeeIdPojoArrayList;
    RequestQueue queue;
    private RegisterStudentListBottomSheetDialog registerStudentListBottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_forgot_password);
        initView();
    }

    private void initView() {
        queue = Volley.newRequestQueue(this);
        mySharedPreferences = new MySharedPreferences(ForgotPasswordActivity.this);
        connectionDetector = new ConnectionDetector(ForgotPasswordActivity.this);
        ivCloseForgotPassword = findViewById(R.id.ivCloseForgotPassword);
        ivCloseForgotPassword.setOnClickListener(this);
        edtEnterRegisterMobileNo = findViewById(R.id.edtEnterRegisterMobileNo);
        btnSendMsg = findViewById(R.id.btnSendMsg);
        btnSendMsg.setOnClickListener(this);

        webview = new WebView(ForgotPasswordActivity.this);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebChromeClient(new WebChromeClient());
        if (Build.VERSION.SDK_INT >= 21) {
            webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//            webview.getSettings().setDefaultTextEncodingName("utf-8");
        }

    }


    private boolean isValid() {
        boolean isValid = true;

        if (CommonUtil.checkIsEmptyOrNullCommon(edtEnterRegisterMobileNo.getText().toString().trim())) {
            isValid = false;
            Toast.makeText(this, "Please enter mobile no.", Toast.LENGTH_SHORT).show();
        } else if (edtEnterRegisterMobileNo.getText().toString().trim().length() != 10) {
            isValid = false;
            Toast.makeText(this, "Please enter valid mobile no.", Toast.LENGTH_SHORT).show();
        }
        return isValid;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseForgotPassword) {
            onBackPressed();
        } else if (v.getId() == R.id.btnSendMsg) {
            if (isValid()) {
                CommonUtil.hideKeyboardCommon(ForgotPasswordActivity.this);
                Intent intent = new Intent(ForgotPasswordActivity.this,VerifyOTPActivity.class);
                startActivity(intent);
//                getInstituteFromDomainUrlApiCall();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getInstituteFromDomainUrlApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(ForgotPasswordActivity.this, "");
            ApiImplementer.getInstituteFromDomainApiImplementer(Urls.DOMAIN_NAME_URL_FOR_FORGOT_PASSWORD, new Callback<GetInstituteFromDomainPojo>() {
                @Override
                public void onResponse(Call<GetInstituteFromDomainPojo> call, Response<GetInstituteFromDomainPojo> response) {
//                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                        String imOtpBasedForgotPassword = response.body().getTable().get(0).getImOtpBaseForgetPwd() + "";
                        String instituteId = response.body().getTable().get(0).getImId() + "";
                        if (imOtpBasedForgotPassword.contentEquals("1")) {
//                            Get_OTP_Base_Login_Details_For_Emp_API(etmobileno.getText().toString().trim(), instituteId);
                        } else {
                            getStudentForgetPasswordDetailsApiCall(false, true, edtEnterRegisterMobileNo.getText().toString().trim());
                        }
                    } else {
                        DialogUtil.hideProgressDialog();
                        Toast.makeText(ForgotPasswordActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetInstituteFromDomainPojo> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(ForgotPasswordActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getStudentForgetPasswordDetailsApiCall(boolean isPdShow, boolean isPdHide, final String email) {

        if (isPdShow) {
            DialogUtil.showProgressDialog(ForgotPasswordActivity.this, "");
        }
        ApiImplementer.getStudentForgetPasswordDetailsApiImplementer(email, new Callback<ArrayList<GetStudentForgotPasswordDetailsPojo>>() {
            @Override
            public void onResponse(Call<ArrayList<GetStudentForgotPasswordDetailsPojo>> call, Response<ArrayList<GetStudentForgotPasswordDetailsPojo>> response) {
                if (isPdHide) {
                    DialogUtil.hideProgressDialog();
                }
                if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                    try {
                        ArrayList<GetStudentForgotPasswordDetailsPojo> getStudentForgotPasswordDetailsPojoArrayList = response.body();
                        if (!CommonUtil.checkIsEmptyOrNullCommon(getStudentForgotPasswordDetailsPojoArrayList.get(0).getUsername()) &&
                                !CommonUtil.checkIsEmptyOrNullCommon(getStudentForgotPasswordDetailsPojoArrayList.get(0).getName())) {
                            showRegisterUserListDialog(getStudentForgotPasswordDetailsPojoArrayList);
                        } else {
                            Toast.makeText(ForgotPasswordActivity.this, "No User Found!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception ex) {
                        Toast.makeText(ForgotPasswordActivity.this, "Exception:-" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Response Code:- " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetStudentForgotPasswordDetailsPojo>> call, Throwable t) {
                DialogUtil.hideProgressDialog();
                Toast.makeText(ForgotPasswordActivity.this, "Request Failed:-" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void closeUserListDialog(String emp_stud_id, String emp_stud_status) {
        if (registerStudentListBottomSheetDialog != null) {
            registerStudentListBottomSheetDialog.dismiss();
            getForgetPasswordDetailByStudentEmpIdApi(emp_stud_id, emp_stud_status, "0");
        }
    }

    private void getForgetPasswordDetailByStudentEmpIdApi(final String emp_stud_id, final String emp_stud_status, final String institute_id) {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(ForgotPasswordActivity.this, "");
            ApiImplementer.getStudentForgetPasswordDetailsApiImplementer(emp_stud_id, emp_stud_status, institute_id, new Callback<ArrayList<GetForgetPasswordDetailsByStudentEmployeeIdPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<GetForgetPasswordDetailsByStudentEmployeeIdPojo>> call, Response<ArrayList<GetForgetPasswordDetailsByStudentEmployeeIdPojo>> response) {
//                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                        getForgetPasswordDetailsByStudentEmployeeIdPojoArrayList = response.body();

                        String encrypted_pwd_config = getForgetPasswordDetailsByStudentEmployeeIdPojoArrayList.get(0).getEncryptedPwdConfig().toString().trim();

                        if (encrypted_pwd_config.equalsIgnoreCase("1")) {
                            if (!TextUtils.isEmpty(getForgetPasswordDetailsByStudentEmployeeIdPojoArrayList.get(0).getInstitute_id().toString())) {
                                getSMSForApplicationApiCall(false, true, getForgetPasswordDetailsByStudentEmployeeIdPojoArrayList.get(0).getInstitute_id().toString());
                            } else {
                                DialogUtil.hideProgressDialog();
                                Toast.makeText(ForgotPasswordActivity.this, "Institute_Id not found!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            DialogUtil.hideProgressDialog();
                            if (getForgetPasswordDetailsByStudentEmployeeIdPojoArrayList.get(0).getStatus().toString().trim().equalsIgnoreCase("1")) {
                                String username = getForgetPasswordDetailsByStudentEmployeeIdPojoArrayList.get(0).getUsername().toString().trim();
                                String password = getForgetPasswordDetailsByStudentEmployeeIdPojoArrayList.get(0).getPassword().toString().trim();

                                String webUrl = "http://message.smartwave.co.in/rest/services/sendSMS/sendGroupSms?AUTH_KEY=c564eac8f430fd3023e1c046e286885b&senderId=ATMIYA&routeId=1&smsContentType=english&message= Username :" + username + "  Password :" + password + "&mobileNos=" + edtEnterRegisterMobileNo.getText().toString().trim();
                                webview.loadUrl(webUrl, null);
                                Toast.makeText(ForgotPasswordActivity.this, "SMS sent", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(ForgotPasswordActivity.this, "Your mobile number is not registered", Toast.LENGTH_LONG).show();
                            }
                        }

                    } else {
                        DialogUtil.hideProgressDialog();
                        Toast.makeText(ForgotPasswordActivity.this, "Response Code:- " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<GetForgetPasswordDetailsByStudentEmployeeIdPojo>> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(ForgotPasswordActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getSMSForApplicationApiCall(boolean isPdShow, boolean isPdHide, String institute_id) {
        if (connectionDetector.isConnectingToInternet()) {
            if (isPdShow) {
                DialogUtil.showProgressDialogNotCancelable(ForgotPasswordActivity.this, "");
            }
            ApiImplementer.getSMSApiForApplicationApiImplementer(institute_id, new Callback<ArrayList<GetSMSApiForApplicationPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<GetSMSApiForApplicationPojo>> call, Response<ArrayList<GetSMSApiForApplicationPojo>> response) {
                    if (isPdHide) {
                        DialogUtil.hideProgressDialog();
                    }
                    try {
                        if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                            ArrayList<GetSMSApiForApplicationPojo> getSMSApiForApplicationPojoArrayList = response.body();
                            SMS_URL = getSMSApiForApplicationPojoArrayList.get(0).getSmsApi();

                            if (!TextUtils.isEmpty(SMS_URL)) {
                                String webUrl = SMS_URL.replace("addmessage", getForgetPasswordDetailsByStudentEmployeeIdPojoArrayList.get(0).getForgetPwdLink());

                                webUrl = webUrl.replace("addmobileno", edtEnterRegisterMobileNo.getText().toString().trim());

                                String urlStr = webUrl;
                                URL url = new URL(urlStr);
                                URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                                url = uri.toURL();

//                                String encodedQueryString = URLEncoder.encode(webUrl,"UTF-8");

                                StringRequest request = new StringRequest(Request.Method.GET, url.toString(), new com.android.volley.Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Toast.makeText(ForgotPasswordActivity.this, "SMS sent", Toast.LENGTH_LONG).show();
                                    }
                                }, new com.android.volley.Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(ForgotPasswordActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                queue.add(request);
                            } else {
                                Toast.makeText(ForgotPasswordActivity.this, "SMS Url Not found!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ForgotPasswordActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<GetSMSApiForApplicationPojo>> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(ForgotPasswordActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }

    }

    private void showRegisterUserListDialog(ArrayList<GetStudentForgotPasswordDetailsPojo> getStudentForgotPasswordDetailsPojoArrayList) {
        registerStudentListBottomSheetDialog = new RegisterStudentListBottomSheetDialog(ForgotPasswordActivity.this, getStudentForgotPasswordDetailsPojoArrayList);
        registerStudentListBottomSheetDialog.show(this.getSupportFragmentManager(), "Select Register Student");
    }

}