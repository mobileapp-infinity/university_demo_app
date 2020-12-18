package com.infinity.infoway.atmiya.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static android.content.Context.MODE_PRIVATE;

public class MySharedPreferences {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public MySharedPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PreferencesConstants.PREFERENCES_FILE_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public void logoutStudent() {
        editor.clear();
        editor.commit();
    }

    public void setStudentUsername(String studentUsername) {
        editor.putString(PreferencesConstants.STUD_USER_NAME, studentUsername);
        editor.apply();
    }

    public String getStudentUsername() {
        return sharedPreferences.getString(PreferencesConstants.STUD_USER_NAME, "");
    }

    public void setStudentPassword(String studentPassword) {
        editor.putString(PreferencesConstants.STUD_PASSWORD, studentPassword);
        editor.apply();
    }

    public String getStudentPassword() {
        return sharedPreferences.getString(PreferencesConstants.STUD_PASSWORD, "");
    }

    public void setStudentId(String studentId) {
        editor.putString(PreferencesConstants.STUDENT_ID, studentId);
        editor.apply();
    }

    public String getStudentId() {
        return sharedPreferences.getString(PreferencesConstants.STUDENT_ID, "");
    }

    public void setDMId(String dmId) {
        editor.putString(PreferencesConstants.DM_ID, dmId);
        editor.apply();
    }

    public String getDmId() {
        return sharedPreferences.getString(PreferencesConstants.DM_ID, "");
    }

    public void setDmFullName(String dmFullName) {
        editor.putString(PreferencesConstants.DM_FULL_NAME, dmFullName);
        editor.apply();
    }

    public String getDmFullName() {
        return sharedPreferences.getString(PreferencesConstants.DM_FULL_NAME, "");
    }

    public void setCourseId(String courseId) {
        editor.putString(PreferencesConstants.COURSE_ID, courseId);
        editor.apply();
    }

    public String getCourseId() {
        return sharedPreferences.getString(PreferencesConstants.COURSE_ID, "");
    }

    public void setCourseFullName(String courseFullName) {
        editor.putString(PreferencesConstants.COURSE_FULL_NAME, courseFullName);
        editor.apply();
    }

    public String getCourseFullName() {
        return sharedPreferences.getString(PreferencesConstants.COURSE_FULL_NAME, "");
    }

    public void setSmId(String smId) {
        editor.putString(PreferencesConstants.SM_ID, smId);
        editor.apply();
    }

    public String getSmId() {
        return sharedPreferences.getString(PreferencesConstants.SM_ID, "");
    }

    public void setSmName(String smName) {
        editor.putString(PreferencesConstants.SM_NAME, smName);
        editor.apply();
    }

    public String getSmName() {
        return sharedPreferences.getString(PreferencesConstants.SM_NAME, "");
    }

    public void setAcId(String acId) {
        editor.putString(PreferencesConstants.AC_ID, acId);
        editor.apply();
    }

    public String getAcId() {
        return sharedPreferences.getString(PreferencesConstants.AC_ID, "");
    }

    public void setAcFullName(String acFullName) {
        editor.putString(PreferencesConstants.AC_FULL_NAME, acFullName);
        editor.apply();
    }

    public String getAcFullName() {
        return sharedPreferences.getString(PreferencesConstants.AC_FULL_NAME, "");
    }

    public void setSwdYearId(String swdYearId) {
        editor.putString(PreferencesConstants.SWD_YEAR_ID, swdYearId);
        editor.apply();
    }

    public String getSwdYearId() {
        return sharedPreferences.getString(PreferencesConstants.SWD_YEAR_ID, "");
    }

    public void setStatus(String status) {
        editor.putString(PreferencesConstants.STATUS, status);
        editor.apply();
    }

    public String getStatus() {
        return sharedPreferences.getString(PreferencesConstants.STATUS, "");
    }

