package com.groupone.mobilestore.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.groupone.mobilestore.databinding.LayoutItemFilterBinding;
import com.groupone.mobilestore.model.Cart;

import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterViewHolder> {


    private Context context;
    private List<String> listFilter;

    private MutableLiveData<List<String>> listFilterLD = new MutableLiveData<>();

    public LiveData<List<String>> getListFilterLD() {
        return listFilterLD;
    }

    public FilterAdapter(Context context, List<String> listFilter) {
        this.context = context;
        this.listFilter = listFilter;
    }

    @NonNull
    @Override
    public FilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemFilterBinding binding = LayoutItemFilterBinding.inflate(LayoutInflater.from(context), parent, false);
        return new FilterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterViewHolder holder, int position) {
        String item = listFilter.get(position);
        holder.binding.tvFilter.setText(item);
        holder.binding.ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listFilter.remove(item);
                notifyDataSetChanged();
                deleteFilterListener();
            }
        });
    }

    private void deleteFilterListener(){
        listFilterLD.postValue(listFilter);
    }

    @Override
    public int getItemCount() {
        return listFilter.size();
    }

    public class FilterViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemFilterBinding binding;
        public FilterViewHolder(LayoutItemFilterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
