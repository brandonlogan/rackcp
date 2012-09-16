package com.rackspace.rackcp;

import com.rackspace.rackcp.fragment_pager_adapters.CPFragmentPagerAdapter;
import com.rackspace.rackcp.fragments.ServersFragment;
import com.rackspace.rackcp.views.SwipeyTabs;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class MainActivity extends FragmentActivity {

    private ViewPager mPager;
    private CPFragmentPagerAdapter mFragmentPagerAdapter;
    private SwipeyTabs mTabs;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPager = (ViewPager)this.findViewById(R.id.productPager);
        mTabs = (SwipeyTabs)this.findViewById(R.id.swipeyTabs);
        mFragmentPagerAdapter = new CPFragmentPagerAdapter(
                                        this.getSupportFragmentManager(),
                                        this, mPager);
        mPager.setAdapter(mFragmentPagerAdapter);
        mTabs.setAdapter(mFragmentPagerAdapter);
        mPager.setOnPageChangeListener(mTabs);
        mPager.setCurrentItem(ServersFragment.POSITION);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
