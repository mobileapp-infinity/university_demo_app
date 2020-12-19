package com.infinity.infoway.atmiya.api;

import com.infinity.infoway.atmiya.login.pojo.CheckVersionApiPojo;
import com.infinity.infoway.atmiya.login.pojo.LogOutPojo;
import com.infinity.infoway.atmiya.login.pojo.StudentLoginPojo;
import com.infinity.infoway.atmiya.student.attendance.pojo.StudentLectureWiseAttendancePojo;
import com.infinity.infoway.atmiya.student.attendance.pojo.StudentSubjectWiseAttendancePojo;
import com.infinity.infoway.atmiya.student.news_or_notificaions.StudentNewsOrNotificationsPojo;
import com.infinity.infoway.atmiya.student.profile.StudentProfilePojo;
import com.infinity.infoway.atmiya.student.student_dashboard.pojo.GetSliderImageUrlsPojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class ApiImplementer {

    public static void logoutUserApiImplementer(int loginUserType, String userId, Callback<ArrayList<LogOutPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<LogOutPojo>> call = apiService.logoutUser(loginUserType, userId);
        call.enqueue(cb);
    }

    public static void checkVersionInfoApiImplementer(int appVersionCode, Callback<CheckVersionApiPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<CheckVersionApiPojo> call = apiService.checkVersionInfo(appVersionCode);
        call.enqueue(cb);
    }

    public static void checkStudentLoginApiImplementer(HashMap<String, String> param, Callback<StudentLoginPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<StudentLoginPojo> call = apiService.checkStudentLogin(param);
        call.enqueue(cb);
    }

    public static void getSliderImagesApiImplementer(String url, String instituteId, Callback<GetSliderImageUrlsPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<GetSliderImageUrlsPojo> call = apiService.getSliderImages(url, instituteId);
        call.enqueue(cb);
    }

    public static void getStudentProfileImplementer(HashMap<String, String> map, Callback<StudentProfilePojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<StudentProfilePojo> call = apiService.getStudentProfile(map);
        call.enqueue(cb);
    }

    public static void getStudentNewsOrNotificationImplementer(String notif_for, String user_status, String user_id, String college_id,
                                                               String dept_id, String course_id, String sem_id, String institute_id,
                                                               String year_id, String notification_count, Callback<StudentNewsOrNotificationsPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<StudentNewsOrNotificationsPojo> call = apiService.getStudentNewsOrNotifications(notif_for, user_status, user_id, college_id, dept_id,
                course_id, sem_id, institute_id, year_id, notification_count);
        call.enqueue(cb);
    }

    public static void getStudentLectureWiseAttendanceApiImplementer(Map<String, String> params, Callback<ArrayList<StudentLectureWiseAttendancePojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<StudentLectureWiseAttendancePojo>> call = apiService.getStudentLectureWiseAttendance(params);
        call.enqueue(cb);
    }

    public static void getStudentSubjectWiseAttendanceApiImplementer(Map<String, String> params, Callback<ArrayList<StudentSubjectWiseAttendancePojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<StudentSubjectWiseAttendancePojo>> call = apiService.getStudentSubjectWiseAttendance(params);
        call.enqueue(cb);
    }

}
