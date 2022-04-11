package com.groupone.mobilestore.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.groupone.mobilestore.view.fragment.DeliveredFragment;
import com.groupone.mobilestore.view.fragment.DeliveringFragment;
import com.groupone.mobilestore.view.fragment.OrderFragment;
import com.groupone.mobilestore.view.fragment.CartFragment;
import com.groupone.mobilestore.view.fragment.HomeFragment;
import com.groupone.mobilestore.view.fragment.ProfileFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {

    public MyViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

//    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
//        super(fragmentActivity);
//    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new DeliveringFragment();
            case 1:
                return new DeliveredFragment();
            default:
                return new DeliveringFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
