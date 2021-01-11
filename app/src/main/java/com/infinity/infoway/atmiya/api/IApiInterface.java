package com.infinity.infoway.atmiya.api;

import com.infinity.infoway.atmiya.faculty.faculty_attendance.FacultyAttendancePojo;
import com.infinity.infoway.atmiya.faculty.faculty_dashboard.pojo.UpdateFaultyFCMTokenPojo;
import com.infinity.infoway.atmiya.faculty.faculty_leave.FacultyLeavePojo;
import com.infinity.infoway.atmiya.faculty.faculty_lecture_plan.FacultyLecturePlanPojo;
import com.infinity.infoway.atmiya.faculty.faculty_news.FacultyNewsPojo;
import com.infinity.infoway.atmiya.faculty.faculty_pending_attendance.FacultyPendingAttendancePojo;
import com.infinity.infoway.atmiya.faculty.faculty_profile.FacultyProfilePojo;
import com.infinity.infoway.atmiya.faculty.faculty_rem_attendance.FacultyRemAttendancePojo;
import com.infinity.infoway.atmiya.faculty.faculty_teaching_update.details_of_theory_sub.FacultyDetailsOfTheorySubjectTaughtPojo;
import com.infinity.infoway.atmiya.faculty.faculty_teaching_update.faculty_student_forum.FacultyStudentForumPojo;
import com.infinity.infoway.atmiya.faculty.faculty_teaching_update.faculty_subject_wise_division_wise_total_theory_period_engaged.FacultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo;
import com.infinity.infoway.atmiya.faculty.faculty_timetable.pojo.FacultyTimeTablePojo;
import com.infinity.infoway.atmiya.login.pojo.EmployeeLoginPojo;
import com.infinity.infoway.atmiya.student.fee_details.pojo.PayWithPaytmPojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.CheckOTPVerificationForEmployeePojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.CheckOTPVerificationForStudentPojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.InsertForgotPasswordOTPSmsRecordPojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.InsertStudentPasswordAndSMSAbsentApiCall;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.OtpBaseLoginDetailsForEmployeePojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.OtpBaseLoginDetailsForStudentPojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.ResetEmployeePasswordPojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.ResetStudentPasswordPojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.UpdateStudentForgotPasswordOtpPojo;
import com.infinity.infoway.atmiya.student.home_work.pojo.StudentHomeWorkPojo;
import com.infinity.infoway.atmiya.student.news_or_notification.UpdateNotificationStatusPojo;
import com.infinity.infoway.atmiya.student.student_dashboard.pojo.UpdateStudentFCMTokenPojo;
import com.infinity.infoway.atmiya.login.pojo.CheckVersionApiPojo;
import com.infinity.infoway.atmiya.login.pojo.LogOutPojo;
import com.infinity.infoway.atmiya.login.pojo.StudentLoginPojo;
import com.infinity.infoway.atmiya.student.assignment.StudentAssignmentListPojo;
import com.infinity.infoway.atmiya.student.attendance.pojo.StudentLectureWiseAttendancePojo;
import com.infinity.infoway.atmiya.student.attendance.pojo.StudentSubjectWiseAttendancePojo;
import com.infinity.infoway.atmiya.student.e_learning.pojo.CheckIsELearningManagementGroupIsCompulsoryOrNot;
import com.infinity.infoway.atmiya.student.e_learning.pojo.CheckIsLearningManagementGroupIsExistOrNotPojo;
import com.infinity.infoway.atmiya.student.e_learning.pojo.ELearningYearListPojo;
import com.infinity.infoway.atmiya.student.e_learning.pojo.EnrollToGroupPojo;
import com.infinity.infoway.atmiya.student.e_learning.pojo.GroupWiseSubjectlistPojo;
import com.infinity.infoway.atmiya.student.e_learning.pojo.InsertStudentLearningManagementPushNotificationPojo;
import com.infinity.infoway.atmiya.student.e_learning.pojo.JoinGroupListPojo;
import com.infinity.infoway.atmiya.student.e_learning.pojo.LearningManagementGroupDetailsPojo;
import com.infinity.infoway.atmiya.student.e_learning.pojo.StudentWiseLearningGroupPojo;
import com.infinity.infoway.atmiya.student.exam.pojo.CIAExamResultPojo;
import com.infinity.infoway.atmiya.student.exam.pojo.CIAExamSemesterPojo;
import com.infinity.infoway.atmiya.student.exam.pojo.StudentReulstPojo;
import com.infinity.infoway.atmiya.student.exam.pojo.DownloadExaminationSchedulePojo;
import com.infinity.infoway.atmiya.student.exam.pojo.DownloadStudentMidResultPojo;
import com.infinity.infoway.atmiya.student.exam.pojo.ExaminationScheduleDetailsPojo;
import com.infinity.infoway.atmiya.student.exam.pojo.ExaminationScheduleProgramWiseTimetablePojo;
import com.infinity.infoway.atmiya.student.exam.pojo.MidResultPojo;
import com.infinity.infoway.atmiya.student.exam.pojo.StudentCIAMarksPojo;
import com.infinity.infoway.atmiya.student.fee_details.pojo.FeeReceiptPojo;
import com.infinity.infoway.atmiya.student.fee_details.pojo.FeeUrlPojo;
import com.infinity.infoway.atmiya.student.fee_details.pojo.GetAllPendingFeeStudentPojo;
import com.infinity.infoway.atmiya.student.fee_details.pojo.GetPaymentButtonHideShowPojo;
import com.infinity.infoway.atmiya.student.fee_details.pojo.GetPaymentSingleButtonHideShowPojo;
import com.infinity.infoway.atmiya.student.fee_details.pojo.PayFeeTypePojoList;
import com.infinity.infoway.atmiya.student.fee_details.pojo.PaySlipOfAxisPojo;
import com.infinity.infoway.atmiya.student.fee_details.pojo.PrintFeeReceiptPojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.GetForgetPasswordDetailsByStudentEmployeeIdPojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.GetInstituteFromDomainPojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.GetSMSApiForApplicationPojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.GetStudentForgotPasswordDetailsPojo;
import com.infinity.infoway.atmiya.student.holiday.HolidayListPojo;
import com.infinity.infoway.atmiya.student.leave_application.pojo.CheckStudentLeaveExistPojo;
import com.infinity.infoway.atmiya.student.leave_application.pojo.InsertStudentLeavePojo;
import com.infinity.infoway.atmiya.student.leave_application.pojo.KindOfLeaveListPojo;
import com.infinity.infoway.atmiya.student.leave_application.pojo.LeaveApplicationHistoryPojo;
import com.infinity.infoway.atmiya.student.leave_application.pojo.SelectLectureForPartialLeavePojo;
import com.infinity.infoway.atmiya.student.leave_application.pojo.TypeOfFileUploadPojo;
import com.infinity.infoway.atmiya.student.leave_application.pojo.UploadStudentLeaveDocumentPojo;
import com.infinity.infoway.atmiya.student.lesson_plan.StudentLessonPlanListPojo;
import com.infinity.infoway.atmiya.student.message_history.MessageHistoryListPojo;
import com.infinity.infoway.atmiya.student.news_or_notification.FacultyOrStudentNewsOrNotificationsPojo;
import com.infinity.infoway.atmiya.student.profile.StudentProfilePojo;
import com.infinity.infoway.atmiya.student.student_activity.StudentActivityPojo;
import com.infinity.infoway.atmiya.student.student_dashboard.pojo.GetSliderImageUrlsPojo;
import com.infinity.infoway.atmiya.student.student_syllabus.SyllabusListPojo;
import com.infinity.infoway.atmiya.student.student_timetable.pojo.StudentTimeTablePojo;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;

