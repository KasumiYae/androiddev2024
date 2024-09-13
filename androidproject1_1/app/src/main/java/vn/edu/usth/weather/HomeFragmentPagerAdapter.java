package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 3;
    private String titles[] = new String[] { "HANOI,VIETNAM", "PARIS,FRANCE", "TOULOUSE,FRANCE" };
    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public int getCount() {
        return PAGE_COUNT; // number of pages for a ViewPager
    }
    @NonNull
    @Override
    public Fragment getItem(int page) {
// returns an instance of Fragment corresponding to the specified page
        switch (page) {
            case 0: new WeatherAndForecastFragment();
            case 1: new WeatherAndForecastFragment();
            case 2: new WeatherAndForecastFragment();
        }
        return new WeatherAndForecastFragment(); // failsafe
    }

    public CharSequence getPageTitle(int page) {
// returns a tab title corresponding to the specified page
        return titles[page];
    }
}