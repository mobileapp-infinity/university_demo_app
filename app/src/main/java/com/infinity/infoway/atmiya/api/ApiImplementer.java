package com.infinity.infoway.atmiya.api;

import com.infinity.infoway.atmiya.login.pojo.StudentLoginPojo;
import com.infinity.infoway.atmiya.student.student_dashboard.pojo.GetSliderImageUrlsPojo;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;

public class ApiImplementer {

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

}
