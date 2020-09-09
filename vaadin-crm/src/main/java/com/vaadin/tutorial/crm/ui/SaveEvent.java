package com.vaadin.tutorial.crm.ui;

import com.vaadin.tutorial.crm.backend.entity.Contact;

public class SaveEvent extends ContactFormEvent {
  SaveEvent(ContactForm source, Contact contact) {
  super(source, contact);
  }
}