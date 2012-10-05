package com.rackspace.rackcp.fragment_pager_adapters;

import com.rackspace.rackcp.adapters.ISwipeyTabsAdapter;
import com.rackspace.rackcp.domain.Auth;
import com.rackspace.rackcp.domain.Server;
import com.rackspace.rackcp.fragments.ServersFragment;
import com.rackspace.rackcp.views.SwipeyTabs;
import com.rackspace.rackcp.R;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class CPFragmentPagerAdapter extends FragmentPagerAdapter
                                     implements ISwipeyTabsAdapter{

    private static final int NUMBER_OF_PAGES = 5;
    
    private Context mContext;
    private ViewPager mPager;
    private String[] titles;
    
    public CPFragmentPagerAdapter(FragmentManager fm, Context context, 
                                      ViewPager pager, Auth authData) {
        super(fm);
        this.mContext = context;
        this.mPager = pager;
        titles = new String[this.getCount()];
        titles[0] = "Load Balancers";
        titles[1] = "Cloud Files";
        titles[2] = ServersFragment.TITLE;
        titles[3] = "Databases";
        titles[4] = "DNS";
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        //each position will have a different fragment
        //0 - Servers
        //1 - Load Balancers
        //etc
        switch(position){
        case 0:
            Server[] servers = new Server[]{new Server(1, "Load Balancer1"),
                    new Server(2, "Load Balancer2"),
                    new Server(3, "Load Balancer3"),
                    new Server(4, "Load Balancer4")};
            f = new ServersFragment(servers);
            break;
        case 1:
            Server[] servers2 = new Server[]{new Server(1, "Container1"),
                    new Server(2, "Container2"),
                    new Server(3, "Container3"),
                    new Server(4, "Container4")};
            f = new ServersFragment(servers2);
            break;
        case 2:
            Server[] servers3 = new Server[]{new Server(1, "Server1"),
                    new Server(2, "Server2"),
                    new Server(3, "Server3"),
                    new Server(4, "Server4"),
                    new Server(5, "Server5"),
                    new Server(6, "Server6")};
            f = new ServersFragment(servers3);
            break;
        case 3:
            Server[] servers4 = new Server[]{new Server(1, "Database1"),
                    new Server(2, "Database2"),
                    new Server(3, "Database3")};
            f = new ServersFragment(servers4);
            break;
        case 4:
            Server[] servers5 = new Server[]{new Server(1, "DNS1"),
                                             new Server(2, "DNS2")};
            f = new ServersFragment(servers5);
            break;
        }
        
        return f;
    }

    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }

    @Override
    public TextView getTab(final int position, SwipeyTabs root) {
        TextView view = (TextView) LayoutInflater.from(mContext).inflate(
                R.layout.swipey_tab_indicator, root, false);
        view.setText(titles[position]);
        view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                        mPager.setCurrentItem(position);
                }
        });
        
        return view;
    }

}
