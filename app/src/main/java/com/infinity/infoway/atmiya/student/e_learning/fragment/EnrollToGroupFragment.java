package com.infinity.infoway.atmiya.student.e_learning.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.custom_class.Animations;
import com.infinity.infoway.atmiya.custom_class.SpinnerSimpleAdapter;
import com.infinity.infoway.atmiya.custom_class.TextViewMediumFont;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.student.e_learning.activity.ELearningActivity;
import com.infinity.infoway.atmiya.student.e_learning.pojo.CheckIsELearningManagementGroupIsCompulsoryOrNot;
import com.infinity.infoway.atmiya.student.e_learning.pojo.ELearningYearListPojo;
import com.infinity.infoway.atmiya.student.e_learning.pojo.GroupWiseSubjectlistPojo;
import com.infinity.infoway.atmiya.student.e_learning.pojo.LearningManagementGroupDetailsPojo;
import com.infinity.infoway.atmiya.student.e_learning.pojo.StudentWiseLearningGroupPojo;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.DialogUtil;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class EnrollToGroupFragment extends Fragment implements View.OnClickListener {

    private static final String SELECT_YEAR = "Select Year";
    private static final String SELECT_GROUP = "Select Group";
    private static EnrollToGroupFragment enrollToGroupFragment = null;

    ELearningActivity eLearningActivity;
    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    Spinner spYear, spGroupName, spSubjectName;
    SpinnerSimpleAdapter spinnerAdapterYear, spinnerAdapterGroupName, spinnerAdapterSubject;
    LinearLayout llExpandedHeaderEnrollToGroup;
    AppCompatImageView ivViewMoreEnrollToGroup;
    RecyclerView rvEnrollToGroupList;
    TextViewRegularFont tvClassName, tvClassDate;
    ArrayList<String> yearArrayList;
    HashMap<String, String> yearNameAndIdHashMap;
    HashMap<String, String> yearNameAndYearStatusHashMap;
    ArrayList<String> eLearningGroupList;
    HashMap<String, String> groupNameAndIdHashMap;
    LinearLayout llEnrollToGroup, llEnrollToGroupProgressbar, llNoDataFoundEnrollToGroup;
    Calendar myCalendarFromDate = Calendar.getInstance();
    Calendar myCalendarToDate = Calendar.getInstance();
    AlertDialog applyFilterDialog;
    ExtendedFloatingActionButton efabApplyFilterEnrollToGroup, efabClearFilterEnrollToGroup;
    FrameLayout flStudentExaminationSchedule;
    LinearLayout llSubjectName;
    ArrayList<String> subjectNameArrayList;
    HashMap<String, String> subjectNameAndIdHashMap;
    boolean isEnrollToGroupIsExpanded = true;

    public EnrollToGroupFragment() {
        // Required empty public constructor
    }

    public static EnrollToGroupFragment newInstance() {
        if (enrollToGroupFragment == null) {
            enrollToGroupFragment = new EnrollToGroupFragment();
        }
        return enrollToGroupFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        eLearningActivity = (ELearningActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enroll_to_group, container, false);
        initView(view);
        getYearListApiCall();
        return view;
    }

    private void initView(View view) {
        mySharedPreferences = new MySharedPreferences(eLearningActivity);
        connectionDetector = new ConnectionDetector(eLearningActivity);
        spYear = view.findViewById(R.id.spYear);
        spGroupName = view.findViewById(R.id.spGroupName);
        spSubjectName = view.findViewById(R.id.spSubjectName);
        llExpandedHeaderEnrollToGroup = view.findViewById(R.id.llExpandedHeaderEnrollToGroup);
        llExpandedHeaderEnrollToGroup.setOnClickListener(this);
        tvClassName = view.findViewById(R.id.tvClassName);
        tvClassDate = view.findViewById(R.id.tvClassDate);
        ivViewMoreEnrollToGroup = view.findViewById(R.id.ivViewMoreEnrollToGroup);
        rvEnrollToGroupList = view.findViewById(R.id.rvEnrollToGroupList);
        llEnrollToGroup = view.findViewById(R.id.llEnrollToGroup);
        llEnrollToGroupProgressbar = view.findViewById(R.id.llEnrollToGroupProgressbar);
        llNoDataFoundEnrollToGroup = view.findViewById(R.id.llNoDataFoundEnrollToGroup);
        efabApplyFilterEnrollToGroup = view.findViewById(R.id.efabApplyFilterEnrollToGroup);
        efabApplyFilterEnrollToGroup.setOnClickListener(this);
        efabClearFilterEnrollToGroup = view.findViewById(R.id.efabClearFilterEnrollToGroup);
        efabClearFilterEnrollToGroup.setOnClickListener(this);
        flStudentExaminationSchedule = view.findViewById(R.id.flStudentExaminationSchedule);
        llSubjectName = view.findViewById(R.id.llSubjectName);

        spYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    if (spGroupName.getSelectedItemPosition() > 0) {
                        //call API

                        String selectedYearId = yearNameAndIdHashMap.get(yearArrayList.get(spYear.getSelectedItemPosition())) + "";
                        String selectedGroupId = groupNameAndIdHashMap.get(eLearningGroupList.get(spGroupName.getSelectedItemPosition())) + "";

                    } else {
                        hideGroup();
                    }
                } else {
                    hideGroup();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spGroupName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    if (spYear.getSelectedItemPosition() > 0) {
                        String selectedYearId = yearNameAndIdHashMap.get(yearArrayList.get(spYear.getSelectedItemPosition())) + "";
                        String selectedGroupId = groupNameAndIdHashMap.get(eLearningGroupList.get(spGroupName.getSelectedItemPosition())) + "";
                        checkIsELearningGrpIsCompulsoryOrNot(selectedGroupId);
                    }
                } else {
                    hideGroup();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void hideGroup() {
        efabApplyFilterEnrollToGroup.setVisibility(View.VISIBLE);
        efabClearFilterEnrollToGroup.setVisibility(View.GONE);
        flStudentExaminationSchedule.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.llExpandedHeaderEnrollToGroup) {
            isEnrollToGroupIsExpanded = toggleLayout(!isEnrollToGroupIsExpanded, ivViewMoreEnrollToGroup, rvEnrollToGroupList);
        } else if (view.getId() == R.id.efabApplyFilterEnrollToGroup) {
            showApplyFilterDialog();
        } else if (view.getId() == R.id.efabClearFilterEnrollToGroup) {
            efabClearFilterEnrollToGroup.setVisibility(View.GONE);
            efabApplyFilterEnrollToGroup.setVisibility(View.VISIBLE);
        }
    }

    private void getYearListApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llEnrollToGroup.setVisibility(View.GONE);
            llEnrollToGroupProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundEnrollToGroup.setVisibility(View.GONE);
            ApiImplementer.getELearningYearListApiImplementer(mySharedPreferences.getInstituteId(), new Callback<ELearningYearListPojo>() {
                @Override
                public void onResponse(Call<ELearningYearListPojo> call, Response<ELearningYearListPojo> response) {
                    try {
                        if (response.isSuccessful() && response.body() != null &&
                                response.body().getTable().size() > 0) {
                            ArrayList<ELearningYearListPojo.Table> tableArrayList = (ArrayList<ELearningYearListPojo.Table>) response.body().getTable();
                            yearArrayList = new ArrayList<>();
                            yearNameAndIdHashMap = new HashMap<>();
                            yearNameAndYearStatusHashMap = new HashMap<>();
                            yearArrayList.add(SELECT_YEAR);

                            for (int i = 0; i < tableArrayList.size(); i++) {
                                if (!CommonUtil.checkIsEmptyOrNullCommon(tableArrayList.get(i).getYearName())) {
                                    String yearName = tableArrayList.get(i).getYearName();
                                    yearArrayList.add(yearName);
                                    yearNameAndIdHashMap.put(yearName, tableArrayList.get(i).getYearId() + "");
                                    yearNameAndYearStatusHashMap.put(yearName, tableArrayList.get(i).getYearIsCurrent() + "");
                                }
                            }
                            spinnerAdapterYear = new SpinnerSimpleAdapter(eLearningActivity, yearArrayList);
                            spYear.setAdapter(spinnerAdapterYear);
                            getStudentWiseLearningManagementGroupApiCall();
                        } else {
                            llEnrollToGroup.setVisibility(View.GONE);
                            llEnrollToGroupProgressbar.setVisibility(View.GONE);
                            llNoDataFoundEnrollToGroup.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception ex) {
                        llEnrollToGroup.setVisibility(View.GONE);
                        llEnrollToGroupProgressbar.setVisibility(View.GONE);
                        llNoDataFoundEnrollToGroup.setVisibility(View.VISIBLE);
                        Toast.makeText(eLearningActivity, "Exception :- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ELearningYearListPojo> call, Throwable t) {
                    llEnrollToGroup.setVisibility(View.GONE);
                    llEnrollToGroupProgressbar.setVisibility(View.GONE);
                    llNoDataFoundEnrollToGroup.setVisibility(View.VISIBLE);
                    Toast.makeText(eLearningActivity, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(eLearningActivity, "No internet connection please try again later.", Toast.LENGTH_SHORT).show();
            eLearningActivity.finish();
        }
    }

    private void getStudentWiseLearningManagementGroupApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            ApiImplementer.getStudentWiseLearningGroupApiImplementer(mySharedPreferences.getStudentId(),
                    new Callback<StudentWiseLearningGroupPojo>() {
                        @Override
                        public void onResponse(Call<StudentWiseLearningGroupPojo> call, Response<StudentWiseLearningGroupPojo> response) {
                            try {
                                llEnrollToGroupProgressbar.setVisibility(View.GONE);
                                if (response.isSuccessful() && response.body() != null &&
                                        response.body().getTable().size() > 0) {
                                    ArrayList<StudentWiseLearningGroupPojo.Table> tableArrayList = (ArrayList<StudentWiseLearningGroupPojo.Table>) response.body().getTable();
                                    eLearningGroupList = new ArrayList<>();
                                    groupNameAndIdHashMap = new HashMap<>();
                                    eLearningGroupList.add(SELECT_GROUP);
                                    for (int i = 0; i < tableArrayList.size(); i++) {
                                        if (!CommonUtil.checkIsEmptyOrNullCommon(tableArrayList.get(i).getGrpName())) {
                                            String grpName = tableArrayList.get(i).getGrpName();
                                            eLearningGroupList.add(grpName);
                                            groupNameAndIdHashMap.put(grpName, tableArrayList.get(i).getGrpId() + "");
                                        }
                                    }
                                    spinnerAdapterGroupName = new SpinnerSimpleAdapter(eLearningActivity, eLearningGroupList);
                                    spGroupName.setAdapter(spinnerAdapterGroupName);
                                    llEnrollToGroup.setVisibility(View.VISIBLE);
                                } else {
                                    llEnrollToGroup.setVisibility(View.GONE);
                                    llNoDataFoundEnrollToGroup.setVisibility(View.VISIBLE);
                                }
                            } catch (Exception ex) {
                                llEnrollToGroup.setVisibility(View.GONE);
                                llEnrollToGroupProgressbar.setVisibility(View.GONE);
                                llNoDataFoundEnrollToGroup.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onFailure(Call<StudentWiseLearningGroupPojo> call, Throwable t) {
                            llEnrollToGroup.setVisibility(View.GONE);
                            llEnrollToGroupProgressbar.setVisibility(View.GONE);
                            llNoDataFoundEnrollToGroup.setVisibility(View.VISIBLE);
                            Toast.makeText(eLearningActivity, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(eLearningActivity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkIsELearningGrpIsCompulsoryOrNot(String grpId) {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(eLearningActivity, "");
            ApiImplementer.checkIsELearningGroupIsCompulsoryOrNotApiImplementer(grpId, new Callback<ArrayList<CheckIsELearningManagementGroupIsCompulsoryOrNot>>() {
                @Override
                public void onResponse(Call<ArrayList<CheckIsELearningManagementGroupIsCompulsoryOrNot>> call, Response<ArrayList<CheckIsELearningManagementGroupIsCompulsoryOrNot>> response) {
                    try {
//                        DialogUtil.hideProgressDialog();
                        if (response.isSuccessful() && response.body() != null &&
                                response.body().size() > 0) {
                            flStudentExaminationSchedule.setVisibility(View.VISIBLE);
                            if (response.body().get(0).getGrpType() == 1) {
                                llSubjectName.setVisibility(View.GONE);
                            } else if (response.body().get(0).getGrpType() == 2) {
                                getGroupWiseSubjectListApiCall(false, true, grpId);
                            } else {
                                llSubjectName.setVisibility(View.GONE);
                            }
                        } else {
                            DialogUtil.hideProgressDialog();
                            Toast.makeText(eLearningActivity, "Something went wrong ,Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception ex) {
                        DialogUtil.hideProgressDialog();
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<CheckIsELearningManagementGroupIsCompulsoryOrNot>> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(eLearningActivity, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(eLearningActivity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
            eLearningActivity.finish();
        }
    }

    private void getGroupWiseSubjectListApiCall(boolean isPdShow, boolean isPdHide, String grp_id) {
        if (connectionDetector.isConnectingToInternet()) {
            if (isPdShow) {
                DialogUtil.showProgressDialogNotCancelable(eLearningActivity, "");
            }
            ApiImplementer.groupWiseLearningManagementSubjectListApiImplementer(mySharedPreferences.getStudentId(), mySharedPreferences.getSmId(),
                    mySharedPreferences.getSwdYearId(), grp_id, new Callback<ArrayList<GroupWiseSubjectlistPojo>>() {
                        @Override
                        public void onResponse(Call<ArrayList<GroupWiseSubjectlistPojo>> call, Response<ArrayList<GroupWiseSubjectlistPojo>> response) {
                            if (isPdHide) {
                                DialogUtil.hideProgressDialog();
                            }
                            if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                                subjectNameArrayList = new ArrayList<>();
                                subjectNameAndIdHashMap = new HashMap<>();

                                for (int i = 0; i < response.body().size(); i++) {
                                    if (!CommonUtil.checkIsEmptyOrNullCommon(response.body().get(i).getSubName())) {
                                        subjectNameArrayList.add(response.body().get(i).getSubName() + "");
                                    }
                                }
                                spinnerAdapterSubject = new SpinnerSimpleAdapter(eLearningActivity, subjectNameArrayList);
                                spSubjectName.setAdapter(spinnerAdapterSubject);

                                llSubjectName.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(eLearningActivity, "Something went wrong ,Please try again later.", Toast.LENGTH_SHORT).show();
                                llSubjectName.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<GroupWiseSubjectlistPojo>> call, Throwable t) {
                            DialogUtil.hideProgressDialog();
                            llSubjectName.setVisibility(View.GONE);
                            Toast.makeText(eLearningActivity, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(eLearningActivity, "No internet connection,please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getEnrollToGroupExpandedListApiCall(String grp_id, String from_date, String to_date, String sub_id) {
        if (connectionDetector.isConnectingToInternet()) {
            ApiImplementer.groupWiseLearningManagementSubjectListApiImplementer(grp_id, mySharedPreferences.getStudentId(),
                    mySharedPreferences.getSmId(), mySharedPreferences.getSwdYearId(), from_date, to_date, sub_id, new Callback<ArrayList<LearningManagementGroupDetailsPojo>>() {
                        @Override
                        public void onResponse(Call<ArrayList<LearningManagementGroupDetailsPojo>> call, Response<ArrayList<LearningManagementGroupDetailsPojo>> response) {
                            if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {

                            } else {
                                Toast.makeText(eLearningActivity, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<LearningManagementGroupDetailsPojo>> call, Throwable t) {
                            Toast.makeText(eLearningActivity, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(eLearningActivity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void showApplyFilterDialog() {
        TextInputEditText tilFromDate, tilToDate;
//        Spinner spSubjectFilter;
        TextViewRegularFont btnCancel;
        AppCompatButton btnApplyFilter;
        LinearLayout llSpSubjectList;

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(eLearningActivity);
        dialogBuilder.setCancelable(false);

        LayoutInflater layoutInflater = LayoutInflater.from(eLearningActivity);
        View dialogView = layoutInflater.inflate(R.layout.layout_for_enroll_togroup_filter_opetion, null);

        tilFromDate = dialogView.findViewById(R.id.tilFromDate);
        tilToDate = dialogView.findViewById(R.id.tilToDate);
//        spSubjectFilter = dialogView.findViewById(R.id.spSubjectFilter);
        btnCancel = dialogView.findViewById(R.id.btnCancel);
        btnApplyFilter = dialogView.findViewById(R.id.btnApplyFilter);
        llSpSubjectList = dialogView.findViewById(R.id.llSpSubjectList);

        tilFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFromDateDialog(tilFromDate);
            }
        });

        tilToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToDateDialog(tilToDate);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyFilterDialog.dismiss();
            }
        });



        btnApplyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidFilterDialog(tilFromDate, tilToDate)) {
                    if (!CommonUtil.checkIsEmptyOrNullCommon(tilFromDate.getText().toString())) {
                        efabApplyFilterEnrollToGroup.setVisibility(View.GONE);
                        efabClearFilterEnrollToGroup.setVisibility(View.VISIBLE);
                    }
                    applyFilterDialog.dismiss();
                }
            }
        });

        dialogBuilder.setView(dialogView);
        applyFilterDialog = dialogBuilder.create();
        if (!applyFilterDialog.isShowing()) {
            applyFilterDialog.show();
        }
    }

    private boolean isValidFilterDialog(TextInputEditText edtFromDate,
                                        TextInputEditText edtToDate) {
        boolean isValid = true;
        boolean isDateEntered = !CommonUtil.checkIsEmptyOrNullCommon(edtFromDate.getText().toString()) ||
                !CommonUtil.checkIsEmptyOrNullCommon(edtToDate.getText().toString());

        if (isDateEntered && CommonUtil.checkIsEmptyOrNullCommon(edtFromDate.getText().toString())) {
            Toast.makeText(eLearningActivity, "Please select from date.", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (isDateEntered && CommonUtil.checkIsEmptyOrNullCommon(edtToDate.getText().toString())) {
            Toast.makeText(eLearningActivity, "Please select to date.", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (isDateEntered && !CommonUtil.checkIsFromDateGraterThanToDate(edtFromDate.getText().toString(),
                edtToDate.getText().toString(), "dd/MM/yyyy")) {
            Toast.makeText(eLearningActivity, "From date can not be greater than to date.", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else {
            isValid = true;
        }

        return isValid;
    }

    private void showFromDateDialog(TextInputEditText tilFromDate) {
        DatePickerDialog datePickerDialogFromDate = new DatePickerDialog(eLearningActivity,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        myCalendarFromDate.set(Calendar.YEAR, year);
                        myCalendarFromDate.set(Calendar.MONTH, monthOfYear);
                        myCalendarFromDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String myFormat = "dd/MM/yyyy";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.US);
                        Date fromDate = null;
                        try {
                            fromDate = simpleDateFormat.parse(simpleDateFormat.format(myCalendarFromDate.getTime()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        tilFromDate.setText(simpleDateFormat.format(fromDate));
                    }
                }, myCalendarFromDate.get(Calendar.YEAR), myCalendarFromDate.get(Calendar.MONTH), myCalendarFromDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialogFromDate.show();
    }

    private void showToDateDialog(TextInputEditText tilToDate) {
        DatePickerDialog datePickerDialogToDate = new DatePickerDialog(eLearningActivity,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        myCalendarToDate.set(Calendar.YEAR, year);
                        myCalendarToDate.set(Calendar.MONTH, monthOfYear);
                        myCalendarToDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String myFormat = "dd/MM/yyyy";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.US);
                        Date toDate = null;
                        try {
                            toDate = simpleDateFormat.parse(simpleDateFormat.format(myCalendarToDate.getTime()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        tilToDate.setText(simpleDateFormat.format(toDate));
                    }
                }, myCalendarToDate.get(Calendar.YEAR), myCalendarToDate.get(Calendar.MONTH), myCalendarToDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialogToDate.show();
    }

    private boolean toggleLayout(boolean isExpanded, View v, RecyclerView rvEnrollToGroupList) {
        Animations.toggleArrow(v, isExpanded);
        if (isExpanded) {
            Animations.expand(rvEnrollToGroupList);
        } else {
            Animations.collapse(rvEnrollToGroupList);
        }
        return isExpanded;

    }

}