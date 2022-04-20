package com.groupone.mobilestore.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.LayoutItemInfoBinding;
import com.groupone.mobilestore.model.Information;

import java.util.List;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.InfoViewHolder> {

    private Context context;
    private List<Information> listInfo;

    public InformationAdapter(Context context, List<Information> listInfo) {
        this.context = context;
        this.listInfo = listInfo;
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemInfoBinding binding = LayoutItemInfoBinding.inflate(LayoutInflater.from(context), parent, false);
        return new InfoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        Information item = listInfo.get(position);
        holder.binding.tvTitle.setText(item.getTitle());
        holder.binding.tvDescription.setText(item.getDescription());
        if (position%2 == 1){
            holder.binding.bgVersion.setCardBackgroundColor(context.getResources().getColor(R.color.background));
        }
    }

    @Override
    public int getItemCount() {
        return listInfo.size();
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemInfoBinding binding;
        public InfoViewHolder(LayoutItemInfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
