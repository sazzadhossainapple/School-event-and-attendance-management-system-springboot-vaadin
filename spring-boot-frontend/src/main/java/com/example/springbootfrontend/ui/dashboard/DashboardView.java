package com.example.springbootfrontend.ui.dashboard;
import com.example.springbootfrontend.model.EventType;
import com.example.springbootfrontend.service.EventEventService;
import com.example.springbootfrontend.ui.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.Map;

@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Dashboard | Management")

public class DashboardView extends VerticalLayout {

    private EventEventService eventService;
    public  DashboardView(EventEventService eventService){
        this.eventService = eventService;
        addClassName("dashboard-view");
      add(getCompaniesChart(),
              getContactStats());
    }

    private Component getCompaniesChart() {
        Chart chart = new Chart(ChartType.PIE);
        DataSeries dataSeries = new DataSeries();
        Map<EventType, Long> companies = eventService.getStats();
        companies.forEach((event, eventType) -> {
            dataSeries.add(new DataSeriesItem(String.valueOf(event), eventType));
        });
        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }

    private Span getContactStats() {

        Span stats = new Span(eventService.count() + " Event Participate");
        stats.addClassName("event-stats");
        return stats;
    }

}
