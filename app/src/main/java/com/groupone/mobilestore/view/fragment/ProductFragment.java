package com.groupone.mobilestore.view.fragment;

import static com.groupone.mobilestore.util.NumberUtils.convertPrice;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentProductBinding;
import com.groupone.mobilestore.model.Comment;
import com.groupone.mobilestore.model.Favorite;
import com.groupone.mobilestore.model.Information;
import com.groupone.mobilestore.model.Product;
import com.groupone.mobilestore.model.ProductDetail;
import com.groupone.mobilestore.model.ProductVersion;
import com.groupone.mobilestore.model.ShoppingCart;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.util.DialogUtils;
import com.groupone.mobilestore.view.adapter.CommentAdapter;
import com.groupone.mobilestore.view.adapter.InformationAdapter;
import com.groupone.mobilestore.view.adapter.SliderAdapter;
import com.groupone.mobilestore.view.adapter.VersionAdapter;
import com.groupone.mobilestore.viewmodel.ProductViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductFragment extends BaseFragment<FragmentProductBinding, ProductViewModel> {

    public static final String TAG = ProductFragment.class.getName();
    public boolean isFavorite = false;
    private SliderView sliderView;
    private SliderAdapter sliderAdapter;
    private Object mData;
    private Product product;
    private String currentVersion;
    private String currentColor;
    private long currentPrice;
    private List<String> listColor = new ArrayList<>();
    private final User user = MyApplication.getInstance().getStorage().user;
    private List<Favorite> favoriteList = MyApplication.getInstance().getStorage().listFavorite;

    @Override
    protected Class<ProductViewModel> getClassVM() {
        return ProductViewModel.class;
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
        binding.tvDescription.setText(product.getDescription());
        binding.tvRating.setText(String.valueOf(product.getRate()));
        binding.ratingBar.setRating(product.getRate());
        binding.tvCountReview.setText(product.getComments().size() + " đánh giá");
        //Log.d(TAG, "favoriteList: " + favoriteList.size());

//        if(MyApplication.getInstance().getStorage().listFavorite == null) {
//            viewModel.getFavoriteProduct(user.getId());
//        } else {
//            favoriteList = MyApplication.getInstance().getStorage().listFavorite;
//        }

        if(favoriteList != null){
            if(favoriteList.size() > 0) {
                for (Favorite item : favoriteList) {
                    Log.d(TAG, "item: " + item.getProductId());
                    if (item.getProductId() == product.getId()) {
                        Log.d(TAG, "ivFavorite: ");
                        binding.ivFavorite.setImageResource(R.drawable.ic_favorite);
                        isFavorite = true;
                        break;
                    }
                }
            }
        } else {
            binding.ivFavorite.setImageResource(R.drawable.ic_favorite);
            isFavorite = true;
        }
        viewModel.getProductVersion(product.getId());
        viewModel.getProductDetail(product.getId());
        viewModel.getComment(product.getId());



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






        binding.ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isFavorite) {
                    binding.ivFavorite.setImageResource(R.drawable.ic_favorite);
                    Favorite favorite = new Favorite(product.getId(), user.getId());
                    viewModel.addFavorite(favorite);
                    isFavorite = true;
                } else {
                    binding.ivFavorite.setImageResource(R.drawable.ic_favorite_border);
                    viewModel.deleteFavorite(user.getId(), product.getId());
                    isFavorite = false;
                }
            }
        });

        binding.btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: " + product.getId());
                ShoppingCart cart = new ShoppingCart(user.getId(), product.getId(), currentPrice,   currentVersion + ", " + currentColor);
                viewModel.addShoppingCart(cart);
                DialogUtils.showLoadingDialog(context);
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

    private void initCommentView(List<Comment> commentList){
        //List<Comment> listComment = new ArrayList<>();
//        listComment.add(new Comment(1, "Mai Phước Lợi", "01-01-2022 12:59", "Phân loại: 128GB, Xám", "Sản phẩm chất lượng tốt, giao hàng nhanh chóng, mình sẽ giới thiệu cho bạn bè, người thân.", 5));
//        listComment.add(new Comment(2, "Mai Phước Lợi", "01-01-2022 12:59", "Phân loại: 128GB, Xám", "Sản phẩm chất lượng tốt, giao hàng nhanh chóng, mình sẽ giới thiệu cho bạn bè, người thân.", 4));
//        listComment.add(new Comment(3, "Mai Phước Lợi", "01-01-2022 12:59", "Phân loại: 128GB, Xám", "Sản phẩm chất lượng tốt, giao hàng nhanh chóng, mình sẽ giới thiệu cho bạn bè, người thân.", 3));
        //listComment.add(new Comment(4, "Mai Phước Lợi", "01-01-2022 12:59", "Phân loại: 128GB, Xám", "Sản phẩm chất lượng tốt, giao hàng nhanh chóng, mình sẽ giới thiệu cho bạn bè, người thân.", 2));

        binding.rvRating.setLayoutManager(new LinearLayoutManager(context));
        CommentAdapter commentAdapter = new CommentAdapter(context, commentList);
        binding.rvRating.setAdapter(commentAdapter);
    }

    private void initProductVersionView(List<ProductVersion> versionList){
        //List<ProductVersion> versionList = product.getProductVersions();
        currentVersion = versionList.get(0).getVersionName();
        currentPrice = product.getPrice();

        List<String> listVersion = new ArrayList<>();
        for (ProductVersion item : versionList) {
            listVersion.add(item.getVersionName());
        }
        binding.rvVersion.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        VersionAdapter adapterVersion = new VersionAdapter(context);
        binding.rvVersion.setAdapter(adapterVersion);
        adapterVersion.renewItems(listVersion);


        listColor = new ArrayList<String>(Arrays.asList(versionList.get(0).getColor().split("\\|")));
        currentColor = listColor.get(0);
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            List<String> items = Stream.of(versionList.get(0).getColor().split("\\|"))
//                    .map(String::trim)
//                    .collect(Collectors.toList());
//        }
//        listColor.add("Xanh");
//        listColor.add("Xám");
//        listColor.add("Vàng");
//        listColor.add("Bạc");


        binding.rvColor.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        VersionAdapter adapterColor = new VersionAdapter(context);
        binding.rvColor.setAdapter(adapterColor);
        adapterColor.renewItems(listColor);

        adapterVersion.getIndexLD().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer index) {
                binding.tvPrice.setText(convertPrice(product.getProductVersions().get(index).getPrice()));
                currentVersion = product.getProductVersions().get(index).getVersionName();
                currentPrice = product.getProductVersions().get(index).getPrice();
                listColor = new ArrayList<String>(Arrays.asList(versionList.get(index).getColor().split("\\|")));
                adapterColor.renewItems(listColor);
            }
        });

        adapterColor.getIndexLD().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer index) {
                currentColor = listColor.get(index);
            }
        });
    }

    private void initProductDetailView(ProductDetail detailList){

        List<Information> listInfo = new ArrayList<>();

        listInfo.add(new Information(1, "Màn hình", detailList.getScreen()));
        listInfo.add(new Information(2, "Hệ điều hành", detailList.getOs()));
        listInfo.add(new Information(3, "Camera sau", detailList.getBackCamera()));
        listInfo.add(new Information(4, "Camera trước", detailList.getFrontCamera()));
        listInfo.add(new Information(5, "Chip", detailList.getChip()));
        listInfo.add(new Information(6, "RAM", detailList.getRam()));
        listInfo.add(new Information(7, "Bộ nhớ trong", detailList.getMemory()));
        listInfo.add(new Information(8, "SIM", detailList.getSim()));
        listInfo.add(new Information(9, "Pin, Sạc", detailList.getBattery()));


        binding.rvInfo.setLayoutManager(new LinearLayoutManager(context));
        InformationAdapter adapterInfo = new InformationAdapter(context, listInfo);
        binding.rvInfo.setAdapter(adapterInfo);
    }

    @Override
    protected FragmentProductBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentProductBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if (key.equals(Constants.KEY_ADD_SHOPPING_CART)){
            int response = (int) data;
            if(response == -1){
                Toast.makeText(context, "Thêm vào giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                DialogUtils.hideLoadingDialog();
                viewModel.getShoppingCartByAccountId(user.getId());
            }
        } else if (key.equals(Constants.KEY_GET_SHOPPING_CART_BY_ACCOUNT)){
            MyApplication.getInstance().getStorage().listCart = (List<ShoppingCart>) data;
        } else if (key.equals(Constants.KEY_ADD_FAVORITE)){
            int response = (int) data;
            Log.d(TAG, "like: " + response);
            if(response == -1){
                Toast.makeText(context, "Thích thất bại", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Thích thành công", Toast.LENGTH_SHORT).show();
                isFavorite = true;
            }
            viewModel.getFavoriteProduct(user.getId());
        } else if (key.equals(Constants.KEY_DELETE_FAVORITE_BY_ACCOUNT)){
            boolean response = (boolean) data;
            Log.d(TAG, "like: " + response);
            if(!response){
                Toast.makeText(context, "Bỏ thích thất bại", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Bỏ thích thành công", Toast.LENGTH_SHORT).show();
                isFavorite = false;
            }
            viewModel.getFavoriteProduct(user.getId());
        } else if (key.equals(Constants.KEY_GET_FAVORITE)) {
            List<Favorite> favorites = (List<Favorite>) data;
            //favoriteList = favorites;
            MyApplication.getInstance().getStorage().listFavorite = favorites;
        } else if(key.equals(Constants.KEY_GET_PRODUCT_VERSION)){
            List<ProductVersion> versionList = (List<ProductVersion>) data;
            initProductVersionView(versionList);
        } else if(key.equals(Constants.KEY_GET_PRODUCT_DETAIL)){
            List<ProductDetail> versionList = (List<ProductDetail>) data;
            initProductDetailView(versionList.get(0));
        } else if(key.equals(Constants.KEY_GET_COMMENT)){
            List<Comment> commentList = (List<Comment>) data;
            List<Comment> topComments = new ArrayList<>();
            if(commentList.size() == 0){

            } else if(commentList.size() > 3) {
                topComments.add(commentList.get(0));
                topComments.add(commentList.get(1));
                topComments.add(commentList.get(2));
            } else {
                topComments.add(commentList.get(0));
            }
            initCommentView(topComments);
            binding.tvExpanded.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBack.showFragmentWithAdd(ReviewFragment.TAG, commentList, true);
                }
            });
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {
        Toast.makeText(context, "Không kết nối được máy chủ", Toast.LENGTH_SHORT).show();
        DialogUtils.hideLoadingDialog();
    }

    @Override
    public void setData(Object data) {
        this.mData = data;
    }
}
