/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdev.gui.back.stat;

/**
 *
 * @author Acer
 */





import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.ScaleImageLabel;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.pdev.entities.Articles;
import com.pdev.entities.datas;
import com.pdev.entities.datas;
import com.pdev.services.ArticlesService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pdev.entities.Articles;
import com.pdev.entities.datas;
import com.pdev.services.ArticlesService;


/**
 *
 * @author oumayma
 */
public class statForm extends Form {

    public statForm(Form previous) {
        setTitle("Nombre");


        // Set up the chart values and colors
        List<Articles> offres = ArticlesService.getInstance().getAll();
        Map<String, Integer> countByCategory = new HashMap<>();
for (Articles offre : offres) {
    int category = offre.getQuantite_article();
    if (countByCategory.containsKey(offre.getTitre_article())) {
        countByCategory.put(offre.getTitre_article(), countByCategory.get(offre.getTitre_article()) + category);
    } else {
        countByCategory.put(offre.getTitre_article(), category);
    }
}

        // Convertir les données en tableau pour la construction du graphique
        String[] categories = countByCategory.keySet().toArray(new String[countByCategory.size()]);
        double[] values = new double[categories.length];
        for (int i = 0; i < categories.length; i++) {
            values[i] = countByCategory.get(categories[i]);
        }

        // Set up the chart renderer
        int[] colors = new int[countByCategory.size()];
        int i = 0;
        for (Map.Entry<String, Integer> entry : countByCategory.entrySet()) {
            categories[i] = entry.getKey();
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

        // Create the chart and chart component
        PieChart chart = new PieChart(buildCategoryDataset(categories, values), renderer);
        ChartComponent chartComponent = new ChartComponent(chart);
        add(chartComponent);

        // Set up the back button
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });
    }

    /**
     * Creates a renderer for the specified colors.
     */
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

    /**
     * Builds a category dataset using the provided categories and values.
     */
  protected CategorySeries buildCategoryDataset(String[] categories, double[] values) {
    CategorySeries series = new CategorySeries("Nombre ");
    for (int i = 0; i < categories.length; i++) {
        series.add(categories[i] + " (" + (int) values[i] + ")", values[i]);
    }
    return series;
}

}