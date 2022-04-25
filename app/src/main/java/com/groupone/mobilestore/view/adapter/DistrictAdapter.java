package com.groupone.mobilestore.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groupone.mobilestore.databinding.LayoutItemCityBinding;
import com.groupone.mobilestore.model.City;
import com.groupone.mobilestore.model.District;

import java.util.List;

public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.VH> {

    private Context context;
    private List<District> listCity;

    private DistrictAdapter.DistrictCallback callback;

    public DistrictAdapter(Context context, List<District> listCity, DistrictAdapter.DistrictCallback callback) {
        this.context = context;
        this.listCity = listCity;
        this.callback = callback;
    }

    @NonNull
    @Override
    public DistrictAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemCityBinding binding = LayoutItemCityBinding.inflate(LayoutInflater.from(context), parent, false);
        return new DistrictAdapter.VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DistrictAdapter.VH holder, int position) {
        District item = listCity.get(position);
        holder.binding.tvName.setText(item.getName());
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.chooseDistrict(holder.getAdapterPosition(), item);
            }
        });
//        if (item.isSelected()) {
//            holder.binding.ivChecked.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public int getItemCount() {
        return listCity.size();
    }

    public interface DistrictCallback {
        void chooseDistrict(int position, District district);
    }

    public class VH extends RecyclerView.ViewHolder {
        private LayoutItemCityBinding binding;

        public VH(LayoutItemCityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