    public void setAcCode(String acCode) {
        editor.putString(PreferencesConstants.AC_CODE, acCode);
        editor.apply();
    }

    public String getAcCode() {
        return sharedPreferences.getString(PreferencesConstants.AC_CODE, "");
    }

    public void setHostelCode(String hostelCode) {
        editor.putString(PreferencesConstants.HOSTEL_CODE, hostelCode);
        editor.apply();
    }

    public String getHostelCode() {
        return sharedPreferences.getString(PreferencesConstants.HOSTEL_CODE, "");
    }

    public void setStudentName(String studentName) {
        editor.putString(PreferencesConstants.STUDENT_NAME, studentName);
        editor.apply();
    }

    public String getStudentName() {
        return sharedPreferences.getString(PreferencesConstants.STUDENT_NAME, "");
    }

    public void setStudAdmissionNo(String studAdmissionNo) {
        editor.putString(PreferencesConstants.STUD_ADMISSION_NO, studAdmissionNo);
        editor.apply();
    }

    public String getStudAdmissionNo() {
        return sharedPreferences.getString(PreferencesConstants.STUD_ADMISSION_NO, "");
    }

    public void setStudentEnrollmentNo(String studentAdmissionNo) {
        editor.putString(PreferencesConstants.STUD_ENROLLMENT_NO, studentAdmissionNo);
        editor.apply();
    }

    public String getStudentEnrollmentNo() {
        return sharedPreferences.getString(PreferencesConstants.STUD_ENROLLMENT_NO, "");
    }

    public void setStudentPhotoUrl(String studentPhotoUrl) {
        editor.putString(PreferencesConstants.STUD_PHOTO, studentPhotoUrl);
        editor.apply();
    }

    public String getStudentPhotoUrl() {
        return sharedPreferences.getString(PreferencesConstants.STUD_PHOTO, "");
    }

    public void setSwdDivisionId(String swdDivisionId) {
        editor.putString(PreferencesConstants.SWD_DIVISION_ID, swdDivisionId);
        editor.apply();
    }

    public String getSwdDivisionId() {
        return sharedPreferences.getString(PreferencesConstants.SWD_DIVISION_ID, "");
    }

    public void setSwdBatchId(String swdBatchId) {
        editor.putString(PreferencesConstants.SWD_BATCH_ID, swdBatchId);
        editor.apply();
    }

    public String getSwdBatchId() {
        return sharedPreferences.getString(PreferencesConstants.SWD_BATCH_ID, "");
    }

    public void setShiftId(String shiftId) {
        editor.putString(PreferencesConstants.SHIFT_ID, shiftId);
        editor.apply();
    }

    public String getShiftId() {
        return sharedPreferences.getString(PreferencesConstants.SHIFT_ID, "");
    }

    public void setImDomainName(String imDomainName) {
        editor.putString(PreferencesConstants.IM_DOMAIN_NAME, imDomainName);
        editor.apply();
    }

    public String getImDomainName() {
        return sharedPreferences.getString(PreferencesConstants.IM_DOMAIN_NAME, "");
    }

    public void setInstituteId(String instituteId) {
        editor.putString(PreferencesConstants.INSTITUTE_ID, instituteId);
        editor.apply();
    }

    public String getInstituteId() {
        return sharedPreferences.getString(PreferencesConstants.INSTITUTE_ID, "");
    }

    public void setFcFile(String fcFile) {
        editor.putString(PreferencesConstants.FC_FILE, fcFile);
        editor.apply();
    }

    public String getFcFile() {
        return sharedPreferences.getString(PreferencesConstants.FC_FILE, "");
    }

    public void setImExamDbName(String imExamDbName) {
        editor.putString(PreferencesConstants.IM_EXAM_DB_NAME, imExamDbName);
        editor.apply();
    }

    public String getImExamDbName() {
        return sharedPreferences.getString(PreferencesConstants.IM_EXAM_DB_NAME, "");
    }


}
