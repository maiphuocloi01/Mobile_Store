package com.groupone.mobilestore.view.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.groupone.mobilestore.databinding.FragmentAddAddressBinding;
import com.groupone.mobilestore.model.City;
import com.groupone.mobilestore.model.District;
import com.groupone.mobilestore.model.Ward;
import com.groupone.mobilestore.view.adapter.CityAdapter;
import com.groupone.mobilestore.view.adapter.DistrictAdapter;
import com.groupone.mobilestore.view.adapter.WardAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class AddAddressFragment extends BaseFragment<FragmentAddAddressBinding, CommonViewModel> {

    public static final String TAG = AddAddressFragment.class.getName();
    List<City> cities;
    List<District> districts;
    List<Ward> wards;

    private static String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonString;
    }

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {

//        List<City> cities;
//        List<District> districts;
//        List<Ward> wards;
        String jsonFileString = getJsonFromAssets(context, "address.json");
        //Log.i("data", jsonFileString);
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<City>>() {
        }.getType();
        cities = gson.fromJson(jsonFileString, listUserType);
//        for (int i = 0; i < cities.size(); i++) {
//            //Log.i("data", "> Item " + i + " " + cities.get(i).getName());
//            districts = cities.get(i).getDistricts();
//            for(District district: districts){
//                //Log.i("data", "> Item " + i + " " + district.getName());
//                wards = district.getWards();
//                for(Ward ward: wards){
//                    //Log.i("data", "> Item " + i + " " + ward.getName());
//                }
//            }
//
//        }

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });

        binding.etCity.setCursorVisible(false);
        binding.etCity.setShowSoftInputOnFocus(false);
        binding.etCity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    showCityPickerDialog(cities);
                }
            }
        });
        binding.etCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCityPickerDialog(cities);
            }
        });



        binding.etDistrict.setCursorVisible(false);
        binding.etDistrict.setShowSoftInputOnFocus(false);
        binding.etDistrict.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if(districts != null) {
                        showDistrictPickerDialog(districts);
                    } else {
                        binding.etCity.setError("Bạn cần nhập mục này trước");
                    }
                }
            }
        });
        binding.etDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(districts != null) {
                    showDistrictPickerDialog(districts);
                } else {
                    binding.etCity.setError("Bạn cần nhập mục này trước");
                }
            }
        });


        binding.etWard.setCursorVisible(false);
        binding.etWard.setShowSoftInputOnFocus(false);
        binding.etWard.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if(wards != null) {
                        showWardPickerDialog(wards);
                    } else {
                        if (binding.etCity.getText().toString().equals("")) {
                            binding.etCity.setError("Bạn cần nhập mục này trước");
                        }
                        binding.etDistrict.setError("Bạn cần nhập mục này trước");
                    }
                }
            }
        });
        binding.etWard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wards != null) {
                    showWardPickerDialog(wards);
                } else {
                    if (binding.etCity.getText().toString().equals("")) {
                        binding.etCity.setError("Bạn cần nhập mục này trước");
                    }
                    binding.etDistrict.setError("Bạn cần nhập mục này trước");
                }
            }
        });


    }

    private void showWardPickerDialog(List<Ward> wards) {
        WardBottomSheetFragment wardBottomSheetFragment = new WardBottomSheetFragment(wards, new WardAdapter.WardCallback() {
            @Override
            public void chooseWard(int position, Ward ward) {
                binding.etWard.setText(ward.getName());
            }
        });
        wardBottomSheetFragment.show(getParentFragmentManager(), wardBottomSheetFragment.getTag());
    }

    private void showDistrictPickerDialog(List<District> districts) {
        binding.etDistrict.setError(null);
        DistrictBottomSheetFragment districtBottomSheetFragment = new DistrictBottomSheetFragment(districts, new DistrictAdapter.DistrictCallback() {
            @Override
            public void chooseDistrict(int position, District district) {
                binding.etDistrict.setText(district.getName());
                wards = districts.get(position).getWards();
                binding.etWard.setText("");
            }
        });
        districtBottomSheetFragment.show(getParentFragmentManager(), districtBottomSheetFragment.getTag());
    }

    private void showCityPickerDialog(List<City> cities) {
        binding.etCity.setError(null);
        CityBottomSheetFragment cityBottomSheetFragment = new CityBottomSheetFragment(cities, new CityAdapter.CityCallback() {
            @Override
            public void chooseCity(int index, City city) {
                //Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
                binding.etCity.setText(city.getName());
//                for(City item: cities){
//                    item.setSelected(false);
//                }
//                city.setSelected(true);
                districts = cities.get(index).getDistricts();
                binding.etDistrict.setText("");
                binding.etWard.setText("");
                wards = null;
            }
        });
        cityBottomSheetFragment.show(getParentFragmentManager(), cityBottomSheetFragment.getTag());
    }

    @Override
    protected FragmentAddAddressBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentAddAddressBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }
}
