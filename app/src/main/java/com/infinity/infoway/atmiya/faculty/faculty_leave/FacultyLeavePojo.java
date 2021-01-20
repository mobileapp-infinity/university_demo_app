package com.infinity.infoway.atmiya.faculty.faculty_leave;

import com.google.gson.annotations.Expose;
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
    @Expose
    private Integer contactID;
    @SerializedName("Leave_Name")
    @Expose
    private String leaveName;
    @SerializedName("Leave_Day")
    @Expose
    private Integer leaveDay;
    @SerializedName("Leave_Balance")
    @Expose
    private Integer leaveBalance;
    @SerializedName("Leave_Taken")
    @Expose
    private Integer leaveTaken;

    public Integer getContactID() {
        return contactID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    public String getLeaveName() {
        return leaveName;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }

    public Integer getLeaveDay() {
        return leaveDay;
    }

    public void setLeaveDay(Integer leaveDay) {
        this.leaveDay = leaveDay;
    }

    public Integer getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(Integer leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    public Integer getLeaveTaken() {
        return leaveTaken;
    }

    public void setLeaveTaken(Integer leaveTaken) {
        this.leaveTaken = leaveTaken;
    }
}
