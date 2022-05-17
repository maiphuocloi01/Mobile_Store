package com.groupone.mobilestore.viewmodel;

import com.groupone.mobilestore.util.Constants;

public class PostViewModel extends BaseViewModel{

    private static final String TAG = PostViewModel.class.getName();

    public void getAllPost(){
        getApi().getAllPost().enqueue(initHandleResponse(Constants.KEY_ALL_POST));
    }

}
