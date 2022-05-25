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
import com.groupone.mobilestore.view.adapter.BankBrandAdapter;

import java.util.List;

public class BankBottomSheetFragment extends BottomSheetDialogFragment {


    private List<String> listBank;
    private BankBrandAdapter.BankCallback callback;

    public BankBottomSheetFragment(List<String> listBank, BankBrandAdapter.BankCallback callback) {
        this.listBank = listBank;
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

        tvTitle.setText("Chọn ngân hàng");
        rvCity.setLayoutManager(new LinearLayoutManager(getContext()));

        BankBrandAdapter adapter = new BankBrandAdapter(getContext(), listBank, new BankBrandAdapter.BankCallback() {
            @Override
            public void chooseBank(int position, String name) {
                callback.chooseBank(position, name);
                dismiss();
            }
        });
        rvCity.setAdapter(adapter);
        rvCity.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return bottomSheetDialog;
    }
}
