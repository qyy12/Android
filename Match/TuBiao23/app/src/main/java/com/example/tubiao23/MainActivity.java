 package com.example.tubiao23;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListAdapter;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

 public class MainActivity extends AppCompatActivity {

    private PieChart pieChart;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pieChart = findViewById(R.id.pie_char);

        List<String> xVals = new ArrayList<>();
        xVals.add("1");
        xVals.add("2");
        xVals.add("3");
        xVals.add("4");
        xVals.add("5");

        List<Entry> yVals = new ArrayList<>();
        yVals.add(new Entry(10,1));
        yVals.add(new Entry(10,1));
        yVals.add(new Entry(10,1));
        yVals.add(new Entry(10,1));
        yVals.add(new Entry(10,1));

        PieDataSet pieDataSet = new PieDataSet(yVals,"");
        pieChart.setDrawHoleEnabled(false);
        pieDataSet.setColors(new int[]{Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW,Color.WHITE});
        PieData data = new PieData(xVals,pieDataSet);
        pieChart.setData(data);
    }
}