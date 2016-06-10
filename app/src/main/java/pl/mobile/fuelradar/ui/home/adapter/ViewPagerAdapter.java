package pl.mobile.fuelradar.ui.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import pl.mobile.fuelradar.ui.home.fragment.FavoritesFragment;
import pl.mobile.fuelradar.ui.home.fragment.NearbyFragment;
import pl.mobile.fuelradar.ui.home.fragment.RouteFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    int tabCount;

    public ViewPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                NearbyFragment tab1 = new NearbyFragment();
                return tab1;
            case 1:
                FavoritesFragment tab2 = new FavoritesFragment();
                return tab2;
            case 2:
                RouteFragment tab3 = new RouteFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
