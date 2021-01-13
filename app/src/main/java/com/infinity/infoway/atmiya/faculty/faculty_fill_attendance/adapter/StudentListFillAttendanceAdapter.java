package com.infinity.infoway.atmiya.faculty.faculty_fill_attendance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.faculty.faculty_fill_attendance.pojo.StudentDetailsFillAttendancePojo;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.ArrayList;

public class StudentListFillAttendanceAdapter extends RecyclerView.Adapter<StudentListFillAttendanceAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<StudentDetailsFillAttendancePojo.TableBean> tableBeanArrayList;

    public StudentListFillAttendanceAdapter(Context context, ArrayList<StudentDetailsFillAttendancePojo.TableBean> tableBeanArrayList) {
        this.context = context;
        this.tableBeanArrayList = tableBeanArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_fill_attendance_student, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (position % 2 == 0){
            holder.llDynamicRow.setBackgroundColor(context.getResources().getColor(R.color.white));
        }else {
            holder.llDynamicRow.setBackgroundColor(context.getResources().getColor(R.color.exam_module_row_color));
        }

        StudentDetailsFillAttendancePojo.TableBean tableBean = tableBeanArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(tableBean.getSwdRollNo())) {
            holder.tvStudentRollNoFillAttendance.setText(tableBean.getSwdRollNo() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(tableBean.getStudName())) {
            holder.tvStudentNameFillAttendance.setText(tableBean.getStudName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(tableBean.getStudEnrollmentNo())) {
            holder.tvStudentEnNoFillAttendance.setText(tableBean.getStudEnrollmentNo() + "");
        }

    }

    @Override
    public int getItemCount() {
        return tableBeanArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewRegularFont tvStudentRollNoFillAttendance;
        TextViewRegularFont tvStudentNameFillAttendance;
        TextViewRegularFont tvStudentEnNoFillAttendance;
        SwitchButton sbtnPresentAbsetntFillAttendance;
        LinearLayout llDynamicRow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentRollNoFillAttendance = itemView.findViewById(R.id.tvStudentRollNoFillAttendance);
            tvStudentNameFillAttendance = itemView.findViewById(R.id.tvStudentNameFillAttendance);
            tvStudentEnNoFillAttendance = itemView.findViewById(R.id.tvStudentEnNoFillAttendance);
            sbtnPresentAbsetntFillAttendance = itemView.findViewById(R.id.sbtnPresentAbsetntFillAttendance);
            llDynamicRow = itemView.findViewById(R.id.llDynamicRow);
        }
    }



}
