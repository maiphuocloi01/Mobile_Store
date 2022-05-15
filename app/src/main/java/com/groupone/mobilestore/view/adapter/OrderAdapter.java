package com.groupone.mobilestore.view.adapter;

import static com.groupone.mobilestore.util.NumberUtils.convertPrice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groupone.mobilestore.databinding.LayoutItemOrderBinding;
import com.groupone.mobilestore.model.Order;

import java.math.BigInteger;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context context;
    private List<Order> listOrder;

    private OderCallback callback;

    public interface OderCallback{
        void gotoReview(int position);
        void gotoRepurchase(int position);
        void gotoDetail(int position);
    }

    public OrderAdapter(Context context, List<Order> listOrder, OderCallback callback) {
        this.callback = callback;
        this.context = context;
        this.listOrder = listOrder;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemOrderBinding binding = LayoutItemOrderBinding.inflate(LayoutInflater.from(context), parent, false);
        return new OrderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Order item = listOrder.get(position);
        holder.binding.tvName.setText(item.getProductName());
        holder.binding.tvPrice.setText(convertPrice(item.getTotalPrice()));
        holder.binding.tvQty.setText(item.getQuantity() + " sản phẩm");
        holder.binding.tvType.setText(item.getType());
        if(item.getStatus() == 1){
            holder.binding.btOrder.setText("Đánh giá");
            holder.binding.btOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(context, "Đánh giá", Toast.LENGTH_SHORT).show();
                    callback.gotoReview(position);
                }
            });
        } else if(item.getStatus() == 2){
            holder.binding.btOrder.setText("Mua lại");
            holder.binding.btOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(context, "Mua lại", Toast.LENGTH_SHORT).show();
                    callback.gotoRepurchase(position);
                }
            });
        }
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.gotoDetail(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemOrderBinding binding;

        public OrderViewHolder(LayoutItemOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
