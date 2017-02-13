package com.example.zeinaamhaz.amherstapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

public class DiningMenu extends AppCompatActivity {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    public static String[] valFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining_menu);
        Intent intent = getIntent();
        GetVal val = new GetVal();
        val.execute(valFood);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println(valFood[0]);
        if(valFood == null){
            System.out.println("YOUR INTERNET CONNECTION IS THE PROBLEM! I was having this error earlier.");
        }
        //System.out.println(valFood[0]);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.Food);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Breakfast");
        listDataHeader.add("Lunch");
        listDataHeader.add("Dinner");

        // Adding child data
        List<String> Breakfast = new ArrayList<String>();
        int i = 0;
        while(valFood[i] != null && i < valFood.length-1){
            Breakfast.add(valFood[i]);
            i++;
        }
        i++;

        List<String> Lunch = new ArrayList<String>();
        while(valFood[i] != null && i < valFood.length-1){
            Lunch.add(valFood[i]);
            i++;
        }
        i++;

        List<String> Dinner = new ArrayList<String>();
        while(valFood[i] != null && i < valFood.length-1){
            Dinner.add(valFood[i]);
            i++;
        }

        listDataChild.put(listDataHeader.get(0), Breakfast); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Lunch);
        listDataChild.put(listDataHeader.get(2), Dinner);
    }

}