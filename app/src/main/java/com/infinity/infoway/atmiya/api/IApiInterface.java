package com.infinity.infoway.atmiya.api;

import com.infinity.infoway.atmiya.login.pojo.StudentLoginPojo;
import com.infinity.infoway.atmiya.student.student_dashboard.pojo.GetSliderImageUrlsPojo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface IApiInterface {

    @GET("student_login_check_api")
    Call<StudentLoginPojo> checkStudentLogin(@QueryMap Map<String, String> params);

    @GET("Get_Image_URL")
    Call<GetSliderImageUrlsPojo> getSliderImages(@Query("url") String url, @Query("institute_id") String institute_id);


}
