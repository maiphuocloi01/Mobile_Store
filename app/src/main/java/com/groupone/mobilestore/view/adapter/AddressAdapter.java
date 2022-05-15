package com.groupone.mobilestore.view.adapter;

import static com.groupone.mobilestore.util.ViewUtils.inv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groupone.mobilestore.databinding.LayoutItemAddressBinding;
import com.groupone.mobilestore.model.Shipment;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {


    private List<Shipment> listAddress;
    private Context context;

    private AddressCallback callback;

    public interface AddressCallback{
        void gotoEditAddress(Shipment shipment);
    }

    public AddressAdapter(Context context, List<Shipment> listAddress, AddressCallback callback) {
        this.callback = callback;
        this.listAddress = listAddress;
        this.context = context;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemAddressBinding binding = LayoutItemAddressBinding.inflate(LayoutInflater.from(context), parent, false);
        return new AddressViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        Shipment address = listAddress.get(position);
        holder.binding.tvName.setText(address.getFullName());
        holder.binding.tvPhone.setText(address.getPhoneNumber());
        holder.binding.tvAddress.setText(address.getAddress());
        holder.binding.tvStreet.setText(address.getStreet());
        if (!address.isDefault()) {
            inv(holder.binding.tvDefault);
        }

        if (!address.isTypeAddress()) {
            holder.binding.tvTypeAddress.setText("Nhà riêng");
        } else {
            holder.binding.tvTypeAddress.setText("Văn phòng");
        }

        holder.binding.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.gotoEditAddress(address);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listAddress.size();
    }

    public class AddressViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemAddressBinding binding;

        public AddressViewHolder(LayoutItemAddressBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
