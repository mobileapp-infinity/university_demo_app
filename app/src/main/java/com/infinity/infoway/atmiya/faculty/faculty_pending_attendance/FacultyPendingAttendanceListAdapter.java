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

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyPendingAttendancePojo.getDepartment())) {
            holder.tvDepartmentNamePendingAttendance.setText(facultyPendingAttendancePojo.getDepartment() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyPendingAttendancePojo.getSubName())) {
            holder.tvSubjectFacultyPendingAttendance.setText(facultyPendingAttendancePojo.getSubName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyPendingAttendancePojo.getDlDate())) {
            holder.tvDateFacultyPendingAttendance.setText(facultyPendingAttendancePojo.getDlDate() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyPendingAttendancePojo.getResourseName())) {
            holder.tvResourceNameFacultyPendingAttendance.setText(facultyPendingAttendancePojo.getResourseName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyPendingAttendancePojo.getBatchName())) {
            holder.tvBatchFacultyPendingAttendance.setText(facultyPendingAttendancePojo.getBatchName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyPendingAttendancePojo.getDivisionName())) {
            holder.tvDivisionPendingAttendance.setText(facultyPendingAttendancePojo.getDivisionName() + "");
        }

        holder.llExpandedHeaderFacultyPendingAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayoutForDefaultOpenCard(!facultyPendingAttendancePojo.isExpanded(), holder.ivViewMoreFacultyPendingAttendance, holder.llExpandableLayoutFacultyPendingAttendance);
                facultyPendingAttendancePojo.setExpanded(show);
            }
        });

        holder.llFillAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return facultyPendingAttendancePojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView ivViewMoreFacultyPendingAttendance;
        LinearLayout llExpandableLayoutFacultyPendingAttendance;
        LinearLayout llExpandedHeaderFacultyPendingAttendance;

        LinearLayout llFillAttendance;
        TextViewRegularFont tvSemesterFacultyPendingAttendance;
        TextViewRegularFont tvDepartmentNamePendingAttendance;
        TextViewRegularFont tvSubjectFacultyPendingAttendance;
        TextViewRegularFont tvDateFacultyPendingAttendance;
        TextViewRegularFont tvResourceNameFacultyPendingAttendance;
        TextViewRegularFont tvBatchFacultyPendingAttendance;
        TextViewRegularFont tvDivisionPendingAttendance;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivViewMoreFacultyPendingAttendance = itemView.findViewById(R.id.ivViewMoreFacultyPendingAttendance);
            llExpandableLayoutFacultyPendingAttendance = itemView.findViewById(R.id.llExpandableLayoutFacultyPendingAttendance);
            llExpandedHeaderFacultyPendingAttendance = itemView.findViewById(R.id.llExpandedHeaderFacultyPendingAttendance);

            llFillAttendance = itemView.findViewById(R.id.llFillAttendance);
            tvSemesterFacultyPendingAttendance = itemView.findViewById(R.id.tvSemesterFacultyPendingAttendance);
            tvDepartmentNamePendingAttendance = itemView.findViewById(R.id.tvDepartmentNamePendingAttendance);
            tvSubjectFacultyPendingAttendance = itemView.findViewById(R.id.tvSubjectFacultyPendingAttendance);
            tvDateFacultyPendingAttendance = itemView.findViewById(R.id.tvDateFacultyPendingAttendance);
            tvResourceNameFacultyPendingAttendance = itemView.findViewById(R.id.tvResourceNameFacultyPendingAttendance);
            tvBatchFacultyPendingAttendance = itemView.findViewById(R.id.tvBatchFacultyPendingAttendance);
            tvDivisionPendingAttendance = itemView.findViewById(R.id.tvDivisionPendingAttendance);
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

