package com.groupone.mobilestore.util;

import android.app.ProgressDialog;
import android.content.Context;

import com.groupone.mobilestore.R;

public class DialogUtils {

    private static ProgressDialog progressDialog;

    public static void showLoadingDialog(Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setContentView(R.layout.custom_progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    public static void hideLoadingDialog(){
        progressDialog.dismiss();
    }


    public static void showLoadDataDialog(Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setContentView(R.layout.custom_load_data_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }
}
