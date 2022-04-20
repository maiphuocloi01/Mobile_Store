package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.databinding.FragmentReviewBinding;
import com.groupone.mobilestore.model.Comment;
import com.groupone.mobilestore.view.adapter.CommentAdapter;
import com.groupone.mobilestore.view.adapter.VersionAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.util.ArrayList;
import java.util.List;

public class ReviewFragment extends BaseFragment<FragmentReviewBinding, CommonViewModel>{

    public static final String TAG = ReviewFragment.class.getName();

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {

        List<Comment> listComment = new ArrayList<>();
        listComment.add(new Comment(1, "Mai Phước Lợi", "01-01-2022 12:59", "Phân loại: 128GB, Xám", "Sản phẩm chất lượng tốt, giao hàng nhanh chóng, mình sẽ giới thiệu cho bạn bè, người thân.", 5));
        listComment.add(new Comment(2, "Mai Phước Lợi", "01-01-2022 12:59", "Phân loại: 128GB, Xám", "Sản phẩm chất lượng tốt, giao hàng nhanh chóng, mình sẽ giới thiệu cho bạn bè, người thân.", 4));
        listComment.add(new Comment(3, "Mai Phước Lợi", "01-01-2022 12:59", "Phân loại: 128GB, Xám", "Sản phẩm chất lượng tốt, giao hàng nhanh chóng, mình sẽ giới thiệu cho bạn bè, người thân.", 3));
        listComment.add(new Comment(4, "Mai Phước Lợi", "01-01-2022 12:59", "Phân loại: 128GB, Xám", "Sản phẩm chất lượng tốt, giao hàng nhanh chóng, mình sẽ giới thiệu cho bạn bè, người thân.", 2));

        binding.rvReview.setLayoutManager(new LinearLayoutManager(context));
        CommentAdapter commentAdapter = new CommentAdapter(context, listComment);
        binding.rvReview.setAdapter(commentAdapter);

        binding.rbAll.setChecked(true);
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });
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
}
