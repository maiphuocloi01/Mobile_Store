package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.databinding.FragmentReviewBinding;
import com.groupone.mobilestore.model.Comment;
import com.groupone.mobilestore.util.ViewUtils;
import com.groupone.mobilestore.view.adapter.CommentAdapter;
import com.groupone.mobilestore.view.adapter.VersionAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.util.ArrayList;
import java.util.List;

public class ReviewFragment extends BaseFragment<FragmentReviewBinding, CommonViewModel>{

    public static final String TAG = ReviewFragment.class.getName();
    private Object mData;

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {



        List<Comment> listComment = (List<Comment>) mData;
        binding.rbAll.setChecked(true);
        if(listComment.size() > 0) {
            ViewUtils.show(binding.rvReview);
            ViewUtils.gone(binding.layoutEmpty);
            initCommentView(listComment);
        } else {
            ViewUtils.gone(binding.rvReview);
            ViewUtils.show(binding.layoutEmpty);
        }

        binding.rbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listComment.size() > 0) {
                    ViewUtils.show(binding.rvReview);
                    ViewUtils.gone(binding.layoutEmpty);
                    initCommentView(listComment);
                } else {
                    ViewUtils.gone(binding.rvReview);
                    ViewUtils.show(binding.layoutEmpty);
                }

            }
        });

        binding.rbFiveStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Comment> filterComment = new ArrayList<>();
                for(Comment comment: listComment){
                    if(comment.getRating() == 5){
                        filterComment.add(comment);
                    }
                }
                if(filterComment.size() > 0) {
                    ViewUtils.show(binding.rvReview);
                    ViewUtils.gone(binding.layoutEmpty);
                    initCommentView(filterComment);
                } else {
                    ViewUtils.gone(binding.rvReview);
                    ViewUtils.show(binding.layoutEmpty);
                }
            }
        });

        binding.rbFourStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Comment> filterComment = new ArrayList<>();
                for(Comment comment: listComment){
                    if(comment.getRating() == 4){
                        filterComment.add(comment);
                    }
                }
                if(filterComment.size() > 0) {
                    ViewUtils.show(binding.rvReview);
                    ViewUtils.gone(binding.layoutEmpty);
                    initCommentView(filterComment);
                } else {
                    ViewUtils.gone(binding.rvReview);
                    ViewUtils.show(binding.layoutEmpty);
                }
            }
        });

        binding.rbThreeStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Comment> filterComment = new ArrayList<>();
                for(Comment comment: listComment){
                    if(comment.getRating() == 3){
                        filterComment.add(comment);
                    }
                }
                if(filterComment.size() > 0) {
                    ViewUtils.show(binding.rvReview);
                    ViewUtils.gone(binding.layoutEmpty);
                    initCommentView(filterComment);
                } else {
                    ViewUtils.gone(binding.rvReview);
                    ViewUtils.show(binding.layoutEmpty);
                }
            }
        });

        binding.rbTwoStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Comment> filterComment = new ArrayList<>();
                for(Comment comment: listComment){
                    if(comment.getRating() == 2){
                        filterComment.add(comment);
                    }
                }
                if(filterComment.size() > 0) {
                    ViewUtils.show(binding.rvReview);
                    ViewUtils.gone(binding.layoutEmpty);
                    initCommentView(filterComment);
                } else {
                    ViewUtils.gone(binding.rvReview);
                    ViewUtils.show(binding.layoutEmpty);
                }
            }
        });

        binding.rbOneStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Comment> filterComment = new ArrayList<>();
                for(Comment comment: listComment){
                    if(comment.getRating() == 1){
                        filterComment.add(comment);
                    }
                }
                if(filterComment.size() > 0) {
                    ViewUtils.show(binding.rvReview);
                    ViewUtils.gone(binding.layoutEmpty);
                    initCommentView(filterComment);
                } else {
                    ViewUtils.gone(binding.rvReview);
                    ViewUtils.show(binding.layoutEmpty);
                }
            }
        });

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });
    }

    private void initCommentView(List<Comment> commentList){
        binding.rvReview.setLayoutManager(new LinearLayoutManager(context));
        CommentAdapter commentAdapter = new CommentAdapter(context, commentList);
        binding.rvReview.setAdapter(commentAdapter);

    }

    @Override
    protected FragmentReviewBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentReviewBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }

    @Override
    public void setData(Object data) {
        this.mData = data;
    }
}