public interface IApiInterface {

    //TODO below API are for student side modules

    @GET("student_login_check_api")
    Call<StudentLoginPojo> checkStudentLogin(@QueryMap Map<String, String> params);

    @GET("get_student_profile_detail_atmiya")
    Call<StudentProfilePojo> getStudentProfile(@QueryMap Map<String, String> params);

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
    Call<ArrayList<StudentReulstPojo>> getStuedntResultList(@Query("enrollement_no") String enrollement_no);

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

    @FormUrlEncoded
    @POST("Upload_Student_Leave_Document")
    Call<UploadStudentLeaveDocumentPojo> uploadStudentLeaveDocument(@Field("name") String name,
                                                                    @Field("image") String image,
                                                                    @Field("stud_leave_id") String stud_leave_id);

    @GET("get_student_leave_application_data_API")
    Call<LeaveApplicationHistoryPojo> getStudentLeaveApplicationHistory(@Query("stud_id") String stud_id,
                                                                        @Query("year_id") String year_id);

    @GET("Get_Student_Wise_Division_Wise_Student_Activity_API")
    Call<ArrayList<StudentActivityPojo>> getStudentActivityList(@Query("stud_id") String stud_id,
                                                                @Query("url") String url);

    @GET("Get_Year_Master_At_Learning_Managemnt_API")
    Call<ELearningYearListPojo> getELearningYear(@Query("institute_id") String institute_id);

