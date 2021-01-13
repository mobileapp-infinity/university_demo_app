package com.infinity.infoway.atmiya.faculty.faculty_fill_attendance.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.custom_class.SpinnerSimpleAdapter;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.faculty.faculty_fill_attendance.adapter.SelectTeachingMethodGridViewAdapter;
import com.infinity.infoway.atmiya.faculty.faculty_fill_attendance.pojo.FacultyPendingAttendanceUnitPojo;
import com.infinity.infoway.atmiya.faculty.faculty_fill_attendance.pojo.GetTeachingAidDetailsPojo;
import com.infinity.infoway.atmiya.faculty.faculty_fill_attendance.pojo.GetTeachingMethodPojo;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.DialogUtil;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyFillAttendanceActivity extends AppCompatActivity implements View.OnClickListener, SelectTeachingMethodGridViewAdapter.ITeachingMethod {

    private static final String SELECT_NO_OF_POST_ON = "Select No Of Post";
    private static final String SELECT_TEACHING_AID = "Select Teaching Aid";
    private static final String SELECT_TEACHING_UNIT = "Select Unit";


    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseFacultyFillAttendance;
    SwitchButton sBtnAttendanceForByRollNo;
    TextViewRegularFont tvAttendanceForByRollNumber;

    LinearLayout llSelectTeachingMethod;
    GridView gvTeachingMethodList;
    String selectedMethodNo = "";
    private boolean isMethodSelected = false;
    ArrayList<GetTeachingMethodPojo.Table> getTeachingMethodArrayListNew;

    SpinnerSimpleAdapter spinnerAdapterYearSelectNoOfPost;
    Spinner spSelectNoOfPostOn;
    ArrayList<String> noOfPostArrayList = new ArrayList<>();

    LinearLayout llFaciltyAidDetails;
    SpinnerSimpleAdapter spinnerAdapterFacultyAidDetails;
    Spinner spSelectTeachingAid;
    ArrayList<String> teachingAidArrayList;
    HashMap<String, String> teachingAidNameAndIdHashMap;


    LinearLayout llFacultyPendingAttendaUnit;
    SpinnerSimpleAdapter spinnerAdapterFacultyUnit;
    Spinner spSelectUnit;
    ArrayList<String> teachingUnitArrayList;
    HashMap<String, String> teachingUnitNameAndIdHashMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_fill_attendance);
        initView();
        getFacultyPendingAttendanceTeachingMethodApiCall(true, false);
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(FacultyFillAttendanceActivity.this);
        connectionDetector = new ConnectionDetector(FacultyFillAttendanceActivity.this);
        ivCloseFacultyFillAttendance = findViewById(R.id.ivCloseFacultyFillAttendance);
        ivCloseFacultyFillAttendance.setOnClickListener(this);
        sBtnAttendanceForByRollNo = findViewById(R.id.sBtnAttendanceForByRollNo);
        tvAttendanceForByRollNumber = findViewById(R.id.tvAttendanceForByRollNumber);

        llSelectTeachingMethod = findViewById(R.id.llSelectTeachingMethod);
        gvTeachingMethodList = findViewById(R.id.gvTeachingMethodList);

        noOfPostArrayList.add(SELECT_NO_OF_POST_ON);

        for (int i = 0; i <= 10; i++) {
            noOfPostArrayList.add(String.valueOf(i));
        }

        spinnerAdapterYearSelectNoOfPost = new SpinnerSimpleAdapter(FacultyFillAttendanceActivity.this, noOfPostArrayList);
        spSelectNoOfPostOn.setAdapter(spinnerAdapterYearSelectNoOfPost);

        spSelectNoOfPostOn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {

                } else {

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        llFaciltyAidDetails = findViewById(R.id.llFaciltyAidDetails);
        spSelectTeachingAid = findViewById(R.id.spSelectTeachingAid);
        spSelectTeachingAid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        llFacultyPendingAttendaUnit = findViewById(R.id.llFacultyPendingAttendaUnit);
        spSelectUnit = findViewById(R.id.spSelectUnit);

        spSelectUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sBtnAttendanceForByRollNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvAttendanceForByRollNumber.setText("Attendance For Present");
                } else {
                    tvAttendanceForByRollNumber.setText("Attendance For Absent");
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultyFillAttendance) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void getFacultyPendingAttendanceTeachingMethodApiCall(boolean isPdShow, boolean isPdHide) {
        if (connectionDetector.isConnectingToInternet()) {
            llSelectTeachingMethod.setVisibility(View.GONE);
            if (isPdShow) {
                DialogUtil.showProgressDialogNotCancelable(FacultyFillAttendanceActivity.this, "");
                ApiImplementer.getTeachingMethodApiImplementer(mySharedPreferences.getEmpInstituteId(), new Callback<GetTeachingMethodPojo>() {
                    @Override
                    public void onResponse(Call<GetTeachingMethodPojo> call, Response<GetTeachingMethodPojo> response) {
                        if (isPdHide) {
                            DialogUtil.hideProgressDialog();
                        }
                        if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                            ArrayList<GetTeachingMethodPojo.Table> getTeachingMethodArrayList = (ArrayList<GetTeachingMethodPojo.Table>) response.body().getTable();
                            llSelectTeachingMethod.setVisibility(View.VISIBLE);
                            SelectTeachingMethodGridViewAdapter selectTeachingMethodGridViewAdapter = new SelectTeachingMethodGridViewAdapter(FacultyFillAttendanceActivity.this,
                                    getTeachingMethodArrayList);
                            gvTeachingMethodList.setAdapter(selectTeachingMethodGridViewAdapter);
                            isMethodSelected = true;
                            getFacultyAidDetailsApiCall(false, true);
                        } else {
                            isMethodSelected = false;
                            DialogUtil.hideProgressDialog();
                            llSelectTeachingMethod.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetTeachingMethodPojo> call, Throwable t) {
                        DialogUtil.hideProgressDialog();
                        isMethodSelected = false;
                        llSelectTeachingMethod.setVisibility(View.GONE);
                        Toast.makeText(FacultyFillAttendanceActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getFacultyAidDetailsApiCall(boolean isPdShow, boolean isPdHide) {
        if (connectionDetector.isConnectingToInternet()) {
            if (isPdShow) {
                DialogUtil.showProgressDialogNotCancelable(FacultyFillAttendanceActivity.this, "");
            }
            llFaciltyAidDetails.setVisibility(View.GONE);
            ApiImplementer.getTeachingAidDetailsApiImplementer(mySharedPreferences.getEmpInstituteId(), new Callback<GetTeachingAidDetailsPojo>() {
                @Override
                public void onResponse(Call<GetTeachingAidDetailsPojo> call, Response<GetTeachingAidDetailsPojo> response) {
                    if (isPdHide) {
                        DialogUtil.hideProgressDialog();
                    }
                    if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                        teachingAidArrayList = new ArrayList<>();
                        teachingAidArrayList.add(SELECT_TEACHING_AID);
                        teachingAidNameAndIdHashMap = new HashMap<>();
                        llFaciltyAidDetails.setVisibility(View.VISIBLE);

                        ArrayList<GetTeachingAidDetailsPojo.Table> tableArrayList = (ArrayList<GetTeachingAidDetailsPojo.Table>) response.body().getTable();

                        for (int i = 0; i < tableArrayList.size(); i++) {
                            GetTeachingAidDetailsPojo.Table table = tableArrayList.get(i);

                            if (!CommonUtil.checkIsEmptyOrNullCommon(table.getTaId()) &&
                                    !CommonUtil.checkIsEmptyOrNullCommon(table.getTaName())) {
                                teachingAidArrayList.add(table.getTaName().trim());
                                teachingAidNameAndIdHashMap.put(table.getTaName().trim(), table.getTaId() + "");
                            }
                        }
                        spinnerAdapterFacultyAidDetails = new SpinnerSimpleAdapter(FacultyFillAttendanceActivity.this, teachingAidArrayList);
                        spSelectTeachingAid.setAdapter(spinnerAdapterFacultyAidDetails);

                    } else {
                        DialogUtil.hideProgressDialog();
                        llFaciltyAidDetails.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<GetTeachingAidDetailsPojo> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    llFaciltyAidDetails.setVisibility(View.GONE);
                    Toast.makeText(FacultyFillAttendanceActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getFacultyPendingAttendanceUnitApiCall(boolean isPdShow, boolean isPdHide, String divId, String subId) {
        if (connectionDetector.isConnectingToInternet()) {
            if (isPdShow) {
                DialogUtil.showProgressDialogNotCancelable(FacultyFillAttendanceActivity.this, "");
            }
            llFacultyPendingAttendaUnit.setVisibility(View.GONE);
            ApiImplementer.getFacultyPendingAttendanceUnitApiImplementer(mySharedPreferences.getEmpId(), divId, subId, mySharedPreferences.getEmpYearId(), new Callback<FacultyPendingAttendanceUnitPojo>() {
                @Override
                public void onResponse(Call<FacultyPendingAttendanceUnitPojo> call, Response<FacultyPendingAttendanceUnitPojo> response) {
                    if (isPdHide) {
                        DialogUtil.hideProgressDialog();
                    }
                    if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                        teachingUnitArrayList = new ArrayList<>();
                        teachingUnitArrayList.add(SELECT_TEACHING_UNIT);
                        teachingUnitNameAndIdHashMap = new HashMap<>();
                        ArrayList<FacultyPendingAttendanceUnitPojo.Table> tableArrayList = (ArrayList<FacultyPendingAttendanceUnitPojo.Table>) response.body().getTable();

                        for (int i = 0; i < tableArrayList.size(); i++) {
                            FacultyPendingAttendanceUnitPojo.Table table = tableArrayList.get(i);
                            if (!CommonUtil.checkIsEmptyOrNullCommon(table.getUnitId()) &&
                                    !CommonUtil.checkIsEmptyOrNullCommon(table.getUnitName())) {
                                teachingUnitArrayList.add(table.getUnitName().trim());
                                teachingUnitNameAndIdHashMap.put(table.getUnitName().trim(),
                                        table.getUnitId() + "");
                            }
                        }


                        spinnerAdapterFacultyUnit = new SpinnerSimpleAdapter(FacultyFillAttendanceActivity.this, teachingUnitArrayList);
                        spSelectUnit.setAdapter(spinnerAdapterFacultyUnit);
                        llFacultyPendingAttendaUnit.setVisibility(View.VISIBLE);
                    } else {
                        llFacultyPendingAttendaUnit.setVisibility(View.GONE);
                        DialogUtil.hideProgressDialog();
                    }
                }

                @Override
                public void onFailure(Call<FacultyPendingAttendanceUnitPojo> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    llFacultyPendingAttendaUnit.setVisibility(View.GONE);
                    Toast.makeText(FacultyFillAttendanceActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClickTeachingMethod(ArrayList<GetTeachingMethodPojo.Table> getTeachingMethodArrayListNew) {
        selectedMethodNo = "";
        this.getTeachingMethodArrayListNew = getTeachingMethodArrayListNew;
        for (int i = 0; i < getTeachingMethodArrayListNew.size(); i++) {
            if (getTeachingMethodArrayListNew.get(i).isSelected()) {
                selectedMethodNo = selectedMethodNo + "," + getTeachingMethodArrayListNew.get(i).getTmId() + "";
            }
        }
        selectedMethodNo = selectedMethodNo.replaceFirst(",", "");
        if (CommonUtil.checkIsEmptyOrNullCommon(selectedMethodNo)) {
            isMethodSelected = false;
        } else {
            isMethodSelected = true;
        }
    }
}