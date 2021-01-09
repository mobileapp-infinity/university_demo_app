package com.infinity.infoway.atmiya.faculty.faculty_attendance;

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
import com.infinity.infoway.atmiya.custom_class.TextViewMediumFont;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.utils.CommonUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class FacultyAttendanceAdapterNew extends RecyclerView.Adapter<FacultyAttendanceAdapterNew.MyViewHolder> {

    Context context;
    ArrayList<FacultyAttendancePojo> facultyAttendancePojoArrayList;
    LayoutInflater layoutInflater;

    public FacultyAttendanceAdapterNew(Context context, ArrayList<FacultyAttendancePojo> facultyAttendancePojoArrayList) {
        this.context = context;
        this.facultyAttendancePojoArrayList = facultyAttendancePojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_faculty_attendance_list_new, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FacultyAttendancePojo facultyAttendancePojo = facultyAttendancePojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyAttendancePojo.getAttDate())) {
            SimpleDateFormat sourceFormat = new SimpleDateFormat("dd-MMM-yyyy");
            try {
                Date parsed;

                if (facultyAttendancePojo.getAttDate().contains("T")){
                    parsed = sourceFormat.parse(facultyAttendancePojo.getAttDate().split("T")[0]);
                }else {
                    parsed = sourceFormat.parse(facultyAttendancePojo.getAttDate());
                }

                String result = sourceFormat.format(parsed);
                holder.tvAttendanceFromDate.setText(" till " + result);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyAttendancePojo.getDay())) {
            holder.tvDays.setText(facultyAttendancePojo.getDay());
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyAttendancePojo.getTotalHour())) {
            if (facultyAttendancePojo.getTotalHour().contains(":")) {
                holder.tvWorkingHours.setText(facultyAttendancePojo.getTotalHour().split(":")[0] + "H" + " " +
                        facultyAttendancePojo.getTotalHour().split(":")[1] + "M");
            } else {
                holder.tvWorkingHours.setText(facultyAttendancePojo.getTotalHour());
            }
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyAttendancePojo.getStatus())) {
            String status = "-";

            if (facultyAttendancePojo.getStatus().equalsIgnoreCase("P")) {
                status = "Present";
            } else if (facultyAttendancePojo.getStatus().equalsIgnoreCase("AB")) {
                status = "Absent";
            } else {
                status = facultyAttendancePojo.getStatus() + "";
            }

            holder.tvStatus.setText(status);
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyAttendancePojo.getLateByHour())) {
            if (facultyAttendancePojo.getTotalHour().contains(":")) {
                holder.tvLateBy.setText(facultyAttendancePojo.getLateByHour().split(":")[0] + "H" + " " +
                        facultyAttendancePojo.getLateByHour().split(":")[1] + "M");
            } else {
                holder.tvLateBy.setText(facultyAttendancePojo.getLateByHour());
            }
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyAttendancePojo.getEarlyByHour())) {
            if (facultyAttendancePojo.getTotalHour().contains(":")) {
                holder.tvEarlyBy.setText(facultyAttendancePojo.getEarlyByHour().split(":")[0] + "H" + " " +
                        facultyAttendancePojo.getEarlyByHour().split(":")[1] + "M");
            } else {
                holder.tvEarlyBy.setText(facultyAttendancePojo.getEarlyByHour());
            }
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyAttendancePojo.getEarlyByHour())) {
            if (facultyAttendancePojo.getTotalHour().contains(":")) {
                holder.tvExtraHours.setText(facultyAttendancePojo.getEarlyByHour().split(":")[0] + "H" + " " +
                        facultyAttendancePojo.getEarlyByHour().split(":")[1] + "M");
            } else {
                holder.tvExtraHours.setText(facultyAttendancePojo.getEarlyByHour());
            }
        }

        if (facultyAttendancePojo.getInoutArray() != null && facultyAttendancePojo.getInoutArray().size() > 0) {



//            View facultyAttendanceLayout = inflaterForMergingLayout.inflate(R.layout.layout_for_in_out_list_item, null);
//
//            LinearLayout tvInOutHeader = facultyAttendanceLayout.findViewById(R.id.tvInOutHeader);
            for (int i = 0; i < facultyAttendancePojo.getInoutArray().size(); i++) {
                LayoutInflater inflaterForMergingLayout = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View facultyAttendanceLayoutChild = inflaterForMergingLayout.inflate(R.layout.in_out_row, null);

                TextViewRegularFont tvInTime = facultyAttendanceLayoutChild.findViewById(R.id.tvInTime);
                TextViewRegularFont tvOutTime = facultyAttendanceLayoutChild.findViewById(R.id.tvOutTime);

//                if (i == facultyAttendancePojo.getInoutArray().size() - 1) {
//                    rowLine.setVisibility(View.GONE);
//                }


                if (!CommonUtil.checkIsEmptyOrNullCommon(facultyAttendancePojo.getInoutArray().get(i).getInTime())) {
                    tvInTime.setText(facultyAttendancePojo.getInoutArray().get(i).getInTime() + "");
                }

                if (!CommonUtil.checkIsEmptyOrNullCommon(facultyAttendancePojo.getInoutArray().get(i).getOutTime())) {
                    tvOutTime.setText(facultyAttendancePojo.getInoutArray().get(i).getOutTime() + "");
                }

//                tvInOutHeader.addView(facultyAttendanceLayoutChild);

                holder.llDynamicLayout.addView(facultyAttendanceLayoutChild);
            }
//            holder.llDynamicLayout.addView(facultyAttendanceLayout);

        } else {
            holder.llDynamicLayout.setVisibility(View.GONE);
        }

        holder.llExpandedHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayout(!facultyAttendancePojoArrayList.get(position).isExpanded(), holder.ivViewMoreBtnFacultyAttendance, holder.llExpandableLayoutFacultyAttendance);
                facultyAttendancePojoArrayList.get(position).setExpanded(show);
            }
        });


    }

    @Override
    public int getItemCount() {
        return facultyAttendancePojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llExpandedHeader;
        AppCompatImageView ivViewMoreBtnFacultyAttendance;
        LinearLayout llExpandableLayoutFacultyAttendance;

        TextViewRegularFont tvAttendanceFromDate;
        TextViewMediumFont tvDays;
        TextViewMediumFont tvWorkingHours;
        TextViewMediumFont tvStatus;
        TextViewMediumFont tvLateBy;
        TextViewMediumFont tvEarlyBy;
        TextViewMediumFont tvExtraHours;

        LinearLayout llDynamicLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            llExpandedHeader = itemView.findViewById(R.id.llExpandedHeader);
            ivViewMoreBtnFacultyAttendance = itemView.findViewById(R.id.ivViewMoreBtnFacultyAttendance);
            llExpandableLayoutFacultyAttendance = itemView.findViewById(R.id.llExpandableLayoutFacultyAttendance);

            tvAttendanceFromDate = itemView.findViewById(R.id.tvAttendanceFromDate);
            tvDays = itemView.findViewById(R.id.tvDays);
            tvWorkingHours = itemView.findViewById(R.id.tvWorkingHours);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvLateBy = itemView.findViewById(R.id.tvLateBy);
            tvEarlyBy = itemView.findViewById(R.id.tvEarlyBy);
            tvExtraHours = itemView.findViewById(R.id.tvExtraHours);

            llDynamicLayout = itemView.findViewById(R.id.llDynamicLayout);
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
