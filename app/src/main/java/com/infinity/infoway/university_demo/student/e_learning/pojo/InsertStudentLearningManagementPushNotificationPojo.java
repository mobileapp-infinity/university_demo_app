package com.infinity.infoway.university_demo.student.e_learning.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InsertStudentLearningManagementPushNotificationPojo {

    @SerializedName("Error_code")
    @Expose
    private String errorCode;
    @SerializedName("Error_msg")
    @Expose
    private String errorMsg;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
