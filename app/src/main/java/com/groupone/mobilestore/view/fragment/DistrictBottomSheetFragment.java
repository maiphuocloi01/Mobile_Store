package com.groupone.mobilestore.view.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.model.City;
import com.groupone.mobilestore.model.District;
import com.groupone.mobilestore.view.adapter.CityAdapter;
import com.groupone.mobilestore.view.adapter.DistrictAdapter;

import java.util.List;

public class DistrictBottomSheetFragment extends BottomSheetDialogFragment {

    private List<District> districtList;
    private DistrictAdapter.DistrictCallback callback;


    public DistrictBottomSheetFragment(List<District> districtList, DistrictAdapter.DistrictCallback callback) {
        this.districtList = districtList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_sheet_city, null);
        bottomSheetDialog.setContentView(view);

        RecyclerView rvCity = view.findViewById(R.id.rv_city);
        TextView tvTitle = view.findViewById(R.id.tv_title);

        tvTitle.setText("Chọn Quận/Huyện");
        rvCity.setLayoutManager(new LinearLayoutManager(getContext()));

        DistrictAdapter adapter = new DistrictAdapter(getContext(), districtList, new DistrictAdapter.DistrictCallback() {
            @Override
            public void chooseDistrict(int position, District district) {
                callback.chooseDistrict(position, district);
                dismiss();
            }
        });
        rvCity.setAdapter(adapter);
        rvCity.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return bottomSheetDialog;
    }
}
