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

}
