package com.groupone.mobilestore.viewmodel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

public class PagerViewModel extends BaseViewModel{
    private Fragment fragment;

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
