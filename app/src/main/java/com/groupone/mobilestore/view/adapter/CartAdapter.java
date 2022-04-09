package com.groupone.mobilestore.view.adapter;

import static com.groupone.mobilestore.util.ConvertString.convertPrice;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.LayoutItemCartBinding;
import com.groupone.mobilestore.model.Cart;
import com.groupone.mobilestore.view.fragment.HomeFragment;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private static final String TAG = HomeFragment.TAG;

    private Context context;
    private List<Cart> listItem;

    public CartAdapter(Context context, List<Cart> listItem) {
        this.context = context;
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemCartBinding binding = LayoutItemCartBinding.inflate(LayoutInflater.from(context), parent, false);

        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart item = listItem.get(position);
        holder.binding.tvName.setText(item.getName());
        holder.binding.tvPrice.setText(convertPrice(item.getPrice()));
        holder.binding.tvType.setText(item.getType() + ", " + item.getColor());
        holder.binding.tvQty.setText(item.getQty() + "");
        holder.binding.ivProduct.setImageResource(item.getImage());
        holder.binding.tvName.setTag(item);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        private LayoutItemCartBinding binding;
        public CartViewHolder(LayoutItemCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


            //Listen select item in shopping cart
            binding.ivChoose.setOnClickListener(view -> {
                Cart item = (Cart) binding.tvName.getTag();
                AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in);
                if (!item.isSelected()) {
                    binding.ivChoose.setImageResource(R.drawable.ic_checked);
                    binding.ivChoose.setColorFilter(ContextCompat.getColor(context, R.color.blue_500));
                    binding.bgCart.setBackgroundColor(ContextCompat.getColor(context, R.color.blue_200));
                    item.setSelected(true);
                } else {
                    binding.ivChoose.setImageResource(R.drawable.ic_uncheck);
                    binding.ivChoose.setColorFilter(ContextCompat.getColor(context, R.color.black_500));
                    binding.bgCart.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                    item.setSelected(false);
                }
            });

            binding.ivSubtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cart item = (Cart) binding.tvName.getTag();
                    if(item.getQty() > 1){
                        item.setQty(item.getQty() - 1);
                        Log.d(TAG, "onClick: " + item.getQty());
                        notifyDataSetChanged();
                    } else {
                        doDeleteItem(item);
                    }
                }
            });

            binding.ivPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cart item = (Cart) binding.tvName.getTag();
                    item.setQty(item.getQty() + 1);
                    notifyDataSetChanged();
                    Log.d(TAG, "onClick: " + item.getQty());
                }
            });

            binding.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cart item = (Cart) binding.tvName.getTag();
                    doDeleteItem(item);
                }
            });
        }

        private void doDeleteItem(Cart item) {
            Toast.makeText(context, "Delete successfully", Toast.LENGTH_SHORT).show();
        }

    }
}
