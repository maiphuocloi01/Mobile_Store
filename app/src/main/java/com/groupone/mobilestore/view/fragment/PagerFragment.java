package com.groupone.mobilestore.view.fragment;

import static com.groupone.mobilestore.util.IMEUtils.hideSoftInput;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentPagerBinding;
import com.groupone.mobilestore.view.adapter.MyViewPagerAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;
import com.groupone.mobilestore.viewmodel.PagerViewModel;

public class PagerFragment extends BaseFragment<FragmentPagerBinding, PagerViewModel> {

    public static final String TAG = PagerFragment.class.getName();

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }

    @Override
    protected Class<PagerViewModel> getClassVM() {
        return PagerViewModel.class;
    }

    @Override
    protected void initViews() {

        //hideSoftInput(binding.getRoot());

//        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(this);
//        binding.vpHome.setAdapter(myViewPagerAdapter);

        if(viewModel.getFragment() == null) {
            loadFragment(new HomeFragment());
        } else {
            loadFragment(viewModel.getFragment());
        }

        binding.bnvHome.setOnItemSelectedListener(item -> {
            Fragment frg;
            int id = item.getItemId();
            switch (id){
                case R.id.bottom_home:
                    frg = new HomeFragment();
                    loadFragment(frg);
                    viewModel.setFragment(frg);
                    return true;
                case R.id.bottom_order:
                    frg = new OrderFragment();
                    loadFragment(frg);
                    viewModel.setFragment(frg);
                    return true;
                case R.id.bottom_cart:
                    frg = new CartFragment();
                    loadFragment(frg);
                    viewModel.setFragment(frg);
                    return true;
                case R.id.bottom_profile:
                    frg = new ProfileFragment();
                    loadFragment(frg);
                    viewModel.setFragment(frg);
                    return true;
            }
            return false;
        });


//        binding.vpHome.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                switch (position) {
//                    case 0:
//                        binding.bnvHome.getMenu().findItem(R.id.bottom_home).setChecked(true);
//                        break;
//                    case 1:
//                        binding.bnvHome.getMenu().findItem(R.id.bottom_order).setChecked(true);
//                        break;
//                    case 2:
//                        binding.bnvHome.getMenu().findItem(R.id.bottom_cart).setChecked(true);
//                        break;
//                    case 3:
//                        binding.bnvHome.getMenu().findItem(R.id.bottom_profile).setChecked(true);
//                        break;
//                }
//            }
//        });

    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void setActionShowFragment(String tag, Object data, boolean isBack) {
        callBack.showFragment(tag, data, isBack);
    }

    @Override
    protected FragmentPagerBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentPagerBinding.inflate(inflater, container, false);
    }

}
