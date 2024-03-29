package com.groupone.mobilestore.view.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentOrderBinding;
import com.groupone.mobilestore.view.adapter.MyViewPagerAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

public class OrderFragment extends BaseFragment<FragmentOrderBinding, CommonViewModel> {

    public static final String TAG = OrderFragment.class.getName();
    private MyViewPagerAdapter viewPagerAdapter;

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {
        Log.d(TAG, "initViews: ");
        viewPagerAdapter = new MyViewPagerAdapter(this);
        binding.vpOrder.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(binding.tabLayoutOrder, binding.vpOrder, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Đang giao");
                        break;
                    case 1:
                        tab.setText("Hoàn thành");
                        break;
                }
            }
        }).attach();

        for (int i = 0; i < binding.tabLayoutOrder.getTabCount(); i++) {

            TextView tv = (TextView) LayoutInflater.from(context)
                    .inflate(R.layout.custom_tablayout, null);

            binding.tabLayoutOrder.getTabAt(i).setCustomView(tv);
        }
    }

    public void setActionShowFragmentFromPager(String tag, Object data, boolean isBack) {
        PagerFragment parentFrag = ((PagerFragment) OrderFragment.this.getParentFragment());
        if (parentFrag != null) {
            parentFrag.setActionShowFragment(tag, data, isBack);
        }
    }

    @Override
    protected FragmentOrderBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentOrderBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
        MyApplication.getInstance().getStorage().listOrder = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
