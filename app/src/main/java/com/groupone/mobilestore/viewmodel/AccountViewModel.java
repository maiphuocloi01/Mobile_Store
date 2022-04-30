package com.groupone.mobilestore.viewmodel;


import android.util.Log;

import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.Constants;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AccountViewModel extends BaseViewModel {

    private static final String TAG = AccountViewModel.class.getName();


    public void login(String username, String password) {
        getApi().login(username, password, "password").enqueue(initHandleResponse(Constants.KEY_LOGIN));
    }

    public void checkRegistrable(String email, String username, String password) {
        getApi().checkRegistrable(new User(username, email)).enqueue(initHandleResponse(Constants.KEY_CHECK_REGISTER));
    }

    public void checkEmailExist(String email) {
        getApi().checkRegistrable(new User(email)).enqueue(initHandleResponse(Constants.KEY_CHECK_EMAIL_EXIST));
    }

    public void register(String username, String fullName, String email, String phoneNumber,
                         String birthday, boolean gender, String avatar, String password) {
        getApi().register(new User(username, fullName, email, phoneNumber, gender,
                birthday, avatar, password))
                .enqueue(initHandleResponse(Constants.KEY_REGISTER));
    }

    public void getUserByUserName(String username) {
        getApi().getUserByUserName(username).enqueue(initHandleResponse(Constants.KEY_GET_BY_USERNAME));
    }

    public void sendOTP(String email) {
        getApi().sendOTP(new User(email)).enqueue(initHandleResponse(Constants.KEY_SEND_OTP));
    }

    public void resetPassword(String email, String password) {
        Log.d(TAG, email + " " + password);
        User myUser = new User(1, email, password);
        Log.d(TAG, myUser.toString());
        getApi().resetPassword(myUser).enqueue(initHandleResponse(Constants.KEY_RESET_PASSWORD));
    }

    public void updateAccount(String fullName, String email, String phoneNumber, String birthday, boolean gender, String avatar) {
        User myUser = new User(null, fullName, email, phoneNumber, gender, birthday, avatar, null);
        getApi().updateAccount(myUser).enqueue(initHandleResponse(Constants.KEY_UPDATE_ACCOUNT));
    }

    public void uploadImageAccount(MultipartBody.Part parts, RequestBody someData) {
        getApi().uploadImage(parts, someData).enqueue(initHandleResponse(Constants.KEY_UPLOAD_IMAGE));
    }


}
