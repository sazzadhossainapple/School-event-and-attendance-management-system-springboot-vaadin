package com.example.springbootfrontend.ui;

import com.example.springbootfrontend.ui.attendance.AttendanceForm;
import com.example.springbootfrontend.ui.dashboard.DashboardView;
import com.example.springbootfrontend.ui.eventform.EventForm;
import com.example.springbootfrontend.ui.viewlist.listView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;


@PWA(
        name = "School Event Management & Attendance System",
        shortName = "Event",
        offlineResources = {
                "./styles/offline.css",
                "./images/offline.png"},
        enableInstallPrompt = false
)

@Theme(value = Lumo.class,variant = Lumo.DARK)
//@Theme(value = Material.class)
//@Theme(value = Lumo.class)
@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout {
    public MainLayout() {
        createHeader();
        createDrawer();
    }



    private void createHeader() {
        H1 logo = new H1("School Event Management & Attendances System");
        logo.addClassName("logo");
        Anchor logout = new Anchor("logout", "Log out");
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo,logout);
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER);

        header.setWidth("100%");
        header.addClassName("header");
        addToNavbar(header);
    }

    private void createDrawer() {

        RouterLink listLink = new RouterLink(" Event List", listView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());


        addToDrawer(new VerticalLayout(
                listLink ,
                new RouterLink("Dashboard", DashboardView.class)
        ));

        addToDrawer(new VerticalLayout(
                listLink,
                new RouterLink("Attendance Form", AttendanceForm.class)

        ));
        addToDrawer(new VerticalLayout(
                listLink,
                new RouterLink("Event Form", EventForm.class)

        ));



    }
}