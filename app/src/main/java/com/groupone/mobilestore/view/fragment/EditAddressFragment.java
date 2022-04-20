package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.groupone.mobilestore.databinding.FragmentEditAddressBinding;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

public class EditAddressFragment extends BaseFragment<FragmentEditAddressBinding, CommonViewModel> {

    public static final String TAG = EditAddressFragment.class.getName();

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });

        binding.etName.setText("Mai Phước Lợi");
        binding.etPhone.setText("0911920503");
        binding.etCity.setText("Thành phố Hồ Chí Minh");
        binding.etDistrict.setText("Thành phố Thủ Đức");
        binding.etWard.setText("Phường Linh Trung");
        binding.etStreet.setText("KTX Khu A ĐHQG, Khu Phố 6");
        binding.rbHome.setChecked(true);

    }

    @Override
    protected FragmentEditAddressBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentEditAddressBinding.inflate(inflater, container, false);
    }
}
