package vn.edu.usth.weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.InputStream; // Import InputStream
import java.net.URL; // Import URL

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForecastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForecastFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ImageView logoImageView;

    public ForecastFragment() {
        // Required empty public constructor
    }

    public static ForecastFragment newInstance(String param1, String param2) {
        ForecastFragment fragment = new ForecastFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);
        logoImageView = view.findViewById(R.id.logoImageView); // Đảm bảo ImageView đã có trong layout

        // Thực hiện tải logo
        new LoadLogoTask().execute("http://ict.usth.edu.vn/wp-content/uploads/usth/usthlogo.png");

        return view;
    }



    protected void onPostExecute(Bitmap result) {
        if (result != null) {
            logoImageView.setImageBitmap(result); // Hiển thị logo lên ImageView
        } else {
            // Nếu không tải được logo, hiển thị hình ảnh mặc định
            logoImageView.setImageResource(R.drawable.usth);
        }
    }

    private class LoadLogoTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            String urlString = urls[0];
            Bitmap logo = null;

            try {
                InputStream in = new URL(urlString).openStream(); // Tải logo từ URL
                logo = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return logo;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                logoImageView.setImageBitmap(result); // Hiển thị logo lên ImageView
            } else {
                // Nếu không tải được logo, có thể đặt một hình ảnh mặc định hoặc thông báo lỗi
                // logoImageView.setImageResource(R.drawable.default_logo);
            }
        }
    }
}