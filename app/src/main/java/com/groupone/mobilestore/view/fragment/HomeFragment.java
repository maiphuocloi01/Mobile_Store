package com.groupone.mobilestore.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentHomeBinding;
import com.groupone.mobilestore.model.Product;
import com.groupone.mobilestore.view.adapter.ProductAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, CommonViewModel> {

    public static final String TAG = HomeFragment.class.getName();

    private PagerFragment parentFrag;

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {

        List<Product> listProduct = new ArrayList<>();

        listProduct.add(new Product(1, R.drawable.img_iphone13, "iPhone 13 Pro Max", "128GB", 3.8, 34000000, 38));
        listProduct.add(new Product(2, R.drawable.img_iphone13_2, "iPhone 13 Pro Max", "128GB", 3.8, 34000000, 38));
        listProduct.add(new Product(3, R.drawable.img_iphone13_3, "iPhone 13 Pro Max Ultra Ultimate Super Plus", "128GB", 3.8, 34000000, 38));
        listProduct.add(new Product(4, R.drawable.img_iphone13_4, "iPhone 13 Pro Max", "128GB", 3.8, 34000000, 38));
        listProduct.add(new Product(5, R.drawable.img_iphone13, "iPhone 13 Pro Max", "128GB", 3.8, 34000000, 38));
        listProduct.add(new Product(6, R.drawable.img_iphone13, "iPhone 13 Pro Max", "128GB", 3.8, 34000000, 38));

        binding.rvProduct.setLayoutManager(new GridLayoutManager(context, 2));
        ProductAdapter adapter = new ProductAdapter(context, listProduct);
        binding.rvProduct.setAdapter(adapter);

        binding.rowSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentFrag = ((PagerFragment) HomeFragment.this.getParentFragment());
                if (parentFrag != null) {
                    parentFrag.setActionShowFragment(SearchFragment.TAG, null, true);
                }
            }
        });
        adapter.getProductLD().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                parentFrag = ((PagerFragment) HomeFragment.this.getParentFragment());
                if (parentFrag != null) {
                    parentFrag.setActionShowFragment(ProductFragment.TAG, product, true);
                }
            }
        });

        binding.btApple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> listName = new ArrayList<>();
                listName.add("Apple");
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("filter", listName);
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
                bundle.putStringArrayList("filter", listName);
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
                bundle.putStringArrayList("filter", listName);
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
                bundle.putStringArrayList("filter", listName);
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
                bundle.putStringArrayList("filter", listName);
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
                bundle.putStringArrayList("filter", listName);
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
                bundle.putStringArrayList("filter", listName);
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
                bundle.putStringArrayList("filter", listName);
                parentFrag = ((PagerFragment) HomeFragment.this.getParentFragment());
                if (parentFrag != null) {
                    parentFrag.setActionShowFragment(SearchResultFragment.TAG, bundle, true);
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

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }

}
