package com.groupone.mobilestore.view.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.LayoutBottomSheetCityBinding;
import com.groupone.mobilestore.model.City;
import com.groupone.mobilestore.model.District;
import com.groupone.mobilestore.view.adapter.CityAdapter;
import com.groupone.mobilestore.view.adapter.DistrictAdapter;

import java.util.List;

public class CityBottomSheetFragment extends BottomSheetDialogFragment {

    private List<City> cityList;
    private CityAdapter.CityCallback callback;

    private List<District> districtList;
    private DistrictAdapter.DistrictCallback callback2;

    public CityBottomSheetFragment(List<City> cityList, CityAdapter.CityCallback callback) {
        this.cityList = cityList;
        this.callback = callback;
    }

    public CityBottomSheetFragment(List<District> districtList, DistrictAdapter.DistrictCallback callback2) {
        this.districtList = districtList;
        this.callback2 = callback2;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_sheet_city, null);
        bottomSheetDialog.setContentView(view);

        RecyclerView rvCity = view.findViewById(R.id.rv_city);
        rvCity.setLayoutManager(new LinearLayoutManager(getContext()));

        CityAdapter adapter = new CityAdapter(getContext(), cityList, new CityAdapter.CityCallback() {
            @Override
            public void chooseCity(int index, City city) {
                callback.chooseCity(index, city);
                dismiss();
            }
        });
        rvCity.setAdapter(adapter);
        rvCity.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return bottomSheetDialog;
    }
}
