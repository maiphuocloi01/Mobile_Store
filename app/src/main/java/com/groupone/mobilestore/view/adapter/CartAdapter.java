package com.groupone.mobilestore.view.adapter;

import static com.groupone.mobilestore.util.NumberUtils.convertPrice;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.LayoutItemCartBinding;
import com.groupone.mobilestore.model.Product;
import com.groupone.mobilestore.model.ShoppingCart;
import com.groupone.mobilestore.view.fragment.HomeFragment;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private static final String TAG = HomeFragment.TAG;


    private Context context;
    private List<ShoppingCart> listItem;
    private CartCallBack callBack;

    public interface CartCallBack{
        void updateQuantity(int id, int quantity);
        void deleteItemCart(int id);
    }

    private MutableLiveData<List<ShoppingCart>> listCartLD = new MutableLiveData<>();

    public LiveData<List<ShoppingCart>> getListCartLD() {
        return listCartLD;
    }

    public CartAdapter(Context context, List<ShoppingCart> listItem, CartCallBack callBack) {
        this.callBack = callBack;
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
        ShoppingCart item = listItem.get(position);
        List<Product> productList = MyApplication.getInstance().getStorage().listProduct;

        for (Product product : productList){
            if(product.getId() == item.getProductId()){
                Glide.with(context).load(product.getImage1()).into(holder.binding.ivProduct);
                holder.binding.tvPrice.setText(convertPrice(item.getPrice()));
                holder.binding.tvType.setText(item.getTypeProduct());
                holder.binding.tvQty.setText(String.valueOf(item.getQuantity()));
                holder.binding.tvName.setText(product.getName());
                holder.binding.tvName.setTag(item);
                break;
            }
        }

        //Listen select item in shopping cart
        holder.binding.ivChoose.setOnClickListener(view -> {
            holder.binding.ivChoose.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
            //ShoppingCart item = (ShoppingCart) binding.tvName.getTag();
            //int index = listItem.indexOf(item);
            if (!item.isSelected()) {
                holder.binding.ivChoose.setImageResource(R.drawable.ic_checked);
                holder.binding.ivChoose.setColorFilter(ContextCompat.getColor(context, R.color.blue_500));
                holder.binding.bgCart.setBackgroundColor(ContextCompat.getColor(context, R.color.blue_200));
                item.setSelected(true);
            } else {
                holder.binding.ivChoose.setImageResource(R.drawable.ic_uncheck);
                holder.binding.ivChoose.setColorFilter(ContextCompat.getColor(context, R.color.black_500));
                holder.binding.bgCart.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                item.setSelected(false);
            }
            changeListCartListener(item, position, false);
        });

        holder.binding.ivSubtract.setOnClickListener(view -> {
//            ShoppingCart item = (ShoppingCart) binding.tvName.getTag();
//            int index = listItem.indexOf(item);
            if(item.getQuantity() > 1){
                item.setQuantity(item.getQuantity() - 1);
                Log.d(TAG, "onClick: " + item.getQuantity());
                notifyDataSetChanged();
                changeListCartListener(item, position, false);
                callBack.updateQuantity(item.getId(), item.getQuantity());

            } else {
                holder.binding.ivChoose.setImageResource(R.drawable.ic_uncheck);
                holder.binding.ivChoose.setColorFilter(ContextCompat.getColor(context, R.color.black_500));
                holder.binding.bgCart.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                item.setSelected(false);
                doDeleteItem(item);
                callBack.deleteItemCart(item.getId());
                changeListCartListener(item, position, true);
            }
            holder.binding.ivSubtract.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
        });

        holder.binding.ivPlus.setOnClickListener(view -> {
//            ShoppingCart item = (ShoppingCart) binding.tvName.getTag();
//            int index = listItem.indexOf(item);
            item.setQuantity(item.getQuantity() + 1);
            notifyDataSetChanged();
            holder.binding.ivPlus.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
            Log.d(TAG, "onClick: " + item.getQuantity());
            changeListCartListener(item, position, false);
            callBack.updateQuantity(item.getId(), item.getQuantity());
        });

        holder.binding.ivDelete.setOnClickListener(view -> {
            holder.binding.ivDelete.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
//            ShoppingCart item = (ShoppingCart) binding.tvName.getTag();
//            int index = listItem.indexOf(item);
            holder.binding.ivChoose.setImageResource(R.drawable.ic_uncheck);
            holder.binding.ivChoose.setColorFilter(ContextCompat.getColor(context, R.color.black_500));
            holder.binding.bgCart.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            item.setSelected(false);
            doDeleteItem(item);
            changeListCartListener(item, position, true);
            callBack.deleteItemCart(item.getId());
        });

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

        }



    }

    private void doDeleteItem(ShoppingCart item) {
        //Toast.makeText(context, "Delete successfully", Toast.LENGTH_SHORT).show();
        //changeListCardListener(item, index);
//        if (item.isSelected()){
//            binding.ivChoose.setImageResource(R.drawable.ic_uncheck);
//            binding.ivChoose.setColorFilter(ContextCompat.getColor(context, R.color.black_500));
//            binding.bgCart.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
//            item.setSelected(false);
//        }
        listItem.remove(item);
        notifyDataSetChanged();
    }

    private void changeListCartListener(ShoppingCart cart, int index, boolean isDelete){
        if (!isDelete) {
            listItem.set(index, cart);
        }
        listCartLD.postValue(listItem);
    }
}
