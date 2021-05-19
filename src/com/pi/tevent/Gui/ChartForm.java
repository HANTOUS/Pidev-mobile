/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Entities.DemandeBus;
import com.pi.tevent.Entities.DemandeChauffeur;
import com.pi.tevent.Entities.DemandeMateriel;
import com.pi.tevent.Entities.Utilisateur;
import com.pi.tevent.Services.DemandeBusServices;
import com.pi.tevent.Services.DemandeChauffeurServices;
import com.pi.tevent.Services.DemandeMaterielServices;
import com.pi.tevent.utils.SessionUser;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class ChartForm extends BaseForm{
    Resources theme ;
    DemandeBusServices dbs = new DemandeBusServices();
    DemandeMaterielServices dms = new DemandeMaterielServices();
    DemandeChauffeurServices dcs = new DemandeChauffeurServices();
    Utilisateur user ;

                ArrayList<DemandeMateriel> listMat = DemandeMaterielServices.getInstance().getDemandeMaterielByUser(user.getId());
                ArrayList<DemandeBus> listBus = dbs.getDemandeBusByUser(user.getId());
                ArrayList<DemandeChauffeur> listChauff = dcs.getDemandeChauffeurByUser(user.getId());
                
                
                double nbMat ;
                double nbBus ;
                double nbChauff ;

    
                
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(50);
    renderer.setAxesColor(ColorUtil.YELLOW);
    renderer.setShowAxes(false);
    renderer.setLabelsColor(ColorUtil.BLACK);
    renderer.setLegendTextSize(90);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
    int k = 0;
        series.add("Demande Materiel ", values[0]);
        series.add("Demande Bus ", values[1]);
        series.add("Demande Chauffeur " , values[2]);

    return series;
}

public ChartForm(Resources theme) {
    
    super("TITLE", BoxLayout.yCenter());
    this.theme=theme ;
    user =  SessionUser.getUser();

    Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
add(createLineSeparator(000000));
getTitleArea().setUIID("Container");
        setTitle("Statistique des demandes");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");
    // Generate the values
    for (DemandeMateriel dm : listMat) {
           nbMat++;
        };
    
    for (DemandeBus db : listBus) {
        nbBus++;
    };
    
    for (DemandeChauffeur dc : listChauff) {
        nbChauff++ ;
    };
    double[] values = new double[]{nbMat, nbBus, nbChauff};

    // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GRAY, ColorUtil.MAGENTA};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(50);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
//    r.setGradientEnabled(true);
//    r.setGradientStart(0, ColorUtil.BLUE);
//    r.setGradientStop(0, ColorUtil.GREEN);
//    r.setHighlighted(true);
    r.setShowLegendItem(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    this.add(c);
}
}
