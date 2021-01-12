package com.infinity.infoway.atmiya.faculty.faculty_pending_attendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.custom_class.CustomAnimationForDefaultExpandableCard;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.utils.CommonUtil;

import java.util.ArrayList;

public class FacultyPendingAttendanceListAdapter extends RecyclerView.Adapter<FacultyPendingAttendanceListAdapter.MyViewHolder> {

    Context context;
    ArrayList<FacultyPendingAttendancePojo.Details> facultyPendingAttendancePojoArrayList;
    LayoutInflater layoutInflater;

    public FacultyPendingAttendanceListAdapter(Context context, ArrayList<FacultyPendingAttendancePojo.Details> facultyPendingAttendancePojoArrayList) {
        this.context = context;
        this.facultyPendingAttendancePojoArrayList = facultyPendingAttendancePojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_faculty_pending_attendance_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        FacultyPendingAttendancePojo.Details facultyPendingAttendancePojo = facultyPendingAttendancePojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyPendingAttendancePojo.getSemesterName())) {
            holder.tvSemesterFacultyPendingAttendance.setText(facultyPendingAttendancePojo.getSemesterName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyPendingAttendancePojo.getDlDate())) {

            String data = facultyPendingAttendancePojo.getDlDate();

            if (data != null && !data.isEmpty()) {
                String date = "";
                try {
                    if (data.contains("/")) {
                        String dateArray[] = data.split("/");
                        date = dateArray[0] + " " + CommonUtil.getMonthSortNameFromNumber(Integer.parseInt(dateArray[1])) + "," + " " + dateArray[2];
                    }
                    holder.tvFacultyPendingAttendanceDate.setText(date);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyPendingAttendancePojo.getCourseName())) {
            holder.tvCourseNameFacultyPendingAttendance.setText(facultyPendingAttendancePojo.getCourseName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyPendingAttendancePojo.getSubName())) {
            holder.tvSubjectNameFacultyPendingAttendance.setText(facultyPendingAttendancePojo.getSubName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyPendingAttendancePojo.getBatchName())) {
            holder.tvBatchFacultyPendingAttendance.setText(facultyPendingAttendancePojo.getBatchName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyPendingAttendancePojo.getBatchName())) {
            holder.tvBatchFacultyPendingAttendance.setText(facultyPendingAttendancePojo.getBatchName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyPendingAttendancePojo.getDivisionName())) {
            holder.tvDivisionFacultyPendingAttendance.setText(facultyPendingAttendancePojo.getDivisionName() + "");
        }

        holder.llFillAttendanceFacultyPendingAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

//        AppCompatImageView ivViewMoreFacultyPendingAttendance;
//        LinearLayout llExpandableLayoutFacultyPendingAttendance;
//        LinearLayout llExpandedHeaderFacultyPendingAttendance;

        TextViewRegularFont tvSemesterFacultyPendingAttendance;
        TextViewRegularFont tvFacultyPendingAttendanceDate;
        TextViewRegularFont tvCourseNameFacultyPendingAttendance;
        TextViewRegularFont tvSubjectNameFacultyPendingAttendance;
        TextViewRegularFont tvBatchFacultyPendingAttendance;
        TextViewRegularFont tvDivisionFacultyPendingAttendance;
        LinearLayout llFillAttendanceFacultyPendingAttendance;


//        TextViewRegularFont tvDepartmentNamePendingAttendance;
//        TextViewRegularFont tvSubjectFacultyPendingAttendance;
//        TextViewRegularFont tvDateFacultyPendingAttendance;
//        TextViewRegularFont tvResourceNameFacultyPendingAttendance;
//        TextViewRegularFont tvBatchFacultyPendingAttendance;
//        TextViewRegularFont tvDivisionPendingAttendance;

        View dynamicLine;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dynamicLine = itemView.findViewById(R.id.dynamicLine);
//            ivViewMoreFacultyPendingAttendance = itemView.findViewById(R.id.ivViewMoreFacultyPendingAttendance);
//            llExpandableLayoutFacultyPendingAttendance = itemView.findViewById(R.id.llExpandableLayoutFacultyPendingAttendance);
//            llExpandedHeaderFacultyPendingAttendance = itemView.findViewById(R.id.llExpandedHeaderFacultyPendingAttendance);
//
//            llFillAttendance = itemView.findViewById(R.id.llFillAttendance);
//            tvSemesterFacultyPendingAttendance = itemView.findViewById(R.id.tvSemesterFacultyPendingAttendance);
//            tvDepartmentNamePendingAttendance = itemView.findViewById(R.id.tvDepartmentNamePendingAttendance);
//            tvSubjectFacultyPendingAttendance = itemView.findViewById(R.id.tvSubjectFacultyPendingAttendance);
//            tvDateFacultyPendingAttendance = itemView.findViewById(R.id.tvDateFacultyPendingAttendance);
//            tvResourceNameFacultyPendingAttendance = itemView.findViewById(R.id.tvResourceNameFacultyPendingAttendance);
//            tvBatchFacultyPendingAttendance = itemView.findViewById(R.id.tvBatchFacultyPendingAttendance);
//            tvDivisionPendingAttendance = itemView.findViewById(R.id.tvDivisionPendingAttendance);

            tvSemesterFacultyPendingAttendance = itemView.findViewById(R.id.tvSemesterFacultyPendingAttendance);
            tvFacultyPendingAttendanceDate = itemView.findViewById(R.id.tvFacultyPendingAttendanceDate);
            tvCourseNameFacultyPendingAttendance = itemView.findViewById(R.id.tvCourseNameFacultyPendingAttendance);
            tvSubjectNameFacultyPendingAttendance = itemView.findViewById(R.id.tvSubjectNameFacultyPendingAttendance);
            tvBatchFacultyPendingAttendance = itemView.findViewById(R.id.tvBatchFacultyPendingAttendance);
            tvDivisionFacultyPendingAttendance = itemView.findViewById(R.id.tvDivisionFacultyPendingAttendance);
            llFillAttendanceFacultyPendingAttendance = itemView.findViewById(R.id.llFillAttendanceFacultyPendingAttendance);
        }
    }

    private boolean toggleLayoutForDefaultOpenCard(boolean isExpanded, View v, LinearLayout layoutExpand) {
        CustomAnimationForDefaultExpandableCard.toggleArrow(v, !isExpanded);
        if (isExpanded) {
            CustomAnimationForDefaultExpandableCard.collapse(layoutExpand);
        } else {
            CustomAnimationForDefaultExpandableCard.expand(layoutExpand);
        }
        return isExpanded;
    }

}

