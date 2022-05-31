package com.groupone.mobilestore.view.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.databinding.FragmentHomeBinding;
import com.groupone.mobilestore.model.Favorite;
import com.groupone.mobilestore.model.Product;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.view.adapter.ProductAdapter;
import com.groupone.mobilestore.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    public static final String TAG = HomeFragment.class.getName();

    private PagerFragment parentFrag;
    private List<Product> listProduct = new ArrayList<>();
    private User user = MyApplication.getInstance().getStorage().user;

    @Override
    protected Class<HomeViewModel> getClassVM() {
        return HomeViewModel.class;
    }

    @Override
    protected void initViews() {

        binding.scrollView.smoothScrollTo(0, 0);

        binding.rvProduct.setLayoutManager(new GridLayoutManager(context, 2));
        if (MyApplication.getInstance().getStorage().listProduct == null) {
            viewModel.getTopSaleProduct();
        } else {
            List<Product> productsStorage = MyApplication.getInstance().getStorage().listProduct;
            ProductAdapter adapter = new ProductAdapter(context, productsStorage.subList(0, 24));
            binding.rvProduct.setAdapter(adapter);
            adapter.getProductLD().observe(this, new Observer<Product>() {
                @Override
                public void onChanged(Product product) {
                    parentFrag = ((PagerFragment) HomeFragment.this.getParentFragment());
                    if (parentFrag != null) {
                        parentFrag.setActionShowFragment(ProductFragment.TAG, product, true);
                    }
                }
            });
        }

        binding.rowSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentFrag = ((PagerFragment) HomeFragment.this.getParentFragment());
                if (parentFrag != null) {
                    parentFrag.setActionShowFragment(SearchFragment.TAG, null, true);
                }
            }
        });

        binding.btApple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> listName = new ArrayList<>();
                listName.add("Apple");
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("filterBrand", listName);
                parentFrag = ((PagerFragment) HomeFragment.this.getParentFragment());
                if (parentFrag != null) {
                    parentFrag.setActionShowFragment(SearchResultFragment.TAG, bundle, true);
                }
            }
        });

        binding.btSamsung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> listName = new ArrayList<>();
                listName.add("Samsung");
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("filterBrand", listName);
                parentFrag = ((PagerFragment) HomeFragment.this.getParentFragment());
                if (parentFrag != null) {
                    parentFrag.setActionShowFragment(SearchResultFragment.TAG, bundle, true);
                }
            }
        });

        binding.btXiaomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> listName = new ArrayList<>();
                listName.add("Xiaomi");
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("filterBrand", listName);
                parentFrag = ((PagerFragment) HomeFragment.this.getParentFragment());
                if (parentFrag != null) {
                    parentFrag.setActionShowFragment(SearchResultFragment.TAG, bundle, true);
                }
            }
        });

        binding.btOppo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> listName = new ArrayList<>();
                listName.add("Oppo");
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("filterBrand", listName);
                parentFrag = ((PagerFragment) HomeFragment.this.getParentFragment());
                if (parentFrag != null) {
                    parentFrag.setActionShowFragment(SearchResultFragment.TAG, bundle, true);
                }
            }
        });

        binding.btNokia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> listName = new ArrayList<>();
                listName.add("Nokia");
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("filterBrand", listName);
                parentFrag = ((PagerFragment) HomeFragment.this.getParentFragment());
                if (parentFrag != null) {
                    parentFrag.setActionShowFragment(SearchResultFragment.TAG, bundle, true);
                }
            }
        });

        binding.btAsus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> listName = new ArrayList<>();
                listName.add("Asus");
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("filterBrand", listName);
                parentFrag = ((PagerFragment) HomeFragment.this.getParentFragment());
                if (parentFrag != null) {
                    parentFrag.setActionShowFragment(SearchResultFragment.TAG, bundle, true);
                }
            }
        });

        binding.btVivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> listName = new ArrayList<>();
                listName.add("Vivo");
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("filterBrand", listName);
                parentFrag = ((PagerFragment) HomeFragment.this.getParentFragment());
                if (parentFrag != null) {
                    parentFrag.setActionShowFragment(SearchResultFragment.TAG, bundle, true);
                }
            }
        });

        binding.btRealme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> listName = new ArrayList<>();
                listName.add("Realme");
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("filterBrand", listName);
                parentFrag = ((PagerFragment) HomeFragment.this.getParentFragment());
                if (parentFrag != null) {
                    parentFrag.setActionShowFragment(SearchResultFragment.TAG, bundle, true);
                }
            }
        });

        binding.btNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentFrag = ((PagerFragment) HomeFragment.this.getParentFragment());
                if (parentFrag != null) {
                    parentFrag.setActionShowFragment(PostFragment.TAG, null, true);
                }
            }
        });

    }

    @Override
    protected FragmentHomeBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentHomeBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if (key.equals(Constants.KEY_GET_TOP_SALE_PRODUCT)) {
            //Log.d(TAG, "apiSuccess: " + data.toString());
            List<Product> products = (List<Product>) data;
            listProduct = products;

            MyApplication.getInstance().getStorage().listProduct = products;

            ProductAdapter adapter = new ProductAdapter(context, products.subList(0, 24));
            binding.rvProduct.setAdapter(adapter);
            //binding.rvProduct.setFocusable(false);
            //binding.rvProduct.setNestedScrollingEnabled(false);

            adapter.getProductLD().observe(this, new Observer<Product>() {
                @Override
                public void onChanged(Product product) {
                    parentFrag = ((PagerFragment) HomeFragment.this.getParentFragment());
                    if (parentFrag != null) {
                        parentFrag.setActionShowFragment(ProductFragment.TAG, product, true);
                    }
                }
            });


//            for (Product item: products){
//                Log.d(TAG, "Prodcut: " + item.getName());
//            }
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {
        if (code == 999) {
            Log.d(TAG, "apiError: " + data.toString());
            Toast.makeText(context, "Không thể kết nối đến máy chủ", Toast.LENGTH_SHORT).show();
        }
    }

}
