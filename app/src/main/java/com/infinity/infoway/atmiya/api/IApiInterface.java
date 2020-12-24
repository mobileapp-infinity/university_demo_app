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
import com.infinity.infoway.atmiya.student.leave_application.pojo.CheckStudentLeaveExistPojo;
import com.infinity.infoway.atmiya.student.leave_application.pojo.InsertStudentLeavePojo;
import com.infinity.infoway.atmiya.student.leave_application.pojo.KindOfLeaveListPojo;
import com.infinity.infoway.atmiya.student.leave_application.pojo.LeaveApplicationHistoryPojo;
import com.infinity.infoway.atmiya.student.leave_application.pojo.SelectLectureForPartialLeavePojo;
import com.infinity.infoway.atmiya.student.leave_application.pojo.TypeOfFileUploadPojo;
import com.infinity.infoway.atmiya.student.leave_application.pojo.UploadStudentLeaveDocumentPojo;
import com.infinity.infoway.atmiya.student.message_history.MessageHistoryListPojo;
import com.infinity.infoway.atmiya.student.news_or_notificaions.StudentNewsOrNotificationsPojo;
import com.infinity.infoway.atmiya.student.profile.StudentProfilePojo;
import com.infinity.infoway.atmiya.student.student_activity.StudentActivityPojo;
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


    @GET("get_leave_type_institute_wise_for_student")
    Call<KindOfLeaveListPojo> getKindOfLeaveList(@Query("institute_id") String institute_id);

    @GET("get_leave_type_file_upload")
    Call<TypeOfFileUploadPojo> getTypeOfLeaveFileUpload(@Query("leave_type_id") String leave_type_id);

    @GET("Get_Date_Wise_Lecture_List_for_student_leave")
    Call<SelectLectureForPartialLeavePojo> selectLectureForPartialLeaveList(@Query("institute_id") String institute_id, @Query("sem_id") String sem_id,
                                                                            @Query("div_id") String div_id, @Query("stud_id") String stud_id,
                                                                            @Query("from_date") String from_date, @Query("year_id") String year_id);

    @GET("Check_Student_Leave_Exist_API")
    Call<CheckStudentLeaveExistPojo> checkStudentLeaveExistOrNot(@Query("stud_id") String stud_id, @Query("year_id") String year_id,
                                                                 @Query("leave_from_date") String leave_from_date, @Query("leave_to_date") String leave_to_date,
                                                                 @Query("leave_day_type") String leave_day_type, @Query("lect_no") String lect_no);


    @GET("insert_student_leave_API")
    Call<InsertStudentLeavePojo> insertStudentLeave(@Query("student_id") String student_id, @Query("leave_type_id") String leave_type_id,
                                                    @Query("from_date") String from_date, @Query("to_date") String to_date,
                                                    @Query("leave_day_type") String leave_day_type, @Query("lecture_date") String lecture_date,
                                                    @Query("lecture_no") String lecture_no, @Query("Remarks") String Remarks,
                                                    @Query("year_id") String year_id, @Query("created_by") String created_by,
                                                    @Query("created_ip") String created_ip, @Query("leave_taken_by") String leave_taken_by);

    @GET("Upload_Student_Leave_Document")
    Call<UploadStudentLeaveDocumentPojo> uploadStudentLeaveDocument(@Query("name") String name,
                                                                    @Query("image") String image,
                                                                    @Query("stud_leave_id") String stud_leave_id);

    @GET("get_student_leave_application_data_API")
    Call<LeaveApplicationHistoryPojo> getStudentLeaveApplicationHistory(@Query("stud_id") String stud_id,
                                                                        @Query("year_id") String year_id);

    @GET("Get_Student_Wise_Division_Wise_Student_Activity_API")
    Call<ArrayList<StudentActivityPojo>> getStudentActivityList(@Query("stud_id") String stud_id,
                                                                @Query("url") String url);

}
