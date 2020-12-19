package com.infinity.infoway.atmiya.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class MySharedPreferences {

    SharedPreferences sharedPreferencesForStudentOrFaculty;
    SharedPreferences.Editor editorForStudentOrFaculty;
    private SharedPreferences sharedPreferencesForFirebaseFcmToken;
    private SharedPreferences.Editor editorForFirebaseFcmToken;
    Context context;

    private static final String FIREBASE_FCM_TOKEN = "fcm_token_details";//please do not use this file name in other places

    public MySharedPreferences(Context context) {
        this.context = context;
        sharedPreferencesForStudentOrFaculty = context.getSharedPreferences(PreferencesConstants.PREFERENCES_ATMIYA_STUDENT_OR_FACULTY_LOGIN_DETAILS, MODE_PRIVATE);
        editorForStudentOrFaculty = sharedPreferencesForStudentOrFaculty.edit();
        sharedPreferencesForFirebaseFcmToken = context.getSharedPreferences(FIREBASE_FCM_TOKEN, MODE_PRIVATE);
        editorForFirebaseFcmToken = sharedPreferencesForFirebaseFcmToken.edit();
    }

    public void setLoginUserType(int loginUserType) {
        editorForStudentOrFaculty.putInt(PreferencesConstants.LOGIN_USER_TYPE, loginUserType);
        editorForStudentOrFaculty.apply();
    }

    public int getLoginUserType() {
        return sharedPreferencesForStudentOrFaculty.getInt(PreferencesConstants.LOGIN_USER_TYPE, 0);
    }

    public void setFCMToken(String fcmToken) {
        editorForFirebaseFcmToken.putString(PreferencesConstants.FCM_TOKEN, fcmToken);
        editorForFirebaseFcmToken.apply();
    }

    public String getFCMToken() {
        return sharedPreferencesForFirebaseFcmToken.getString(PreferencesConstants.FCM_TOKEN, "");
    }


    public boolean checkIsStudentCurrentlyLoggedIn() {
        return sharedPreferencesForStudentOrFaculty.contains(PreferencesConstants.STUDENT_ID) &&
                sharedPreferencesForStudentOrFaculty.contains(PreferencesConstants.LOGIN_USER_TYPE);
    }


    public void logoutStudentOrFaulty() {
        editorForStudentOrFaculty.clear();
        editorForStudentOrFaculty.commit();
    }

    public void setStudentUsername(String studentUsername) {
        editorForStudentOrFaculty.putString(PreferencesConstants.STUD_USER_NAME, studentUsername);
        editorForStudentOrFaculty.apply();
    }

    public String getStudentUsername() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.STUD_USER_NAME, "");
    }

    public void setStudentPassword(String studentPassword) {
        editorForStudentOrFaculty.putString(PreferencesConstants.STUD_PASSWORD, studentPassword);
        editorForStudentOrFaculty.apply();
    }

    public String getStudentPassword() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.STUD_PASSWORD, "");
    }

    public void setStudentId(String studentId) {
        editorForStudentOrFaculty.putString(PreferencesConstants.STUDENT_ID, studentId);
        editorForStudentOrFaculty.apply();
    }

    public String getStudentId() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.STUDENT_ID, "");
    }

    public void setDMId(String dmId) {
        editorForStudentOrFaculty.putString(PreferencesConstants.DM_ID, dmId);
        editorForStudentOrFaculty.apply();
    }

    public String getDmId() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.DM_ID, "");
    }

    public void setDmFullName(String dmFullName) {
        editorForStudentOrFaculty.putString(PreferencesConstants.DM_FULL_NAME, dmFullName);
        editorForStudentOrFaculty.apply();
    }

    public String getDmFullName() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.DM_FULL_NAME, "");
    }

    public void setCourseId(String courseId) {
        editorForStudentOrFaculty.putString(PreferencesConstants.COURSE_ID, courseId);
        editorForStudentOrFaculty.apply();
    }

    public String getCourseId() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.COURSE_ID, "");
    }

    public void setCourseFullName(String courseFullName) {
        editorForStudentOrFaculty.putString(PreferencesConstants.COURSE_FULL_NAME, courseFullName);
        editorForStudentOrFaculty.apply();
    }

    public String getCourseFullName() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.COURSE_FULL_NAME, "");
    }

    public void setSmId(String smId) {
        editorForStudentOrFaculty.putString(PreferencesConstants.SM_ID, smId);
        editorForStudentOrFaculty.apply();
    }

    public String getSmId() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.SM_ID, "");
    }

    public void setSmName(String smName) {
        editorForStudentOrFaculty.putString(PreferencesConstants.SM_NAME, smName);
        editorForStudentOrFaculty.apply();
    }

    public String getSmName() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.SM_NAME, "");
    }

    public void setAcId(String acId) {
        editorForStudentOrFaculty.putString(PreferencesConstants.AC_ID, acId);
        editorForStudentOrFaculty.apply();
    }

    public String getAcId() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.AC_ID, "");
    }

    public void setAcFullName(String acFullName) {
        editorForStudentOrFaculty.putString(PreferencesConstants.AC_FULL_NAME, acFullName);
        editorForStudentOrFaculty.apply();
    }

    public String getAcFullName() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.AC_FULL_NAME, "");
    }

    public void setSwdYearId(String swdYearId) {
        editorForStudentOrFaculty.putString(PreferencesConstants.SWD_YEAR_ID, swdYearId);
        editorForStudentOrFaculty.apply();
    }

    public String getSwdYearId() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.SWD_YEAR_ID, "");
    }

    public void setStatus(String status) {
        editorForStudentOrFaculty.putString(PreferencesConstants.STATUS, status);
        editorForStudentOrFaculty.apply();
    }

    public String getStatus() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.STATUS, "");
    }

    public void setAcCode(String acCode) {
        editorForStudentOrFaculty.putString(PreferencesConstants.AC_CODE, acCode);
        editorForStudentOrFaculty.apply();
    }

    public String getAcCode() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.AC_CODE, "");
    }

    public void setHostelCode(String hostelCode) {
        editorForStudentOrFaculty.putString(PreferencesConstants.HOSTEL_CODE, hostelCode);
        editorForStudentOrFaculty.apply();
    }

    public String getHostelCode() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.HOSTEL_CODE, "");
    }

    public void setStudentName(String studentName) {
        editorForStudentOrFaculty.putString(PreferencesConstants.STUDENT_NAME, studentName);
        editorForStudentOrFaculty.apply();
    }

    public String getStudentName() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.STUDENT_NAME, "");
    }

    public void setStudAdmissionNo(String studAdmissionNo) {
        editorForStudentOrFaculty.putString(PreferencesConstants.STUD_ADMISSION_NO, studAdmissionNo);
        editorForStudentOrFaculty.apply();
    }

    public String getStudAdmissionNo() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.STUD_ADMISSION_NO, "");
    }

    public void setStudentEnrollmentNo(String studentAdmissionNo) {
        editorForStudentOrFaculty.putString(PreferencesConstants.STUD_ENROLLMENT_NO, studentAdmissionNo);
        editorForStudentOrFaculty.apply();
    }

    public String getStudentEnrollmentNo() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.STUD_ENROLLMENT_NO, "");
    }

    public void setStudentPhotoUrl(String studentPhotoUrl) {
        editorForStudentOrFaculty.putString(PreferencesConstants.STUD_PHOTO, studentPhotoUrl);
        editorForStudentOrFaculty.apply();
    }

    public String getStudentPhotoUrl() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.STUD_PHOTO, "");
    }

    public void setSwdDivisionId(String swdDivisionId) {
        editorForStudentOrFaculty.putString(PreferencesConstants.SWD_DIVISION_ID, swdDivisionId);
        editorForStudentOrFaculty.apply();
    }

    public String getSwdDivisionId() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.SWD_DIVISION_ID, "");
    }

    public void setSwdBatchId(String swdBatchId) {
        editorForStudentOrFaculty.putString(PreferencesConstants.SWD_BATCH_ID, swdBatchId);
        editorForStudentOrFaculty.apply();
    }

    public String getSwdBatchId() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.SWD_BATCH_ID, "");
    }

    public void setShiftId(String shiftId) {
        editorForStudentOrFaculty.putString(PreferencesConstants.SHIFT_ID, shiftId);
        editorForStudentOrFaculty.apply();
    }

    public String getShiftId() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.SHIFT_ID, "");
    }

    public void setImDomainName(String imDomainName) {
        editorForStudentOrFaculty.putString(PreferencesConstants.IM_DOMAIN_NAME, imDomainName);
        editorForStudentOrFaculty.apply();
    }

    public String getImDomainName() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.IM_DOMAIN_NAME, "");
    }

    public void setInstituteId(String instituteId) {
        editorForStudentOrFaculty.putString(PreferencesConstants.INSTITUTE_ID, instituteId);
        editorForStudentOrFaculty.apply();
    }

    public String getInstituteId() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.INSTITUTE_ID, "");
    }

    public void setFcFile(String fcFile) {
        editorForStudentOrFaculty.putString(PreferencesConstants.FC_FILE, fcFile);
        editorForStudentOrFaculty.apply();
    }

    public String getFcFile() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.FC_FILE, "");
    }

    public void setImExamDbName(String imExamDbName) {
        editorForStudentOrFaculty.putString(PreferencesConstants.IM_EXAM_DB_NAME, imExamDbName);
        editorForStudentOrFaculty.apply();
    }

    public String getImExamDbName() {
        return sharedPreferencesForStudentOrFaculty.getString(PreferencesConstants.IM_EXAM_DB_NAME, "");
    }


    //=========  //CODE FOR STORING ONLY LOGGED IN STUDENT LIST //==================================================//

    private static final String LOGGED_IN_STUDENT_LIST_PREFERENCES = "logged_in_student_list"; //Please do not use this preference name in other places


    HashMap<String, ArrayList<String>> testHashMap = new HashMap();


    Gson gson = new Gson();

    public void storeStudentIdAndName(String studentEnNo, String studentPassword, String studentName, Context context) {


        ArrayList<String> studentNameAndPassArrayList = new ArrayList<>();
        studentNameAndPassArrayList.add(studentName);
        studentNameAndPassArrayList.add(studentPassword);

        testHashMap.put(studentEnNo, studentNameAndPassArrayList);

        String hashMapString = gson.toJson(testHashMap);

        SharedPreferences getReg = context.getSharedPreferences(LOGGED_IN_STUDENT_LIST_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editPref = getReg.edit();
        HashMap<String, ArrayList<String>> existingHashMap = getStudentIdAndName(context);
        if (existingHashMap == null) {
            existingHashMap = new HashMap<>();
        }
        existingHashMap.putAll(jsonToMap(hashMapString));
        hashMapString = gson.toJson(existingHashMap);
        editPref.putString(PreferencesConstants.LOGGED_IN_STUDENT_PASSWORD_AND_NAME_JASON, hashMapString);
        editPref.apply();

    }

    public HashMap<String, ArrayList<String>> getStudentIdAndName(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(LOGGED_IN_STUDENT_LIST_PREFERENCES, MODE_PRIVATE);
        java.lang.reflect.Type type = new TypeToken<HashMap<String, ArrayList<String>>>() {
        }.getType();
        String storedHashMapString = preferences.getString(PreferencesConstants.LOGGED_IN_STUDENT_PASSWORD_AND_NAME_JASON, "");
        return gson.fromJson(storedHashMapString, type);
    }

    private Map<String, ArrayList<String>> jsonToMap(String jsonString) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        JSONObject jObject = null;
        try {
            jObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Iterator<?> keys = jObject.keys();

        while (keys.hasNext()) {

            JSONArray studentNameAndPassArray;
            ArrayList<String> studentNameAndPassArrayList = null;


            String key = (String) keys.next();
//            String value = null;
            try {
                studentNameAndPassArrayList = new ArrayList<>();
                studentNameAndPassArray = jObject.getJSONArray(key);

                for (int i = 0; i < studentNameAndPassArray.length(); i++) {
                    studentNameAndPassArrayList.add(studentNameAndPassArray.getString(i));
                }

//                value = jObject.getString(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put(key, studentNameAndPassArrayList);

        }
        return map;
    }


    public void deleteStudentNameAndId(String studentEnNo, Context context) {
        HashMap<String, ArrayList<String>> existingHashMap = getStudentIdAndName(context);
        if (existingHashMap != null && existingHashMap.size() > 0) {
            existingHashMap.remove(studentEnNo);
        }
        SharedPreferences getReg = context.getSharedPreferences(LOGGED_IN_STUDENT_LIST_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editPref = getReg.edit();
        String hashMapString = gson.toJson(existingHashMap);
        editPref.putString(PreferencesConstants.LOGGED_IN_STUDENT_PASSWORD_AND_NAME_JASON, hashMapString);
        editPref.apply();
    }


}