    @GET("Get_Student_Wise_Learning_Management_Group_API")
    Call<StudentWiseLearningGroupPojo> getStudentWiseLearningGroup(@Query("stud_id") String stud_id);

    @GET("Check_Learning_Management_Group_is_Compulsory_or_Not_API")
    Call<ArrayList<CheckIsELearningManagementGroupIsCompulsoryOrNot>> checkIsELearningGroupIsCompulsoryOrNot(@Query("grp_id") String grp_id);

    @GET("Get_Learning_Management_Groups_For_Students_API")
    Call<ArrayList<JoinGroupListPojo>> getJoinLearningManagementGroups(@Query("stud_id") String stud_id,
                                                                       @Query("year_id") String year_id);

    @GET("Get_Group_Wise_Learning_Management_Subject_Details_API")
    Call<ArrayList<GroupWiseSubjectlistPojo>> groupWiseLearningManagementSubjectList(@Query("stud_id") String stud_id,
                                                                                     @Query("sem_id") String sem_id,
                                                                                     @Query("year_id") String year_id,
                                                                                     @Query("grp_id") String grp_id);

    @GET("Get_Learning_Management_Group_Details_Group_Wise_New_API")
    Call<ArrayList<LearningManagementGroupDetailsPojo>> groupWiseLearningManagementSubjectList(@Query("grp_id") String grp_id,
                                                                                               @Query("stud_id") String stud_id,
                                                                                               @Query("sem_id") String sem_id,
                                                                                               @Query("year_id") String year_id,
                                                                                               @Query("from_date") String from_date,
                                                                                               @Query("to_date") String to_date,
                                                                                               @Query("sub_id") String sub_id);

    @GET("Check_Exist_Learning_Mangement_Group_Wise_Student_Master_API")
    Call<CheckIsLearningManagementGroupIsExistOrNotPojo> checkIsLearningManagementGroupIsExistOrNot(@Query("grp_id") String grp_id,
                                                                                                    @Query("stud_id") String stud_id);


    @GET("Insert_Student_To_Learning_Management_Group_Wise_Student_Master_API")
    Call<EnrollToGroupPojo> enrollToGroup(@Query("grp_id") String grp_id,
                                          @Query("stud_id") String stud_id,
                                          @Query("created_by") String created_by,
                                          @Query("created_ip") String created_ip,
                                          @Query("lm_grpws_is_enroll_status") String lm_grpws_is_enroll_status);


    @GET("Insert_Student_Learning_Management_Push_Notification_API")
    Call<InsertStudentLearningManagementPushNotificationPojo> insertStudentLearningManagementPushNotification(
            @Query("grp_id") String grp_id,
            @Query("year_id") String year_id,
            @Query("stud_id") String stud_id,
            @Query("sem_id") String sem_id,
            @Query("nt_type") String nt_type,
            @Query("notif_desc") String notif_desc,
            @Query("read_status") String read_status,
            @Query("created_by") String created_by,
            @Query("created_ip") String created_ip,
            @Query("institute_id") String institute_id);

    @GET("Get_Student_Wise_Holidays_Detail_API")
    Call<ArrayList<HolidayListPojo>> getStudentHolidayList(
            @Query("stud_id") String stud_id);


    @GET("get_student_timetable_api_lopgin")
    Call<ArrayList<StudentTimeTablePojo>> getStudentTimetable(
            @Query("stud_id") String stud_id,
            @Query("course_id") String course_id,
            @Query("div_id") String div_id,
            @Query("shift_id") String shift_id,
            @Query("batch_id") String batch_id,
            @Query("year_id") String year_id);


