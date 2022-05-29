package com.groupone.mobilestore.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

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
    private final ArrayList<String> listFilterBrand = new ArrayList<>();
    private final ArrayList<String> listFilterPrice = new ArrayList<>();
    private final ArrayList<String> listFilterCategory = new ArrayList<>();
    private final ArrayList<String> listFilterRam = new ArrayList<>();
    private final ArrayList<String> listFilterRom = new ArrayList<>();
    private final ArrayList<String> listFilterScreen = new ArrayList<>();

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
                if(listFilterBrand.size() > 0 || listFilterPrice.size() > 0
                        || listFilterCategory.size() > 0
                        || listFilterRam.size() > 0
                        || listFilterRom.size() > 0
                        || listFilterScreen.size() > 0
                ) {
                    Bundle bundle = new Bundle();
                    //bundle.putStringArrayList("filter", listFilter);
                    bundle.putStringArrayList("filterBrand", listFilterBrand);
                    bundle.putStringArrayList("filterPrice", listFilterPrice);
                    bundle.putStringArrayList("filterCategory", listFilterCategory);
                    bundle.putStringArrayList("filterRam", listFilterRam);
                    bundle.putStringArrayList("filterRom", listFilterRom);
                    bundle.putStringArrayList("filterScreen", listFilterScreen);
                    callBack.showFragment(SearchResultFragment.TAG, bundle, false);
                } else {
                    Toast.makeText(context, "Chưa có mục nào được chọn", Toast.LENGTH_SHORT).show();
                }
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
                addFilterBrandList(binding.btApple);
            }
        });

        binding.btSamsung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterBrandList(binding.btSamsung);
            }
        });

        binding.btXiaomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterBrandList(binding.btXiaomi);
            }
        });

        binding.btOppo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterBrandList(binding.btOppo);
            }
        });

        binding.btNokia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterBrandList(binding.btNokia);
            }
        });

        binding.btAsus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterBrandList(binding.btAsus);
            }
        });

        binding.btVivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterBrandList(binding.btVivo);
            }
        });

        binding.btRealme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterBrandList(binding.btRealme);
            }
        });

        binding.tvPrice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterPriceList(binding.tvPrice1);
            }
        });

        binding.tvPrice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterPriceList(binding.tvPrice2);
            }
        });

        binding.tvPrice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterPriceList(binding.tvPrice3);
            }
        });

        binding.tvPrice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterPriceList(binding.tvPrice4);
            }
        });

        binding.tvPrice5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterPriceList(binding.tvPrice5);
            }
        });

        binding.tvPrice6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterPriceList(binding.tvPrice6);
            }
        });

        binding.tvType1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterCategoryList(binding.tvType1);
            }
        });

        binding.tvType2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterCategoryList(binding.tvType2);
            }
        });

        binding.tvRam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterRamList(binding.tvRam2);
            }
        });

        binding.tvRam3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterRamList(binding.tvRam3);
            }
        });

        binding.tvRam4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterRamList(binding.tvRam4);
            }
        });

        binding.tvRam6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterRamList(binding.tvRam6);
            }
        });

        binding.tvRam8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterRamList(binding.tvRam8);
            }
        });

        binding.tvRam12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterRamList(binding.tvRam12);
            }
        });

        binding.tvRom32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterRomList(binding.tvRom32);
            }
        });

        binding.tvRom64.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterRomList(binding.tvRom64);
            }
        });

        binding.tvRom128.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterRomList(binding.tvRom128);
            }
        });

        binding.tvRom256.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterRomList(binding.tvRom256);
            }
        });

        binding.tvRom512.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterRomList(binding.tvRom512);
            }
        });

        binding.tvRom1024.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterRomList(binding.tvRom1024);
            }
        });

        binding.tvScreen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterScreenList(binding.tvScreen1);
            }
        });

        binding.tvScreen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilterScreenList(binding.tvScreen2);
            }
        });

        binding.btCancel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetAllView();
            }
        });

    }

    /*private void addFilterList(View view){
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
    }*/

    private void resetAllView(){
        resetSelectView(binding.btApple);
        resetSelectView(binding.btAsus);
        resetSelectView(binding.btSamsung);
        resetSelectView(binding.btXiaomi);
        resetSelectView(binding.btOppo);
        resetSelectView(binding.btNokia);
        resetSelectView(binding.btVivo);
        resetSelectView(binding.btRealme);
        resetSelectView(binding.tvPrice1);
        resetSelectView(binding.tvPrice2);
        resetSelectView(binding.tvPrice3);
        resetSelectView(binding.tvPrice4);
        resetSelectView(binding.tvPrice5);
        resetSelectView(binding.tvPrice6);
        resetSelectView(binding.tvType1);
        resetSelectView(binding.tvType2);
        resetSelectView(binding.tvRam2);
        resetSelectView(binding.tvRam3);
        resetSelectView(binding.tvRam4);
        resetSelectView(binding.tvRam6);
        resetSelectView(binding.tvRam8);
        resetSelectView(binding.tvRam12);
        resetSelectView(binding.tvRom32);
        resetSelectView(binding.tvRom64);
        resetSelectView(binding.tvRom128);
        resetSelectView(binding.tvRom256);
        resetSelectView(binding.tvRom512);
        resetSelectView(binding.tvRom1024);
        resetSelectView(binding.tvScreen1);
        resetSelectView(binding.tvScreen2);
        listFilterBrand.clear();
        listFilterPrice.clear();
        listFilterCategory.clear();
        listFilterRam.clear();
        listFilterRom.clear();
        listFilterScreen.clear();
    }

    private void resetSelectView(View view){
        if(view.isSelected()){
            view.setBackgroundResource(R.drawable.bg_white_corner_10);
            view.setSelected(false);
        }
    }

    private void addFilterBrandList(View view){
        if(!view.isSelected()){
            view.setBackgroundResource(R.drawable.bg_gray_corner_10);
            listFilterBrand.add(view.getContentDescription().toString());
            view.setSelected(true);
        } else {
            view.setBackgroundResource(R.drawable.bg_white_corner_10);
            view.setSelected(false);
            listFilterBrand.remove(view.getContentDescription().toString());
        }
    }

    private void addFilterPriceList(View view){
        if(!view.isSelected()){
            view.setBackgroundResource(R.drawable.bg_gray_corner_10);
            listFilterPrice.add(view.getContentDescription().toString());
            view.setSelected(true);
        } else {
            view.setBackgroundResource(R.drawable.bg_white_corner_10);
            view.setSelected(false);
            listFilterPrice.remove(view.getContentDescription().toString());
        }
    }

    private void addFilterCategoryList(View view){
        if(!view.isSelected()){
            view.setBackgroundResource(R.drawable.bg_gray_corner_10);
            listFilterCategory.add(view.getContentDescription().toString());
            view.setSelected(true);
        } else {
            view.setBackgroundResource(R.drawable.bg_white_corner_10);
            view.setSelected(false);
            listFilterCategory.remove(view.getContentDescription().toString());
        }
    }

    private void addFilterRamList(View view){
        if(!view.isSelected()){
            view.setBackgroundResource(R.drawable.bg_gray_corner_10);
            listFilterRam.add(view.getContentDescription().toString());
            view.setSelected(true);
        } else {
            view.setBackgroundResource(R.drawable.bg_white_corner_10);
            view.setSelected(false);
            listFilterRam.remove(view.getContentDescription().toString());
        }
    }

    private void addFilterRomList(View view){
        if(!view.isSelected()){
            view.setBackgroundResource(R.drawable.bg_gray_corner_10);
            listFilterRom.add(view.getContentDescription().toString());
            view.setSelected(true);
        } else {
            view.setBackgroundResource(R.drawable.bg_white_corner_10);
            view.setSelected(false);
            listFilterRom.remove(view.getContentDescription().toString());
        }
    }

    private void addFilterScreenList(View view){
        if(!view.isSelected()){
            view.setBackgroundResource(R.drawable.bg_gray_corner_10);
            listFilterScreen.add(view.getContentDescription().toString());
            view.setSelected(true);
        } else {
            view.setBackgroundResource(R.drawable.bg_white_corner_10);
            view.setSelected(false);
            listFilterScreen.remove(view.getContentDescription().toString());
        }
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
