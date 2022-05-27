package com.groupone.mobilestore.view.adapter;

import static com.groupone.mobilestore.util.NumberUtils.convertParentheses;
import static com.groupone.mobilestore.util.NumberUtils.convertPrice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.groupone.mobilestore.databinding.LayoutItemFavoriteBinding;
import com.groupone.mobilestore.databinding.LayoutItemProductBinding;
import com.groupone.mobilestore.model.Product;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private final Context context;
    private final List<Product> listProduct;
    private FavoriteCallBack callBack;

    public interface FavoriteCallBack{
        void gotoProduct(Product product);
        void deleteFavoriteProduct(int id);
    }

    public FavoriteAdapter(Context context, List<Product> listProduct, FavoriteCallBack callBack) {
        this.context = context;
        this.listProduct = listProduct;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemFavoriteBinding itemProductBinding = LayoutItemFavoriteBinding.inflate(LayoutInflater.from(context), parent, false);
        return new FavoriteViewHolder(itemProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.FavoriteViewHolder holder, int position) {
        Product product = listProduct.get(position);
        holder.itemProductBinding.tvName.setText(product.getName());
        holder.itemProductBinding.tvType.setText(product.getType());
        holder.itemProductBinding.tvPrice.setText(convertPrice(product.getPrice()));
        holder.itemProductBinding.tvRate.setText(String.valueOf(product.getRate()));
        holder.itemProductBinding.tvCountReview.setText(convertParentheses(product.getCountReview()));
        Glide.with(context).load(product.getImage1()).into(holder.itemProductBinding.ivProduct);
        holder.itemProductBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.gotoProduct(product);
            }
        });

        holder.itemProductBinding.ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.deleteFavoriteProduct(product.getId());
                listProduct.remove(product);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }


    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemFavoriteBinding itemProductBinding;

        public FavoriteViewHolder(LayoutItemFavoriteBinding itemProductBinding) {
            super(itemProductBinding.getRoot());
            this.itemProductBinding = itemProductBinding;

        }
    }


}
