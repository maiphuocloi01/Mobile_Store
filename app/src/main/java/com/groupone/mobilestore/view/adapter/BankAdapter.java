package com.groupone.mobilestore.view.adapter;

import static com.groupone.mobilestore.util.NumberUtils.hideCardNumber;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.LayoutItemBankBinding;
import com.groupone.mobilestore.model.Bank;

import java.util.List;

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.BankViewHolder> {

    private Context context;
    private List<Bank> listBank;

    public BankAdapter(Context context, List<Bank> listBank) {
        this.context = context;
        this.listBank = listBank;
    }

    @NonNull
    @Override
    public BankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemBankBinding binding = LayoutItemBankBinding.inflate(LayoutInflater.from(context), parent, false);
        return new BankViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BankViewHolder holder, int position) {
        Bank item = listBank.get(position);
        holder.binding.tvBrand.setText(item.getBrand());
        holder.binding.tvName.setText(item.getName());
        holder.binding.tvCardNumber.setText(hideCardNumber(item.getCardNumber()));

        holder.binding.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        if(item.getBrand().equals("Thẻ VISA")) {
            holder.binding.ivLogo.setImageResource(R.drawable.ic_visa);
        } else if(item.getBrand().equals("Vietcombank")){
            holder.binding.ivLogo.setImageResource(R.drawable.ic_vietcombank);
        }

    }

    @Override
    public int getItemCount() {
        return listBank.size();
    }

    public class BankViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemBankBinding binding;
        public BankViewHolder(LayoutItemBankBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}
