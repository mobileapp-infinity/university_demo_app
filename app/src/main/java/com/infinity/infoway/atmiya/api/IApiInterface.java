package com.infinity.infoway.atmiya.api;

import com.infinity.infoway.atmiya.login.pojo.StudentLoginPojo;
import com.infinity.infoway.atmiya.student.attendance.pojo.StudentLectureWiseAttendancePojo;
import com.infinity.infoway.atmiya.student.attendance.pojo.StudentSubjectWiseAttendancePojo;
import com.infinity.infoway.atmiya.student.news_or_notificaions.StudentNewsOrNotificationsPojo;
import com.infinity.infoway.atmiya.student.profile.StudentProfilePojo;
import com.infinity.infoway.atmiya.student.student_dashboard.pojo.GetSliderImageUrlsPojo;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;

public interface IApiInterface {

    @GET("student_login_check_api")
    Call<StudentLoginPojo> checkStudentLogin(@QueryMap Map<String, String> params);

    @GET("Get_Image_URL")
    Call<GetSliderImageUrlsPojo> getSliderImages(@Query("url") String url, @Query("institute_id") String institute_id);


    @GET("get_student_profile_detail_atmiya")
    Call<StudentProfilePojo> getStudentProfile(@QueryMap Map<String, String> params);

    @GET("Get_User_Wise_Announcement_Notification_API_with_Count")
    @Streaming
    Call<StudentNewsOrNotificationsPojo> getStudentNewsOrNotifications(
            @Query("notif_for") String notif_for,
            @Query("user_status") String user_status,
            @Query("user_id") String user_id,
            @Query("college_id") String college_id,
            @Query("dept_id") String dept_id,
            @Query("course_id") String course_id,
            @Query("sem_id") String sem_id,
            @Query("institute_id") String institute_id,
            @Query("year_id") String year_id,
            @Query("notif_count") String notification_count);

    @GET("get_student_attendance_api")
    Call<ArrayList<StudentLectureWiseAttendancePojo>> getStudentLectureWiseAttendance(@QueryMap Map<String, String> params);

    @GET("get_subject_name_to_display_attendance_in_student_profile")
    @Streaming
    Call<ArrayList<StudentSubjectWiseAttendancePojo>> getStudentSubjectWiseAttendance(@QueryMap Map<String, String> params);



}
