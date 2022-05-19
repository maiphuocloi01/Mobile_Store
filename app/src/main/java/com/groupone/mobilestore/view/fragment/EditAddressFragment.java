package com.groupone.mobilestore.view.fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.databinding.FragmentEditAddressBinding;
import com.groupone.mobilestore.model.City;
import com.groupone.mobilestore.model.District;
import com.groupone.mobilestore.model.Shipment;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.model.Ward;
import com.groupone.mobilestore.view.adapter.CityAdapter;
import com.groupone.mobilestore.view.adapter.DistrictAdapter;
import com.groupone.mobilestore.view.adapter.WardAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditAddressFragment extends BaseFragment<FragmentEditAddressBinding, CommonViewModel> {

    public static final String TAG = EditAddressFragment.class.getName();
    private Object mData;
    private List<City> cities;
    private List<District> districts;
    private List<Ward> wards;
    private final User user = MyApplication.getInstance().getStorage().user;


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

        Shipment shipment = (Shipment) mData;

        List<String> listAddress = new ArrayList<String>(Arrays.asList(shipment.getAddress().split(",")));



        String jsonFileString = getJsonFromAssets(context, "address.json");
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<City>>() {
        }.getType();
        cities = gson.fromJson(jsonFileString, listUserType);

        for(int i = 0; i < cities.size(); i++){
            if(cities.get(i).getName().equals(listAddress.get(2).trim())){
                districts = cities.get(i).getDistricts();
            }
        }

        if(districts != null) {
            for (int i = 0; i < districts.size(); i++) {
                if (districts.get(i).getName().equals(listAddress.get(1).trim())) {
                    wards = districts.get(i).getWards();
                }
            }
        }


        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });

        binding.etName.setText(shipment.getFullName());
        binding.etPhone.setText(shipment.getPhoneNumber());
        binding.etCity.setText(listAddress.get(2).trim());
        binding.etDistrict.setText(listAddress.get(1).trim());
        binding.etWard.setText(listAddress.get(0).trim());
        binding.etStreet.setText(shipment.getStreet());
        if(shipment.isTypeAddress()) {
            binding.rbOffice.setChecked(true);
        } else {
            binding.rbHome.setChecked(true);
        }

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

    @Override
    protected FragmentEditAddressBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentEditAddressBinding.inflate(inflater, container, false);
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
                binding.etCity.setText(city.getName());
                districts = cities.get(index).getDistricts();
                binding.etDistrict.setText("");
                binding.etWard.setText("");
                wards = null;
            }
        });
        cityBottomSheetFragment.show(getParentFragmentManager(), cityBottomSheetFragment.getTag());
    }

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }

    @Override
    public void setData(Object data) {
        this.mData = data;
    }
}
