package com.infinity.infoway.atmiya.faculty.faculty_teaching_update.faculty_subject_wise_division_wise_total_theory_period_engaged;

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

public class FacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedAdapter extends RecyclerView.Adapter<FacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<FacultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo> facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojoArrayList;

    public FacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedAdapter(Context context, ArrayList<FacultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo> facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojoArrayList) {
        this.context = context;
        this.facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojoArrayList = facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_faculty_subject_wise_division_wise_total_theory_period_engaged_items, parent, false);
        return new FacultySubjectWiseDivisionWiseTotalTheoryPeriodEngagedAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        FacultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo = facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo.getSubName())) {
            holder.tvSubjectNameFaculty.setText(facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo.getSubName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo.getYearName())) {
            holder.tvAcademicYearFaculty.setText(facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo.getYearName()+ "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo.getSemName())) {
            holder.tvSemesterFaculty.setText(facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo.getSemName()+ "");
        }


        if (!CommonUtil.checkIsEmptyOrNullCommon(facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo.getDivName())) {
            holder.tvDivisionFaculty.setText(facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo.getDivName()+ "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo.getLecPerWeek())) {
            holder.tvTotalLectureEngagedFaculty.setText(facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo.getLecPerWeek() + "");
        }
        holder.llExpandedHeaderFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayout(!facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojoArrayList.get(position).isExpanded(), holder.ivViewMoreFaculty, holder.llExpandableLayoutFaculty);
                facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojoArrayList.get(position).setExpanded(show);
            }
        });

    }

    @Override
    public int getItemCount() {
        return facultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojoArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView ivViewMoreFaculty;
        LinearLayout llExpandableLayoutFaculty;
        LinearLayout llExpandedHeaderFaculty;

        TextViewRegularFont tvSubjectNameFaculty;
        TextViewRegularFont tvAcademicYearFaculty;
        TextViewRegularFont tvSemesterFaculty;

        TextViewRegularFont tvDivisionFaculty;
        TextViewRegularFont tvTotalLectureEngagedFaculty;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivViewMoreFaculty = itemView.findViewById(R.id.ivViewMoreFaculty);
            llExpandableLayoutFaculty = itemView.findViewById(R.id.llExpandableLayoutFaculty);
            llExpandedHeaderFaculty = itemView.findViewById(R.id.llExpandedHeaderFaculty);
            tvSubjectNameFaculty = itemView.findViewById(R.id.tvSubjectNameFaculty);
            tvAcademicYearFaculty = itemView.findViewById(R.id.tvAcademicYearFaculty);
            tvSemesterFaculty = itemView.findViewById(R.id.tvSemesterFaculty);
            tvDivisionFaculty = itemView.findViewById(R.id.tvDivisionFaculty);
            tvTotalLectureEngagedFaculty = itemView.findViewById(R.id.tvTotalLectureEngagedFaculty);
        }
    }

    private boolean toggleLayout(boolean isExpanded, View v, LinearLayout layoutExpand) {
        CustomAnimationForDefaultExpandableCard.toggleArrow(v, isExpanded);
        if (isExpanded) {
            CustomAnimationForDefaultExpandableCard.expand(layoutExpand);
        } else {
            CustomAnimationForDefaultExpandableCard.collapse(layoutExpand);
        }
        return isExpanded;

    }
}
