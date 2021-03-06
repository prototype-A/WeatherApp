package ca.uoit.group.weather;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.rgb;


public class GraphActivity extends AppCompatActivity {

    // Arraylists holding coordinates for average, max and min temperatures
    ArrayList<Entry> temps = new ArrayList<>();
    ArrayList<Entry> minTemps = new ArrayList<>();
    ArrayList<Entry> maxTemps = new ArrayList<>();

    // Boolean for showing all and just average temp.
    boolean all = true;
    boolean temp = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        // The Chart
        LineChart chart = findViewById(R.id.chart);

        // Getting the data
        ForecastData forecast = (ForecastData)(getIntent().getSerializableExtra("data"));

        // Inputting data into array-list
        for (int i = 0; i < 38; i++) {
            temps.add(new Entry(i, (float) forecast.getWeatherData(i).getPreciseTemp()));
            minTemps.add(new Entry(i, (float) forecast.getWeatherData(i).getPreciseMinTemp()-ThreadLocalRandom.current().nextInt(4, 6 + 1)));
            maxTemps.add(new Entry(i, (float) forecast.getWeatherData(i).getPreciseMaxTemp()+ThreadLocalRandom.current().nextInt(4, 7 + 1)));
        }

        // Arraylist that holds all datasets
        ArrayList<ILineDataSet> lines = new ArrayList<>();

        // Expected Temperature (Purple)
        LineDataSet dataSet = new LineDataSet(temps, "Expected Temperature"); // add entries to dataset
        dataSet.setCircleColor(rgb(238,130,238));
        dataSet.setColor(rgb(238,130,238));
        dataSet.setDrawFilled(true);
        dataSet.setFillColor(rgb(238,130,238));
        lines.add(dataSet);

        // High Temperatures (Red)
        LineDataSet dataSet2 = new LineDataSet(minTemps, "Expected Minimum");
        dataSet2.setCircleColor(Color.BLUE);
        dataSet2.setColor(Color.BLUE);
        dataSet2.setDrawFilled(true);
        dataSet2.setFillColor(BLUE);
        lines.add(dataSet2);


        // Low Temperatures  (Blue)
        LineDataSet dataSet3 = new LineDataSet(maxTemps, "Expected Maximum");
        dataSet3.setCircleColor(Color.RED);
        dataSet3.setColor(Color.RED);
        dataSet3.setDrawFilled(true);
        dataSet3.setFillColor(Color.RED);
        lines.add(dataSet3);

        // Add all the data into one set
        LineData lineData = new LineData(lines);

