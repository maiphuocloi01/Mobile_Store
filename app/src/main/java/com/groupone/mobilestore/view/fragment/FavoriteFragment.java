package com.groupone.mobilestore.view.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.databinding.FragmentFavoriteBinding;
import com.groupone.mobilestore.model.Favorite;
import com.groupone.mobilestore.model.Product;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.util.ViewUtils;
import com.groupone.mobilestore.view.adapter.FavoriteAdapter;
import com.groupone.mobilestore.viewmodel.FavoriteViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends BaseFragment<FragmentFavoriteBinding, FavoriteViewModel> implements FavoriteAdapter.FavoriteCallBack {

    public static final String TAG = FavoriteFragment.class.getName();
    private User user = MyApplication.getInstance().getStorage().user;
    private List<Favorite> favoriteList;
    private List<Product> products = MyApplication.getInstance().getStorage().listProduct;
    private List<Product> listProductFavorite;
    private FavoriteAdapter adapter;

    @Override
    protected Class<FavoriteViewModel> getClassVM() {
        return FavoriteViewModel.class;
    }

    @Override
    protected void initViews() {

        listProductFavorite = new ArrayList<>();
        favoriteList = new ArrayList<>();

        if(MyApplication.getInstance().getStorage().listFavorite == null) {
            viewModel.getFavoriteProduct(user.getId());
        } else {
            favoriteList = MyApplication.getInstance().getStorage().listFavorite;
            displayFavoriteProduct(favoriteList);
        }

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });

    }

    private void displayFavoriteProduct(List<Favorite> favorites) {
        for (Favorite item : favorites) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                Product findProduct = (Product) products.stream()
                        .filter(product -> item.getProductId() == product.getId())
                        .findAny()
                        .orElse(null);
                if (findProduct != null) {
                    listProductFavorite.add(findProduct);
                }
            } else {
                for (Product findProduct : products) {
                    if (findProduct.getId() == item.getProductId()) {
                        listProductFavorite.add(findProduct);
                        break;
                    }
                }
            }
        }
        initFavoriteView(listProductFavorite);

    }

    private void initFavoriteView(List<Product> products){
        if (products.size() > 0) {

            ViewUtils.gone(binding.layoutEmpty);
            ViewUtils.show(binding.layoutFavorite);
            binding.rvFavor.setLayoutManager(new LinearLayoutManager(context));
            adapter = new FavoriteAdapter(context, products, this);
            binding.rvFavor.setAdapter(adapter);
            binding.tvCount.setText(String.format(listProductFavorite.size() + " sản phẩm"));

        } else {
            ViewUtils.show(binding.layoutEmpty);
            ViewUtils.gone(binding.layoutFavorite);
        }
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
            displayFavoriteProduct(favoriteList);
        } else if (key.equals(Constants.KEY_DELETE_FAVORITE)) {
            boolean response = (boolean) data;
            Log.d(TAG, "unlike: " + response);
            if (!response) {
                //Toast.makeText(context, "Bỏ thích thất bại", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(context, "Bỏ thích thành công", Toast.LENGTH_SHORT).show();
                MyApplication.getInstance().getStorage().listFavorite = null;
            }
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {

    }

    @Override
    public void gotoProduct(Product product) {
        callBack.showFragment(ProductFragment.TAG, product, true);
    }

    @Override
    public void deleteFavoriteProduct(int id) {
        viewModel.deleteFavorite(user.getId(), id);
        for (Product findProduct : listProductFavorite) {
            if (findProduct.getId() == id) {
                listProductFavorite.remove(findProduct);
                break;
            }
        }
        initFavoriteView(listProductFavorite);
        //binding.tvCount.setText(String.format(listProduct.size() - 1 + " sản phẩm"));
    }

}
