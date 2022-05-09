package com.groupone.mobilestore.view.fragment;

import static com.groupone.mobilestore.viewmodel.PagerViewModel.KEY_LOGIN_WITH_TOKEN;

import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentPagerBinding;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.CommonUtils;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.viewmodel.PagerViewModel;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class PagerFragment extends BaseFragment<FragmentPagerBinding, PagerViewModel> {

    public static final String TAG = PagerFragment.class.getName();

    @Override
    public void apiSuccess(String key, Object data) {

        if (key.equals(KEY_LOGIN_WITH_TOKEN)) {
            String response = (String) data;
            Log.d(TAG, "apiSuccess: " + response);
            if (response.equals("")) {
                Toast.makeText(context, "Phiên đăng nhập hết hạn", Toast.LENGTH_SHORT).show();
                CommonUtils.getInstance().clearPref(Constants.ACCESS_TOKEN);
                callBack.showFragment(LoginFragment.TAG, null, false);
            } else {
                String username = CommonUtils.getInstance().getPref(Constants.USERNAME);
                viewModel.getUserByUserName(username);
            }
//            else {
//                goToHome();
//            }
        }
        if (key.equals(Constants.KEY_GET_BY_USERNAME)){
            MyApplication.getInstance().getStorage().user = (User) data;
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {
        if (code == 999){
            Toast.makeText(context, "Không thể kết nối đến máy chủ", Toast.LENGTH_SHORT).show();
            callBack.showFragment(LoginFragment.TAG, null, false);
        }
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

        viewModel.loginWithToken(CommonUtils.getInstance().getPref(Constants.ACCESS_TOKEN));

        if (viewModel.getFragment() == null) {
            loadFragment(new HomeFragment());
        } else {
            loadFragment(viewModel.getFragment());
        }

        binding.bnvHome.setOnItemSelectedListener(item -> {
            Fragment frg;
            int id = item.getItemId();
            switch (id) {
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
