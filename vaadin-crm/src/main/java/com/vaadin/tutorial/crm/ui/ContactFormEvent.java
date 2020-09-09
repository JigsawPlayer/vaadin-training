package com.vaadin.tutorial.crm.ui;

import com.vaadin.flow.component.ComponentEvent;
//import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.shared.Registration;
import com.vaadin.tutorial.crm.backend.entity.Contact;

public  abstract class ContactFormEvent extends ComponentEvent<ContactForm> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final Contact contact;

    protected ContactFormEvent(final ContactForm source, final Contact contact) {
        super(source, false);
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }

}

 public <T extends ComponentEvent<?>> Registration addListener(final Class<T> eventType,
  final ComponentEventListener<T> listener) { 
  return getEventBus().addListener(eventType, listener);
}

private ContactFormEvent getEventBus() {
	return null;
}
