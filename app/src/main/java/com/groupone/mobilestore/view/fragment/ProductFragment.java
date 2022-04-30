package com.groupone.mobilestore.view.fragment;

import static com.groupone.mobilestore.util.NumberUtils.convertPrice;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentProductBinding;
import com.groupone.mobilestore.model.Comment;
import com.groupone.mobilestore.model.Information;
import com.groupone.mobilestore.model.Product;
import com.groupone.mobilestore.util.NumberUtils;
import com.groupone.mobilestore.view.adapter.CommentAdapter;
import com.groupone.mobilestore.view.adapter.InformationAdapter;
import com.groupone.mobilestore.view.adapter.SliderAdapter;
import com.groupone.mobilestore.view.adapter.VersionAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends BaseFragment<FragmentProductBinding, CommonViewModel> {

    public static final String TAG = ProductFragment.class.getName();
    SliderView sliderView;
    private SliderAdapter sliderAdapter;
    public boolean isFavorite = true;
    private Object mData;
    private Product product;

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });

        product = (Product) mData;
        binding.tvName.setText(product.getName());
        binding.tvPrice.setText(convertPrice(product.getPrice()));

        List<String> listVersion = new ArrayList<>();
        listVersion.add("128GB");
        listVersion.add("256GB");
        listVersion.add("512GB");
        listVersion.add("1TB");

        binding.rvVersion.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        VersionAdapter adapterVersion = new VersionAdapter(context, listVersion);
        binding.rvVersion.setAdapter(adapterVersion);

        List<String> listColor = new ArrayList<>();
        listColor.add("Xanh");
        listColor.add("Xám");
        listColor.add("Vàng");
        listColor.add("Bạc");

        binding.rvColor.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        VersionAdapter adapterColor = new VersionAdapter(context, listColor);
        binding.rvColor.setAdapter(adapterColor);

        List<Information> listInfo = new ArrayList<>();
        listInfo.add(new Information(1, "Màn hình", "OLED6.7\", Super Retina XDR"));
        listInfo.add(new Information(2, "Hệ điều hành", "iOS 15"));
        listInfo.add(new Information(3, "Camera sau", "3 camera 12 MP"));
        listInfo.add(new Information(4, "Camera trước", "12 MP"));
        listInfo.add(new Information(5, "Chip", "Apple A15 Bionic"));
        listInfo.add(new Information(6, "RAM", "6 GB"));
        listInfo.add(new Information(7, "Bộ nhớ trong", "128 GB"));
        listInfo.add(new Information(8, "SIM", "1 Nano SIM & 1 eSIM, Hỗ trợ 5G"));
        listInfo.add(new Information(9, "Pin, Sạc", "4352 mAh, 20 W"));


        binding.rvInfo.setLayoutManager(new LinearLayoutManager(context));
        InformationAdapter adapterInfo = new InformationAdapter(context, listInfo);
        binding.rvInfo.setAdapter(adapterInfo);


        List<Comment> listComment = new ArrayList<>();
        listComment.add(new Comment(1, "Mai Phước Lợi", "01-01-2022 12:59", "Phân loại: 128GB, Xám", "Sản phẩm chất lượng tốt, giao hàng nhanh chóng, mình sẽ giới thiệu cho bạn bè, người thân.", 5));
        listComment.add(new Comment(2, "Mai Phước Lợi", "01-01-2022 12:59", "Phân loại: 128GB, Xám", "Sản phẩm chất lượng tốt, giao hàng nhanh chóng, mình sẽ giới thiệu cho bạn bè, người thân.", 4));
        listComment.add(new Comment(3, "Mai Phước Lợi", "01-01-2022 12:59", "Phân loại: 128GB, Xám", "Sản phẩm chất lượng tốt, giao hàng nhanh chóng, mình sẽ giới thiệu cho bạn bè, người thân.", 3));
        //listComment.add(new Comment(4, "Mai Phước Lợi", "01-01-2022 12:59", "Phân loại: 128GB, Xám", "Sản phẩm chất lượng tốt, giao hàng nhanh chóng, mình sẽ giới thiệu cho bạn bè, người thân.", 2));

        binding.rvRating.setLayoutManager(new LinearLayoutManager(context));
        CommentAdapter commentAdapter = new CommentAdapter(context, listComment);
        binding.rvRating.setAdapter(commentAdapter);

        sliderView = binding.imageSlider;

        sliderAdapter = new SliderAdapter(context);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        renewItems(binding.imageSlider);

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });
        binding.tvExpanded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.showFragment(ReviewFragment.TAG, null, true);
            }
        });



        binding.ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isFavorite){
                    binding.ivFavorite.setImageResource(R.drawable.ic_favorite);
                    isFavorite = false;
                } else {
                    binding.ivFavorite.setImageResource(R.drawable.ic_favorite_border);
                    isFavorite = true;
                }
            }
        });
    }

    public void renewItems(View view) {
        List<String> sliderItemList = new ArrayList<>();
        //dummy data
        sliderItemList.add(product.getImage1());
        sliderItemList.add(product.getImage2());
        sliderItemList.add(product.getImage3());
        sliderItemList.add(product.getImage4());

        sliderAdapter.renewItems(sliderItemList);
    }

    @Override
    protected FragmentProductBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentProductBinding.inflate(inflater, container, false);
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
