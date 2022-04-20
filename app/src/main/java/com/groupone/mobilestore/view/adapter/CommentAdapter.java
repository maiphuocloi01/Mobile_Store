package com.groupone.mobilestore.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groupone.mobilestore.databinding.LayoutItemReviewBinding;
import com.groupone.mobilestore.model.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private Context context;
    private List<Comment> listComment;

    public CommentAdapter(Context context, List<Comment> listComment) {
        this.context = context;
        this.listComment = listComment;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemReviewBinding binding = LayoutItemReviewBinding.inflate(LayoutInflater.from(context), parent, false);
        return new CommentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment item = listComment.get(position);
        holder.binding.tvName.setText(item.getFullName());
        holder.binding.tvDate.setText(item.getCreateAt());
        holder.binding.tvType.setText(item.getType());
        holder.binding.tvContent.setText(item.getContent());
        holder.binding.ratingBar.setRating(item.getRating());
    }

    @Override
    public int getItemCount() {
        return listComment.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemReviewBinding binding;
        public CommentViewHolder(LayoutItemReviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
