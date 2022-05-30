package com.groupone.mobilestore.viewmodel;

import com.groupone.mobilestore.model.Favorite;
import com.groupone.mobilestore.model.ShoppingCart;
import com.groupone.mobilestore.util.Constants;

public class ProductViewModel extends BaseViewModel{

    private static final String TAG = ProductViewModel.class.getName();


    public void addShoppingCart(ShoppingCart cart){
        getApi().addShoppingCart(cart).enqueue(initHandleResponse(Constants.KEY_ADD_SHOPPING_CART));
    }

    public void getShoppingCartByAccountId(int id){
        getApi().getShoppingCartByAccountId(id).enqueue(initHandleResponse(Constants.KEY_GET_SHOPPING_CART_BY_ACCOUNT));
    }

    public void addFavorite(Favorite favorite){
        getApi().addFavorite(favorite).enqueue(initHandleResponse(Constants.KEY_ADD_FAVORITE));
    }

    public void deleteFavorite(int accountId, int productId){
        getApi().deleteFavoriteByAccountId(accountId, productId).enqueue(initHandleResponse(Constants.KEY_DELETE_FAVORITE_BY_ACCOUNT));
    }

    public void getFavoriteProduct(int id){
        getApi().getFavoriteProductByAccountId(id).enqueue(initHandleResponse(Constants.KEY_GET_FAVORITE));
    }

    public void getProductVersion(int id){
        getApi().getProductVersionByProductId(id).enqueue(initHandleResponse(Constants.KEY_GET_PRODUCT_VERSION));
    }

    public void getProductDetail(int id){
        getApi().getProductDetailByProductId(id).enqueue(initHandleResponse(Constants.KEY_GET_PRODUCT_DETAIL));
    }

    public void getComment(int id){
        getApi().getCommentByProductId(id).enqueue(initHandleResponse(Constants.KEY_GET_COMMENT));
    }
}
