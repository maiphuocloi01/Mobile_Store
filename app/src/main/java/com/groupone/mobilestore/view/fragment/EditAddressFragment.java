package com.groupone.mobilestore.view.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentEditAddressBinding;
import com.groupone.mobilestore.model.City;
import com.groupone.mobilestore.model.District;
import com.groupone.mobilestore.model.Shipment;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.model.Ward;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.util.DialogUtils;
import com.groupone.mobilestore.view.adapter.CityAdapter;
import com.groupone.mobilestore.view.adapter.DistrictAdapter;
import com.groupone.mobilestore.view.adapter.WardAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;
import com.groupone.mobilestore.viewmodel.ShipmentViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditAddressFragment extends BaseFragment<FragmentEditAddressBinding, ShipmentViewModel> {

    public static final String TAG = EditAddressFragment.class.getName();
    private Object mData;
    private List<City> cities;
    private List<District> districts;
    private List<Ward> wards;
    private final User user = MyApplication.getInstance().getStorage().user;
    private Shipment shipment;

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
    protected Class<ShipmentViewModel> getClassVM() {
        return ShipmentViewModel.class;
    }

    @Override
    protected void initViews() {

        shipment = (Shipment) mData;

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

        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newName = binding.etName.getText().toString();
                String newPhone = binding.etPhone.getText().toString();
                String newCity = binding.etCity.getText().toString();
                String newDistrict = binding.etDistrict.getText().toString();
                String newWard = binding.etWard.getText().toString();
                String newStreet = binding.etStreet.getText().toString();
                boolean newType = binding.rbOffice.isChecked();

                if (TextUtils.isEmpty(binding.etName.getText())) {
                    binding.etName.setError("Không được bỏ trống");

                } else if (TextUtils.isEmpty(binding.etPhone.getText())) {
                    binding.etPhone.setError("Không được bỏ trống");

                } else if (TextUtils.isEmpty(binding.etCity.getText())) {
                    binding.etCity.setError("Không được bỏ trống");

                } else if (TextUtils.isEmpty(binding.etDistrict.getText())) {
                    binding.etDistrict.setError("Không được bỏ trống");

                } else if (TextUtils.isEmpty(binding.etWard.getText())) {
                    binding.etWard.setError("Không được bỏ trống");

                } else if (TextUtils.isEmpty(binding.etStreet.getText())) {
                    binding.etStreet.setError("Không được bỏ trống");

                } else if (!binding.rbHome.isChecked() && !binding.rbOffice.isChecked()) {
                    Toast.makeText(context, "Chọn loại địa chỉ", Toast.LENGTH_SHORT).show();
                } else if (!newName.equals(shipment.getFullName()) ||
                        !newPhone.equals(shipment.getPhoneNumber()) ||
                        !newStreet.equals(shipment.getStreet()) ||
                        !newCity.equals(listAddress.get(2).trim()) ||
                        !newDistrict.equals(listAddress.get(1).trim()) ||
                        !newWard.equals(listAddress.get(0).trim()) ||
                        newType != shipment.isTypeAddress()
                ) {

                    String ward = binding.etWard.getText().toString().trim();
                    String district = binding.etDistrict.getText().toString().trim();
                    String city = binding.etCity.getText().toString().trim();
                    boolean typeAddress = false;
                    if (binding.rbOffice.isChecked()) {
                        typeAddress = true;
                    }
                    Shipment updateShipment = new Shipment(shipment.getId(),
                            user.getId(),
                            binding.etName.getText().toString().trim(),
                            binding.etPhone.getText().toString().trim(),
                            String.format(ward + ", " + district + ", " + city),
                            binding.etStreet.getText().toString().trim(),
                            typeAddress,
                            false
                    );
                    viewModel.updateShipment(updateShipment);
                    DialogUtils.showLoadingDialog(context);
                }
                else {
                    Toast.makeText(context, "Không có thông tin nào thay đổi", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

    }

    @Override
    protected FragmentEditAddressBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentEditAddressBinding.inflate(inflater, container, false);
    }

    private void showAlertDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_alert_dialog);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);

        dialog.setCancelable(false);

        TextView tvTitle = dialog.findViewById(R.id.tv_title);
        TextView tvDescription = dialog.findViewById(R.id.tv_description);
        Button btnCancel = dialog.findViewById(R.id.bt_cancel3);
        Button btnConfirm = dialog.findViewById(R.id.bt_confirm3);

        tvTitle.setText("Thông báo");
        tvDescription.setText("Bạn có muốn xoá địa chỉ này không?");

        btnCancel.setOnClickListener(view -> dialog.dismiss());

        btnConfirm.setOnClickListener(view -> {
            viewModel.deleteShipmentById(shipment.getId());
            DialogUtils.showLoadingDialog(context);
            dialog.dismiss();
        });


        dialog.show();
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
        if(key.equals(Constants.KEY_UPDATE_SHIPMENT)){
            boolean response = (boolean) data;
            if(!response){
                Toast.makeText(context, "Sửa địa chỉ thất bại", Toast.LENGTH_SHORT).show();
                DialogUtils.hideLoadingDialog();
            } else {
                Toast.makeText(context, "Sửa địa chỉ thành công", Toast.LENGTH_SHORT).show();
                viewModel.getShipmentByAccountId(user.getId());
            }
        } else if(key.equals(Constants.KEY_DELETE_SHIPMENT)){
            boolean response = (boolean) data;
            if(!response){
                Toast.makeText(context, "Xoá địa chỉ thất bại", Toast.LENGTH_SHORT).show();
                DialogUtils.hideLoadingDialog();
            } else {
                Toast.makeText(context, "Xoá địa chỉ thành công", Toast.LENGTH_SHORT).show();
                viewModel.getShipmentByAccountId(user.getId());
            }
        }
        else if(key.equals(Constants.KEY_GET_SHIPMENT_BY_ACCOUNT)){
            MyApplication.getInstance().getStorage().listShipment = (List<Shipment>) data;
            DialogUtils.hideLoadingDialog();
            callBack.backToPrev();
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {

    }

    @Override
    public void setData(Object data) {
        this.mData = data;
    }
}
