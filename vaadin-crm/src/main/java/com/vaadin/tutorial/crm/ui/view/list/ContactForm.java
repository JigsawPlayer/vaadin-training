package com.vaadin.tutorial.crm.ui.view.list;

import java.util.List;

import javax.validation.ValidationException;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.data.binder.BeanValidationBinder;
//import com.vaadin.flow.data.binder.BeanValidationBinder;
//import com.vaadin.flow.data.binder.Binder;
//import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
//import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.tutorial.crm.backend.entity.Company;
import com.vaadin.tutorial.crm.backend.entity.Contact;
//import com.vaadin.flow.component.textfield.EmailField;
public class ContactForm extends FormLayout { 

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    TextField firstName = new TextField("First name");
  TextField lastName = new TextField("Last name");
  EmailField email = new EmailField("Email");
  ComboBox<Contact.Status> status = new ComboBox<>("Status");
  ComboBox<Company> company = new ComboBox<>("Company");
private Contact contact;
  Button save = new Button("Save"); 
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");
Binder<Contact> binder = new BeanValidationBinder<>(Contact.class); 
public ContactForm(List<Company> companies) {
    addClassName("contact-form"); 
    binder.bindInstanceFields(this); 
     company.setItems(companies); 
  company.setItemLabelGenerator(Company::getName); 
  status.setItems(Contact.Status.values());

      add(firstName,
        lastName,
        email,
        company,
        status,
        createButtonsLayout()); 
  }

  private HorizontalLayout createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY); 
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    save.addClickShortcut(Key.ENTER); 
    close.addClickShortcut(Key.ESCAPE);

    save.addClickListener(event -> {
        try {
            validateAndSave();
        } catch (com.vaadin.flow.data.binder.ValidationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    });
  delete.addClickListener(event -> fireEvent(new DeleteEvent(this, contact))); 
  close.addClickListener(event -> fireEvent(new CloseEvent(this))); 
  binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid())); 
  return new HorizontalLayout(save, delete, close);
  }

  private void validateAndSave() throws com.vaadin.flow.data.binder.ValidationException {
  try {
  binder.writeBean(contact); 
  fireEvent(new SaveEvent(this, contact)); 
  } catch (ValidationException e) {
  e.printStackTrace();
  }
}
  public void setContact(Contact contact) {
  this.contact = contact; 
  binder.readBean(contact); 
  }


// Events
public abstract class ContactFormEvent extends ComponentEvent<ContactForm> {
    /**
     *
     */
    
    private final Contact contact;

    protected ContactFormEvent(final ContactForm source, final Contact contact) {
        super(source, false);
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }

}

public class SaveEvent extends ContactFormEvent {
  SaveEvent(ContactForm source, Contact contact) {
  super(source, contact);
  }
}
public  class CloseEvent extends ContactFormEvent {
  CloseEvent(ContactForm source) {
  super(source, null);
  }
}
public  class DeleteEvent extends ContactFormEvent {
  DeleteEvent(ContactForm source, Contact contact) {
  super(source, contact);
  }
}
 public <T extends ComponentEvent<?>> Registration addListener(final Class<T> eventType,  final ComponentEventListener<T> listener) { 
  return getEventBus().addListener(eventType, listener);
}

}