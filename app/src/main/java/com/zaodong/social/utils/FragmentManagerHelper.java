package com.zaodong.social.utils;


import android.util.Log;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 2021/4/27 16:21
 * @Version:
 */
public class FragmentManagerHelper {
    private static final String TAG = FragmentManagerHelper.class.getSimpleName();

    private FragmentManager mFragmentManager;
    private int mContainerViewId;


    public FragmentManagerHelper(@Nullable FragmentManager fragmentManager, @IdRes int containerViewId) {
        this.mFragmentManager = fragmentManager;
        this.mContainerViewId = containerViewId;
    }


    public int getChildCount() {
        List<Fragment> childFragments = mFragmentManager.getFragments();
        if (childFragments == null) {
            return 0;
        }
        return childFragments.size();
    }


    private void add(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(mContainerViewId, fragment, fragment.getClass().getName());
        fragmentTransaction.commit();
    }


    public synchronized void switchFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        List<Fragment> childFragments = mFragmentManager.getFragments();
        if (childFragments.size() > 0) {
            for (Fragment childFragment : childFragments) {
                fragmentTransaction.hide(childFragment);
            }
        }
        for (Fragment childFragment : childFragments) {
            Log.e(TAG, "switchFragment: " + childFragment.getClass().getSimpleName());
        }


        if (!childFragments.contains(fragment)) {
            fragmentTransaction.add(mContainerViewId, fragment, fragment.getClass().getName());
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }


    public Fragment getFragmentByTag(String tag) {
        return mFragmentManager.findFragmentByTag(tag);
    }
}
