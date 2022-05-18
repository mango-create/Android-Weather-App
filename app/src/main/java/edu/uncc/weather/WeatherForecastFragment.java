package edu.uncc.weather;
/*
Assignment Inclass_08
Fiile Name weather
Raymond Townsend
Mathew Mango
 */
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WeatherForecastFragment extends Fragment {
    ListView listView;
    TextView cityname, name, temp, tempmin, tempmax, human, stats;
    ArrayAdapter<ArrayList<Weather>> adapter;
    Weather weather;
    Forecast lits_json;
    private final OkHttpClient client = new OkHttpClient();
    private static final String ARG_PARAM_CITY = "ARG_PARAM_CITY";
    private DataService.City mCity;

    public static WeatherForecastFragment newInstance(DataService.City city) {
        WeatherForecastFragment fragment = new WeatherForecastFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_forecast, container, false);
        getActivity().setTitle("Weather Forecast");
        HttpUrl urls = HttpUrl.parse("https://api.openweathermap.org/data/2.5/forecast").newBuilder()
                .addQueryParameter("q", String.valueOf(mCity))
                .addQueryParameter("appid", "ecb72d1daf73d7dfcc8eb0860b6610c7")
                .addQueryParameter("units", "imperial")
                .addQueryParameter("lang", "en")
                .build();

        Request request = new Request.Builder()
                .url(urls)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                cityname = view.findViewById(R.id.cityName);


                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Forecast lits_json = gson.fromJson(response.body().charStream(), Forecast.class);


                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            cityname.setText(mCity + "");
                            adapter = new WeatherForcast_Layout(getContext(), R.id.wetherfiveday, lits_json.list);
                            listView = view.findViewById(R.id.ListView);
                            listView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    });


                }
            }
        });


        return view;
    }
}