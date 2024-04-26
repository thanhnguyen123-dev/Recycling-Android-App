package com.example.recycleme;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.recycleme.cart.NodeData;
import com.example.recycleme.cart.UserTree;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class StatisticActivity extends BaseActivity {

    private List<RecycledItem> recycledItems;
    private PieChart chartMaterials;
    private BarChart chartRecycledItemOverTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_statistic, contentFrameLayout);

        recycledItems = UserTree.getInstance().getAllRecycledItems();

        chartMaterials = findViewById(R.id.chartMaterialsStatistic);
        chartRecycledItemOverTime = findViewById(R.id.chartRecycledItemsCount);
        setChartMaterials();
        setChartRecycledItemOverTime();
    }

    private void setChartMaterials() {
        Map<String, Integer> materialCount = new HashMap<>();
        for (RecycledItem item: recycledItems) {
            String material = item.getMaterial();
            materialCount.put(material, materialCount.getOrDefault(material, 0) + 1);
        }

        List<PieEntry> entries = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : materialCount.entrySet()) {
            entries.add(new PieEntry(entry.getValue(), entry.getKey()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Materials");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData data = new PieData(dataSet);
        chartMaterials.setData(data);
        chartMaterials.invalidate();
    }

    private void setChartRecycledItemOverTime() {
        List<NodeData<List<RecycledItem>>> recycledItemsOverTime = UserTree.getInstance().traverseReturnItemAndDate();

        Map<LocalDate, Integer> recyclableCount = new TreeMap<>();
        for (NodeData<List<RecycledItem>> data: recycledItemsOverTime) {
            List<RecycledItem> recycledItemList = data.getValue();
            LocalDate date = data.getDateTime().toLocalDate();

            int count = recycledItemList.size();
            recyclableCount.put(date, recyclableCount.getOrDefault(date, 0) + count);
        }

        List<BarEntry> entries = new ArrayList<>();
        List<String> xAxisLabels = new ArrayList<>();
        int index = 0;
        for (Map.Entry<LocalDate, Integer> entry: recyclableCount.entrySet()) {
            int count = entry.getValue();
            LocalDate date = entry.getKey();

            entries.add(new BarEntry(index++, count));
            xAxisLabels.add(date.toString());
        }

        BarDataSet dataSet = new BarDataSet(entries, "Recycled Items Over Time");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData barData = new BarData(dataSet);
        chartRecycledItemOverTime.setData(barData);
        XAxis xAxis = chartRecycledItemOverTime.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        YAxis yAxis = chartRecycledItemOverTime.getAxisLeft();
        yAxis.setGranularity(1f);
        yAxis.setAxisMinimum(0f);
        chartRecycledItemOverTime.invalidate();
    }


}