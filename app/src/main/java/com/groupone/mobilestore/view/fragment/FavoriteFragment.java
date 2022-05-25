package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentFavoriteBinding;
import com.groupone.mobilestore.model.Favorite;
import com.groupone.mobilestore.model.Product;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.view.adapter.ProductAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;
import com.groupone.mobilestore.viewmodel.FavoriteViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>{

    public static final String TAG = FavoriteFragment.class.getName();
    private User user = MyApplication.getInstance().getStorage().user;
    private List<Favorite> favoriteList = MyApplication.getInstance().getStorage().listFavorite;
    private List<Product> listProduct = new ArrayList<>();
    private List<Product> products = MyApplication.getInstance().getStorage().listProduct;
    @Override
    protected Class<FavoriteViewModel> getClassVM() {
        return FavoriteViewModel.class;
    }

    @Override
    protected void initViews() {

        if (favoriteList == null) {
            viewModel.getFavoriteProduct(user.getId());
        } else {
            for (Favorite item: favoriteList){
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    Product findProduct = (Product) products.stream()
                            .filter(product -> item.getProductId() == product.getId())
                            .findAny()
                            .orElse(null);
                    if (findProduct != null){
                        listProduct.add(findProduct);
                    }
                }
//                for(Product findProduct: products){
//                    if(findProduct.getId() == item.getProductId()){
//                        listProduct.add(findProduct);
//                        break;
//                    }
//                }
            }
        }
        if(listProduct != null) {
            binding.rvFavor.setLayoutManager(new LinearLayoutManager(context));
            ProductAdapter adapter = new ProductAdapter(context, listProduct);
            binding.rvFavor.setAdapter(adapter);

            binding.tvCount.setText(listProduct.size() + " sản phẩm");

        }

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });

    }

    @Override
    protected FragmentFavoriteBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentFavoriteBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if (key.equals(Constants.KEY_GET_FAVORITE)) {
            List<Favorite> favorites = (List<Favorite>) data;
            favoriteList = favorites;
            MyApplication.getInstance().getStorage().listFavorite = favorites;
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {

    }
}
