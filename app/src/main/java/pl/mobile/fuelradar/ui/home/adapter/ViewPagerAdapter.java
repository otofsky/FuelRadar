package pl.mobile.fuelradar.ui.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import pl.mobile.fuelradar.ui.home.fragment.FavoritesFragment;
import pl.mobile.fuelradar.ui.home.fragment.MapsFragment;
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
                return MapsFragment.newInstance(MapsFragment.TYPE_TASK.NEAREST);
            case 1:
                return MapsFragment.newInstance(MapsFragment.TYPE_TASK.FAVOURITE);
            case 2:
                return MapsFragment.newInstance(MapsFragment.TYPE_TASK.ROUTE);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
