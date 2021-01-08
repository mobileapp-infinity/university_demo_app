package com.infinity.infoway.atmiya.faculty.faculty_attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.util.Pair;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class FacultyAttendanceActivity extends AppCompatActivity implements View.OnClickListener, MaterialPickerOnPositiveButtonClickListener {

    AppCompatImageView ivCloseFacultyAttendance;
    AppCompatButton tvSelectDate;
    MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.dateRangePicker();

    MaterialDatePicker materialDatePicker;

    TextViewRegularFont tvFacultyAttendanceFromDate, tvFacultyAttendanceToDate;
    MaterialCardView cvFacultyAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_attendance);
        initView();

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();


        long toDay = MaterialDatePicker.todayInUtcMilliseconds();

//        calendar.setTimeInMillis(toDay);
//
//        builder.setSelection(toDay);
        builder.setTitleText("Select A Date");
        materialDatePicker = builder.build();


        materialDatePicker.addOnPositiveButtonClickListener(this);



    }

    private void initView() {
        ivCloseFacultyAttendance = findViewById(R.id.ivCloseFacultyAttendance);
        ivCloseFacultyAttendance.setOnClickListener(this);
        tvSelectDate = findViewById(R.id.tvSelectDate);
        tvSelectDate.setOnClickListener(this);
        tvFacultyAttendanceFromDate = findViewById(R.id.tvFacultyAttendanceFromDate);
        tvFacultyAttendanceToDate = findViewById(R.id.tvFacultyAttendanceToDate);
        cvFacultyAttendance = findViewById(R.id.cvFacultyAttendance);
        cvFacultyAttendance.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultyAttendance) {
            onBackPressed();
        } else if (v.getId() == R.id.cvFacultyAttendance) {
            materialDatePicker.show(getSupportFragmentManager(), "Date Picker");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onPositiveButtonClick(Object selectedDate) {


        final Pair<Date, Date> rangeDate = new Pair<>(new Date((Long)((Pair) selectedDate).first), new Date((Long) ((Pair) selectedDate).second));
        Date startDate = rangeDate.first;
        Date endDate = rangeDate.second;
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd MMM yyyy");
        tvFacultyAttendanceFromDate.setText(simpleFormat.format(startDate));
        tvFacultyAttendanceToDate.setText( simpleFormat.format(endDate));

    }
}