package vn.edu.usth.weather;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.i(Tag, "Create");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.refresh_button) {
            simulateNetworkRequest();
            return true;
        } else if (id == R.id.setting_button) {
            Intent intent = new Intent(this, PrefActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void simulateNetworkRequest() {
        Toast.makeText(this, "Refreshing...", Toast.LENGTH_SHORT).show();

        // Sử dụng Handler để mô phỏng yêu cầu mạng
        new Thread(() -> {
            try {
                // Mô phỏng thời gian chờ của yêu cầu mạng
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Sử dụng Handler để cập nhật giao diện sau khi hoàn thành yêu cầu mạng
            new Handler(Looper.getMainLooper()).post(() -> {
                Toast.makeText(WeatherActivity.this, "Data refreshed", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }

    final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            String content = msg.getData().getString("server_response");
            Toast.makeText(WeatherActivity.this, content, Toast.LENGTH_SHORT).show();
        }
    };

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

        // Play the extracted MP3 file
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
