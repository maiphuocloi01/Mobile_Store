package com.groupone.mobilestore.viewmodel;

import android.util.Log;

import com.groupone.mobilestore.model.User;

public class AccountViewModel extends BaseViewModel{
    private static final String TAG = AccountViewModel.class.getName();

    public static final String KEY_LOGIN = "KEY_LOGIN";
    public static final String KEY_REGISTER = "KEY_REGISTER";
    public static final String KEY_CHECK_REGISTER = "KEY_CHECK_REGISTER";
    //private String email, username, password;



    public void login(String username, String password){
        getApi().login(username, password, "password").enqueue(initHandleResponse(KEY_LOGIN));
    }

    public void checkRegistrable(String email, String username, String password){
//        this.email = email;
//        this.username = username;
//        this.password = password;
//        Log.d(TAG, this.email + this.username + this.password);
        getApi().checkRegistrable(new User(username, email)).enqueue(initHandleResponse(KEY_CHECK_REGISTER));
    }

    public void register(String username,String fullName, String email, String phoneNumber, String birthday, boolean gender, String avatar, String password){
        //Log.d(TAG, email + username + password);
        getApi().register(new User(username, fullName, email, phoneNumber, gender, birthday, avatar, password)).enqueue(initHandleResponse(KEY_REGISTER));
    }

//    public void loginWithToken(String token){
//        getApi().loginWithToken("Bearer" + token).enqueue(initHandleResponse(KEY_LOGIN_WITH_TOKEN));
//    }

}
