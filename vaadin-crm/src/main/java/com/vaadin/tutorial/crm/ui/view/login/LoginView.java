package com.vaadin.tutorial.crm.ui.view.login;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("login")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
@PageTitle("Login | vaadin CRM")
public class LoginView extends VerticalLayout implements BeforeEnterObserver{
    private LoginForm login=new LoginForm();
    public LoginView(){
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        login.setAction("login");
        add(new H1("Vaadin CRM"),login);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent){
        if(beforeEnterEvent.getLocation().getQueryParameters().getParameters().containsKey("error")){
            login.setError(true);
        }
    }
}