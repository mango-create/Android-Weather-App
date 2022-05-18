package edu.uncc.weather;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;

public class WeatherForcast_Layout extends ArrayAdapter {
    ArrayList<Weather> listweather;
    Weather TheOneThatRulesThemAll;
    ImageView imageView;

    public WeatherForcast_Layout(@NonNull Context context, int resource, @NonNull ArrayList<Weather> objects) {
        super(context, resource, objects);
        this.listweather = objects;
    }

    @NonNull
    @Override
    public View getView(int postion, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fiveday_layout, parent, false);
        }

        TextView temp = convertView.findViewById(R.id.textView10);
        imageView = convertView.findViewById(R.id.imageView2);
        TextView tempmin = convertView.findViewById(R.id.textView12);
        TextView tempmax = convertView.findViewById(R.id.textView11);

        TextView human = convertView.findViewById(R.id.textView14);
        TextView stats = convertView.findViewById(R.id.textView15);
        TextView dates = convertView.findViewById(R.id.Date);

        TheOneThatRulesThemAll = listweather.get(postion);

        HttpUrl url = HttpUrl.parse("http://openweathermap.org/img/wn/").newBuilder()
                .addPathSegment(TheOneThatRulesThemAll.weather.get(0).icon + "@2x.png")
                .build();
        Picasso.get().load(String.valueOf(url)).into(imageView);
        dates.setText(TheOneThatRulesThemAll.dt_txt + "");
        human.setText("Humidity: " + TheOneThatRulesThemAll.main.humidity + "%");
        temp.setText(TheOneThatRulesThemAll.main.temp + " F");
        tempmin.setText("Min: " + TheOneThatRulesThemAll.main.temp_min + "F");
        tempmax.setText("Max: " + TheOneThatRulesThemAll.main.temp_max + "F");
        stats.setText(TheOneThatRulesThemAll.weather.get(0).description);
        return convertView;
    }


}