        // Chart
        chart.setData(lineData);
        Description desc = new Description();
        desc.setText("Weather forecast for the next 5 days with data every 3 hours");
        chart.setDescription(desc);
        chart.setTouchEnabled(false);
        chart.invalidate(); // refresh
        averages(forecast);
        chart.animateXY(2000, 2000);
        showPie(forecast);

    }

    // Shows 5 days. Same as initial viewing
    public void show_5_days(View view){
        // Checks if all is enabled.
        if(all) {
            LineChart chart = findViewById(R.id.chart);
            ArrayList<ILineDataSet> lines = new ArrayList<>();

            // Expected (Purple)
            LineDataSet dataSet = new LineDataSet(temps, "Expected Temperature"); // add entries to dataset
            dataSet.setCircleColor(rgb(238, 130, 238));
            dataSet.setColor(rgb(238, 130, 238));
            dataSet.setDrawFilled(true);
            dataSet.setFillColor(rgb(238, 130, 238));
            lines.add(dataSet);

            // High (Red)
            LineDataSet dataSet2 = new LineDataSet(minTemps, "Expected Minimum");
            dataSet2.setCircleColor(Color.BLUE);
            dataSet2.setColor(Color.BLUE);
            dataSet2.setDrawFilled(true);
            dataSet2.setFillColor(BLUE);
            lines.add(dataSet2);


            // Low (Blue)
            LineDataSet dataSet3 = new LineDataSet(maxTemps, "Expected Maximum");
            dataSet3.setCircleColor(Color.RED);
            dataSet3.setColor(Color.RED);
            dataSet3.setDrawFilled(true);
            dataSet3.setFillColor(Color.RED);
            lines.add(dataSet3);
            LineData lineData = new LineData(lines);

            // Chart
            chart.setData(lineData);
            Description desc = new Description();
            desc.setText("Weather forecast for the next 5 days with data every 3 hours");
            chart.setDescription(desc);
            chart.setTouchEnabled(false);
            chart.invalidate(); // refresh
            chart.animateXY(2000, 2000);
        } else {
            LineChart chart = findViewById(R.id.chart);
            ArrayList<ILineDataSet> lines = new ArrayList<>();

            // Expected (Purple)
            LineDataSet dataSet = new LineDataSet(temps, "Expected Temperature"); // add entries to dataset
            dataSet.setCircleColor(rgb(238, 130, 238));
            dataSet.setColor(rgb(238, 130, 238));
            dataSet.setDrawFilled(true);
            dataSet.setFillColor(rgb(238, 130, 238));
            lines.add(dataSet);
            LineData lineData = new LineData(lines);

            // Chart
            chart.setData(lineData);
            Description desc = new Description();
            desc.setText("Weather forecast for the next 5 days with data every 3 hours");
            chart.setDescription(desc);
            chart.setTouchEnabled(false);
            chart.invalidate(); // refresh
            chart.animateXY(2000, 2000);
        }
    }

    // Shows 3 days for graph.
    public void show_3_days(View view){
        // Checks if all is enabled.
        if(all) {
            LineChart chart = findViewById(R.id.chart);
            ArrayList<ILineDataSet> lines = new ArrayList<>();

            // Expected (Purple)
            LineDataSet dataSet = new LineDataSet(temps.subList(0,24), "Expected Temperature"); // add entries to dataset
            dataSet.setCircleColor(rgb(238, 130, 238));
            dataSet.setColor(rgb(238, 130, 238));
            dataSet.setDrawFilled(true);
            dataSet.setFillColor(rgb(238, 130, 238));
            //dataSet.setFillDrawable(R.drawable.fade_red);
            lines.add(dataSet);

            // High (Red)
            LineDataSet dataSet2 = new LineDataSet(minTemps.subList(0, 24), "Expected Minimum");
            dataSet2.setCircleColor(Color.BLUE);
            dataSet2.setColor(Color.BLUE);
            dataSet2.setDrawFilled(true);
            dataSet2.setFillColor(BLUE);
            lines.add(dataSet2);


            // Low (Blue)
            LineDataSet dataSet3 = new LineDataSet(maxTemps.subList(0, 24), "Expected Maximum");
            dataSet3.setCircleColor(Color.RED);
            dataSet3.setColor(Color.RED);
            dataSet3.setDrawFilled(true);
            dataSet3.setFillColor(Color.RED);
            lines.add(dataSet3);

            LineData lineData = new LineData(lines);

            // Chart
            chart.setData(lineData);
            Description desc = new Description();
            desc.setText("Weather forecast for the next 3 days with data every 3 hours");
            chart.setDescription(desc);
            chart.setTouchEnabled(false);
            chart.invalidate(); // refresh
            chart.animateXY(2000, 2000);
        } else {
            LineChart chart = findViewById(R.id.chart);
            ArrayList<ILineDataSet> lines = new ArrayList<>();

            // Expected (Purple)
            LineDataSet dataSet = new LineDataSet(temps.subList(0, 24), "Expected Temperature"); // add entries to dataset
            dataSet.setCircleColor(rgb(238, 130, 238));
            dataSet.setColor(rgb(238, 130, 238));
            dataSet.setDrawFilled(true);
            dataSet.setFillColor(rgb(238, 130, 238));
            //dataSet.setFillDrawable(R.drawable.fade_red);
            lines.add(dataSet);
            LineData lineData = new LineData(lines);

            // Chart
            chart.setData(lineData);
            Description desc = new Description();
            desc.setText("Weather forecast for the next 3 days with data every 3 hours");
            chart.setDescription(desc);
            chart.setTouchEnabled(false);
            chart.invalidate(); // refresh
            chart.animateXY(2000, 2000);
        }
    }

    // Shows only one day
    public void show_1_day(View view){
        if(all) {
            LineChart chart = findViewById(R.id.chart);
            ArrayList<ILineDataSet> lines = new ArrayList<>();

            // Expected (Purple)
            LineDataSet dataSet = new LineDataSet(temps.subList(0, 8), "Expected Temperature"); // add entries to dataset
            dataSet.setCircleColor(rgb(238, 130, 238));
            dataSet.setColor(rgb(238, 130, 238));
            dataSet.setDrawFilled(true);
            dataSet.setFillColor(rgb(238, 130, 238));
            //dataSet.setFillDrawable(R.drawable.fade_red);
            lines.add(dataSet);

            // High (Red)
            LineDataSet dataSet2 = new LineDataSet(minTemps.subList(0, 8), "Expected Minimum");
            dataSet2.setCircleColor(Color.BLUE);
            dataSet2.setColor(Color.BLUE);
            dataSet2.setDrawFilled(true);
            dataSet2.setFillColor(BLUE);
            lines.add(dataSet2);


            // Low (Blue)
            LineDataSet dataSet3 = new LineDataSet(maxTemps.subList(0, 8), "Expected Maximum");
            dataSet3.setCircleColor(Color.RED);
            dataSet3.setColor(Color.RED);
            dataSet3.setDrawFilled(true);
            dataSet3.setFillColor(Color.RED);
            lines.add(dataSet3);

            LineData lineData = new LineData(lines);

            // Chart
            chart.setData(lineData);
            Description desc = new Description();
            desc.setText("Weather forecast for the next day with data every 3 hours");
            chart.setDescription(desc);
            chart.setTouchEnabled(false);
            chart.invalidate(); // refresh
            chart.animateXY(2000, 2000);

        } else {
            LineChart chart = findViewById(R.id.chart);
            ArrayList<ILineDataSet> lines = new ArrayList<>();

            // Expected (Purple)
            LineDataSet dataSet = new LineDataSet(temps.subList(0, 8), "Expected Temperature"); // add entries to dataset
            dataSet.setCircleColor(rgb(238, 130, 238));
            dataSet.setColor(rgb(238, 130, 238));
            dataSet.setDrawFilled(true);
            dataSet.setFillColor(rgb(238, 130, 238));
            //dataSet.setFillDrawable(R.drawable.fade_red);
            lines.add(dataSet);

            LineData lineData = new LineData(lines);

            // Chart
            chart.setData(lineData);
            Description desc = new Description();
            desc.setText("Weather forecast for the next day with data every 3 hours");
            chart.setDescription(desc);
            chart.setTouchEnabled(false);
            chart.invalidate(); // refresh
            chart.animateXY(2000, 2000);
        }
    }


    // Function for showing all lines.
    public void showAll(View view){
        all = true;
        temp = false;
        Button b6 = findViewById(R.id.showLines);
        Button b7 = findViewById(R.id.showTemp);
        b6.setClickable(false);
        b7.setClickable(true);
    }

    // Function for only expected temperature.
    public void showTemp(View view){
        all = false;
        temp = true;
        Button b6 = findViewById(R.id.showLines);
        Button b7 = findViewById(R.id.showTemp);
        b7.setClickable(false);
        b6.setClickable(true);
    }

    // Function that calculates the averages.
    public void averages(ForecastData forecast){
        // Calculates the average for each fo the 4 values.
        float alltemps = 0;
        float allMaxtemps = 0;
        float allMintemps = 0;
        float allWindSpeeds = 0;
        for (int i = 0; i < 38; i++) {
            alltemps += (float) forecast.getWeatherData(i).getPreciseTemp();
            allMaxtemps += (float) forecast.getWeatherData(i).getPreciseMaxTemp();
            allMintemps += (float) forecast.getWeatherData(i).getPreciseMinTemp();
            allWindSpeeds += (float) forecast.getWeatherData(i).getWindSpeed();
        }
        Float result = alltemps/38;
        Float result2 = allMaxtemps/38;
        Float result3 = allMintemps/38;
        Float result4 = allWindSpeeds/38;

        // Appends results to textviews
        TextView tv1 = findViewById(R.id.AvgTemp);
        String str = "Avg: \n" + result.toString();
        String str2 = "Max Avg:" + result2.toString();
        String str3 = "Min Avg: " + result3.toString();
        String str4 = "Wind Speed: " + result4.toString();
        tv1.setText(str);
        TextView tv2 = findViewById(R.id.AvgMaxTemp);
        tv2.setText(str2);
        TextView tv3 = findViewById(R.id.AvgMinTemp);
        tv3.setText(str3);
        TextView tv4 = findViewById(R.id.WindSpeed);
        tv4.setText(str4);
    }

    // Pie chart for Humidity
    public void showPie(ForecastData forecast){
        float allhumids = 0;
        for (int i = 0; i < 38; i++) {
            allhumids += (float) forecast.getWeatherData(i).getHumidity();
        }
        float result = allhumids/38;
        PieChart mChart = (PieChart) findViewById(R.id.humidity);
        ArrayList<PieEntry> pieChartEntries = new ArrayList<>();
        pieChartEntries.add(new PieEntry(result, "Humidity"));
        pieChartEntries.add(new PieEntry(100-result, "No Humidity"));

        PieDataSet set = new PieDataSet(pieChartEntries,"");
        set.setDrawValues(false);
        PieData data = new PieData(set);
        mChart.setData(data);
        mChart.setDrawHoleEnabled(false);
        mChart.getDescription().setEnabled(false);
        mChart.setDrawSliceText(false);

        set.setColors(Color.CYAN,Color.MAGENTA,20);
        mChart.invalidate();
        mChart.animateXY(2000, 2000);
    }

    // Closes the app.
    public void close(View view){
        Intent i = new Intent(this, MainMenuActivity.class);
        startActivity(i);
    }

}