    @GET("Get_Student_Current_sem_syllabus")
    Call<ArrayList<SyllabusListPojo>> getStudentSyllabusList(
            @Query("stud_id") String stud_id);

    @GET("Student_Lesson_Planning_API")
    Call<ArrayList<StudentLessonPlanListPojo>> getStudentLessonPlanList(
            @Query("stud_id") String stud_id);

    @GET("GetPendingFeeType")
    Call<ArrayList<PayFeeTypePojoList>> getPendingFeeType(
            @Query("StudId") String StudId,
            @Query("YearId") String YearId,
            @Query("AdmissionNo") String AdmissionNo,
            @Query("CompanyCode") String CompanyCode,
            @Query("hostel_code") String hostel_code);

    @GET("GetPendingFeeStudentURL")
    Call<FeeUrlPojo> feeUrlPojo(
            @Query("Ord_stud_id") String Ord_stud_id,
            @Query("Ord_year_id") String Ord_year_id,
            @Query("Ord_Exam_id") String Ord_Exam_id,
            @Query("Ord_sem_id") String Ord_sem_id,
            @Query("Ord_Fee_Source") String Ord_Fee_Source,
            @Query("Ord_Fee_Type") String Ord_Fee_Type,
            @Query("Ord_created_ip") String Ord_created_ip);

    @GET("GetPaymentButtonHideShow")
    Call<ArrayList<GetPaymentButtonHideShowPojo>> getPaymentButtonHideShow(
            @Query("StudId") String StudId,
            @Query("FeeType") String FeeType);

    @GET("GetPaymentSingleButtonHideShow")
    Call<ArrayList<GetPaymentSingleButtonHideShowPojo>> getPaymentSingleButtonHideShow();

    @GET("GetAllPendingFeeStudent")
    Call<ArrayList<GetAllPendingFeeStudentPojo>> getAllPendingFeeStudent(
            @Query("StudId") String StudId,
            @Query("YearId") String YearId,
            @Query("AdmissionNo") String AdmissionNo,
            @Query("ClassName") String ClassName,
            @Query("CompanyCode") String CompanyCode,
            @Query("FeeType") String FeeType,
            @Query("hostel_code") String hostel_code);


    @GET("Get_Student_CIA_Marks")
    Call<ArrayList<StudentCIAMarksPojo>> getStudentCIAMarks(
            @Query("stud_id") String stud_id,
            @Query("sem_id") String sem_id,
            @Query("exam_type") String exam_type);

    @GET("Get_Semester_By_Course_Marks_Display")
    Call<CIAExamSemesterPojo> getCIAExamSemester(
            @Query("course_id") String course_id,
            @Query("sem_id") String sem_id,
            @Query("dept_id") String dept_id);

    @GET("get_result_publish_configuration_semester_wise_for_api")
    Call<ArrayList<MidResultPojo>> getStudentMidResult(
            @Query("institute_id") String institute_id,
            @Query("dept_id") String dept_id,
            @Query("course_id") String course_id,
            @Query("sem_id") String sem_id,
            @Query("stud_id") String stud_id,
            @Query("srpc_id") String srpc_id);

    @GET("get_stud_publish_result_download")
    Call<DownloadStudentMidResultPojo> downloadStudentMidResult(
            @Query("sem_id") String sem_id,
            @Query("srpc_id") String srpc_id,
            @Query("stud_id") String stud_id,
            @Query("dept_id") String dept_id,
            @Query("course_id") String course_id);


    @GET("Get_Institute_From_Domain_API")
    Call<GetInstituteFromDomainPojo> getInstituteFromDomain(
            @Query("domain_name") String domain_name);

    @GET("get_student_forget_password_detail")
    Call<ArrayList<GetStudentForgotPasswordDetailsPojo>> getStudentForgetPassDeail(@Query("email") String email);

    @GET("get_forget_password_detail_by_Student_Employee_ID")
    Call<ArrayList<GetForgetPasswordDetailsByStudentEmployeeIdPojo>> getForgotPasswordDetailsByStudentOrEmpId(
            @Query("emp_stud_id") String emp_stud_id,
            @Query("emp_stud_status") String emp_stud_status,
            @Query("institute_id") String institute_id);

    @GET("GET_SMS_API_For_Application")
    Call<ArrayList<GetSMSApiForApplicationPojo>> getSMSApiForApplication(
            @Query("institute_id") String institute_id);

