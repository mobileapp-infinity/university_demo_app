package com.infinity.infoway.atmiya.api;

import com.infinity.infoway.atmiya.login.pojo.CheckVersionApiPojo;
import com.infinity.infoway.atmiya.login.pojo.LogOutPojo;
import com.infinity.infoway.atmiya.login.pojo.StudentLoginPojo;
import com.infinity.infoway.atmiya.student.assignment.StudentAssignmentListPojo;
import com.infinity.infoway.atmiya.student.attendance.pojo.StudentLectureWiseAttendancePojo;
import com.infinity.infoway.atmiya.student.attendance.pojo.StudentSubjectWiseAttendancePojo;
import com.infinity.infoway.atmiya.student.exam.pojo.CIAExamResultPojo;
import com.infinity.infoway.atmiya.student.exam.pojo.CIAMarkstPojo;
import com.infinity.infoway.atmiya.student.exam.pojo.DownloadExaminationSchedulePojo;
import com.infinity.infoway.atmiya.student.exam.pojo.ExaminationScheduleDetailsPojo;
import com.infinity.infoway.atmiya.student.exam.pojo.ExaminationScheduleProgramWiseTimetablePojo;
import com.infinity.infoway.atmiya.student.fee_details.pojo.FeeReceiptPojo;
import com.infinity.infoway.atmiya.student.fee_details.pojo.PaySlipOfAxisPojo;
import com.infinity.infoway.atmiya.student.fee_details.pojo.PrintFeeReceiptPojo;
import com.infinity.infoway.atmiya.student.message_history.MessageHistoryListPojo;
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

    @GET("Logout_and_Clear_FCM_id_of_User")
    Call<ArrayList<LogOutPojo>> logoutUser(@Query("login_user_type") int login_user_type,
                                           @Query("user_id") String user_id);

    @GET("check_version_api")
    Call<CheckVersionApiPojo> checkVersionInfo(@Query("version") int version);

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


    @GET("Fee_Receipt_List")
    Call<ArrayList<FeeReceiptPojo>> getFeeReceipt(@Query("stud_id") String stud_id);

    @GET("Print_Fee_Receipt")
    @Streaming
    Call<PrintFeeReceiptPojo> downloadFeeReceipt(@Query("stud_id") String stud_id, @Query("Receipt_no") String receipt_no);

    @GET("Get_Student_All_Exam_Detail_By_Enrollment_no")
    Call<ArrayList<CIAMarkstPojo>> getStuedntCIAMarksList(@Query("enrollement_no") String enrollement_no);

    @GET("Download_Student_Exam_Result")
    @Streaming
    Call<CIAExamResultPojo> downloadCIAExamResult(@Query("Collegeid") String Collegeid, @Query("Yearid") String Yearid, @Query("Semid") String Semid,
                                                  @Query("Exam_id") String Exam_id, @Query("stud_id") String stud_id);


    @GET("Get_Exam_Details_by_stud_id_API")
    Call<ExaminationScheduleDetailsPojo> getExaminationScheduleDetails(@Query("stud_id") String stud_id,
                                                                       @Query("exam_db") String exam_db);

    @GET("Get_Program_wise_timetable_by_stud_id_API")
    Call<ExaminationScheduleProgramWiseTimetablePojo> getExaminationScheduleProgramWiseTimetable(@Query("sem_id") String sem_id,
                                                                                                 @Query("year_id") String year_id,
                                                                                                 @Query("stud_id") String stud_id,
                                                                                                 @Query("repeater_status") String repeater_status,
                                                                                                 @Query("exam_db") String exam_db);

    @GET("Download_AU_Examination_Schedule")
    Call<DownloadExaminationSchedulePojo> downloadExaminationSchedule(@Query("sem_id") String sem_id,
                                                                      @Query("year_id") String year_id,
                                                                      @Query("stud_id") String stud_id,
                                                                      @Query("repeater_status") String repeater_status,
                                                                      @Query("exam_db") String exam_db);


    @GET("Print_Axis_Payslip")
    @Streaming
    Call<PaySlipOfAxisPojo> downloadPaySlipOfAxis(@Query("stud_id") String stud_id);

    @GET("Get_Student_Assignment_Detail")
    @Streaming
    Call<ArrayList<StudentAssignmentListPojo>> getStudentAssignmentList(@Query("stud_id") String stud_id, @Query("year_id") String year_id);


    @GET("get_student_sms_send_detail_with_content")
    @Streaming
    Call<ArrayList<MessageHistoryListPojo>> getStudentMessageHistory(@Query("stud_id") String stud_id, @Query("year_id") String year_id);


}
