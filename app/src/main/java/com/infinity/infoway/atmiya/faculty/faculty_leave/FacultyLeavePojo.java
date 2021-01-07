package com.infinity.infoway.atmiya.faculty.faculty_leave;

import com.google.gson.annotations.SerializedName;

public class FacultyLeavePojo {


    boolean isExpanded = true;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    @SerializedName("Contact_ID")
    public String Contact_ID;

    public String getContact_ID() {
        return Contact_ID;
    }

    @SerializedName("Leave_Name")
    public String Leave_Name;

    public String getLeave_Name() {
        return Leave_Name;
    }

    @SerializedName("Leave_Day")
    public String Leave_Day;

    public String getLeave_Day() {
        return Leave_Day;
    }

    @SerializedName("Leave_Balance")
    public String Leave_Balance;

    public String getLeave_Balance() {
        return Leave_Balance;
    }

    @SerializedName("Leave_Taken")
    public String Leave_Taken;

    public String getLeave_Taken() {
        return Leave_Taken;
    }

}
