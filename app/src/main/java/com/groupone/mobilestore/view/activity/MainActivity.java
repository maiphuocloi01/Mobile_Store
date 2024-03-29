package com.groupone.mobilestore.view.activity;

import static com.groupone.mobilestore.util.Constants.ACCESS_TOKEN;
import static com.groupone.mobilestore.util.IMEUtils.hideSoftInput;
import static com.groupone.mobilestore.util.IMEUtils.isActive;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentTransaction;

import com.groupone.mobilestore.R;
import com.groupone.mobilestore.util.CommonUtils;
import com.groupone.mobilestore.view.callback.OnMainCallBack;
import com.groupone.mobilestore.view.fragment.BaseFragment;
import com.groupone.mobilestore.view.fragment.LoginFragment;
import com.groupone.mobilestore.view.fragment.PagerFragment;

import java.lang.reflect.Constructor;

public class MainActivity extends AppCompatActivity implements OnMainCallBack {


    public static final String TAG = MainActivity.class.getName();

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MobileStore);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        if (CommonUtils.getInstance().getPref(ACCESS_TOKEN) != null) {
            initViewsWithToken();
        } else {
            initViewsWithoutToken();
        }
    }

    private void initViewsWithoutToken() {
        LoginFragment frg = new LoginFragment();
        frg.setCallBack(this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_main, frg, LoginFragment.class.getName())
                .commit();
    }

    private void initViewsWithToken() {
        PagerFragment frg = new PagerFragment();
        frg.setCallBack(this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_main, frg, PagerFragment.class.getName())
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
    public void showFragmentWithAdd(String tag, Object data, boolean isBack) {
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
            trans.add(R.id.layout_main, frg, tag).commit();

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