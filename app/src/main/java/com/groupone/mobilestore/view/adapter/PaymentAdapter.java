package com.groupone.mobilestore.view.adapter;

import static com.groupone.mobilestore.util.NumberUtils.convertPrice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.databinding.LayoutItemPaymentBinding;
import com.groupone.mobilestore.model.Product;
import com.groupone.mobilestore.model.ShoppingCart;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.MyViewHolder> {

    private Context context;
    private List<ShoppingCart> listProduct;
    private PaymentCallBack callBack;

    public interface PaymentCallBack{
        void gotoProductDetail(int productId);
    }

    public PaymentAdapter(Context context, List<ShoppingCart> listProduct, PaymentCallBack callBack) {
        this.context = context;
        this.listProduct = listProduct;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemPaymentBinding binding = LayoutItemPaymentBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ShoppingCart item = listProduct.get(position);
        List<Product> productList = MyApplication.getInstance().getStorage().listProduct;

        for (Product product : productList){
            if(product.getId() == item.getProductId()){
                Glide.with(context).load(product.getImage1()).into(holder.binding.ivProduct);
                holder.binding.tvPrice.setText(convertPrice(item.getPrice()));
                holder.binding.tvType.setText(item.getTypeProduct());
                holder.binding.tvCount.setText(String.format(item.getQuantity() + " sản phẩm"));
                holder.binding.tvName.setText(product.getName());
                break;
            }
        }
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.gotoProductDetail(item.getProductId());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listProduct != null){
            return listProduct.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemPaymentBinding binding;
        public MyViewHolder(LayoutItemPaymentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
