package com.infinity.infoway.atmiya.faculty.faculty_dashboard.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacultyAnnouncementPojo {

    @SerializedName("srno")
    @Expose
    private Integer srno;
    @SerializedName("notif_head")
    @Expose
    private String notifHead;
    @SerializedName("notif_msg")
    @Expose
    private String notifMsg;
    @SerializedName("notif_date")
    @Expose
    private String notifDate;
    @SerializedName("notif_file_path")
    @Expose
    private String notifFilePath;
    @SerializedName("notif_file")
    @Expose
    private String notifFile;
    @SerializedName("app_file_path")
    @Expose
    private String appFilePath;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("announced_by")
    @Expose
    private String announcedBy;
    @SerializedName("notif_no")
    @Expose
    private Integer notifNo;

    public Integer getSrno() {
        return srno;
    }

    public void setSrno(Integer srno) {
        this.srno = srno;
    }

    public String getNotifHead() {
        return notifHead;
    }

    public void setNotifHead(String notifHead) {
        this.notifHead = notifHead;
    }

    public String getNotifMsg() {
        return notifMsg;
    }

    public void setNotifMsg(String notifMsg) {
        this.notifMsg = notifMsg;
    }

    public String getNotifDate() {
        return notifDate;
    }

    public void setNotifDate(String notifDate) {
        this.notifDate = notifDate;
    }

    public String getNotifFilePath() {
        return notifFilePath;
    }

    public void setNotifFilePath(String notifFilePath) {
        this.notifFilePath = notifFilePath;
    }

    public String getNotifFile() {
        return notifFile;
    }

    public void setNotifFile(String notifFile) {
        this.notifFile = notifFile;
    }

    public String getAppFilePath() {
        return appFilePath;
    }

    public void setAppFilePath(String appFilePath) {
        this.appFilePath = appFilePath;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getAnnouncedBy() {
        return announcedBy;
    }

    public void setAnnouncedBy(String announcedBy) {
        this.announcedBy = announcedBy;
    }

    public Integer getNotifNo() {
        return notifNo;
    }

    public void setNotifNo(Integer notifNo) {
        this.notifNo = notifNo;
    }


}
