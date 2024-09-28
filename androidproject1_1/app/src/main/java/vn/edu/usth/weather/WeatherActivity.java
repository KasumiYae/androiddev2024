package vn.edu.usth.weather;

import android.os.Bundle;
import android.util.Log;
import android.media.MediaPlayer;
import android.os.Environment;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class WeatherActivity extends AppCompatActivity {
    private static final String Tag = "WeatherActivity";
    private MediaPlayer mediaPlayer;


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
        extractAndPlayMusic();
    }

    private void extractAndPlayMusic() {
        File musicFile = new File(Environment.getExternalStorageDirectory(), "example.mp3");
        if (!musicFile.exists()) {
            try {
                InputStream is = getResources().openRawResource(R.raw.example);  // Replace 'sample' with your MP3 file name
                FileOutputStream fos = new FileOutputStream(musicFile);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(musicFile.getAbsolutePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        Log.i(Tag, "Destroy");

    }




}
