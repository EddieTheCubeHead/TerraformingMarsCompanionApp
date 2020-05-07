package com.example.terraformingmarscompanionapp.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter
{

    private static final String[] TAB_TITLES =
            new String[]{"Player", "Cards", "Special"};

    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position)
    {
        // default instructional comment:
        // "getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below)."

        // main view pages go here.
        switch (position)
        {
            case 0:
                return new ResourcesFragment();
            case 1:
                return new CardsFragment();
            case 2:
                return new SpecialCardFragment();
            default:
                return null;
        }
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}