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

import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterViewHolder> {


    private Context context;
    private List<String> listFilter;

    private MutableLiveData<List<String>> listFilterLD = new MutableLiveData<>();

    public LiveData<List<String>> getListFilterLD() {
        return listFilterLD;
    }

    public FilterAdapter(Context context) {
        this.context = context;
    }

    public void renewItems(List<String> listFilter) {
        this.listFilter = listFilter;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.listFilter.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(String listFilter) {
        this.listFilter.add(listFilter);
        notifyDataSetChanged();
    }

    public void addItems(List<String> listFilter) {
        if(listFilter.size() > 0) {
            if(this.listFilter == null){
                this.listFilter = listFilter;
            } else {
                for (String item : listFilter) {
                    this.listFilter.add(item);
                }
            }
            notifyDataSetChanged();
        }
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
        if (listFilter != null) {
            return listFilter.size();
        }
        return 0;
    }

    public class FilterViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemFilterBinding binding;
        public FilterViewHolder(LayoutItemFilterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
