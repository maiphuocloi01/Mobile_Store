package com.groupone.mobilestore.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.groupone.mobilestore.view.callback.OnMainCallBack;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.view.fragment.BaseFragment;
import com.groupone.mobilestore.view.fragment.HomeFragment;
import com.groupone.mobilestore.view.fragment.PagerFragment;

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
    }

    private void initViews() {



        PagerFragment frg = new PagerFragment();
        frg.setCallBack(this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_main, frg, HomeFragment.class.getName())
                .commit();

    }

    @Override
    public void showFragment(String tag, Object data, boolean isBack) {
        try {
            Class<?> clazz = Class.forName(tag);
            Constructor<?> cons = clazz.getConstructor();
            BaseFragment<?,?> frg = (BaseFragment<?, ?>) cons.newInstance();
            frg.setData(data);
            frg.setCallBack(this);
            FragmentTransaction trans = getSupportFragmentManager()
                    .beginTransaction();
            if(isBack){
                trans.addToBackStack(null);
            }
            trans.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
            trans.replace(R.id.layout_main, frg, tag).commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void backToPrev() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}