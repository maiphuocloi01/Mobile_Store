package com.groupone.mobilestore.viewmodel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.groupone.mobilestore.util.Constants;

public class PagerViewModel extends BaseViewModel{

    public static final String KEY_LOGIN_WITH_TOKEN = "KEY_LOGIN_WITH_TOKEN";
    private Fragment fragment;

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void loginWithToken(String token){
        getApi().loginWithToken("Bearer " + token).enqueue(initHandleResponse(KEY_LOGIN_WITH_TOKEN));
    }

    public void getUserByUserName(String username) {
        getApi().getUserByUserName(username).enqueue(initHandleResponse(Constants.KEY_GET_BY_USERNAME));
    }

    public void getFavoriteProduct(int id){
        getApi().getFavoriteProductByAccountId(id).enqueue(initHandleResponse(Constants.KEY_GET_FAVORITE));
    }

}