    @GET("Update_Isrp_Student_FCM_Id")
    Call<UpdateStudentFCMTokenPojo> updateStudentFcmToken(
            @Query("stud_id") String stud_id,
            @Query("FCM_ID") String FCM_ID,
            @Query(value = "KEY", encoded = true) String KEY);

    @GET("get_otp_base_login_details_for_emp_API")
    Call<OtpBaseLoginDetailsForEmployeePojo> getOTPBaseLoginDetailsForEmployee(
            @Query("uname_mobile") String uname_mobile,
            @Query("institute_id") String institute_id);

    @GET("get_otp_base_login_details_for_stud_API")
    Call<OtpBaseLoginDetailsForStudentPojo> getOTPBaseLoginDetailsForStudent(
            @Query("uname_mobile") String uname_mobile,
            @Query("institute_id") String institute_id);

    @GET("insert_stduent_password_send_sms_absent_API")
    Call<InsertStudentPasswordAndSMSAbsentApiCall> insertStudentPasswordAndSendSmsAbsent(
            @Query("sms") String sms);

    @GET("Insert_Forget_Password_OTP_SMS_Record_API")
    Call<InsertForgotPasswordOTPSmsRecordPojo> insertForgotPasswordOTPSmsRecordApi(
            @Query("user_id") String user_id,
            @Query("is_emp") String is_emp,
            @Query("is_stud") String is_stud,
            @Query("mobile_no") String mobile_no,
            @Query("otp_msg") String otp_msg,
            @Query("created_ip") String created_ip,
            @Query("created_by") String created_by);

    @GET("Update_Stud_Emp_Forget_Password_OTP_API")
    Call<UpdateStudentForgotPasswordOtpPojo> updateStudentEmpForgotPasswordOTP(
            @Query("user_id") String user_id,
            @Query("is_emp") String is_emp,
            @Query("is_stud") String is_stud,
            @Query("otp") String otp,
            @Query("modify_ip") String modify_ip,
            @Query("modify_by") String modify_by);

    @GET("check_otp_base_login_with_emp_uname_API")
    Call<CheckOTPVerificationForEmployeePojo> checkOTPVerificationForEmployee(
            @Query("username") String username,
            @Query("otp") String otp,
            @Query("institute_id") String institute_id,
            @Query("ip_addr") String ip_addr);

    @GET("check_otp_base_login_with_stud_uname_API")
    Call<CheckOTPVerificationForStudentPojo> checkOTPVerificationForStudent(
            @Query("username") String username,
            @Query("otp") String otp,
            @Query("institute_id") String institute_id,
            @Query("ip_addr") String ip_addr);

    @GET("Reset_Student_Password_API")
    Call<ResetStudentPasswordPojo> resetStudentPassword(
            @Query("stud_id") String stud_id,
            @Query("institute_id") String institute_id,
            @Query("password") String password,
            @Query("modify_by") String modify_by,
            @Query("modify_ip") String modify_ip);

    @GET("Reset_Employee_Password_API")
    Call<ResetEmployeePasswordPojo> resetEmployeePassword(
            @Query("emp_id") String emp_id,
            @Query("institute_id") String institute_id,
            @Query("password") String password,
            @Query("modify_by") String modify_by,
            @Query("modify_ip") String modify_ip);

    @GET("get_homework_and_content_delivered_API")
    Call<ArrayList<StudentHomeWorkPojo>> getStudentHomeWork(
            @Query("stud_id") String stud_id,
            @Query("year_id") String year_id,
            @Query("div_id") String div_id);

    @GET("Paynow_PAYTM")
    Call<PayWithPaytmPojo> payWithPaytm(
            @Query("SchoolId") String SchoolId,
            @Query("AdmissionNo") String AdmissionNo);

    //TODO Below API are for employee side modules

    @GET("login_user_new_api")
    Call<EmployeeLoginPojo> employeeLoginCheck(
            @Query("username") String username,
            @Query("password") String password);

    @GET("Update_Isrp_employee_FCM_Id")
    Call<UpdateFaultyFCMTokenPojo> updateFacultyFcmToken(
            @Query("emp_id") String emp_id,
            @Query("FCM_ID") String FCM_ID,
            @Query(value = "KEY", encoded = true) String KEY);

