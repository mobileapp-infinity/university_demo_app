package com.infinity.infoway.atmiya.student.leave_application.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.custom_class.SpinnerSimpleAdapter;
import com.infinity.infoway.atmiya.custom_class.TextViewMediumFont;
import com.infinity.infoway.atmiya.student.leave_application.activity.LeaveApplicationActivity;
import com.infinity.infoway.atmiya.student.leave_application.adapter.PartialLeaveLectureListAdapter;
import com.infinity.infoway.atmiya.student.leave_application.pojo.KindOfLeaveListPojo;
import com.infinity.infoway.atmiya.student.leave_application.pojo.SelectLectureForPartialLeavePojo;
import com.infinity.infoway.atmiya.student.leave_application.pojo.TypeOfFileUploadPojo;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.DialogUtil;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApplyILeaveFragment extends Fragment implements View.OnClickListener,
        UploadFileBottomSheetDialog.CloseUploadFileDialog,
        LeaveApplicationActivity.ILeaveApplicationFileUpload, PartialLeaveLectureListAdapter.ISelectLecture {

    private static final String SELECT_TYPE_OF_LEAVE = "Select Type Of Leave";
    private static final String SELECT_KIND_OF_LEAVE = "Select Kind Of Leave";

    LeaveApplicationActivity leaveApplicationActivity;
    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;

    LinearLayout llApplyLeaveFragment;
    //    LinearLayout llApplyLeaveProgress;
//    LinearLayout llNoDataFoundApplyLeave;
    Spinner spKindOfLeave, spLeaveType;
    SpinnerSimpleAdapter spinnerAdapterKindOfLeave, spinnerAdapterTypeOfLeave;
    AppCompatEditText edtFromDate, edtToDate, edtUploadFile;
    TextInputEditText tilNote;
    TextViewMediumFont tvSaveLeave;

    ArrayList<String> typeOfLeaveArrayList;
    ArrayList<String> kindOfLeaveArrayList;
    HashMap<String, String> hashMapKindOfLeave;
    LinearLayout llUploadFile;
    Calendar myCalendarFromDate = Calendar.getInstance();
    Calendar myCalendarToDate = Calendar.getInstance();

    private boolean isFileUploadCompulsory = false;
    UploadFileBottomSheetDialog uploadFileBottomSheetDialog;
    RecyclerView rvLectureList;
    LinearLayout llSelectLecture;
    DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");


    final DatePickerDialog.OnDateSetListener fromDateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendarFromDate.set(Calendar.YEAR, year);
            myCalendarFromDate.set(Calendar.MONTH, monthOfYear);
            myCalendarFromDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String myFormat = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.US);
            Date fromDate = null;
            try {
                fromDate = simpleDateFormat.parse(simpleDateFormat.format(myCalendarFromDate.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            edtFromDate.setText(simpleDateFormat.format(fromDate));

            if (!CommonUtil.checkIsEmptyOrNullCommon(edtFromDate.getText().toString())) {
                String fromDateArray[] = edtFromDate.getText().toString().split("-");
                selectLectureListApiCall(fromDateArray[2] + "/" + fromDateArray[1] + "/" + fromDateArray[0]);
            }

        }
    };

    final DatePickerDialog.OnDateSetListener toDateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendarToDate.set(Calendar.YEAR, year);
            myCalendarToDate.set(Calendar.MONTH, monthOfYear);
            myCalendarToDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String myFormat = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.US);
            Date toDate = null;
            try {
                toDate = simpleDateFormat.parse(simpleDateFormat.format(myCalendarToDate.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            edtToDate.setText(simpleDateFormat.format(toDate));
        }
    };


    private static ApplyILeaveFragment fragment = null;

    public static ApplyILeaveFragment newInstance() {
        if (fragment == null) {
            fragment = new ApplyILeaveFragment();
        }
        return fragment;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        leaveApplicationActivity = (LeaveApplicationActivity) context;
    }

    public ApplyILeaveFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apply_leave, container, false);
        initView(view);
        getKindOfLeaveTypeApiCall();
        return view;
    }


    private boolean isValid() {
        boolean isValid = true;
        if (spKindOfLeave.getSelectedItemPosition() == 0) {
            Toast.makeText(leaveApplicationActivity, "Please Select Kind Of Leave.", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (spLeaveType.getSelectedItemPosition() == 0) {
            Toast.makeText(leaveApplicationActivity, "Please Select Leave Type Of Leave.", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (CommonUtil.checkIsEmptyOrNullCommon(edtFromDate.getText().toString())) {
            Toast.makeText(leaveApplicationActivity, "Please Select From-Date.", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (CommonUtil.checkIsEmptyOrNullCommon(edtToDate.getText().toString())) {
            Toast.makeText(leaveApplicationActivity, "Please Select To-Date.", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (isFileUploadCompulsory && CommonUtil.checkIsEmptyOrNullCommon(edtUploadFile.getText().toString())) {
            Toast.makeText(leaveApplicationActivity, "Please upload file.", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (CommonUtil.checkIsEmptyOrNullCommon(tilNote.getText().toString())) {
            Toast.makeText(leaveApplicationActivity, "Please enter remarks.", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        return isValid;
    }

    private void getKindOfLeaveTypeApiCall() {
        DialogUtil.showProgressDialogNotCancelable(leaveApplicationActivity, "");
        ApiImplementer.getKindOfLeaveListApiImplementer(mySharedPreferences.getInstituteId(), new Callback<KindOfLeaveListPojo>() {
            @Override
            public void onResponse(Call<KindOfLeaveListPojo> call, Response<KindOfLeaveListPojo> response) {
                DialogUtil.hideProgressDialog();
                if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                    kindOfLeaveArrayList = new ArrayList<>();
                    kindOfLeaveArrayList.add(SELECT_KIND_OF_LEAVE);
                    hashMapKindOfLeave = new HashMap<>();

                    ArrayList<KindOfLeaveListPojo.Table> tableArrayList = (ArrayList<KindOfLeaveListPojo.Table>) response.body().getTable();
                    for (int i = 0; i < tableArrayList.size(); i++) {
                        if (!CommonUtil.checkIsEmptyOrNullCommon(tableArrayList.get(i).getLtName())) {
                            kindOfLeaveArrayList.add(tableArrayList.get(i).getLtName() + "");
                            hashMapKindOfLeave.put(tableArrayList.get(i).getLtName(), tableArrayList.get(i).getLtId() + "");
                        }
                    }
                    spinnerAdapterKindOfLeave = new SpinnerSimpleAdapter(leaveApplicationActivity, kindOfLeaveArrayList);
                    spKindOfLeave.setAdapter(spinnerAdapterKindOfLeave);
                } else {

                }
            }

            @Override
            public void onFailure(Call<KindOfLeaveListPojo> call, Throwable t) {
                DialogUtil.hideProgressDialog();
                Toast.makeText(leaveApplicationActivity, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTypeOfFileUpload(String leave_type_id) {
        ApiImplementer.getTypeOfFileUploadApiImplementer(leave_type_id, new Callback<TypeOfFileUploadPojo>() {
            @Override
            public void onResponse(Call<TypeOfFileUploadPojo> call, Response<TypeOfFileUploadPojo> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                    ArrayList<TypeOfFileUploadPojo.Table> table = (ArrayList<TypeOfFileUploadPojo.Table>) response.body().getTable();
                    if (table.get(0).getLtIsDocAttachment() == 1 && table.get(0).getLtIsDocCompulsory() == 1) {
                        isFileUploadCompulsory = true;
                        llUploadFile.setVisibility(View.VISIBLE);
                    } else if (table.get(0).getLtIsDocAttachment() == 1 && table.get(0).getLtIsDocCompulsory() == 0) {
                        llUploadFile.setVisibility(View.VISIBLE);
                        isFileUploadCompulsory = false;
                    } else {
                        isFileUploadCompulsory = false;
                        llUploadFile.setVisibility(View.GONE);
                    }
                } else {
                    isFileUploadCompulsory = false;
                    llUploadFile.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<TypeOfFileUploadPojo> call, Throwable t) {
                isFileUploadCompulsory = false;
                llUploadFile.setVisibility(View.GONE);
            }
        });
    }

    private void selectLectureListApiCall(String from_date) {
        ApiImplementer.selectLectureListApiImplementer(mySharedPreferences.getInstituteId(), mySharedPreferences.getSmId(), mySharedPreferences.getSwdDivisionId(),
                mySharedPreferences.getStudentId(), from_date, mySharedPreferences.getSwdYearId(), new Callback<SelectLectureForPartialLeavePojo>() {
                    @Override
                    public void onResponse(Call<SelectLectureForPartialLeavePojo> call, Response<SelectLectureForPartialLeavePojo> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                            llSelectLecture.setVisibility(View.VISIBLE);
                            ArrayList<SelectLectureForPartialLeavePojo.TableBean> tableBeanArrayList = (ArrayList<SelectLectureForPartialLeavePojo.TableBean>) response.body().getTable();
                            tableBeanArrayList.get(0).setSelected(true);
                            rvLectureList.setAdapter(new PartialLeaveLectureListAdapter(leaveApplicationActivity, ApplyILeaveFragment.this, tableBeanArrayList));
                        } else {
                            llSelectLecture.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<SelectLectureForPartialLeavePojo> call, Throwable t) {
                        llSelectLecture.setVisibility(View.GONE);
                    }
                });
    }

    private void initView(View view) {
        mySharedPreferences = new MySharedPreferences(leaveApplicationActivity);
        connectionDetector = new ConnectionDetector(leaveApplicationActivity);
        llApplyLeaveFragment = view.findViewById(R.id.llApplyLeaveFragment);
//        llApplyLeaveProgress = view.findViewById(R.id.llApplyLeaveProgress);
//        llNoDataFoundApplyLeave = view.findViewById(R.id.llNoDataFoundApplyLeave);
        spKindOfLeave = view.findViewById(R.id.spKindOfLeave);
        spLeaveType = view.findViewById(R.id.spLeaveType);
        edtFromDate = view.findViewById(R.id.edtFromDate);
        edtFromDate.setOnClickListener(this);
        edtToDate = view.findViewById(R.id.edtToDate);
        edtToDate.setOnClickListener(this);
        edtUploadFile = view.findViewById(R.id.edtUploadFile);
        edtUploadFile.setOnClickListener(this);
        tilNote = view.findViewById(R.id.tilNote);
        tvSaveLeave = view.findViewById(R.id.tvSaveLeave);
        tvSaveLeave.setOnClickListener(this);
        llUploadFile = view.findViewById(R.id.llUploadFile);
        llSelectLecture = view.findViewById(R.id.llSelectLecture);

        typeOfLeaveArrayList = new ArrayList<>();
        typeOfLeaveArrayList.add(SELECT_TYPE_OF_LEAVE);
        typeOfLeaveArrayList.add("Partially Leave");
        typeOfLeaveArrayList.add("Full Leave");

        spinnerAdapterTypeOfLeave = new SpinnerSimpleAdapter(leaveApplicationActivity, typeOfLeaveArrayList);
        spLeaveType.setAdapter(spinnerAdapterTypeOfLeave);

        rvLectureList = view.findViewById(R.id.rvLectureList);


        spKindOfLeave.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    getTypeOfFileUpload(hashMapKindOfLeave.get(kindOfLeaveArrayList.get(position).trim()));
                } else {
                    llUploadFile.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvSaveLeave) {
            if (isValid()) {
            }
        } else if (v.getId() == R.id.edtFromDate) {
            Date fromDate = myCalendarFromDate.getTime();
            DatePickerDialog datePickerDialogFromDate = new DatePickerDialog(leaveApplicationActivity, fromDateListener, myCalendarFromDate
                    .get(Calendar.YEAR), myCalendarFromDate.get(Calendar.MONTH),
                    myCalendarFromDate.get(Calendar.DAY_OF_MONTH));
            datePickerDialogFromDate.getDatePicker().setMinDate(fromDate.getTime());
            datePickerDialogFromDate.show();
        } else if (v.getId() == R.id.edtToDate) {
            Date result = myCalendarToDate.getTime();
            DatePickerDialog datePickerDialogToDate = new DatePickerDialog(leaveApplicationActivity, toDateListener, myCalendarToDate
                    .get(Calendar.YEAR), myCalendarToDate.get(Calendar.MONTH),
                    myCalendarToDate.get(Calendar.DAY_OF_MONTH));
            datePickerDialogToDate.getDatePicker().setMinDate(result.getTime());
            datePickerDialogToDate.show();
        } else if (v.getId() == R.id.edtUploadFile) {
            uploadFileBottomSheetDialog = new UploadFileBottomSheetDialog(leaveApplicationActivity, ApplyILeaveFragment.this);
            uploadFileBottomSheetDialog.show(leaveApplicationActivity.getSupportFragmentManager(), "Select Image");
        }
    }

    @Override
    public void onFileSelectOrCapture(String fileName, String byteArayOfFile) {
        if (!CommonUtil.checkIsEmptyOrNullCommon(fileName)) {
            edtUploadFile.setText(fileName);
        }
    }

    @Override
    public void closeUploadFileBottomSheetDialog() {
        uploadFileBottomSheetDialog.dismiss();
    }

    @Override
    public void onLectureSelected() {
        Toast.makeText(leaveApplicationActivity, "Lecture Selected", Toast.LENGTH_SHORT).show();
    }
}