package com.groupone.mobilestore.api;

import com.groupone.mobilestore.model.Product;
import com.groupone.mobilestore.model.ShoppingCart;
import com.groupone.mobilestore.model.Token;
import com.groupone.mobilestore.model.User;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Api {

    //Account API
    @GET("Api/AccountController/GetAccountById/{Id}")
    @Headers("Content-type: application/json")
    Call<User> getUserById(@Path("Id") int id);

    @GET("Api/AccountController/GetAccountByUserName/{UserName}")
    @Headers("Content-type: application/json")
    Call<User> getUserByUserName(@Path("UserName") String username);

    @POST("Api/AccountController/IsRegisterAble")
    @Headers("Content-type: application/json")
    Call<Integer> checkRegistrable(@Body User user);

    @POST("Api/AccountController/ResetPassword")
    Call<Boolean> resetPassword(@Body User user);

    @POST("Api/AccountController/UpdateAccount")
    Call<Boolean> updateAccount(@Body User user);

    @Multipart
    @POST("Api/AccountController/UploadImage")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part part, @Part("somedata") RequestBody requestBody);

    @POST("Api/AccountController/Register")
    @Headers("Content-type: application/json")
    Call<Integer> register(@Body User user);

    @GET("Api/AccountController/LoginWithToken")
    Call<String> loginWithToken(@Header("Authorization") String auth);

    @POST("Api/AccountController/SendOTP")
    Call<String> sendOTP(@Body User user);

    @POST("Api/AccountController/Login")
    @FormUrlEncoded
    Call<Token> login(@Field("username") String username, @Field("password") String password, @Field("grant_type") String type);


    //Product API
    @GET("Api/ProductController/GetTopSaleProduct")
    @Headers("Content-type: application/json")
    Call<List<Product>> getTopSaleProduct();


    //Shopping Cart API
    @POST("Api/ShoppingCartController/AddShoppingCart")
    @Headers("Content-type: application/json")
    Call<Integer> addShoppingCart(@Body ShoppingCart cart);

    @GET("Api/ShoppingCartController/GetShoppingCartByAccountId/{Id}")
    @Headers("Content-type: application/json")
    Call<List<ShoppingCart>> getShoppingCartByAccountId(@Path("Id") int id);

    @DELETE("Api/ShoppingCartController/DeleteShoppingCartById/{Id}")
    @Headers("Content-type: application/json")
    Call<Boolean> deleteItemCartById(@Path("Id") int id);
}