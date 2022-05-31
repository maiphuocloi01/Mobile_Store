package com.groupone.mobilestore.view.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.databinding.FragmentEvaluateBinding;
import com.groupone.mobilestore.model.Comment;
import com.groupone.mobilestore.model.Order;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.viewmodel.CommonViewModel;
import com.groupone.mobilestore.viewmodel.OrderViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EvaluateFragment extends BaseFragment<FragmentEvaluateBinding, OrderViewModel>{

    public static final String TAG = EvaluateFragment.class.getName();

    private Object mData;
    private final User user = MyApplication.getInstance().getStorage().user;

    @Override
    protected Class<OrderViewModel> getClassVM() {
        return OrderViewModel.class;
    }

    @Override
    protected void initViews() {

        Order order = (Order) mData;

        binding.tvProductName.setText(order.getProductName());
        binding.tvType.setText(order.getType());
        Glide.with(context).load(order.getImage()).into(binding.ivProduct);

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });

        binding.btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(binding.etContent.getText())) {
                    binding.etContent.setError("Vui lòng nhập nội dung");
                } else {
                    String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
                    Comment comment = new Comment(order.getProductId(),
                            user.getId(),
                            user.getFullName(),
                            timeStamp,
                            order.getType(),
                            binding.etContent.getText().toString().trim(),
                            (int) binding.ratingBar.getRating()
                    );
                    Log.d(TAG, "onClick: " + comment.toString());
                    viewModel.addComment(comment);
                    viewModel.changeBillStatus(order.getId(), 2);
                }
            }
        });
    }

    @Override
    protected FragmentEvaluateBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentEvaluateBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if(key.equals(Constants.KEY_ADD_COMMENT)){
            int response = (int) data;
            if(response == -1){
                Log.d(TAG, "apiSuccess: thất bại");
            } else {
                Log.d(TAG, "apiSuccess: thành công");
                Toast.makeText(context, "Đánh giá thành công", Toast.LENGTH_SHORT).show();
            }
        } else if(key.equals(Constants.KEY_CHANGE_STATUS_BILL)){
            callBack.showFragment(PagerFragment.TAG, null, false);
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {

    }

    @Override
    public void setData(Object data) {
        this.mData = data;
    }
}
