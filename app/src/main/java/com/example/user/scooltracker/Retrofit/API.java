package com.example.user.scooltracker.Retrofit;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by USER on 01-02-2018.
 */

public interface API {

    @FormUrlEncoded
    @POST("/sample/school_app/api/teachers_api.php")
    Call<JsonElement>login(@Field("action")String action,@Field("user_name")String user_name,@Field("password")String password);

    @FormUrlEncoded
    @POST("/sample/school_app/api/teachers_api.php")
    Call<JsonElement>getProfile(@Field("action")String action,@Field("user_id")int userId);

    @FormUrlEncoded
    @POST("/sample/school_app/api/teachers_api.php")
    Call<JsonElement>getClass(@Field("action")String action);

    @FormUrlEncoded
    @POST("/sample/school_app/api/teachers_api.php")
    Call<JsonElement>getDivision(@Field("action")String action,@Field("user_id")int classa);

    @FormUrlEncoded
    @POST("/sample/school_app/api/teachers_api.php")
    Call<JsonElement>getAttendance(@Field("action")String action,@Field("user_id")String userId,@Field("class")String classa,
                                    @Field("division")String division);
}
