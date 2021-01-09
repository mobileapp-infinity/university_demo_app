package com.infinity.infoway.atmiya.faculty.faculty_attendance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.google.gson.internal.$Gson$Preconditions;
import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.student.e_learning.pojo.JoinGroupListPojo;
import com.infinity.infoway.atmiya.utils.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class FacultyAttendanceAdapter extends BaseExpandableListAdapter {

    private FacultyAttendanceActivity facultyAttendanceActivity;
    private LayoutInflater layoutInflater;
    private ArrayList<FacultyAttendancePojo> facultyAttendancePojoHeaderArrayList;
    private HashMap<String, ArrayList<FacultyAttendancePojo.InoutArray>> facultyAttendanceChildHashMap;

    public FacultyAttendanceAdapter(FacultyAttendanceActivity facultyAttendanceActivity,
                                    ArrayList<FacultyAttendancePojo> facultyAttendancePojoHeaderArrayList,
                                    HashMap<String, ArrayList<FacultyAttendancePojo.InoutArray>> facultyAttendanceChildHashMap) {
        this.facultyAttendanceActivity = facultyAttendanceActivity;
        this.layoutInflater = LayoutInflater.from(facultyAttendanceActivity);
        this.facultyAttendancePojoHeaderArrayList = facultyAttendancePojoHeaderArrayList;
        this.facultyAttendanceChildHashMap = facultyAttendanceChildHashMap;
    }

    @Override
    public int getGroupCount() {
        return facultyAttendancePojoHeaderArrayList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return facultyAttendanceChildHashMap.get(facultyAttendancePojoHeaderArrayList.get(groupPosition).getAttDate()).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return facultyAttendancePojoHeaderArrayList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return facultyAttendanceChildHashMap.get(facultyAttendancePojoHeaderArrayList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.inflater_faculty_attendance_header_list_item, null);
        }

        FacultyAttendancePojo facultyAttendancePojo = facultyAttendancePojoHeaderArrayList.get(groupPosition);

        TextViewRegularFont tvFacultyAttendanceHeader = view.findViewById(R.id.tvFacultyAttendanceHeader);
        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyAttendancePojo.getDay())) {
            tvFacultyAttendanceHeader.setText(facultyAttendancePojo.getDay() + "");
        }

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.inflater_faculty_attendance_child_item, null);
        }
        FacultyAttendancePojo.InoutArray inoutArray = facultyAttendanceChildHashMap.get(facultyAttendancePojoHeaderArrayList.get(groupPosition).getAttDate()).get(childPosition);

        TextViewRegularFont tvFacultyAttendanceFromDate = view.findViewById(R.id.tvFacultyAttendanceFromDate);
        TextViewRegularFont tvFacultyAttendanceToDate = view.findViewById(R.id.tvFacultyAttendanceToDate);

        tvFacultyAttendanceFromDate.setText(inoutArray.getInTime() + "");
        tvFacultyAttendanceToDate.setText(inoutArray.getOutTime() + "");

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
