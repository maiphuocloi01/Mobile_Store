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
import com.groupone.mobilestore.model.District;
import com.groupone.mobilestore.model.Ward;
import com.groupone.mobilestore.view.adapter.DistrictAdapter;
import com.groupone.mobilestore.view.adapter.WardAdapter;

import java.util.List;

public class WardBottomSheetFragment extends BottomSheetDialogFragment {

    private List<Ward> wardList;
    private WardAdapter.WardCallback callback;


    public WardBottomSheetFragment(List<Ward> wardList, WardAdapter.WardCallback callback) {
        this.wardList = wardList;
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

        tvTitle.setText("Chọn Xã/Phường");
        rvCity.setLayoutManager(new LinearLayoutManager(getContext()));

        WardAdapter adapter = new WardAdapter(getContext(), wardList, new WardAdapter.WardCallback() {
            @Override
            public void chooseWard(int position, Ward ward) {
                callback.chooseWard(position, ward);
                dismiss();
            }
        });
        rvCity.setAdapter(adapter);
        rvCity.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return bottomSheetDialog;
    }
}
