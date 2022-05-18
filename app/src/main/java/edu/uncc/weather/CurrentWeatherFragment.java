package edu.uncc.weather;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import edu.uncc.weather.databinding.FragmentCurrentWeatherBinding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class CurrentWeatherFragment extends Fragment {
    private static final String ARG_PARAM_CITY = "ARG_PARAM_CITY";
    private DataService.City mCity;
    FragmentCurrentWeatherBinding binding;
    Weather weather;
    private final OkHttpClient client = new OkHttpClient();
    TextView temp, tempmax, tempmin, description, humidity, windspeed, winddegree, clouds, city;
    ImageView weatherIcon;

    public CurrentWeatherFragment() {
        // Required empty public constructor
    }

    public static CurrentWeatherFragment newInstance(DataService.City city) {
        CurrentWeatherFragment fragment = new CurrentWeatherFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_CITY, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCity = (DataService.City) getArguments().getSerializable(ARG_PARAM_CITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        weatherIcon = view.findViewById(R.id.imageView);
        city = view.findViewById(R.id.city);
        temp = view.findViewById(R.id.temp);
        tempmax = view.findViewById(R.id.tempmax);
        tempmin = view.findViewById(R.id.tempmin);
        description = view.findViewById(R.id.description);
        humidity = view.findViewById(R.id.humidity);
        windspeed = view.findViewById(R.id.windspeed);
        winddegree = view.findViewById(R.id.winddegree);
        clouds = view.findViewById(R.id.clouds);


        HttpUrl url = HttpUrl.parse("https://api.openweathermap.org/data/2.5/weather").newBuilder()
                .addQueryParameter("q", String.valueOf(mCity))
                .addQueryParameter("appid", "") //can insert API key here
                .addQueryParameter("units", "imperial")
                .addQueryParameter("lang", "en")
                .build();


        Request request = new Request.Builder()
                .url(String.valueOf(url))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    weather = gson.fromJson(response.body().charStream(), Weather.class);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            HttpUrl url = HttpUrl.parse("http://openweathermap.org/img/wn/").newBuilder()
                                    .addPathSegment(weather.weather.get(0).icon + "@2x.png")
                                    .build();


                            Picasso.get().load(String.valueOf(url)).into(weatherIcon);
                            city.setText(mCity + "");
                            temp.setText(weather.main.temp + " F");
                            tempmax.setText(weather.main.temp_max + " F");
                            tempmin.setText(weather.main.temp_min + " F");
                            description.setText(weather.weather.get(0).description + "");
                            humidity.setText(weather.main.humidity + "%");
                            windspeed.setText(weather.wind.speed + " miles/hr");
                            winddegree.setText(weather.wind.deg + " degrees");
                            clouds.setText(weather.clouds.all + "%");
                        }
                    });


                }
            }
        });
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityforcast.passCity(mCity);
            }
        });


        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Current Weather");
    }


    weatherforcast cityforcast;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof weatherforcast) {
            cityforcast = (weatherforcast) context;
        } else {
            throw new RuntimeException("You forgot something important");
        }

    }


}

interface weatherforcast {
    void passCity(DataService.City city);
}
