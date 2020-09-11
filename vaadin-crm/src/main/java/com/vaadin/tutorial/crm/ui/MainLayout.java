package com.vaadin.tutorial.crm.ui;

@CssImport("./styles/views/main/main-view.css")
public class MainLayout extends AppLayout {
    public MainLayout() {
        createHeader();
        createDrawer();
    }
    private void createHeader() {
        H1 logo = new H1("Vaadin CRM");
        logo.addClassName("logo");
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);

        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");
        addToNavbar(header);
    }
    private void createDrawer() {
        RouterLink listLink = new RouterLink("List", ListView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(new VerticalLayout(listLink));
    }
}
