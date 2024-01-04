package com.example.application.views.login;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("Login")
@Route(value = "login")
@RouteAlias(value = "")
@Uses(Icon.class)
public class LoginView extends Composite<VerticalLayout> {

    public LoginView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button buttonPrimary = new Button();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H1 h1 = new H1();
        EmailField emailField = new EmailField();
        PasswordField passwordField = new PasswordField();
        Anchor link = new Anchor();
        Button buttonPrimary2 = new Button();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        RouterLink routerLink = new RouterLink();
        RouterLink routerLink2 = new RouterLink();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        layoutRow.setAlignItems(Alignment.CENTER);
        layoutRow.setJustifyContentMode(JustifyContentMode.END);
        buttonPrimary.setText("Registrieren");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        h1.setText("Find a Supervisor");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, h1);
        h1.setWidth("max-content");
        emailField.setLabel("Email");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, emailField);
        emailField.setWidth("min-content");
        passwordField.setLabel("Password");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, passwordField);
        passwordField.setWidth("min-content");
        link.setText("Passwort vergessen?");
        link.setHref("#");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, link);
        link.setWidth("160px");
        buttonPrimary2.setText("Login");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, buttonPrimary2);
        buttonPrimary2.setWidth("min-content");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.setHeight("min-content");
        routerLink.setText("Datenschutz");
        routerLink.setRoute(LoginView.class);
        routerLink.setWidth("min-content");
        routerLink2.setText("Impressum");
        routerLink2.setRoute(LoginView.class);
        routerLink2.setWidth("min-content");
        getContent().add(layoutRow);
        layoutRow.add(buttonPrimary);
        getContent().add(layoutColumn2);
        layoutColumn2.add(h1);
        layoutColumn2.add(emailField);
        layoutColumn2.add(passwordField);
        layoutColumn2.add(link);
        layoutColumn2.add(buttonPrimary2);
        getContent().add(layoutRow2);
        layoutRow2.add(routerLink);
        layoutRow2.add(routerLink2);
    }
}
