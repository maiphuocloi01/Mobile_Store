package com.groupone.mobilestore.api;

import com.groupone.mobilestore.model.Token;
import com.groupone.mobilestore.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    @GET("Api/AccountController/GetAccountById/{Id}")
    @Headers("Content-type: application/json")
    Call<User> getUserById(@Path("Id") int id);

    @GET("Api/AccountController/GetAccountByUserName/{UserName}")
    @Headers("Content-type: application/json")
    Call<User> getUserByUserName(@Path("UserName") String username);

    @POST("Api/AccountController/IsRegisterAble")
    @Headers("Content-type: application/json")
    Call<Integer> checkRegistrable(@Body User user);

    @POST("Api/AccountController/Register")
    @Headers("Content-type: application/json")
    Call<Integer> register(@Body User user);

    @GET("Api/AccountController/LoginWithToken")
    Call<String> loginWithToken(@Header("Authorization") String auth);

    @POST("Api/AccountController/Login")
    @FormUrlEncoded
    Call<Token> login(@Field("username") String username, @Field("password") String password, @Field("grant_type") String type);
}