    @GET("employee_profile_api")
    Call<ArrayList<FacultyProfilePojo>> getFacultyProfileDetails(
            @Query("emp_id") String emp_id);


    @GET("Get_Employee_Timetable_Display_with_Merge_Lecture")
    Call<ArrayList<FacultyTimeTablePojo>> getFacultyTimeTable(
            @Query("emp_id") String emp_id,
            @Query("year_id") String year_id);


    //Common For Both Student And Employee(Faculty)

    @GET("Get_User_Wise_Announcement_Notification_API_with_Count")
    @Streaming
    Call<FacultyOrStudentNewsOrNotificationsPojo> getStudentNewsOrNotifications(
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

    @GET("Get_Image_URL")
    Call<GetSliderImageUrlsPojo> getSliderImages(@Query("url") String url, @Query("institute_id") String institute_id);

    @GET("Logout_and_Clear_FCM_id_of_User")
    Call<ArrayList<LogOutPojo>> logoutUser(@Query("login_user_type") int login_user_type,
                                           @Query("user_id") String user_id);

    @GET("check_version_api")
    Call<CheckVersionApiPojo> checkVersionInfo(@Query("version") int version);

    @GET("Update_Student_Employee_Wise_Notification_Status_API")
    Call<UpdateNotificationStatusPojo> updateStudentOrEmployeeNotificationStatus(
            @Query("notif_for") String notif_for,
            @Query("nt_id") String nt_id,
            @Query("modify_by") String modify_by,
            @Query("modify_ip") String modify_ip);

    @POST("Get_Leave_Balance")
    @Streaming
    Call<ArrayList<FacultyLeavePojo>> getFacultyLeave(@Query("KEY") String KEY, @Query("Contact_ID") String Contact_ID, @Query("Year") int year);

    @GET("Remaining_attendance_API")
    Call<ArrayList<FacultyRemAttendancePojo>> getFacultyRemAttendance(@Query("emp_id") String emp_id);

    @GET("Get_Emp_Wise_Lecture_Planning_Api")
    Call<ArrayList<FacultyLecturePlanPojo>> getEmployeeWiseLecturePlanning(@Query("emp_id") String emp_id);

    @GET("Get_Campus_News_Detail_API")
    Call<ArrayList<FacultyNewsPojo>> getFacultyNewsDetails(@Query("institute_id") String institute_id);

    @GET("Get_Punch_Data")
    Call<ArrayList<FacultyAttendancePojo>> getFacultyAttendance(
            @Query("KEY") String KEY,
            @Query("Contact_ID") String Contact_ID,
            @Query("Company_ID") String Company_ID,
            @Query("FromDate") String FromDate,
            @Query("ToDate") String ToDate);

    @GET("Get_employee_allocated_subject_details_by_id_API")
    @Streaming
    Call<ArrayList<FacultyDetailsOfTheorySubjectTaughtPojo>> getEmployeeAllocatedSubjectDetailsByIdApi(@Query("emp_id") String emp_id,
                                                                                                       @Query("RowsPerPage") int RowsPerPage,
                                                                                                       @Query("PageNumber") int PageNumber);


    @GET("Get_SubjectWise_DivisionWise_Total_Theory_Period_Engaged_API")
    @Streaming
    Call<ArrayList<FacultySubjectAndDivisionWiseTotalTheoryPeriodEngagedPojo>> GetSubjectWiseDivisionWiseTotalTheoryPeriodEngagedAPI(@Query("emp_id") String emp_id,
                                                                                                                                     @Query("year_id") String year_id, @Query("RowsPerPage") String RowsPerPage, @Query("PageNumber") String PageNumber);


    @GET("Get_Student_Forum_Activity_Faculty_Wise_API")
    @Streaming
    Call<ArrayList<FacultyStudentForumPojo>> GetStudentForumActivityFacultyWiseAPI(@Query("emp_id") String emp_id,
                                                                                   @Query("year_id") String year_id, @Query("institute_id") String institute_id, @Query("RowsPerPage") String RowsPerPage, @Query("PageNumber") String PageNumber);


    @GET("Get_Pending_Attendance_Detail_Employee_Wise_API")
    @Streaming
    Call<FacultyPendingAttendancePojo> getFacultyPendingAttendance(@Query("emp_id") String emp_id,
                                                                              @Query("year_id") String year_id);

}
