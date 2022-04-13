package com.groupone.mobilestore.view.activity;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
import static com.groupone.mobilestore.util.IMEUtils.hideSoftInput;
import static com.groupone.mobilestore.util.IMEUtils.isActive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.groupone.mobilestore.view.callback.OnMainCallBack;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.view.fragment.BaseFragment;
import com.groupone.mobilestore.view.fragment.LoginFragment;

import java.lang.reflect.Constructor;

public class MainActivity extends AppCompatActivity implements OnMainCallBack {

    //private ViewPager2 mViewPager;
    //private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mViewPager = findViewById(R.id.vp_home);
        //bottomNavigationView = findViewById(R.id.bnv_home);

        initViews();
        //changeStatusBarColor();
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }
        if (Build.VERSION.SDK_INT <= 29) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        } else {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        }
    }

    private void initViews() {

        LoginFragment frg = new LoginFragment();
        frg.setCallBack(this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_main, frg, LoginFragment.class.getName())
                .commit();

    }

    @Override
    public void showFragment(String tag, Object data, boolean isBack) {
        try {

            if (isActive(this)) {
                hideSoftInput(MainActivity.this);
            }
            Class<?> clazz = Class.forName(tag);
            Constructor<?> cons = clazz.getConstructor();
            BaseFragment<?, ?> frg = (BaseFragment<?, ?>) cons.newInstance();
            frg.setData(data);
            frg.setCallBack(this);
            FragmentTransaction trans = getSupportFragmentManager()
                    .beginTransaction();
            if (isBack) {
                trans.addToBackStack(null);
            }
            trans.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
            trans.replace(R.id.layout_main, frg, tag).commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void backToPrev() {
        if (isActive(this)) {
            hideSoftInput(MainActivity.this);
        }
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}