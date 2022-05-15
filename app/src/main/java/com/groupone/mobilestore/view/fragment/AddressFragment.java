package com.groupone.mobilestore.view.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.databinding.FragmentAddressBinding;
import com.groupone.mobilestore.model.Shipment;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.view.adapter.AddressAdapter;
import com.groupone.mobilestore.view.adapter.ProductAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;
import com.groupone.mobilestore.viewmodel.ShipmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddressFragment extends BaseFragment<FragmentAddressBinding, ShipmentViewModel> implements AddressAdapter.AddressCallback {

    public static final String TAG = AddressFragment.class.getName();
    private AddressAdapter adapter;

    private List<Shipment> listAddress = new ArrayList<>();
    private final User user = MyApplication.getInstance().getStorage().user;

    @Override
    protected Class<ShipmentViewModel> getClassVM() {
        return ShipmentViewModel.class;
    }

    @Override
    protected void initViews() {

        if(MyApplication.getInstance().getStorage().listShipment == null) {
            Log.d(TAG, "viewModel: ");
            viewModel.getShipmentByAccountId(user.getId());
        } else {
            Log.d(TAG, "getStorage: ");
            listAddress = MyApplication.getInstance().getStorage().listShipment;
            initShipmentView();
        }



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
        if(key.equals(Constants.KEY_GET_SHIPMENT_BY_ACCOUNT)){
            listAddress = (List<Shipment>) data;
            Log.d(TAG, "apiSuccess: " + listAddress.size());
            initShipmentView();
            MyApplication.getInstance().getStorage().listShipment = listAddress;
        }
    }

    private void initShipmentView(){
        binding.rvAddress.setLayoutManager(new LinearLayoutManager(context));
        adapter = new AddressAdapter(context, listAddress, this);
        binding.rvAddress.setAdapter(adapter);
    }

    @Override
    public void apiError(String key, int code, Object data) {

    }

    @Override
    public void gotoEditAddress(Shipment shipment) {
        callBack.showFragment(EditAddressFragment.TAG, null, true);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "out onResume: ");
        if(MyApplication.getInstance().getStorage().listShipment != null){
            Log.d(TAG, "onResume: ");
            listAddress = MyApplication.getInstance().getStorage().listShipment;
            initShipmentView();
        }
    }
}
