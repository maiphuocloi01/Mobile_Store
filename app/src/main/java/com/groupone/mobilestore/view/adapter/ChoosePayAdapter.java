package com.groupone.mobilestore.view.adapter;

import static com.groupone.mobilestore.util.NumberUtils.hideCardNumber;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.LayoutItemChoosepayBinding;
import com.groupone.mobilestore.model.Bank;
import com.groupone.mobilestore.view.fragment.ChooseAddressFragment;

import java.util.List;

public class ChoosePayAdapter extends RecyclerView.Adapter<ChoosePayAdapter.MyViewHolder> {

    private Context context;
    private List<Bank> listBank;
    private ChoosePayCallBack callBack;

    public interface ChoosePayCallBack{
        void choosePayment(Bank bank);
    }

    public ChoosePayAdapter(Context context, List<Bank> listBank, ChoosePayCallBack callBack) {
        this.context = context;
        this.listBank = listBank;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemChoosepayBinding binding = LayoutItemChoosepayBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Bank item = listBank.get(position);
        holder.binding.tvBrand.setText(item.getBrand());
        holder.binding.tvName.setText(item.getName());
        holder.binding.tvCardNumber.setText(hideCardNumber(item.getCardNumber()));

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.choosePayment(item);
            }
        });

        if(item.getBrand().equals("Thẻ VISA")) {
            holder.binding.ivLogo.setImageResource(R.drawable.ic_visa);
        } else if(item.getBrand().equals("Vietcombank")){
            holder.binding.ivLogo.setImageResource(R.drawable.ic_vietcombank);
        } else if(item.getBrand().equals("BIDV")){
            holder.binding.ivLogo.setImageResource(R.drawable.ic_bidv);
        } else if(item.getBrand().equals("Techcombank")){
            holder.binding.ivLogo.setImageResource(R.drawable.ic_techcombank);
        } else if(item.getBrand().equals("Vietinbank")){
            holder.binding.ivLogo.setImageResource(R.drawable.ic_viettinbank);
        }
    }

    @Override
    public int getItemCount() {
        if(listBank != null){
            return listBank.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemChoosepayBinding binding;
        public MyViewHolder(LayoutItemChoosepayBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
