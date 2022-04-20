package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentDeliveredBinding;
import com.groupone.mobilestore.model.Order;
import com.groupone.mobilestore.model.Shipment;
import com.groupone.mobilestore.view.adapter.AddressAdapter;
import com.groupone.mobilestore.view.adapter.OrderAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.util.ArrayList;
import java.util.List;

public class DeliveredFragment extends BaseFragment<FragmentDeliveredBinding, CommonViewModel> implements OrderAdapter.OderCallback {

    public static final String TAG = DeliveredFragment.class.getName();

    private OrderAdapter adapter;

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {
        List<Order> listOrder = new ArrayList<>();
        Shipment shipment = new Shipment(1, "Mai Phước Lợi", "0911920503", "Linh Trung, Thủ Đức, Hồ Chí Minh", "KTX Khu A, Khu phố 6", 1, true);
        listOrder.add(new Order(1, "iPhone 13 Pro Max", R.drawable.img_iphone13, "01-01-2022 12:56", 1, 1, 34020000, 40000, "", "128GB, Xám", shipment));
        listOrder.add(new Order(1, "iPhone 13 Pro", R.drawable.img_iphone13_2, "01-01-2022 12:56", 1, 2, 29040000, 40000, "", "128GB, Vàng đồng", shipment));

        binding.rvDelivered.setLayoutManager(new LinearLayoutManager(context));
        adapter = new OrderAdapter(context, listOrder, this);
        binding.rvDelivered.setAdapter(adapter);


    }

    @Override
    protected FragmentDeliveredBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentDeliveredBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }

    @Override
    public void gotoReview(int position) {
        OrderFragment parentFrag = ((OrderFragment) DeliveredFragment.this.getParentFragment());
        if (parentFrag != null) {
            parentFrag.setActionShowFragmentFromPager(EvaluateFragment.TAG, null, true);
        }
    }

    @Override
    public void gotoRepurchase(int position) {
        OrderFragment parentFrag = ((OrderFragment) DeliveredFragment.this.getParentFragment());
        if (parentFrag != null) {
            parentFrag.setActionShowFragmentFromPager(PaymentFragment.TAG, null, true);
        }
    }

    @Override
    public void gotoDetail(int position) {
        OrderFragment parentFrag = ((OrderFragment) DeliveredFragment.this.getParentFragment());
        if (parentFrag != null) {
            parentFrag.setActionShowFragmentFromPager(OrderDetailFragment.TAG, null, true);
        }
    }
}
