package com.groupone.mobilestore;

public interface OnMainCallBack {
    void showFragment(String tag, Object data, boolean isBack);
    void backToPrev();
}
