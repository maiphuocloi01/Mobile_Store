package com.groupone.mobilestore.view.callback;

public interface OnMainCallBack {
    void showFragment(String tag, Object data, boolean isBack);
    void showFragmentWithAdd(String tag, Object data, boolean isBack);
    void backToPrev();
}
