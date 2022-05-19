package com.groupone.mobilestore.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.groupone.mobilestore.databinding.FragmentPostBinding;
import com.groupone.mobilestore.model.Post;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.view.adapter.CartAdapter;
import com.groupone.mobilestore.view.adapter.PostAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;
import com.groupone.mobilestore.viewmodel.PostViewModel;

import java.util.List;

public class PostFragment extends BaseFragment<FragmentPostBinding, PostViewModel> implements PostAdapter.PostCallBack {

    public static final String TAG = PostFragment.class.getName();


    @Override
    protected Class<PostViewModel> getClassVM() {
        return PostViewModel.class;
    }

    @Override
    protected void initViews() {
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });

        viewModel.getAllPost();

    }

    @Override
    protected FragmentPostBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentPostBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if(key.equals(Constants.KEY_ALL_POST)){
            List<Post> postList = (List<Post>) data;

            Log.d(TAG, "apiSuccess: " + postList.size());

            PostAdapter postAdapter = new PostAdapter(context, postList, this);
            binding.rvPost.setAdapter(postAdapter);

        }
    }

    @Override
    public void apiError(String key, int code, Object data) {
        if (code == 999) {
            Log.d(TAG, "apiError: " + data.toString());
            Toast.makeText(context, "Không thể kết nối đến máy chủ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void gotoArticle(String link) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(browserIntent);
    }
}
