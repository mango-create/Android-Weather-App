package edu.uncc.weather;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class Weather {
    MainWeather main;

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    String dt_txt;
    List<Weather_weather> weather;
    WindWeather wind;
    CloudsWeather clouds;


    @Override
    public String toString() {
        return "Weather{" +
                "main=" + main +
                ", dt_txt='" + dt_txt + '\'' +
                ", weather=" + weather +
                ", wind=" + wind +
                ", clouds=" + clouds +
                '}';
    }

    public MainWeather getMain() {
        return main;
    }

    public void setMain(MainWeather main) {
        this.main = main;
    }

    public List<Weather_weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather_weather> weather) {
        this.weather = weather;
    }

    public WindWeather getWind() {
        return wind;
    }

    public void setWind(WindWeather wind) {
        this.wind = wind;
    }

    public CloudsWeather getClouds() {
        return clouds;
    }

    public void setClouds(CloudsWeather clouds) {
        this.clouds = clouds;
    }
}


class MainWeather {
    double temp, feels_like, temp_min, temp_max, pressure, humidity;

    @Override
    public String toString() {
        return "MainWeather{" +
                "temp=" + temp +
                ", feels_like=" + feels_like +
                ", temp_min=" + temp_min +
                ", temp_max=" + temp_max +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                '}';
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(double feels_like) {
        this.feels_like = feels_like;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) { this.temp_min = temp_min; }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}

class Weather_weather {
    int id;
    String main, description, icon;

    @Override
    public String toString() {
        return "Weather_weather{" +
                "id=" + id +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

class WindWeather {
    double speed, deg;

    @Override
    public String toString() {
        return "WindWeather{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }
}

class CloudsWeather {
    int all;

    @Override
    public String toString() {
        return "CloudsWeather{" +
                "all=" + all +
                '}';
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }
}
