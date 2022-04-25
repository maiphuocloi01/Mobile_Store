package com.groupone.mobilestore.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groupone.mobilestore.databinding.LayoutItemCityBinding;
import com.groupone.mobilestore.model.City;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.VH> {

    private Context context;
    private List<City> listCity;

    private CityCallback callback;

    public CityAdapter(Context context, List<City> listCity, CityCallback callback) {
        this.context = context;
        this.listCity = listCity;
        this.callback = callback;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemCityBinding binding = LayoutItemCityBinding.inflate(LayoutInflater.from(context), parent, false);
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        City item = listCity.get(position);
        holder.binding.tvName.setText(item.getName());
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.chooseCity(holder.getAdapterPosition(), item);
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

    public interface CityCallback {
        void chooseCity(int position, City city);
    }

    public class VH extends RecyclerView.ViewHolder {
        private LayoutItemCityBinding binding;

        public VH(LayoutItemCityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
