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

public class BankBrandAdapter extends RecyclerView.Adapter<BankBrandAdapter.MyViewHolder> {

    private Context context;
    private List<String> listName;

    private BankCallback callback;

    public interface BankCallback {
        void chooseBank(int position, String name);
    }

    public BankBrandAdapter(Context context, List<String> listName, BankCallback callback) {
        this.context = context;
        this.listName = listName;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemCityBinding binding = LayoutItemCityBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String item = listName.get(position);
        holder.binding.tvName.setText(item);
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.chooseBank(holder.getAdapterPosition(), item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemCityBinding binding;
        public MyViewHolder(LayoutItemCityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
