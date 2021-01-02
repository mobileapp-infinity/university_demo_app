package com.infinity.infoway.atmiya.api;

import com.infinity.infoway.atmiya.student.forgot_password.pojo.CheckOTPVerificationForEmployeePojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.CheckOTPVerificationForStudentPojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.InsertForgotPasswordOTPSmsRecordPojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.InsertStudentPasswordAndSMSAbsentApiCall;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.OtpBaseLoginDetailsForEmployeePojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.OtpBaseLoginDetailsForStudentPojo;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.UpdateStudentForgotPasswordOtpPojo;
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
import com.infinity.infoway.atmiya.student.exam.pojo.CIAMarkstPojo;
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
import com.infinity.infoway.atmiya.student.news_or_notification.StudentNewsOrNotificationsPojo;
import com.infinity.infoway.atmiya.student.profile.StudentProfilePojo;
import com.infinity.infoway.atmiya.student.student_activity.StudentActivityPojo;
import com.infinity.infoway.atmiya.student.student_dashboard.pojo.GetSliderImageUrlsPojo;
import com.infinity.infoway.atmiya.student.student_syllabus.SyllabusListPojo;
import com.infinity.infoway.atmiya.student.student_timetable.pojo.StudentTimeTablePojo;

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

    public static void getFeeReceiptApiImplementer(String stud_id, Callback<ArrayList<FeeReceiptPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<FeeReceiptPojo>> call = apiService.getFeeReceipt(stud_id);
        call.enqueue(cb);
    }

    public static void downloadFeeReceiptApiImplementer(String stud_id, String Receipt_no, Callback<PrintFeeReceiptPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<PrintFeeReceiptPojo> call = apiService.downloadFeeReceipt(stud_id, Receipt_no);
        call.enqueue(cb);
    }


    public static void getCIAMarksListApiImplementer(String enrollement_no, Callback<ArrayList<CIAMarkstPojo>> cb) {
        final IApiInterface apiService = ApiClientForCIAMarks.getClient().create(IApiInterface.class);
        Call<ArrayList<CIAMarkstPojo>> call = apiService.getStuedntCIAMarksList(enrollement_no);
        call.enqueue(cb);
    }

    public static void downloadCIAExamResultApiImplementer(String Collegeid, String Yearid, String Semid, String Exam_id, String stud_id,
                                                           Callback<CIAExamResultPojo> cb) {
        final IApiInterface apiService = ApiClientForCIAMarks.getClient().create(IApiInterface.class);
        Call<CIAExamResultPojo> call = apiService.downloadCIAExamResult(Collegeid, Yearid, Semid, Exam_id, stud_id);
        call.enqueue(cb);
    }

    public static void examinationScheduleDetailsApiImplementer(String stud_id, String exam_db,
                                                                Callback<ExaminationScheduleDetailsPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ExaminationScheduleDetailsPojo> call = apiService.getExaminationScheduleDetails(stud_id, exam_db);
        call.enqueue(cb);
    }

    public static void examinationScheduleProgramWiseTimetableApiImplementer(String sem_id, String year_id, String stud_id, String repeater_status, String exam_db,
                                                                             Callback<ExaminationScheduleProgramWiseTimetablePojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ExaminationScheduleProgramWiseTimetablePojo> call = apiService.getExaminationScheduleProgramWiseTimetable(sem_id, year_id, stud_id, repeater_status, exam_db);
        call.enqueue(cb);
    }

    public static void downloadExaminationScheduleApiImplementer(String sem_id, String year_id, String stud_id, String repeater_status, String exam_db,
                                                                 Callback<DownloadExaminationSchedulePojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<DownloadExaminationSchedulePojo> call = apiService.downloadExaminationSchedule(sem_id, year_id, stud_id, repeater_status, exam_db);
        call.enqueue(cb);
    }

    public static void downloadPaySlipOfAxisApiImplementer(String stud_id,
                                                           Callback<PaySlipOfAxisPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<PaySlipOfAxisPojo> call = apiService.downloadPaySlipOfAxis(stud_id);
        call.enqueue(cb);
    }

    public static void getStudentAssignmentListApiImplementer(String stud_id, String year_id,
                                                              Callback<ArrayList<StudentAssignmentListPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<StudentAssignmentListPojo>> call = apiService.getStudentAssignmentList(stud_id, year_id);
        call.enqueue(cb);
    }

    public static void getStudentMessageHistoryListApiImplementer(String stud_id, String year_id,
                                                                  Callback<ArrayList<MessageHistoryListPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<MessageHistoryListPojo>> call = apiService.getStudentMessageHistory(stud_id, year_id);
        call.enqueue(cb);
    }


    public static void getKindOfLeaveListApiImplementer(String institute_id,
                                                        Callback<KindOfLeaveListPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<KindOfLeaveListPojo> call = apiService.getKindOfLeaveList(institute_id);
        call.enqueue(cb);
    }

    public static void getTypeOfFileUploadApiImplementer(String leave_type_id,
                                                         Callback<TypeOfFileUploadPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<TypeOfFileUploadPojo> call = apiService.getTypeOfLeaveFileUpload(leave_type_id);
        call.enqueue(cb);
    }

    public static void selectLectureListApiImplementer(String institute_id,
                                                       String sem_id,
                                                       String div_id,
                                                       String stud_id,
                                                       String from_date,
                                                       String year_id,
                                                       Callback<SelectLectureForPartialLeavePojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<SelectLectureForPartialLeavePojo> call = apiService.selectLectureForPartialLeaveList(institute_id, sem_id, div_id, stud_id, from_date, year_id);
        call.enqueue(cb);
    }


    public static void checkStudentLeaveExisOrNotApiImplementer(String stud_id,
                                                                String year_id,
                                                                String leave_from_date,
                                                                String leave_to_date,
                                                                String leave_day_type,
                                                                String lect_no,
                                                                Callback<CheckStudentLeaveExistPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<CheckStudentLeaveExistPojo> call = apiService.checkStudentLeaveExistOrNot(stud_id, year_id, leave_from_date, leave_to_date, leave_day_type, lect_no);
        call.enqueue(cb);
    }

    public static void insertStudentLeaveApiImplementer(String student_id,
                                                        String leave_type_id,
                                                        String from_date,
                                                        String to_date,
                                                        String leave_day_type,
                                                        String lecture_date,
                                                        String lecture_no,
                                                        String Remarks,
                                                        String year_id,
                                                        String created_by,
                                                        String created_ip,
                                                        String leave_taken_by,
                                                        Callback<InsertStudentLeavePojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<InsertStudentLeavePojo> call = apiService.insertStudentLeave(student_id, leave_type_id, from_date, to_date, leave_day_type,
                lecture_date, lecture_no, Remarks, year_id, created_by, created_ip, leave_taken_by);
        call.enqueue(cb);
    }


    public static void uploadStudentLeaveDocumentApiImplementer(String fileName, String image, String stud_leave_id,
                                                                Callback<UploadStudentLeaveDocumentPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<UploadStudentLeaveDocumentPojo> call = apiService.uploadStudentLeaveDocument(fileName, image, stud_leave_id);
        call.enqueue(cb);
    }

    public static void getStudentLeaveApplicationHistoryApiImplementer(String stud_id, String year_id,
                                                                       Callback<LeaveApplicationHistoryPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<LeaveApplicationHistoryPojo> call = apiService.getStudentLeaveApplicationHistory(stud_id, year_id);
        call.enqueue(cb);
    }

    public static void getStudentActivityListApiImplementer(String stud_id, String url,
                                                            Callback<ArrayList<StudentActivityPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<StudentActivityPojo>> call = apiService.getStudentActivityList(stud_id, url);
        call.enqueue(cb);
    }

    public static void getELearningYearListApiImplementer(String institute_id,
                                                          Callback<ELearningYearListPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ELearningYearListPojo> call = apiService.getELearningYear(institute_id);
        call.enqueue(cb);
    }

    public static void getStudentWiseLearningGroupApiImplementer(String stud_id,
                                                                 Callback<StudentWiseLearningGroupPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<StudentWiseLearningGroupPojo> call = apiService.getStudentWiseLearningGroup(stud_id);
        call.enqueue(cb);
    }

    public static void checkIsELearningGroupIsCompulsoryOrNotApiImplementer(String grp_id,
                                                                            Callback<ArrayList<CheckIsELearningManagementGroupIsCompulsoryOrNot>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<CheckIsELearningManagementGroupIsCompulsoryOrNot>> call = apiService.checkIsELearningGroupIsCompulsoryOrNot(grp_id);
        call.enqueue(cb);
    }

    public static void getJoinLearningManagementGroupsApiImplementer(String stud_id, String year_id,
                                                                     Callback<ArrayList<JoinGroupListPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<JoinGroupListPojo>> call = apiService.getJoinLearningManagementGroups(stud_id, year_id);
        call.enqueue(cb);
    }

    public static void groupWiseLearningManagementSubjectListApiImplementer(String stud_id, String sem_id, String year_id, String grp_id,
                                                                            Callback<ArrayList<GroupWiseSubjectlistPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<GroupWiseSubjectlistPojo>> call = apiService.groupWiseLearningManagementSubjectList(stud_id, sem_id, year_id, grp_id);
        call.enqueue(cb);
    }

    public static void groupWiseLearningManagementSubjectListApiImplementer(String grp_id, String stud_id, String sem_id, String year_id,
                                                                            String from_date, String to_date, String sub_id,
                                                                            Callback<ArrayList<LearningManagementGroupDetailsPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<LearningManagementGroupDetailsPojo>> call = apiService.groupWiseLearningManagementSubjectList(grp_id, stud_id, sem_id, year_id,
                from_date, to_date, sub_id);
        call.enqueue(cb);
    }

    public static void checkIsLearningManagementGroupIsExistOrNotApiImplementer(String grp_id, String stud_id,
                                                                                Callback<CheckIsLearningManagementGroupIsExistOrNotPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<CheckIsLearningManagementGroupIsExistOrNotPojo> call = apiService.checkIsLearningManagementGroupIsExistOrNot(grp_id, stud_id);
        call.enqueue(cb);
    }

    public static void enrollToGroupApiImplementer(String grp_id, String stud_id, String created_by,
                                                   String created_ip, String lm_grpws_is_enroll_status,
                                                   Callback<EnrollToGroupPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<EnrollToGroupPojo> call = apiService.enrollToGroup(grp_id, stud_id, created_by, created_ip, lm_grpws_is_enroll_status);
        call.enqueue(cb);
    }


    public static void insertStudentLearningManagementPushNotificationApiImplementer(
            String grp_id, String year_id, String stud_id, String sem_id, String nt_type,
            String notif_desc, String read_status, String created_by, String created_ip,
            String institute_id,
            Callback<InsertStudentLearningManagementPushNotificationPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<InsertStudentLearningManagementPushNotificationPojo> call = apiService.insertStudentLearningManagementPushNotification(
                grp_id, year_id, stud_id, sem_id, nt_type, notif_desc, read_status, created_by,
                created_ip, institute_id);
        call.enqueue(cb);
    }

    public static void studentHolidayListApiImplementer(String student_id, Callback<ArrayList<HolidayListPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<HolidayListPojo>> call = apiInterface.getStudentHolidayList(student_id);
        call.enqueue(cb);
    }

    public static void getStudentTimetableApiImplementer(String stud_id, String course_id, String div_id, String shift_id,
                                                         String batch_id, String year_id, Callback<ArrayList<StudentTimeTablePojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<StudentTimeTablePojo>> call = apiInterface.getStudentTimetable(stud_id, course_id, div_id, shift_id, batch_id, year_id);
        call.enqueue(cb);
    }

    public static void getStudentSyllabusListApiImplementer(String stud_id, Callback<ArrayList<SyllabusListPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<SyllabusListPojo>> call = apiInterface.getStudentSyllabusList(stud_id);
        call.enqueue(cb);
    }

    public static void getStudentLessonPlanListApiImplementer(String stud_id, Callback<ArrayList<StudentLessonPlanListPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<StudentLessonPlanListPojo>> call = apiInterface.getStudentLessonPlanList(stud_id);
        call.enqueue(cb);
    }

    public static void getPendingFeeTypeApiImplementer(String StudId, String YearId, String AdmissionNo,
                                                       String CompanyCode, String hostel_code, Callback<ArrayList<PayFeeTypePojoList>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<PayFeeTypePojoList>> call = apiInterface.getPendingFeeType(StudId, YearId, AdmissionNo, CompanyCode, hostel_code);
        call.enqueue(cb);
    }

    public static void feeUrlPojoApiImplementer(String Ord_stud_id, String Ord_year_id, String Ord_Exam_id,
                                                String Ord_sem_id, String Ord_Fee_Source, String Ord_Fee_Type, String Ord_created_ip,
                                                Callback<FeeUrlPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<FeeUrlPojo> call = apiInterface.feeUrlPojo(Ord_stud_id, Ord_year_id, Ord_Exam_id, Ord_sem_id, Ord_Fee_Source, Ord_Fee_Type, Ord_created_ip);
        call.enqueue(cb);
    }

    public static void getPaymentButtonHideShowApiImplementer(String StudId, String FeeType, Callback<ArrayList<GetPaymentButtonHideShowPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<GetPaymentButtonHideShowPojo>> call = apiInterface.getPaymentButtonHideShow(StudId, FeeType);
        call.enqueue(cb);
    }

    public static void getPaymentSingleButtonHideShowApiImplementer(Callback<ArrayList<GetPaymentSingleButtonHideShowPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<GetPaymentSingleButtonHideShowPojo>> call = apiInterface.getPaymentSingleButtonHideShow();
        call.enqueue(cb);
    }

    public static void getAllPendingFeeStudentApiImplementer(String StudId, String YearId, String AdmissionNo,
                                                             String ClassName, String CompanyCode, String FeeType, String hostel_code,
                                                             Callback<ArrayList<GetAllPendingFeeStudentPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<GetAllPendingFeeStudentPojo>> call = apiInterface.getAllPendingFeeStudent(StudId, YearId, AdmissionNo,
                ClassName, CompanyCode, FeeType, hostel_code);
        call.enqueue(cb);
    }

    public static void getStudentCIAMarksApiImplementer(String stud_id, String sem_id, String exam_type,
                                                        Callback<ArrayList<StudentCIAMarksPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<StudentCIAMarksPojo>> call = apiInterface.getStudentCIAMarks(stud_id, sem_id, exam_type);
        call.enqueue(cb);
    }

    public static void getCIAExamSemesterApiImplementer(String course_id, String sem_id, String dept_id,
                                                        Callback<CIAExamSemesterPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<CIAExamSemesterPojo> call = apiInterface.getCIAExamSemester(course_id, sem_id, dept_id);
        call.enqueue(cb);
    }

    public static void getStudentMidResultApiImplementer(String institute_id, String dept_id, String course_id,
                                                         String sem_id, String stud_id, String srpc_id, Callback<ArrayList<MidResultPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<MidResultPojo>> call = apiInterface.getStudentMidResult(institute_id, dept_id, course_id, sem_id, stud_id, srpc_id);
        call.enqueue(cb);
    }

    public static void downloadStudentMidResultApiImplementer(String sem_id, String srpc_id, String stud_id,
                                                              String dept_id, String course_id, Callback<DownloadStudentMidResultPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<DownloadStudentMidResultPojo> call = apiInterface.downloadStudentMidResult(sem_id, srpc_id, stud_id, dept_id, course_id);
        call.enqueue(cb);
    }

    public static void getInstituteFromDomainApiImplementer(String domain_name, Callback<GetInstituteFromDomainPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<GetInstituteFromDomainPojo> call = apiInterface.getInstituteFromDomain(domain_name);
        call.enqueue(cb);
    }

    public static void getStudentForgetPasswordDetailsApiImplementer(String email, Callback<ArrayList<GetStudentForgotPasswordDetailsPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<GetStudentForgotPasswordDetailsPojo>> call = apiInterface.getStudentForgetPassDeail(email);
        call.enqueue(cb);
    }

    public static void getStudentForgetPasswordDetailsApiImplementer(String emp_stud_id, String emp_stud_status, String institute_id, Callback<ArrayList<GetForgetPasswordDetailsByStudentEmployeeIdPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<GetForgetPasswordDetailsByStudentEmployeeIdPojo>> call = apiInterface.getForgotPasswordDetailsByStudentOrEmpId(emp_stud_id, emp_stud_status, institute_id);
        call.enqueue(cb);
    }

    public static void getSMSApiForApplicationApiImplementer(String institute_id, Callback<ArrayList<GetSMSApiForApplicationPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<GetSMSApiForApplicationPojo>> call = apiInterface.getSMSApiForApplication(institute_id);
        call.enqueue(cb);
    }

    public static void updateStudentFcmTokenApiImplementer(String stud_id, String FCM_ID,
                                                           String KEY, Callback<UpdateStudentFCMTokenPojo> cb) {
        final IApiInterface apiInterface = ApiClientForStudentAndEmployeeFcmApi.getClient().create(IApiInterface.class);
        Call<UpdateStudentFCMTokenPojo> call = apiInterface.updateStudentFcmToken(stud_id, FCM_ID, KEY);
        call.enqueue(cb);
    }


    public static void getOTPBaseLoginDetailsForEmployeeApiImplementer(String uname_mobile, String institute_id, Callback<OtpBaseLoginDetailsForEmployeePojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<OtpBaseLoginDetailsForEmployeePojo> call = apiInterface.getOTPBaseLoginDetailsForEmployee(uname_mobile, institute_id);
        call.enqueue(cb);
    }

    public static void getOTPBaseLoginDetailsForStudentApiImplementer(String uname_mobile, String institute_id, Callback<OtpBaseLoginDetailsForStudentPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<OtpBaseLoginDetailsForStudentPojo> call = apiInterface.getOTPBaseLoginDetailsForStudent(uname_mobile, institute_id);
        call.enqueue(cb);
    }

    public static void insertStudentPasswordAndSendSmsAbsentApiImplementer(String sms, Callback<InsertStudentPasswordAndSMSAbsentApiCall> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<InsertStudentPasswordAndSMSAbsentApiCall> call = apiInterface.insertStudentPasswordAndSendSmsAbsent(sms);
        call.enqueue(cb);
    }

    public static void insertForgotPasswordOTPSmsRecordApiApiImplementer(String user_id, String is_emp, String is_stud,
                                                                         String mobile_no, String otp_msg, String created_ip,
                                                                         String created_by, Callback<InsertForgotPasswordOTPSmsRecordPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<InsertForgotPasswordOTPSmsRecordPojo> call = apiInterface.insertForgotPasswordOTPSmsRecordApi(user_id, is_emp,
                is_stud, mobile_no, otp_msg, created_ip, created_by);
        call.enqueue(cb);
    }

    public static void updateStudentEmpForgotPasswordOTPApiImplementer(String user_id, String is_emp, String is_stud,
                                                                       String otp, String modify_ip, String modify_by,
                                                                       Callback<UpdateStudentForgotPasswordOtpPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<UpdateStudentForgotPasswordOtpPojo> call = apiInterface.updateStudentEmpForgotPasswordOTP(user_id, is_emp, is_stud,
                otp, modify_ip, modify_by);
        call.enqueue(cb);
    }

    public static void checkOTPVerificationForEmployeeApiImplementer(String username, String otp, String institute_id, String ip_addr,
                                                                     Callback<CheckOTPVerificationForEmployeePojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<CheckOTPVerificationForEmployeePojo> call = apiInterface.checkOTPVerificationForEmployee(username, otp, institute_id, ip_addr);
        call.enqueue(cb);
    }

    public static void checkOTPVerificationForStudentApiImplementer(String username, String otp, String institute_id, String ip_addr,
                                                                    Callback<CheckOTPVerificationForStudentPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<CheckOTPVerificationForStudentPojo> call = apiInterface.checkOTPVerificationForStudent(username, otp, institute_id, ip_addr);
        call.enqueue(cb);
    }

}
