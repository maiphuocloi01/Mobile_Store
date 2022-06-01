package com.groupone.mobilestore.view.fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.databinding.FragmentCompletedBinding;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.util.DialogUtils;
import com.groupone.mobilestore.viewmodel.PaymentViewModel;

import java.util.ArrayList;
import java.util.List;

public class CompletedFragment extends BaseFragment<FragmentCompletedBinding, PaymentViewModel> {

    public static final String TAG = CompletedFragment.class.getName();

    private Object mData;
    private List<Integer> listCartId = new ArrayList<>();
    private int countCart = 0;
    private int temp = 1;

    @Override
    protected Class<PaymentViewModel> getClassVM() {
        return PaymentViewModel.class;
    }

    @Override
    protected void initViews() {

        DialogUtils.hideLoadingDialog();

        listCartId = (List<Integer>) mData;
        countCart = listCartId.size();

        if (listCartId.size() > 0) {
            
            for (int id : listCartId) {
                Log.d(TAG, "initViews: " + id);

            }
            viewModel.deleteItemCartById(listCartId.get(0));
            //DialogUtils.showLoadingDialog(context);
        }

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showLoadingDialog(context);
                new Handler().postDelayed(() -> {
                    MyApplication.getInstance().getStorage().listCart = null;
                    MyApplication.getInstance().getStorage().listOrder = null;
                    callBack.backToPrev();
                    DialogUtils.hideLoadingDialog();
                }, 1500);
            }
        });

        binding.btGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showLoadingDialog(context);
                new Handler().postDelayed(() -> {
                    MyApplication.getInstance().getStorage().listCart = null;
                    MyApplication.getInstance().getStorage().listOrder = null;
                    callBack.showFragment(PagerFragment.TAG, null, false);
                    DialogUtils.hideLoadingDialog();
                }, 1500);

            }
        });
    }

    @Override
    protected FragmentCompletedBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentCompletedBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if (key.equals(Constants.KEY_DELETE_CART)) {
            Log.d(TAG, "apiSuccess: KEY_DELETE_CART");
            if(countCart > 1 && temp < listCartId.size()){
                viewModel.deleteItemCartById(listCartId.get(temp));
                temp++;
            }
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {

    }

    @Override
    public void setData(Object data) {
        this.mData = data;
    }
}
