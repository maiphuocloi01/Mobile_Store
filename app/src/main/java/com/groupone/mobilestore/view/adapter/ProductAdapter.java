package com.groupone.mobilestore.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groupone.mobilestore.databinding.LayoutItemProductBinding;
import com.groupone.mobilestore.model.Product;

import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final Context context;
    private final List<Product> listProduct;

    public ProductAdapter(Context context, List<Product> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemProductBinding itemProductBinding = LayoutItemProductBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ProductViewHolder(itemProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product product = listProduct.get(position);
        holder.itemProductBinding.tvName.setText(product.getName());
        holder.itemProductBinding.tvType.setText(product.getType());
        holder.itemProductBinding.tvPrice.setText(convertPrice(product.getPrice()));
        holder.itemProductBinding.tvRate.setText(String.valueOf(product.getRate()));
        holder.itemProductBinding.tvCountReview.setText(convertCountReview(product.getCountReview()));

        //Glide.with(context).load(product.getImage()).into(holder.itemProductBinding.ivProduct);
        holder.itemProductBinding.ivProduct.setImageResource(product.getImage());
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemProductBinding itemProductBinding;

        public ProductViewHolder(LayoutItemProductBinding itemProductBinding) {
            super(itemProductBinding.getRoot());
            this.itemProductBinding = itemProductBinding;
        }
    }

    public static String convertPrice(long price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(price) + "₫";
    }

    public static String convertCountReview(int count) {
        return "(" + count + ")";
    }

}
