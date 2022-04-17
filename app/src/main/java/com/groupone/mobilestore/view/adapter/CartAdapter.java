package com.groupone.mobilestore.view.adapter;

import static com.groupone.mobilestore.util.NumberUtils.convertPrice;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.LayoutItemCartBinding;
import com.groupone.mobilestore.model.Cart;
import com.groupone.mobilestore.view.fragment.HomeFragment;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private static final String TAG = HomeFragment.TAG;

    private Context context;
    private List<Cart> listItem;

    private MutableLiveData<List<Cart>> listCartLD = new MutableLiveData<>();

    public LiveData<List<Cart>> getListCartLD() {
        return listCartLD;
    }

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
                binding.ivChoose.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                Cart item = (Cart) binding.tvName.getTag();
                int index = listItem.indexOf(item);
                if (!item.isSelected()) {
                    binding.ivChoose.setImageResource(R.drawable.ic_checked);
                    binding.ivChoose.setColorFilter(ContextCompat.getColor(context, R.color.blue_500));
                    binding.bgCart.setBackgroundColor(ContextCompat.getColor(context, R.color.blue_200));
                    item.setSelected(true);
                    changeListCartListener(item, index, false);
                } else {
                    binding.ivChoose.setImageResource(R.drawable.ic_uncheck);
                    binding.ivChoose.setColorFilter(ContextCompat.getColor(context, R.color.black_500));
                    binding.bgCart.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                    item.setSelected(false);
                    changeListCartListener(item, index, false);
                }
            });

            binding.ivSubtract.setOnClickListener(view -> {
                Cart item = (Cart) binding.tvName.getTag();
                int index = listItem.indexOf(item);
                if(item.getQty() > 1){
                    item.setQty(item.getQty() - 1);
                    Log.d(TAG, "onClick: " + item.getQty());
                    notifyDataSetChanged();
                    changeListCartListener(item, index, false);

                } else {
                    doDeleteItem(item);
                    changeListCartListener(item, index, true);
                }
                binding.ivSubtract.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
            });

            binding.ivPlus.setOnClickListener(view -> {
                Cart item = (Cart) binding.tvName.getTag();
                int index = listItem.indexOf(item);
                item.setQty(item.getQty() + 1);
                notifyDataSetChanged();
                binding.ivPlus.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                Log.d(TAG, "onClick: " + item.getQty());
                changeListCartListener(item, index, false);
            });

            binding.ivDelete.setOnClickListener(view -> {
                binding.ivDelete.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                Cart item = (Cart) binding.tvName.getTag();
                int index = listItem.indexOf(item);
                doDeleteItem(item);
                changeListCartListener(item, index, true);
            });
        }

        private void doDeleteItem(Cart item) {
            //Toast.makeText(context, "Delete successfully", Toast.LENGTH_SHORT).show();
            //changeListCardListener(item, index);
            if (item.isSelected()){
                binding.ivChoose.setImageResource(R.drawable.ic_uncheck);
                binding.ivChoose.setColorFilter(ContextCompat.getColor(context, R.color.black_500));
                binding.bgCart.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                item.setSelected(false);
            }
            listItem.remove(item);
            notifyDataSetChanged();
        }

        private void changeListCartListener(Cart cart, int index, boolean isDelete){
            if (!isDelete) {
                listItem.set(index, cart);
            }
            listCartLD.postValue(listItem);
        }

    }
}
