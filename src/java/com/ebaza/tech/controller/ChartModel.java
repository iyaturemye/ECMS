/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.controller;

import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author Godwin
 */
@ManagedBean
public class ChartModel {

    private LineChartModel chartModel = new LineChartModel();
    private BarChartModel barModel2 = new BarChartModel();


    public ChartModel() {
        ChartSeries driv = new ChartSeries();
        driv.setLabel("Driving License");
        driv.set("driving License", 10);
        ChartSeries pass = new ChartSeries();
        pass.setLabel("Passport");
        pass.set("passport", 2);
        ChartSeries nid = new ChartSeries();
        nid.setLabel("NationalId");
        nid.set("nationalId", 3);
        ChartSeries icyubuta = new ChartSeries();
        icyubuta.setLabel("Icyubutaka");
        icyubuta.set("Icyubutaka", 50);
        ChartSeries re = new ChartSeries();
        re.setLabel("REB");
        re.set("REB", 30);
        ChartSeries ve = new ChartSeries();
        ve.setLabel("Certificate IMMatriculation");
        ve.set("Certificate IMMatriculation", 15);
        barModel2.addSeries(driv);
        barModel2.addSeries(pass);
        barModel2.addSeries(nid);
        barModel2.addSeries(icyubuta);
        barModel2.addSeries(re);
        barModel2.addSeries(ve);

        CartesianChartModel chartModel = new CartesianChartModel();
        ChartSeries primefacesSeries = new ChartSeries();
        primefacesSeries.setLabel("PrimeFaces");
        primefacesSeries.set("jan", 150);
        primefacesSeries.set("feb", 350);
        primefacesSeries.set("march", 100);
        primefacesSeries.set("april", 50);
        primefacesSeries.set("may", 200);
        primefacesSeries.set("june", 250);
        primefacesSeries.set("july", 150);
        primefacesSeries.set("august", 20);
        primefacesSeries.set("september", 40);
        primefacesSeries.set("october", 100);
        primefacesSeries.set("november", 100);
        primefacesSeries.set("december", 240);

        ChartSeries querySeries = new ChartSeries();
        querySeries.setLabel("jQuery");
        querySeries.set("jan", 50);
        querySeries.set("feb", 750);
        querySeries.set("march", 200);
        querySeries.set("april", 50);
        querySeries.set("may", 220);
        querySeries.set("june", 350);
        querySeries.set("july", 250);
        querySeries.set("august", 10);
        querySeries.set("september", 40);
        querySeries.set("october", 30);
        querySeries.set("november", 50);
        querySeries.set("december", 20);
        this.chartModel.addSeries(primefacesSeries);
        this.chartModel.addSeries(querySeries);

    }

    public LineChartModel getChartModel() {
        return chartModel;
    }

    public void setChartModel(LineChartModel chartModel) {
        this.chartModel = chartModel;
    }

    public BarChartModel getBarModel2() {
        return barModel2;
    }

    public void setBarModel2(BarChartModel barModel2) {
        this.barModel2 = barModel2;
    }

}
