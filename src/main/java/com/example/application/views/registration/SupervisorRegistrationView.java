package com.example.application.views.registration;

import com.example.application.views.login.LoginView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("supervisor-registration")
@PageTitle("Supervisor-Registrierung")
public class SupervisorRegistrationView extends VerticalLayout {

    private EmailField emailField = new EmailField("E-Mail");
    private PasswordField passwordField = new PasswordField("Passwort");
    private PasswordField confirmPasswordField = new PasswordField("Passwort wiederholen");
    private ComboBox<String> degreeComboBox = new ComboBox<>("Welchen der folgenden Abschlüsse haben Sie?");

    public SupervisorRegistrationView() {
        // Header mit App-Namen
        H1 header = new H1("Supervisor-Registrierung");

        // Füllen Sie die ComboBox mit Auswahlmöglichkeiten
        degreeComboBox.setItems("Bachelor of Science", "Master of Science", "Doctor of Science", "andere", "keinen");

        // FormLayout für die Eingabefelder
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(emailField, "");
        formLayout.addFormItem(passwordField, "");
        formLayout.addFormItem(confirmPasswordField, "");
        formLayout.addFormItem(degreeComboBox, "");

        // Setzen Sie die Breite der Labels
        formLayout.setColspan(emailField, 2);
        formLayout.setColspan(passwordField, 2);
        formLayout.setColspan(confirmPasswordField, 2);
        formLayout.setColspan(degreeComboBox, 2);

        // "Registrieren"-Button
        Button registerButton = new Button("Registrieren", event -> {
            // Hier können Sie die Validierung und Registrierungslogik implementieren
            validateAndRegister();
        });

        // "Zurück zum Login"-Button
        Button backButton = new Button("Zurück zum Login", event -> {
            // Hier können Sie die Logik zum Navigieren zur Login-Seite implementieren
            getUI().ifPresent(ui -> ui.navigate(LoginView.class));
        });

        // Vertikales Layout für Header, FormLayout und Buttons
        VerticalLayout mainLayout = new VerticalLayout(header, formLayout, registerButton, backButton);
        mainLayout.setHorizontalComponentAlignment(Alignment.CENTER, formLayout, registerButton, backButton);
        mainLayout.setAlignSelf(Alignment.CENTER, formLayout);

        add(mainLayout);
    }

    private void validateAndRegister() {
        // Validierung der E-Mail
        String email = emailField.getValue();
        if (email == null || !email.endsWith("@supervisor.com") || email.indexOf('@') == 0) {
            showError("Die E-Mail muss auf @supervisor.com enden und vor dem @-Zeichen etwas enthalten.");
            return;
        }

        // Validierung des Passworts
        String password = passwordField.getValue();
        String confirmPassword = confirmPasswordField.getValue();
        if (password.length() < 8 || password.length() > 30 || !password.equals(confirmPassword)) {
            showError("Das Passwort muss zwischen 8 und 30 Zeichen lang sein und die Passwörter müssen übereinstimmen.");
            return;
        }

        // Validierung der ComboBox
        String selectedDegree = degreeComboBox.getValue();
        if ("andere".equals(selectedDegree) || "keinen".equals(selectedDegree)) {
            showError("Registrierung nicht möglich. Eventuell reichen Ihre Qualifikationen nicht aus.");
            return;
        }

        // Hier können Sie die Registrierungslogik für den Supervisor implementieren
        // Beispiel: Daten in der Datenbank speichern

        // Erfolgsmeldung anzeigen
        Notification.show("Registrierung erfolgreich! Du kannst dich jetzt einloggen.");

        // Zurück zur Login-Seite navigieren
        getUI().ifPresent(ui -> ui.navigate(LoginView.class));
    }

    private void showError(String message) {
        Notification.show(message, 3000, Notification.Position.TOP_CENTER);
    }
}
