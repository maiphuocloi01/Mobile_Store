package com.groupone.mobilestore.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.groupone.mobilestore.databinding.LayoutItemNewsBinding;
import com.groupone.mobilestore.model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private Context context;
    private List<Post> postList;
    private PostCallBack callBack;

    public interface PostCallBack{
        void gotoArticle(String link);
    }

    public PostAdapter(Context context, List<Post> postList, PostCallBack callBack) {
        this.context = context;
        this.postList = postList;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemNewsBinding binding = LayoutItemNewsBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Post item = postList.get(position);
        holder.binding.tvName.setText(item.getTitle());
        Glide.with(context).load(item.getImage()).into(holder.binding.ivPost);
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.gotoArticle(item.getLink());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(postList != null){
            return postList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemNewsBinding binding;
        public MyViewHolder(LayoutItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
