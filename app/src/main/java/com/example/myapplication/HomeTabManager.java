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
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
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
    private List<List<String>> tabChildTags = new ArrayList<>();
    private List<Integer> tabDepth = new ArrayList<>();

    @StringDef
    public @interface FragmentTag {
        String TAG_CASCADE = "CASCADE";
        String TAG_INBOX = "INBOX";
        String TAG_EXPLORE = "EXPLORE";
        String TAG_INTO = "INTO";
        String TAG_FAVORITES = "FAVORITES";
        String TAG_SUBSCRIPTION = "SUBSCRIPTION";
        String TAG_UNKNOWN = "UNKNOWN";

        String MyFragment1 = "MyFragment1";
        String MyFragment1_2 = "MyFragment1_2";
        String MyFragment2 = "MyFragment2";
        String MyFragment2_2 = "MyFragment2_2";
        String MyFragment3 = "MyFragment3";
        String MyFragment3_2 = "MyFragment3_2";
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
        addTabs(TAG_CASCADE, Arrays.asList(FragmentTag.MyFragment1, FragmentTag.MyFragment1_2));

//        tabs.add(TAG_INBOX);
//        if (isABtestingIntoTab) {
//            tabs.add(TAG_INTO);
//        } else if (!isABtestingExploreMove) {

        addTabs(TAG_EXPLORE, Arrays.asList(FragmentTag.MyFragment2, FragmentTag.MyFragment2_2));
//        }

        addTabs(TAG_FAVORITES, Arrays.asList(FragmentTag.MyFragment3));
//        if (hasSubscriptionTab) {
//            tabs.add(TAG_SUBSCRIPTION);
//        }

        // record fragments by tab position
//        list =  new ArrayList<ArrayList<BaseFragment>>(tabs.size());

    }

    private void addTabs(String tag, List<String> childTags) {
        tabs.add(tag);
        tabChildTags.add(childTags);
        tabDepth.add(0);
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

//    private List list = new ArrayList<ArrayList<BaseFragment>>();

    public int getChildDepth(int selectTabPosition) {
       return tabDepth.get(selectTabPosition);
    }

    public void popUp(int selectTabPosition) {
        int currentDepth = tabDepth.get(selectTabPosition);
        int targetDepth = currentDepth - 1;
        String tabTag = tabs.get(selectTabPosition);
        String childTag = tabChildTags.get(selectTabPosition).get(targetDepth);
        selectTab(tabTag, childTag);
    }

    public int getChildDepth(int selectTabPosition, String childTag) {
        List<String> childTags = tabChildTags.get(selectTabPosition);
        for (int i = 0 ; i < childTags.size() ; i++ ) {
            if (childTag.equals(childTags.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public void selectTab(@FragmentTag String tabTag, @FragmentTag String childTag) {
//
//        if (isABtestingExploreMove) {
//            if (targetTag.equals(TAG_EXPLORE)) {
//                isInExplore = true;
//            } else if (targetTag.equals(TAG_CASCADE) && isInExplore) {
//                targetTag = TAG_EXPLORE;
//            }
//        }

        int targetTabPosition = getTabPosition(tabTag);
        int depth;
        if (childTag == null) {
            // get current depth
            depth = tabDepth.get(targetTabPosition);
            // first child tag
            childTag = tabChildTags.get(targetTabPosition).get(depth);
        } else {
            depth = getChildDepth(targetTabPosition, childTag);
            tabDepth.set(targetTabPosition, depth);
        }

        Log.d("testtt" , " selectTab , tab = " + tabTag + " , child = " + childTag + " , depth = " + depth);

        String previousFragmentTag = selectedFragmentTag;
        if (TextUtils.equals(previousFragmentTag, childTag)) {
            return;
        }
        Fragment lastSelectedFragment = fm.findFragmentByTag(selectedFragmentTag);
        FragmentTransaction transaction = fm.beginTransaction();
        if (lastSelectedFragment != null) {
            transaction.hide(lastSelectedFragment);
        }
        selectedFragmentTag = childTag;
        Fragment fragment = fm.findFragmentByTag(childTag);

        if (fragment == null) {
            switch (childTag) {
                case TAG_CASCADE:
                    fragment = new MyFragment1();
                    break;
                case TAG_EXPLORE:
                    fragment = new MyFragment2();
                    break;
                case TAG_FAVORITES:
                    fragment = new MyFragment3();
                    break;
                case FragmentTag.MyFragment1:
                    fragment = new MyFragment1();
                    break;
                case FragmentTag.MyFragment1_2:
                    fragment = new MyFragment1_2();
                    break;
                case FragmentTag.MyFragment2:
                    fragment = new MyFragment2();
                    break;
                case FragmentTag.MyFragment2_2:
                    fragment = new MyFragment2_2();
                    break;
                case FragmentTag.MyFragment3:
                    fragment = new MyFragment3();
                    break;
            }

            Log.d("testtt" , " selectTab , new fragment = " + fragment);
            transaction.add(R.id.fragment_container, fragment, childTag);
//            list.add(fragment);
        }

        Log.d("testtt" , " selectTab , show fragment = " + fragment);
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