package com.example.application.views.registration;

import com.example.application.views.login.LoginView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("registration")
@PageTitle("Registrierung")
public class RegistrationView extends VerticalLayout {

    private RadioButtonGroup<String> roleSelection = new RadioButtonGroup<>();
    private TextField usernameField = new TextField("Benutzername");
    private TextField passwordField = new TextField("Passwort");

    public RegistrationView() {
        // Header mit App-Namen
        H1 header = new H1("Registrierung");

        // Rolle-Auswahl mit Radio-Button
        roleSelection.setLabel("Rolle auswählen");
        roleSelection.setItems("Student", "Supervisor");
        roleSelection.setValue("Student"); // Standardmäßig auf "Student" setzen

        // FormLayout für die Eingabefelder
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(roleSelection, "");

        // "Weiter"-Button
        Button nextButton = new Button("Weiter", event -> {
            // Hier können Sie die Logik zur Benutzerregistrierung implementieren

            // Je nach ausgewählter Rolle navigieren
            if ("Student".equals(roleSelection.getValue())) {
                getUI().ifPresent(ui -> ui.navigate(StudentRegistrationView.class));
            } else if ("Supervisor".equals(roleSelection.getValue())) {
                getUI().ifPresent(ui -> ui.navigate(SupervisorRegistrationView.class));
            }
        });

        // "Zurück zum Login"-Button
        Button backButton = new Button("Zurück zum Login", event -> {
            // Hier können Sie die Logik zum Navigieren zur Login-Seite implementieren
            getUI().ifPresent(ui -> ui.navigate(LoginView.class));
        });

        // Horizontales Layout für Buttons
        HorizontalLayout buttonLayout = new HorizontalLayout(nextButton, backButton);

        // Vertikales Layout für Header, FormLayout und Buttons
        VerticalLayout mainLayout = new VerticalLayout(header, formLayout, buttonLayout);
        mainLayout.setHorizontalComponentAlignment(Alignment.CENTER, formLayout, buttonLayout);
        mainLayout.setSizeFull();
        mainLayout.setAlignSelf(Alignment.CENTER, formLayout);

        add(mainLayout);
    }
}
