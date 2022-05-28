package com.groupone.mobilestore.view.fragment;

import static com.groupone.mobilestore.util.IMEUtils.hideSoftInput;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.databinding.FragmentSearchResultBinding;
import com.groupone.mobilestore.model.Product;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.util.StringConvert;
import com.groupone.mobilestore.util.ViewUtils;
import com.groupone.mobilestore.view.adapter.FilterAdapter;
import com.groupone.mobilestore.view.adapter.ProductAdapter;
import com.groupone.mobilestore.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchResultFragment extends BaseFragment<FragmentSearchResultBinding, HomeViewModel> {

    public static final String TAG = SearchResultFragment.class.getName();
    private Object mData;
    private List<Product> listProduct = MyApplication.getInstance().getStorage().listProduct;

    @Override
    protected Class<HomeViewModel> getClassVM() {
        return HomeViewModel.class;
    }

    @Override
    protected void initViews() {

        if (listProduct == null) {
            viewModel.getTopSaleProduct();
        }
        Bundle newData = (Bundle) mData;
        if (newData != null) {
            List<String> listFilterBrand = new ArrayList<>();
            List<String> listFilterPrice = new ArrayList<>();
            List<String> listFilterCategory = new ArrayList<>();
            List<String> listFilterRam = new ArrayList<>();
            List<String> listFilterRom = new ArrayList<>();
            List<String> listFilterScreen = new ArrayList<>();
            List<Product> productFilter = MyApplication.getInstance().getStorage().listProduct;
            Log.d(TAG, "init Product: " + productFilter.size() + " sản phẩm");

            if (newData.getStringArrayList("filterBrand") != null) {
                if(newData.getStringArrayList("filterBrand").size() > 0) {
                    listFilterBrand = newData.getStringArrayList("filterBrand");
                    Log.d(TAG, "listFilterBrand: " + listFilterBrand.size());
                    productFilter = filterProductByBrand(listFilterBrand, productFilter);
                }
            }
            if (newData.getStringArrayList("filterPrice") != null) {
                if(newData.getStringArrayList("filterPrice").size() > 0) {
                    listFilterPrice = newData.getStringArrayList("filterPrice");
                    Log.d(TAG, "listFilterPrice: " + listFilterPrice.size());
                    productFilter = filterProductByPrice(listFilterPrice, productFilter);
                }
            }
            if (newData.getStringArrayList("filterCategory") != null) {
                if(newData.getStringArrayList("filterCategory").size() > 0) {
                    listFilterCategory = newData.getStringArrayList("filterCategory");
                    Log.d(TAG, "listFilterCategory: " + listFilterCategory.size());
                    productFilter = filterProductByCategory(listFilterCategory, productFilter);
                }
            }
            if (newData.getStringArrayList("filterRam") != null) {
                if(newData.getStringArrayList("filterRam").size() > 0) {
                    listFilterRam = newData.getStringArrayList("filterRam");
                    productFilter = filterProductByRam(listFilterRam, productFilter);
                }
            }
            if (newData.getStringArrayList("filterRom") != null) {
                if(newData.getStringArrayList("filterRom").size() > 0) {
                    listFilterRom = newData.getStringArrayList("filterRom");
                    productFilter = filterProductByRom(listFilterRom, productFilter);
                }
            }
            if (newData.getStringArrayList("filterScreen") != null) {
                if(newData.getStringArrayList("filterScreen").size() > 0) {
                    listFilterScreen = newData.getStringArrayList("filterScreen");
                    productFilter = filterProductByScreen(listFilterScreen, productFilter);
                }
            }
            if (newData.getString("search") != null) {
                binding.etSearch.setText(newData.getString("search"));
                searchProduct(binding.etSearch.getText().toString().trim());
            } else {
                Log.d(TAG, "displayProduct: " + productFilter.size() + " sản phẩm");
                displayProduct(productFilter);
            }
            binding.rvFilter.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            FilterAdapter adapter = new FilterAdapter(context);
            //adapter.renewItems(listFilter);
            if (listFilterBrand.size() > 0) {
                adapter.addItems(listFilterBrand);
            }
            if (listFilterPrice.size() > 0) {
                adapter.addItems(listFilterPrice);
            }
            if (listFilterCategory.size() > 0) {
                adapter.addItems(listFilterCategory);
            }
            if (listFilterRam.size() > 0) {
                adapter.addItems(listFilterRam);
            }
            if (listFilterRom.size() > 0) {
                adapter.addItems(listFilterRom);
            }
            if (listFilterScreen.size() > 0) {
                adapter.addItems(listFilterScreen);
            }
            binding.rvFilter.setAdapter(adapter);

        }

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.showFragment(PagerFragment.TAG, null, false);
            }
        });

        binding.ivFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.showFragment(FilterFragment.TAG, null, true);
            }
        });

        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideSoftInput(binding.etSearch);
                    Log.d(TAG, "onEditorAction: " + listProduct.size());
                    searchProduct(v.getText().toString().trim());
                    return true;
                }
                return false;
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        //Log.d(TAG, "onResume: ");

    }


    @Override
    public void setData(Object data) {
        this.mData = data;
    }

    @Override
    protected FragmentSearchResultBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentSearchResultBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if (key.equals(Constants.KEY_GET_TOP_SALE_PRODUCT)) {
            //Log.d(TAG, "apiSuccess: " + data.toString());
            listProduct = (List<Product>) data;
            MyApplication.getInstance().getStorage().listProduct = listProduct;
        }
    }

    private List<Product> filterProductByBrand(List<String> listFilterBrand, List<Product> listProductFilter) {
        List<Product> productSearch = new ArrayList<>();
        if (listProductFilter != null) {
            Log.d(TAG, "listProductFilter: " + listProductFilter.size() + " sản phẩm");
            if (listProductFilter.size() > 0 && listFilterBrand.size() > 0) {
                for (String filter : listFilterBrand) {
                    for (Product item : listProductFilter) {
                        if (item.getBrand().equalsIgnoreCase(filter)) {
                            productSearch.add(item);
                        }
                    }
                }

            }
        }
        Log.d(TAG, "filterProductByBrand: " + productSearch.size() + " sản phẩm");
        return productSearch;
    }

    private List<Product> filterProductByPrice(List<String> listFilterPrice, List<Product> listProductFilter) {
        List<Product> productSearch = new ArrayList<>();
        if (listProductFilter != null) {
            if (listProductFilter.size() > 0 && listFilterPrice.size() > 0) {
                for (String filter : listFilterPrice) {
                    switch (filter) {
                        case "Dưới 2 triệu":
                            for (Product item : listProductFilter) {
                                if (item.getPrice() < 2000000) {
                                    productSearch.add(item);
                                }
                            }
                            break;
                        case "Từ 2 - 4 triệu":
                            for (Product item : listProductFilter) {
                                if (item.getPrice() >= 2000000 && item.getPrice() < 4000000) {
                                    productSearch.add(item);
                                }
                            }
                            break;
                        case "Từ 4 - 7 triệu":
                            for (Product item : listProductFilter) {
                                if (item.getPrice() >= 4000000 && item.getPrice() < 7000000) {
                                    productSearch.add(item);
                                }
                            }
                            break;
                        case "Từ 7 - 13 triệu":
                            for (Product item : listProductFilter) {
                                if (item.getPrice() >= 7000000 && item.getPrice() < 13000000) {
                                    productSearch.add(item);
                                }
                            }
                            break;
                        case "Từ 13 - 20 triệu":
                            for (Product item : listProductFilter) {
                                if (item.getPrice() >= 13000000 && item.getPrice() < 20000000) {
                                    productSearch.add(item);
                                }
                            }
                            break;
                        case "Trên 20 triệu":
                            for (Product item : listProductFilter) {
                                if (item.getPrice() >= 20000000) {
                                    productSearch.add(item);
                                }
                            }
                            break;
                    }
                }
            }
        }
        Log.d(TAG, "filterProductByPrice: " + productSearch.size() + " sản phẩm");
        return productSearch;
    }

    private List<Product> filterProductByCategory(List<String> listFilterCategory, List<Product> listProductFilter) {
        List<Product> productSearch = new ArrayList<>();
        if (listProductFilter != null) {
            if (listProductFilter.size() > 0 && listFilterCategory.size() > 0) {
                for (String filter : listFilterCategory) {
                    if (filter.equals("Điện thoại")) {
                        for (Product item : listProductFilter) {
                            if (!item.isCategory()) {
                                productSearch.add(item);
                            }
                        }
                    } else if (filter.equals("Máy tính bảng")) {
                        for (Product item : listProductFilter) {
                            if (item.isCategory()) {
                                productSearch.add(item);
                            }
                        }
                    }
                }

            }
        }
        Log.d(TAG, "filterProductByCategory: " + productSearch.size() + " sản phẩm");
        return productSearch;
    }

    private List<Product> filterProductByRam(List<String> listFilterRam, List<Product> listProductFilter) {
        List<Product> productSearch = new ArrayList<>();
        if (listProductFilter != null) {
            if (listProductFilter.size() > 0 && listFilterRam.size() > 0) {
                for (String filter : listFilterRam) {
                    if (filter.equals("2GB")) {
                        for (Product item : listProductFilter) {
                            if (item.getRam() == 2) {
                                productSearch.add(item);
                            }
                        }
                    } else if (filter.equals("3GB")) {
                        for (Product item : listProductFilter) {
                            if (item.getRam() == 3) {
                                productSearch.add(item);
                            }
                        }
                    } else if (filter.equals("4GB")) {
                        for (Product item : listProductFilter) {
                            if (item.getRam() == 4) {
                                productSearch.add(item);
                            }
                        }
                    } else if (filter.equals("6GB")) {
                        for (Product item : listProductFilter) {
                            if (item.getRam() == 6) {
                                productSearch.add(item);
                            }
                        }
                    } else if (filter.equals("8GB")) {
                        for (Product item : listProductFilter) {
                            if (item.getRam() == 8) {
                                productSearch.add(item);
                            }
                        }
                    } else if (filter.equals("12GB")) {
                        for (Product item : listProductFilter) {
                            if (item.getRam() == 12) {
                                productSearch.add(item);
                            }
                        }
                    }
                }
            }
        }
        Log.d(TAG, "filterProductByRam: " + productSearch.size() + " sản phẩm");
        return productSearch;
    }

    private List<Product> filterProductByRom(List<String> listFilterRom, List<Product> listProductFilter) {
        List<Product> productSearch = new ArrayList<>();
        if (listProductFilter != null) {
            if (listProductFilter.size() > 0 && listFilterRom.size() > 0) {
                for (String filter : listFilterRom) {
                    if (filter.equals("32GB")) {
                        for (Product item : listProductFilter) {
                            if (item.getMemory() == 32) {
                                productSearch.add(item);
                            }
                        }
                    } else if (filter.equals("64GB")) {
                        for (Product item : listProductFilter) {
                            if (item.getMemory() == 64) {
                                productSearch.add(item);
                            }
                        }
                    } else if (filter.equals("128GB")) {
                        for (Product item : listProductFilter) {
                            if (item.getMemory() == 128) {
                                productSearch.add(item);
                            }
                        }
                    } else if (filter.equals("256GB")) {
                        for (Product item : listProductFilter) {
                            if (item.getMemory() == 256) {
                                productSearch.add(item);
                            }
                        }
                    } else if (filter.equals("512GB")) {
                        for (Product item : listProductFilter) {
                            if (item.getMemory() == 512) {
                                productSearch.add(item);
                            }
                        }
                    } else if (filter.equals("1TB")) {
                        for (Product item : listProductFilter) {
                            if (item.getMemory() == 1024) {
                                productSearch.add(item);
                            }
                        }
                    }
                }
            }
        }
        Log.d(TAG, "filterProductByRom: " + productSearch.size() + " sản phẩm");
        return productSearch;
    }

    private List<Product> filterProductByScreen(List<String> listFilterScreen, List<Product> listProductFilter) {
        List<Product> productSearch = new ArrayList<>();
        if (listProductFilter != null) {
            if (listProductFilter.size() > 0 && listFilterScreen.size() > 0) {
                for (String filter : listFilterScreen) {
                    if (filter.equals("Màn hình nhỏ gọn")) {
                        for (Product item : listProductFilter) {
                            if (item.getScreenSize() < 6.0f) {
                                productSearch.add(item);
                            }
                        }
                    } else if (filter.equals("Từ 6 inch trở lên")) {
                        for (Product item : listProductFilter) {
                            if (item.getScreenSize() >= 6.0f) {
                                productSearch.add(item);
                            }
                        }
                    }
                }

            }
        }
        Log.d(TAG, "filterProductByScreen: " + productSearch.size() + " sản phẩm");
        return productSearch;
    }

    private void searchProduct(String strSearch) {
        if (!strSearch.equals("")) {
            List<Product> productSearch = new ArrayList<>();
            if (listProduct != null) {
                Log.d(TAG, "searchProduct: " + strSearch);
                if (listProduct.size() > 0 && !strSearch.equals("")) {
                    for (Product item : listProduct) {
                        if (StringConvert.removeDiacriticalMarks(item.getName()).toLowerCase().contains(strSearch.toLowerCase())) {
                            productSearch.add(item);
                        }
                    }

                }
            }
            displayProduct(productSearch);
        }

    }

    private void displayProduct(List<Product> productSearch) {
        if (productSearch.size() > 0) {
            Log.d(TAG, "productSearch by filter: " + productSearch.size());
            ViewUtils.show(binding.tvCountResult);
            ViewUtils.gone(binding.layoutEmptySearch);
            binding.tvCountResult.setText("Tìm thấy " + productSearch.size() + " kết quả");
            binding.rvProduct.setLayoutManager(new GridLayoutManager(context, 2));
            initProductView(productSearch);
        } else {
            ViewUtils.gone(binding.tvCountResult);
            ViewUtils.show(binding.layoutEmptySearch);
        }
    }

    private void initProductView(List<Product> productSearch) {
        Log.d(TAG, "show adapter: ");
        ProductAdapter adapter = new ProductAdapter(context, productSearch);
        binding.rvProduct.setAdapter(adapter);
        adapter.getProductLD().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                callBack.showFragment(ProductFragment.TAG, product, true);
            }
        });
    }

    @Override
    public void apiError(String key, int code, Object data) {

    }
}
