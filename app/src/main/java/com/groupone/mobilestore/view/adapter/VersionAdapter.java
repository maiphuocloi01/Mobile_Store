package com.groupone.mobilestore.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.LayoutItemVersionBinding;

import java.util.List;

public class VersionAdapter extends RecyclerView.Adapter<VersionAdapter.VersionViewHolder> {

    private Context context;
    private List<String> listVersion;
    private int row_index = 0;

    public VersionAdapter(Context context, List<String> listVersion) {
        this.context = context;
        this.listVersion = listVersion;
    }

    @NonNull
    @Override
    public VersionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemVersionBinding binding = LayoutItemVersionBinding.inflate(LayoutInflater.from(context), parent, false);
        return new VersionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VersionViewHolder holder, int position) {
        String item = listVersion.get(position);
        holder.binding.tvVersion.setText(item);
        holder.binding.getRoot().setOnClickListener(view -> {
            row_index=holder.getAdapterPosition();
            notifyDataSetChanged();
        });
        if(row_index==position){
            holder.binding.bgVersion.setCardBackgroundColor(context.getResources().getColor(R.color.blue_500));
        }
        else
        {

            holder.binding.bgVersion.setCardBackgroundColor(context.getResources().getColor(R.color.black_200));
        }
    }

    @Override
    public int getItemCount() {
        return listVersion.size();
    }

    public class VersionViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemVersionBinding binding;
        public VersionViewHolder(LayoutItemVersionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
