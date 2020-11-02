package com.example.springbootfrontend.ui.viewlist;

import com.example.springbootfrontend.model.Event;
import com.example.springbootfrontend.service.EventEventService;
import com.example.springbootfrontend.ui.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value ="", layout = MainLayout.class)
@PageTitle("listView | Event Management")
public class listView extends VerticalLayout {
   private Event event;
    private EventEventService eventService;
    private Grid eventGrid;
    private Binder<Event> eventBinder;
    public listView(EventEventService eventService){

        this.eventService =eventService;
        addClassName("list-view");
        eventGrid =new Grid<>(Event.class);
       event = new Event();
        eventGrid.setItems(eventService.findAll());
        eventBinder = new Binder<>();
       // eventGrid.setSelectionMode(Grid.SelectionMode.MULTI);



//
//
//        eventGrid
//                .addColumn(Event::getId)
//                .setWidth("150px")
//                .setFlexGrow(0)
//                .setHeader(getStyleHeader("Id Number"));
//
//        eventGrid
//                .addColumn(Event::getLocalDateTime)
//                .setFlexGrow(1)
//                .setHeader(getStyleHeader("Date and Time"));
//
//        eventGrid
//                .addColumn(Event::getPlace)
//                .setFlexGrow(1)
//                .setHeader(getStyleHeader(" Event Place"));
//
//        eventGrid
//                .addColumn(Event::getEventType)
//                .setFlexGrow(1)
//                .setHeader(getStyleHeader("Event Type"));




        eventGrid.addComponentColumn(event1 ->getDeleteCloumn(event))
                .setWidth("50px")
                .setFlexGrow(0);

        add(eventGrid);

    }
//    private Component getStyleHeader(String text) {
//        H4 header = new H4(text);
//        header
//                .getStyle()
//                //.set("background-color","blue")
//                .set("border-with","3px");
//        return header;
//    }

    private Component getDeleteCloumn(Event event) {
        Button button = new Button();
        button.setIcon(VaadinIcon.FILE_REMOVE.create());
        button.getElement().setProperty("title","This is a delete button!");
        button.addClickListener(event1 -> {
            eventService.delete(event);
            eventGrid.setItems(eventService.findAll());});

        return button;
    }
}
