package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.databinding.FragmentAddressBinding;
import com.groupone.mobilestore.model.Shipment;
import com.groupone.mobilestore.view.adapter.AddressAdapter;
import com.groupone.mobilestore.view.adapter.ProductAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddressFragment extends BaseFragment<FragmentAddressBinding, CommonViewModel> {

    public static final String TAG = AddressFragment.class.getName();
    private AddressAdapter adapter;

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {

        List<Shipment> listAddress = new ArrayList<>();

        listAddress.add(new Shipment(1, "Mai Phước Lợi", "0911920503", "Linh Trung, Thủ Đức, Hồ Chí Minh", "KTX Khu A, Khu phố 6", 1, true));
        listAddress.add(new Shipment(2, "Hoàng Thái Dương", "0911920503", "Linh Trung, Thủ Đức, Hồ Chí Minh", "KTX Khu A, Khu phố 6", 2, false));


        binding.rvAddress.setLayoutManager(new LinearLayoutManager(context));
        adapter = new AddressAdapter(context, listAddress);
        binding.rvAddress.setAdapter(adapter);

        binding.frameAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.showFragment(AddAddressFragment.TAG, null, true);
            }
        });
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });
    }

    @Override
    protected FragmentAddressBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentAddressBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }
}
