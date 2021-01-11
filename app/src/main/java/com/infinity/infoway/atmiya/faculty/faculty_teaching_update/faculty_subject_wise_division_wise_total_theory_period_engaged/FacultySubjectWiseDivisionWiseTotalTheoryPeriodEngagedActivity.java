
package com.infinity.infoway.atmiya.faculty.faculty_teaching_update.faculty_subject_wise_division_wise_total_theory_period_engaged;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedActivity extends AppCompatActivity implements View.OnClickListener {


    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    Calendar calendar;
    LinearLayoutManager layoutManager;
    AppCompatImageView ivCloseFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngaged;
    LinearLayout llFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedList, llFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedProgressbar, llNoDataFoundFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngaged;
    LinearLayout llFacultyProgressbarTotalTheoryPeriodEngaged;

    RecyclerView rvFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngaged;
    FacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedAdapter facultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedAdapter;
    ArrayList<FacultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo> facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojoArrayList = new ArrayList<>();
    private int currentPageNo = 1;
    private boolean isScrolling = false;
    private boolean hasMoreData = true;
    private int currentItemCount = 0;
    private int totalItemCount = 0;
    private int scrolledOutItemCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty__subject__wise__division__wise__total__theory__period__engaged);
        initView();
        GetSubjectWiseDivisionWiseTotalTheoryPeriodEngagedAPI(true);
        rvFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngaged.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                scrolledOutItemCount = layoutManager.findFirstVisibleItemPosition();

                if ((hasMoreData) && (isScrolling) && (currentItemCount + scrolledOutItemCount == totalItemCount)) {
                    isScrolling = false;
                    currentPageNo = currentPageNo + 1;
                    GetSubjectWiseDivisionWiseTotalTheoryPeriodEngagedAPI(false);
                }
            }
        });


    }


    private void initView() {
        calendar = Calendar.getInstance();
        mySharedPreferences = new MySharedPreferences(FacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedActivity.this);
        connectionDetector = new ConnectionDetector(FacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedActivity.this);
        ivCloseFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngaged = findViewById(R.id.ivCloseFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngaged);
        ivCloseFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngaged.setOnClickListener(this);
        rvFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngaged = findViewById(R.id.rvFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngaged);
        layoutManager = new LinearLayoutManager(FacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedActivity.this);
        rvFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngaged.setLayoutManager(layoutManager);
        llFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedList = findViewById(R.id.llFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedList);
        llFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedProgressbar = findViewById(R.id.llFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedProgressbar);
        llNoDataFoundFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngaged = findViewById(R.id.llNoDataFoundFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngaged);
        llFacultyProgressbarTotalTheoryPeriodEngaged = findViewById(R.id.llFacultyProgressbarTotalTheoryPeriodEngaged);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngaged) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void GetSubjectWiseDivisionWiseTotalTheoryPeriodEngagedAPI(boolean isProgressbarShowing) {
        if (connectionDetector.isConnectingToInternet()) {

            if (isProgressbarShowing) {
                llFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedProgressbar.setVisibility(View.VISIBLE);
            } else {
                llFacultyProgressbarTotalTheoryPeriodEngaged.setVisibility(View.VISIBLE);
            }

            ApiImplementer.getFacultySubjectAndDivisionWiseTotalTheoryPeriodEngagedImplementer(mySharedPreferences.getEmpId(), mySharedPreferences.getEmpYearId(),
                    String.valueOf(CommonUtil.ROW_PER_PAGE), String.valueOf(currentPageNo), new Callback<ArrayList<FacultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo>>() {
                        @Override
                        public void onResponse(Call<ArrayList<FacultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo>> call, Response<ArrayList<FacultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo>> response) {
                            if (connectionDetector.isConnectingToInternet()) {
                                if (isProgressbarShowing) {
                                    llFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedProgressbar.setVisibility(View.GONE);
                                } else {
                                    llFacultyProgressbarTotalTheoryPeriodEngaged.setVisibility(View.GONE);
                                }
                                try {
                                    if (response.isSuccessful() && response.body() != null) {

                                        llFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedList.setVisibility(View.VISIBLE);
                                        llNoDataFoundFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngaged.setVisibility(View.GONE);


                                        facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojoArrayList.addAll(response.body()); //response body
                                        if (!response.body().isEmpty() && response.body().size() > 0) {
                                            if (currentPageNo == 1) {
                                                facultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedAdapter = new FacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedAdapter(FacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedActivity.this, facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojoArrayList);
                                                rvFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngaged.setAdapter(facultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedAdapter);

                                            } else {
                                                facultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedAdapter.notifyDataSetChanged();
                                            }
                                        } else {
                                            if (currentPageNo == 1) {
                                                llFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedList.setVisibility(View.GONE);
                                                llFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedProgressbar.setVisibility(View.GONE);
                                                llNoDataFoundFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngaged.setVisibility(View.VISIBLE);

                                            } else {
                                                hasMoreData = false;
                                            }
                                        }
                                    } else {
                                        Toast.makeText(FacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedActivity.this, "Response Code:- " + response.code(), Toast.LENGTH_SHORT).show();
                                    }

                                } catch (Exception ex) {
                                    Toast.makeText(FacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedActivity.this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                                }


                            } else {
                                Toast.makeText(FacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedActivity.this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ArrayList<FacultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo>> call, Throwable t) {
                            llFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedList.setVisibility(View.GONE);
                            llFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedProgressbar.setVisibility(View.GONE);
                            llNoDataFoundFacultySubjectWiseDivisionWiseTotalTheoryPeriodEngaged.setVisibility(View.VISIBLE);
                            Toast.makeText(FacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });


        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


}
