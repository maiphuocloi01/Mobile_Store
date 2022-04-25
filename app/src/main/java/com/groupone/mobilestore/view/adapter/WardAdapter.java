package com.groupone.mobilestore.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groupone.mobilestore.databinding.LayoutItemCityBinding;
import com.groupone.mobilestore.model.District;
import com.groupone.mobilestore.model.Ward;

import java.util.List;

public class WardAdapter extends RecyclerView.Adapter<WardAdapter.VH> {

    private Context context;
    private List<Ward> listWard;

    private WardAdapter.WardCallback callback;

    public WardAdapter(Context context, List<Ward> listWard, WardAdapter.WardCallback callback) {
        this.context = context;
        this.listWard = listWard;
        this.callback = callback;
    }

    @NonNull
    @Override
    public WardAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemCityBinding binding = LayoutItemCityBinding.inflate(LayoutInflater.from(context), parent, false);
        return new WardAdapter.VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WardAdapter.VH holder, int position) {
        Ward item = listWard.get(position);
        holder.binding.tvName.setText(item.getName());
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.chooseWard(holder.getAdapterPosition(), item);
            }
        });
//        if (item.isSelected()) {
//            holder.binding.ivChecked.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public int getItemCount() {
        return listWard.size();
    }

    public interface WardCallback {
        void chooseWard(int position, Ward ward);
    }

    public class VH extends RecyclerView.ViewHolder {
        private LayoutItemCityBinding binding;

        public VH(LayoutItemCityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

