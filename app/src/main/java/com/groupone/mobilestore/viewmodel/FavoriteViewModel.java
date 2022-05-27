package com.groupone.mobilestore.viewmodel;

import com.groupone.mobilestore.model.ShoppingCart;
import com.groupone.mobilestore.util.Constants;

public class FavoriteViewModel extends BaseViewModel{

    private static final String TAG = FavoriteViewModel.class.getName();


    public void getFavoriteProduct(int id){
        getApi().getFavoriteProductByAccountId(id).enqueue(initHandleResponse(Constants.KEY_GET_FAVORITE));
    }

    public void deleteFavorite(int accountId, int productId){
        getApi().deleteFavoriteByAccountId(accountId, productId).enqueue(initHandleResponse(Constants.KEY_DELETE_FAVORITE));
    }
}
