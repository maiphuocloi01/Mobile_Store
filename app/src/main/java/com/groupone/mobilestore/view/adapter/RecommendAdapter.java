package com.groupone.mobilestore.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groupone.mobilestore.databinding.LayoutItemRecommendBinding;

import java.util.List;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.MyViewHolder> {

    private Context context;
    private List<String> listRecommend;

    public RecommendAdapter(Context context, List<String> listRecommend) {
        this.context = context;
        this.listRecommend = listRecommend;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemRecommendBinding binding = LayoutItemRecommendBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String item = listRecommend.get(position);
        holder.binding.tvSearch.setText(item);
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.binding.getRoot().startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRecommend.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemRecommendBinding binding;
        public MyViewHolder(LayoutItemRecommendBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
