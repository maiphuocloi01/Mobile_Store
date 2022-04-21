package com.groupone.mobilestore.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterViewHolder> {


    private Context context;
    private List<String> listFilter;

    public FilterAdapter(Context context, List<String> listFilter) {
        this.context = context;
        this.listFilter = listFilter;
    }

    @NonNull
    @Override
    public FilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FilterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FilterViewHolder extends RecyclerView.ViewHolder {
        public FilterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
