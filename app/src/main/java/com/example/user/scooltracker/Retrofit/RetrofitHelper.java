package com.example.user.scooltracker.Retrofit;

import com.example.user.scooltracker.LoginActivity;
import com.example.user.scooltracker.ProfileActivity;
import com.example.user.scooltracker.Teacher.Teach_attendance_Activity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by USER on 01-02-2018.
 */

public class RetrofitHelper {
    private static API api;

    public RetrofitHelper(LoginActivity loginActivity) {
        initRestAdapter();
    }

    public RetrofitHelper(ProfileActivity profileActivity) {
        initRestAdapter();
    }

    public RetrofitHelper(Teach_attendance_Activity teach_attendance_activity) {
        initRestAdapter();
    }

    public static API getApi() {
        return api;
    }

    public static void setApi(API api) {
        RetrofitHelper.api = api;
    }
    private void initRestAdapter(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://comcubeindia.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        setApi(retrofit.create(API.class));
    }
}
