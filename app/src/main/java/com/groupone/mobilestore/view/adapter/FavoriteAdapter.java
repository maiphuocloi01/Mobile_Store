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

import com.groupone.mobilestore.databinding.LayoutItemProductBinding;
import com.groupone.mobilestore.model.Product;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private final Context context;
    private final List<Product> listProduct;

    private MutableLiveData<Product> productLD = new MutableLiveData<>();

    public LiveData<Product> getProductLD() {
        return productLD;
    }

    public FavoriteAdapter(Context context, List<Product> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
    }

    @NonNull
    @Override
    public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemProductBinding itemProductBinding = LayoutItemProductBinding.inflate(LayoutInflater.from(context), parent, false);
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

        //Glide.with(context).load(product.getImage()).into(holder.itemProductBinding.ivProduct);
        holder.itemProductBinding.ivProduct.setImageResource(product.getImage());
        holder.itemProductBinding.tvName.setTag(product);
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    private void clickItemProduct(Product product) {
        productLD.postValue(product);
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemProductBinding itemProductBinding;

        public FavoriteViewHolder(LayoutItemProductBinding itemProductBinding) {
            super(itemProductBinding.getRoot());
            this.itemProductBinding = itemProductBinding;
            itemProductBinding.viewProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemProductBinding.viewProduct.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                    clickItemProduct((Product) itemProductBinding.tvName.getTag());
                }
            });
        }
    }


}