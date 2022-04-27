package com.groupone.mobilestore.view.activity;

import static com.groupone.mobilestore.util.IMEUtils.hideSoftInput;
import static com.groupone.mobilestore.util.IMEUtils.isActive;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.groupone.mobilestore.R;
import com.groupone.mobilestore.api.Api;
import com.groupone.mobilestore.util.CommonUtils;
import com.groupone.mobilestore.view.callback.OnMainCallBack;
import com.groupone.mobilestore.view.fragment.BaseFragment;
import com.groupone.mobilestore.view.fragment.HomeFragment;
import com.groupone.mobilestore.view.fragment.LoginFragment;
import com.groupone.mobilestore.view.fragment.PagerFragment;

import java.lang.reflect.Constructor;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnMainCallBack {

    //private ViewPager2 mViewPager;
    //private BottomNavigationView bottomNavigationView;
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";

    public static final String KEY_LOGIN_WITH_TOKEN = "KEY_LOGIN_WITH_TOKEN";
    public static final String TAG = MainActivity.class.getName();
    private static final String BASE_URL = "http://www.nhom01.somee.com/";

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


        //AccountViewModel viewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        if (CommonUtils.getInstance().getPref(ACCESS_TOKEN) != null) {
            //viewModel.loginWithToken(CommonUtils.getInstance().getPref(ACCESS_TOKEN));
            Log.d(TAG, CommonUtils.getInstance().getPref(ACCESS_TOKEN));
            getApi().loginWithToken("Bearer " + CommonUtils.getInstance().getPref(ACCESS_TOKEN)).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "initHandleResponse: " + response.body());
                        handleSuccess(KEY_LOGIN_WITH_TOKEN, response.body());
                    } else {
                        handleFail(KEY_LOGIN_WITH_TOKEN, response.code(), response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
                    handleException(KEY_LOGIN_WITH_TOKEN, t);
                }
            });
        } else {
            initViews();
        }

//        if (Build.VERSION.SDK_INT <= 29) {
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//
//        } else {
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//            WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
//        }


    }

    public Api getApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().callTimeout(30, TimeUnit.SECONDS).build())
                .build();
        return retrofit.create(Api.class);
    }

    private void initViews() {

        LoginFragment frg = new LoginFragment();
        frg.setCallBack(this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_main, frg, LoginFragment.class.getName())
                .commit();

    }

    private void goToHome() {

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


    private void handleException(String key, Throwable t) {
        Toast.makeText(this, "Error: " + 999 + ", " + t, Toast.LENGTH_SHORT).show();
        //callBack.apiError(key, 999, t);
    }

    private void handleFail(String key, int code, ResponseBody responseBody) {
        Log.e(TAG, "handleFail: " + code);
        Log.e(TAG, "handleFail: " + responseBody);
        //callBack.apiError(key, code, responseBody);
        Toast.makeText(this, "Error: " + code + ", " + responseBody, Toast.LENGTH_SHORT).show();
    }

    private void handleSuccess(String key, Object obj) {
        if (key.equals(KEY_LOGIN_WITH_TOKEN)) {
            String response = (String) obj;
            Log.d("MainActivity", "apiSuccess: " + response);
            if (response.equals("")) {
                Toast.makeText(this, "Phiên đăng nhập hết hạn", Toast.LENGTH_SHORT).show();
                initViews();
            } else {
                goToHome();
            }
        }
    }
}