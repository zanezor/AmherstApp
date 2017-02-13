package com.example.zeinaamhaz.amherstapp;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GetVal extends AsyncTask<String[], Void, String[]> {

    @Override
    protected String[] doInBackground(String[]... params) {
        try {
            Document valWeb = Jsoup.connect("https://www.amherst.edu/campuslife/housing-dining/dining/menu").get();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String divTag = "dining-menu-" + dateFormat.format(date);

            //BREAKFAST HEADERS followed by BREAKFAST ITEMS
            String divTag1 = divTag + "-Breakfast-menu-listing";
            Elements breakfastDiv = valWeb.select("#" + divTag1);
            Elements breakfastHeaders = breakfastDiv.select(".dining-course-name");
            Elements breakfastItems = breakfastDiv.select("p");

            //LUNCH HEADERS followed by LUNCH ITEMS
            String divTag2 = divTag + "-Lunch-menu-listing";
            Elements lunchDiv = valWeb.select("#" + divTag2);
            Elements lunchHeaders = lunchDiv.select(".dining-course-name");
            Elements lunchItems = lunchDiv.select("p");

            //DINNER HEADERS followed by DINNER ITEMS
            String divTag3 = divTag + "-Dinner-menu-listing";
            Elements dinnerDiv = valWeb.select("#" + divTag3);
            Elements dinnerHeaders = dinnerDiv.select(".dining-course-name");
            Elements dinnerItems = dinnerDiv.select("p");

            params[0] = new String[breakfastHeaders.size() + lunchHeaders.size() + dinnerHeaders.size() + 2];

            //add Headers in arr[x][0]
            int foodCount = 0;
            for(Element i : breakfastHeaders){
                params[0][foodCount] = i.text();
                foodCount++;
            }
            foodCount++;
            for(Element i : lunchHeaders){
                params[0][foodCount] = i.text();
                foodCount++;
            }
            foodCount++;
            for(Element i : dinnerHeaders){
                params[0][foodCount] = i.text();
                foodCount++;
            }

            //add Items in arr[x][1]
            foodCount = 0;
            for(Element i : breakfastItems){
                params[0][foodCount] = params[0][foodCount] + ":\n" + i.text();
                foodCount++;
            }
            foodCount++;
            for(Element i : lunchItems){
                params[0][foodCount] = params[0][foodCount] + ":\n" + i.text();
                foodCount++;
            }
            foodCount++;
            for(Element i : dinnerItems){
                params[0][foodCount] = params[0][foodCount] + ":\n" + i.text();
                foodCount++;
            }
        }
        catch(IOException e){
            //do nothing
            System.out.println("FILE NOT FOUND EXCEPTION");
        }
        return params[0];
    }

    @Override
    protected void onPostExecute(String[] strings) {
        DiningMenu.valFood = strings;
    }
}
