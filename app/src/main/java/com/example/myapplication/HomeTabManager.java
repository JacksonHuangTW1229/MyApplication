package com.example.myapplication;

//
// Copyright 2016 by Grindr LLC,
// All rights reserved.
//
// This software is confidential and proprietary information of
// Grindr LLC ("Confidential Information").
// You shall not disclose such Confidential Information and shall use
// it only in accordance with the terms of the license agreement
// you entered into with Grindr LLC.
//

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;


import androidx.annotation.DrawableRes;
import androidx.annotation.StringDef;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static com.example.myapplication.HomeTabManager.FragmentTag.TAG_CASCADE;
import static com.example.myapplication.HomeTabManager.FragmentTag.TAG_EXPLORE;
import static com.example.myapplication.HomeTabManager.FragmentTag.TAG_FAVORITES;
import static com.example.myapplication.HomeTabManager.FragmentTag.TAG_INBOX;
import static com.example.myapplication.HomeTabManager.FragmentTag.TAG_INTO;
import static com.example.myapplication.HomeTabManager.FragmentTag.TAG_SUBSCRIPTION;
import static com.example.myapplication.HomeTabManager.FragmentTag.TAG_UNKNOWN;

public class HomeTabManager {
    private List<String> tabs = new ArrayList<>();

    @StringDef
    public @interface FragmentTag {
        String TAG_CASCADE = "CASCADE";
        String TAG_INBOX = "INBOX";
        String TAG_EXPLORE = "EXPLORE";
        String TAG_INTO = "INTO";
        String TAG_FAVORITES = "FAVORITES";
        String TAG_SUBSCRIPTION = "SUBSCRIPTION";
        String TAG_UNKNOWN = "UNKNOWN";
    }


    FragmentManager fm;
    MainActivity activity;

    private String selectedFragmentTag;
//    private boolean isABtestingIntoTab;
//    private boolean isABtestingExploreMove;
//    private boolean hasSubscriptionTab;
    private boolean isInExplore = false;

    public HomeTabManager(MainActivity activity) {
        this.activity = activity;
        this.fm = activity.getSupportFragmentManager();

        initTabs();
    }

    /**
     * check what tabs we have when create()
     */
    private void initTabs() {
        tabs.add(TAG_CASCADE);
//        tabs.add(TAG_INBOX);
//        if (isABtestingIntoTab) {
//            tabs.add(TAG_INTO);
//        } else if (!isABtestingExploreMove) {
            tabs.add(TAG_EXPLORE);
//        }
        tabs.add(TAG_FAVORITES);
//        if (hasSubscriptionTab) {
//            tabs.add(TAG_SUBSCRIPTION);
//        }
    }

    public void onDestroy() {
        this.activity = null;
        this.fm = null;
    }


    public @FragmentTag String getHomeTabTag(int position) {
        if (tabs == null || position < 0 || position >= tabs.size()) {
            // should not be here, just to avoid OutOfIndexException
            return TAG_CASCADE;
        }
        return tabs.get(position);
    }

    public int getTabPosition(String homeTabTag) {
        int position = tabs.indexOf(homeTabTag);
        return position >= 0 ? position : 0 ;
    }

    public int getCount() {
        return tabs.size();
    }

    public @DrawableRes int getTabIconId(@FragmentTag String homeTabTag) {
        switch (homeTabTag) {
            case TAG_FAVORITES:
                return R.drawable.home_favorites_tab;
            case TAG_CASCADE:
                return R.drawable.home_cascade_tab;
            case TAG_EXPLORE:
                return R.drawable.home_explore_tab;
//            case TAG_INTO:
//                return R.drawable.home_into_tab;
//            case TAG_INBOX:
//                return R.drawable.home_inbox_tab;
//            case TAG_SUBSCRIPTION:
//                return R.drawable.home_subscription_tab;
            default:
                return -1;
        }
    }

    public @StringRes int getTabTextId(@FragmentTag String homeTabTag) {
        switch (homeTabTag) {
            case TAG_FAVORITES:
                return R.string.home_tab_favs_text;
            case TAG_CASCADE:
                return R.string.home_tab_nearby_text;
            case TAG_EXPLORE:
                return R.string.home_tab_explore_text;
//            case TAG_INTO:
//                return R.string.home_tab_into_tab_text;
//            case TAG_INBOX:
//                return R.string.home_tab_inbox_text;
//            case TAG_SUBSCRIPTION:
//                return R.string.home_tab_store_text;
            default:
                return -1;
        }
    }

    public int getTabDescriptionId(@FragmentTag String homeTabTag) {
        switch (homeTabTag) {
            case TAG_FAVORITES:
                return R.string.home_tab_faves;
            case TAG_CASCADE:
                return R.string.home_tab_home;
            case TAG_EXPLORE:
                return R.string.home_tab_explore;
//            case TAG_INTO:
//                return R.string.home_tab_into_tab;
//            case TAG_INBOX:
//                return R.string.home_tab_inbox;
//            case TAG_SUBSCRIPTION:
//                return R.string.home_tab_subscription;
            default:
                return -1;
        }
    }

    public void setInExplore(boolean inExplore) {
        isInExplore = inExplore;
    }

    public void selectTab(@FragmentTag String targetTag) {
//
//        if (isABtestingExploreMove) {
//            if (targetTag.equals(TAG_EXPLORE)) {
//                isInExplore = true;
//            } else if (targetTag.equals(TAG_CASCADE) && isInExplore) {
//                targetTag = TAG_EXPLORE;
//            }
//        }

        String previousFragmentTag = selectedFragmentTag;
        if (TextUtils.equals(previousFragmentTag, targetTag)) {
            return;
        }
        Fragment lastSelectedFragment = fm.findFragmentByTag(selectedFragmentTag);
        FragmentTransaction transaction = fm.beginTransaction();
        if (lastSelectedFragment != null) {
            transaction.hide(lastSelectedFragment);
        }
        selectedFragmentTag = targetTag;
        Fragment fragment = fm.findFragmentByTag(targetTag);
        if (fragment == null) {
            switch (targetTag) {
                case TAG_CASCADE:
                    fragment = new MyFragment1();
                    break;
//                case TAG_INBOX:
//                    fragment = new InboxFragment();
//                    break;
                case TAG_EXPLORE:
                    fragment = new MyFragment2();
                    break;
                case TAG_FAVORITES:
                    fragment = new MyFragment3();
                    break;
//                case TAG_SUBSCRIPTION:
//                    fragment = storeFragmentFactory.newInstance(PurchaseConstants.PURCHASE_SOURCE_NAV_BAR);
//                    break;
//                case TAG_INTO:
//                    fragment = new IntoTabFragment();
//                    break;
            }
            transaction.add(R.id.fragment_container, fragment, targetTag);
        }
        transaction.show(fragment);
        // Note: we are purposely allowing state loss. If we lose state, it's better than crashing.
        // Articles online suggest using this method as a last resort:
        // https://www.androiddesignpatterns.com/2013/08/fragment-transaction-commit-state-loss.html
        // We've inspected the places we called commitNow() and they seem ok given the article above.
        // We still are seeing exceptions however, so we just accept the state loss if HomeActivity
        // restore another fragment instead.
        transaction.commitNowAllowingStateLoss();
//        activity.selectDrawerFilter(permissionDeniedForFragment ? TAG_UNKNOWN : targetTag);
    }

    public Fragment getSelectedFragment() {
        return selectedFragmentTag != null ? fm.findFragmentByTag(selectedFragmentTag) : null;
    }
}