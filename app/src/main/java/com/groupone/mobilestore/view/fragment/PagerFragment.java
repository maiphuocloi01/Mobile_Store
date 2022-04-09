package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentPagerBinding;
import com.groupone.mobilestore.view.adapter.MyViewPagerAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

public class PagerFragment extends BaseFragment<FragmentPagerBinding, CommonViewModel> {

    public static final String TAG = PagerFragment.class.getName();

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

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(this);
        binding.vpHome.setAdapter(myViewPagerAdapter);

        binding.bnvHome.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.bottom_home) {
                binding.vpHome.setCurrentItem(0);
            } else if (id == R.id.bottom_order) {
                binding.vpHome.setCurrentItem(1);
            } else if (id == R.id.bottom_cart) {
                binding.vpHome.setCurrentItem(2);
            } else if (id == R.id.bottom_profile) {
                binding.vpHome.setCurrentItem(3);
            }
            return true;
        });

        binding.vpHome.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        binding.bnvHome.getMenu().findItem(R.id.bottom_home).setChecked(true);
                        break;
                    case 1:
                        binding.bnvHome.getMenu().findItem(R.id.bottom_order).setChecked(true);
                        break;
                    case 2:
                        binding.bnvHome.getMenu().findItem(R.id.bottom_cart).setChecked(true);
                        break;
                    case 3:
                        binding.bnvHome.getMenu().findItem(R.id.bottom_profile).setChecked(true);
                        break;
                }
            }
        });

    }


    public void setActionShowFragment(String tag, Object data, boolean isBack) {
        callBack.showFragment(tag, data, isBack);
    }

    @Override
    protected FragmentPagerBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentPagerBinding.inflate(inflater, container, false);
    }

}
