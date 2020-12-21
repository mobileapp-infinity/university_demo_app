package com.infinity.infoway.atmiya.student.exam.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.student.exam.adapter.CIAMarksListAdapter;
import com.infinity.infoway.atmiya.student.exam.pojo.CIAMarkstPojo;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CIAMarksActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    AppCompatImageView ivCloseCIAMarks;
    ConnectionDetector connectionDetector;
    LinearLayout llStudentCIAMarksList, llCIAMarksProgressbar, llNoDataFoundCIAMarks;
    RecyclerView rvCIAMarksStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_i_a_marks);
        initView();
        getStudentCIAMarksListApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(CIAMarksActivity.this);
        connectionDetector = new ConnectionDetector(CIAMarksActivity.this);
        ivCloseCIAMarks = findViewById(R.id.ivCloseCIAMarks);
        ivCloseCIAMarks.setOnClickListener(this);
        rvCIAMarksStudent = findViewById(R.id.rvCIAMarksStudent);
        llStudentCIAMarksList = findViewById(R.id.llStudentCIAMarksList);
        llCIAMarksProgressbar = findViewById(R.id.llCIAMarksProgressbar);
        llNoDataFoundCIAMarks = findViewById(R.id.llNoDataFoundCIAMarks);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseCIAMarks) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void getStudentCIAMarksListApiCall() {

        if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getStudentEnrollmentNo())) {
            if (connectionDetector.isConnectingToInternet()) {
                llStudentCIAMarksList.setVisibility(View.GONE);
                llCIAMarksProgressbar.setVisibility(View.VISIBLE);
                llNoDataFoundCIAMarks.setVisibility(View.GONE);
                ApiImplementer.getCIAMarksListApiImplementer(mySharedPreferences.getStudentEnrollmentNo(), new Callback<ArrayList<CIAMarkstPojo>>() {
                    @Override
                    public void onResponse(Call<ArrayList<CIAMarkstPojo>> call, Response<ArrayList<CIAMarkstPojo>> response) {
                        llCIAMarksProgressbar.setVisibility(View.GONE);
                        if (response.isSuccessful() && response.body() != null &&
                                response.body().size() > 0) {
                            llStudentCIAMarksList.setVisibility(View.VISIBLE);
                            llNoDataFoundCIAMarks.setVisibility(View.GONE);
                            rvCIAMarksStudent.setAdapter(new CIAMarksListAdapter(CIAMarksActivity.this, response.body()));
                        } else {
                            llStudentCIAMarksList.setVisibility(View.GONE);
                            llNoDataFoundCIAMarks.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<CIAMarkstPojo>> call, Throwable t) {
                        llStudentCIAMarksList.setVisibility(View.GONE);
                        llCIAMarksProgressbar.setVisibility(View.GONE);
                        llNoDataFoundCIAMarks.setVisibility(View.VISIBLE);
                        Toast.makeText(CIAMarksActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "No internet connection,please try again later", Toast.LENGTH_SHORT).show();
            }
        } else {
            llStudentCIAMarksList.setVisibility(View.GONE);
            llCIAMarksProgressbar.setVisibility(View.GONE);
            llNoDataFoundCIAMarks.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Enrollment no not generated!", Toast.LENGTH_SHORT).show();//As per talk with priyanka madam 21-12-2020 if enrollment no not found then display this massage
        }

    }

}
