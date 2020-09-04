package com.vaadin.tutorial.crm.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.Contact;
import com.vaadin.tutorial.crm.backend.service.ContactService;
import org.hibernate.dialect.lock.UpdateLockingStrategy;


@Route("")
public class MainView extends VerticalLayout {
    private ContactService contactService;
    private Grid<Contact> grid=new Grid<>(Contact.class);
    private TextField filterText=new TextField();
    public MainView(ContactService contactService) {
        this.contactService=contactService;
        addClassName("list-view");
        setSizeFull();
        configureFilter();
        configureGrid();
        add(grid);
        updateList();
    }
    private void configureGrid(){
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName","lastName","email","status");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
    private void configureFilter(){
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
    }

    private void updateList(){
        //System.out.println(contactService.findAll());
        grid.setItems(contactService.findAll());
    }
}
