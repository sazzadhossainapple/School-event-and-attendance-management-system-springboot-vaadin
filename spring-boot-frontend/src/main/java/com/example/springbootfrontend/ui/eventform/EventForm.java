package com.example.springbootfrontend.ui.eventform;

import com.example.springbootfrontend.model.Event;
import com.example.springbootfrontend.model.EventType;
import com.example.springbootfrontend.service.EventEventService;
import com.example.springbootfrontend.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="froms", layout = MainLayout.class)
@PageTitle("Event Form | Management")
public class EventForm extends VerticalLayout{

    private EventEventService eventService;
    private Binder<Event> eventBinder;
    private Grid<Event> eventGrid;


    public EventForm(EventEventService eventService) {

        TextField textFieldID = new TextField("Id Number","Enter Your Id Number");
        DateTimePicker dateTimePicker = new DateTimePicker("Date and Time");
        Checkbox checkbox =new Checkbox("Date Time");
        TextArea place = new TextArea("Place name","Enter the event place");
        ComboBox<EventType> events = new ComboBox<>("Event Type");
        Button saveb = new Button("Save");
        saveb.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button resetb = new Button("Reset");
        resetb.addThemeVariants(ButtonVariant.LUMO_ERROR);
        resetb.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        this.eventService = eventService;
        FormLayout formLayout = new FormLayout();
        addClassName("event-form");
        eventBinder = new Binder<>();
        eventGrid =new Grid<>();
        events.setItems(EventType.values());
    formLayout. add(textFieldID,
                dateTimePicker,
                place,
                events,saveb,resetb
        );



add(formLayout);

        eventBinder
                .forField(events)
                .asRequired()
                .bind(Event::getEventType,Event::setEventType);

       eventBinder
                .forField(textFieldID)
                .asRequired()
                .withValidator(Id -> Id.length()==13,"IDs must be a 13 digits long")
                .withConverter(new StringToLongConverter("Id must be a Number"))
                .withValidator(Id -> Id > 999,"Id must be 4 digit number")
                //.bind(Student::getId,Student::setId);
              .bind(event -> event.getId(),(event,id)-> event.setId(id));


    eventBinder
                .forField(dateTimePicker)
                .asRequired()
                .bind(Event::getLocalDateTime,Event::setLocalDateTime);

    eventBinder
            .forField(place)
            .asRequired()
            .bind(Event::getPlace,Event::setPlace);






        saveb.addClickListener(event ->{
          Event event1 = new Event();
            try {
                eventBinder.writeBean(event1);
                Notification.show(event1.toString());
                Event savedEvent = eventService.saveEvent(event1);
                Notification.show("Saved " + savedEvent.getId());
//                Notification.show("Saved " + savedEvent.getLocalDateTime());
//                Notification.show("Saved " + savedEvent.getPlace());
//                Notification.show("Saved " + savedEvent.getEventType());
            } catch (ValidationException e) {
                Notification.show(e.getMessage());
                e.printStackTrace();
            }catch (Exception e)
            {
                Notification.show(e.getMessage());
            }
        });
        resetb.addClickListener(buttonClickEvent -> eventBinder.setBean(new Event()));


    }

}
