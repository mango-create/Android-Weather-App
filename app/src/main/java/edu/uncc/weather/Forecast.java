package edu.uncc.weather;

import java.util.ArrayList;


public class Forecast {
    @Override
    public String toString() {
        return "json_List{" +
                "list=" + list +
                '}';
    }

    public ArrayList<Weather> getList() {
        return list;
    }

    public void setList(ArrayList<Weather> list) {
        this.list = list;
    }

    ArrayList<Weather> list;
}
