/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.gui.back.reclamation;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.pdev.entities.Reclamation;
import com.pdev.services.ReclamationService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author ASUS
 */
public class ReclamationStat extends Form{
    public ReclamationStat(Form previous) {
     setTitle("Reclamation Statistics");

        List<Reclamation> reclamations = ReclamationService.getInstance().getAll();
        Map<String, Integer> countByDate = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        for (Reclamation reclamation : reclamations) {
            String date = dateFormat.format(reclamation.getDater());
            if (countByDate.containsKey(date)) {
                countByDate.put(date, countByDate.get(date) + 1);
            } else {
                countByDate.put(date, 1);
            }
        }

        String[] dates = countByDate.keySet().toArray(new String[countByDate.size()]);
        double[] values = new double[dates.length];
        for (int i = 0; i < dates.length; i++) {
            values[i] = countByDate.get(dates[i]);
        }

        int[] colors = new int[countByDate.size()];
        int i = 0;
        for (Map.Entry<String, Integer> entry : countByDate.entrySet()) {
            dates[i] = entry.getKey();
            values[i] = entry.getValue();
            colors[i] = ColorUtil.BLUE + i * 100;
            i++;
        }
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsTextSize(100);
        renderer.setLabelsColor(ColorUtil.BLACK);
        renderer.setLegendTextSize(100);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(false);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);

        PieChart chart = new PieChart(buildCategoryDataset(dates, values), renderer);
        ChartComponent chartComponent = new ChartComponent(chart);
        add(chartComponent);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });
    }
        private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }
        protected CategorySeries buildCategoryDataset(String[] dates, double[] values) {
        CategorySeries series = new CategorySeries("Reclamation Count per Month");
        for (int i = 0; i < dates.length; i++) {
            series.add(dates[i] + " (" + (int) values[i] + ")", values[i]);
        }
        return series;
    }
}

