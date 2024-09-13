package vn.edu.usth.weather;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class WeatherActivity extends AppCompatActivity {
    private static final String Tag = "WeatherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());


        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);

        Log.i(Tag, "Create");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(Tag, "Start");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(Tag, "Resume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(Tag, "Pause");

    }

    @Override
    protected void onStop() {
        Log.i(Tag, "Stop");
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(Tag, "Destroy");

    }




}
