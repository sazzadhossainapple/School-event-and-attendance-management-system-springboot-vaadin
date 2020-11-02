package com.example.springbootfrontend.ui.attendance;

import com.example.springbootfrontend.model.Attendance;
import com.example.springbootfrontend.model.Event;
import com.example.springbootfrontend.service.AttendanceService;
import com.example.springbootfrontend.ui.MainLayout;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Route(value="attendance", layout = MainLayout.class)
@PageTitle("Attendance Form | Management")

public class AttendanceForm extends VerticalLayout {

    private AttendanceService attendanceService;
    private Binder<Attendance> attendanceBinder;
    private Grid<Attendance> attendanceGrid;

    public AttendanceForm( AttendanceService attendanceService){
        this.attendanceService = attendanceService;
        attendanceBinder = new Binder<>();
        attendanceGrid = new Grid<>();

        TextField idField = new TextField("Student ID","Student ID 13 digit ");
        TextField nameField = new TextField("Student Name","Student Full Name");
        DatePicker datePicker = new DatePicker("Attendance Date");
        Button submitButton = new Button("Save");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.setIcon(VaadinIcon.FILE_ADD.create());
        Button resetb = new Button("Reset");
        resetb.setIcon(VaadinIcon.REFRESH.create());
        resetb.addThemeVariants(ButtonVariant.LUMO_ERROR);
        resetb.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout horizontalLayout =new HorizontalLayout();
        FormLayout formLayout = new FormLayout();
        horizontalLayout.add(submitButton,resetb);
        formLayout.add(idField,nameField,datePicker);

        add(formLayout,horizontalLayout,attendanceGrid);

        attendanceBinder
                .forField(idField)
                .asRequired()
                .withValidator(Id -> Id.length()==13,"IDs must be a 13 digits long")
                .withConverter(new StringToLongConverter("Id must be a Number"))
                .withValidator(Id -> Id > 999,"Id must be 4 digit number")
                .bind(student -> student.getId(),(attendance,id)-> attendance.setId(id));

      attendanceBinder
                .forField(nameField)
                .asRequired()
                .withValidator(name -> name.length() >= 3,"Name should be at least 3 letter long")
                //.withValidator(name -> name.length() <=10," Name cannot be more than 10 letter long")
                .withValidator(name -> !name.contains("_"),"Name cannot underscore")
                .bind(Attendance::getName,Attendance::setName);

      attendanceBinder
                .forField(datePicker)
                .asRequired()
               // .withValidator(dob -> DAYS.between(dob, LocalDate.now()) >17 *365,"Students should be at least 18 years old")
                .bind(Attendance::getLocalDate,Attendance::setLocalDate);

        attendanceGrid
                .addColumn(Attendance::getId)
                .setWidth("150px")
                .setFlexGrow(0)
                .setHeader(getStyleHeader("Student ID"));

        attendanceGrid
                .addColumn(Attendance::getName)
                .setFlexGrow(1)
                .setHeader(getStyleHeader("Student Name"));

        attendanceGrid
                .addColumn(Attendance::getLocalDate)
                .setFlexGrow(1)
                .setHeader(getStyleHeader("Date"));


        attendanceGrid
                .addComponentColumn(attendance -> getEditCloumn(attendance))
                .setWidth("50px")
                .setFlexGrow(0);

       attendanceGrid
                .addComponentColumn(attendance -> getDeleteCloumn(attendance))
                .setWidth("50px")
                .setFlexGrow(0);

        attendanceGrid.setItems(attendanceService.findAll());


        submitButton.addClickListener(event ->{
            Attendance attendance = new Attendance();
            try {
                attendanceBinder.writeBean(attendance);
                Notification.show(attendance.toString());
               Attendance savedAttendance = attendanceService.saveAttendance(attendance);
                attendanceGrid.setItems(attendanceService.findAll());
                Notification.show("Saved " + savedAttendance.getName());
            } catch (ValidationException e) {
                Notification.show(e.getMessage());
                e.printStackTrace();
            }catch (Exception e)
            {
                Notification.show(e.getMessage());
            }




        });


        resetb.addClickListener(buttonClickEvent -> attendanceBinder.setBean(new Attendance()));

    }
    private Component getStyleHeader(String text) {
        H4 header = new H4(text);
        header
                .getStyle()
                //.set("background-color","blue")
                .set("border-with","3px");
        return header;
    }


    private Component getDeleteCloumn(Attendance attendance) {
        Button button = new Button();
        //studentBinder.readBean(student);
        button.setIcon(VaadinIcon.FILE_REMOVE.create());
        button.getElement().setProperty("title","This is a delete button!");
        button.addClickListener(event -> {
            attendanceService.delete(attendance);
            attendanceGrid.setItems(attendanceService.findAll());});

        return button;
    }

    private Component getEditCloumn(Attendance attendance) {

        Button button = new Button();
        //studentBinder.readBean(student);
        button.setIcon(VaadinIcon.EDIT.create());
        button.addClickListener(event -> attendanceBinder.readBean(attendance));

        return button;
    }

}
