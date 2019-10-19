package com.example.kriti;

import android.content.Context;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.kriti.BlankFragment;
import com.example.kriti.CoursesFragment;
import com.example.kriti.R;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext=context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new DepartmentFragment();
        } else
            return new ClubsFragment();

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.department);
        } else
            return mContext.getString(R.string.clubs);

}}
