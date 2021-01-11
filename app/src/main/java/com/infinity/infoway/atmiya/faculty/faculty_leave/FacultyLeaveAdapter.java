package com.infinity.infoway.atmiya.faculty.faculty_leave;

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

public class FacultyLeaveAdapter extends RecyclerView.Adapter<FacultyLeaveAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<FacultyLeavePojo> facultyLeavePojoArrayList;

    public FacultyLeaveAdapter(Context context, ArrayList<FacultyLeavePojo> facultyLeavePojoArrayList) {
        this.context = context;
        this.facultyLeavePojoArrayList = facultyLeavePojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.infalter_faculty_leave_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FacultyLeavePojo facultyLeavePojo = facultyLeavePojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLeavePojo.getLeave_Name())) {
            holder.tvLeaveTypeFaculty.setText(facultyLeavePojo.getLeave_Name() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLeavePojo.getLeave_Day())) {
            holder.tvLeaveDaysFaculty.setText(facultyLeavePojo.getLeave_Day() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLeavePojo.getLeave_Taken())) {
            holder.tvTakenLeaveFaculty.setText(facultyLeavePojo.getLeave_Taken() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLeavePojo.getLeave_Balance())) {
            holder.tvBalanceLeaveFaculty.setText(facultyLeavePojo.getLeave_Balance() + "");
        }

        holder.llExpandedHeaderFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayoutForDefaultOpenCard(!facultyLeavePojo.isExpanded(), holder.ivViewMoreFaculty, holder.llExpandableLayoutFaculty);
                facultyLeavePojo.setExpanded(show);
            }
        });

    }

    @Override
    public int getItemCount() {
        return facultyLeavePojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView ivViewMoreFaculty;
        LinearLayout llExpandableLayoutFaculty;
        LinearLayout llExpandedHeaderFaculty;

        TextViewRegularFont tvLeaveTypeFaculty;
        TextViewRegularFont tvLeaveDaysFaculty;
        TextViewRegularFont tvTakenLeaveFaculty;
        TextViewRegularFont tvBalanceLeaveFaculty;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivViewMoreFaculty = itemView.findViewById(R.id.ivViewMoreFaculty);
            llExpandableLayoutFaculty = itemView.findViewById(R.id.llExpandableLayoutFaculty);
            llExpandedHeaderFaculty = itemView.findViewById(R.id.llExpandedHeaderFaculty);
            tvLeaveTypeFaculty = itemView.findViewById(R.id.tvLeaveTypeFaculty);
            tvLeaveDaysFaculty = itemView.findViewById(R.id.tvLeaveDaysFaculty);
            tvTakenLeaveFaculty = itemView.findViewById(R.id.tvTakenLeaveFaculty);
            tvBalanceLeaveFaculty = itemView.findViewById(R.id.tvBalanceLeaveFaculty);
        }
    }

    private boolean toggleLayoutForDefaultOpenCard(boolean isExpanded, View v, LinearLayout layoutExpand) {
        CustomAnimationForDefaultExpandableCard.toggleArrow(v, isExpanded);
        if (isExpanded) {
            CustomAnimationForDefaultExpandableCard.expand(layoutExpand);
        } else {
            CustomAnimationForDefaultExpandableCard.collapse(layoutExpand);
        }
        return isExpanded;
    }

}
