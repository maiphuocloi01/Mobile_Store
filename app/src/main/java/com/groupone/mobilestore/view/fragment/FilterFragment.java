package com.groupone.mobilestore.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentFilterBinding;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.util.ArrayList;
import java.util.List;

public class FilterFragment extends BaseFragment<FragmentFilterBinding, CommonViewModel> {

    public static final String TAG = FilterFragment.class.getName();

    private final ArrayList<String> listFilter = new ArrayList<>();

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });
        binding.btConfirm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("filter", listFilter);
                callBack.showFragment(SearchResultFragment.TAG, bundle, false);
            }
        });

        binding.btCancel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.btApple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.btApple);
            }
        });

        binding.btSamsung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.btSamsung);
            }
        });

        binding.btXiaomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.btXiaomi);
            }
        });

        binding.btOppo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.btOppo);
            }
        });

        binding.btNokia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.btNokia);
            }
        });

        binding.btAsus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.btAsus);
            }
        });

        binding.btVivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.btVivo);
            }
        });

        binding.btRealme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.btRealme);
            }
        });

        binding.tvPrice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvPrice1);
            }
        });

        binding.tvPrice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvPrice2);
            }
        });

        binding.tvPrice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvPrice3);
            }
        });

        binding.tvPrice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvPrice4);
            }
        });

        binding.tvPrice5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvPrice5);
            }
        });

        binding.tvPrice6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvPrice6);
            }
        });

        binding.tvType1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvType1);
            }
        });

        binding.tvType2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvType2);
            }
        });

        binding.tvRam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvRam2);
            }
        });

        binding.tvRam3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvRam3);
            }
        });

        binding.tvRam4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvRam4);
            }
        });

        binding.tvRam6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvRam6);
            }
        });

        binding.tvRam8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvRam8);
            }
        });

        binding.tvRam12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvRam12);
            }
        });

        binding.tvRom32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvRom32);
            }
        });

        binding.tvRom64.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvRom64);
            }
        });

        binding.tvRom128.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvRom128);
            }
        });

        binding.tvRom256.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvRom256);
            }
        });

        binding.tvRom512.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvRom512);
            }
        });

        binding.tvRom1024.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvRom1024);
            }
        });

        binding.tvScreen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvScreen1);
            }
        });

        binding.tvScreen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterList(binding.tvScreen2);
            }
        });

    }

    private void addFilterList(View view){
        if(!view.isSelected()){
            view.setBackgroundResource(R.drawable.bg_gray_corner_10);
            listFilter.add(view.getContentDescription().toString());
            view.setSelected(true);
        } else {
            view.setBackgroundResource(R.drawable.bg_white_corner_10);
            view.setSelected(false);
            listFilter.remove(view.getContentDescription().toString());
        }
        Log.d(TAG, listFilter.toString());
    }

    @Override
    protected FragmentFilterBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentFilterBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }
}
