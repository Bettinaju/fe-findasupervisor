package com.example.application.views.registration;

import com.example.application.views.login.LoginView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("student-registration")
@PageTitle("Studentenregistrierung")
public class StudentRegistrationView extends VerticalLayout {

    private EmailField emailField = new EmailField("E-Mail");
    private PasswordField passwordField = new PasswordField("Passwort");
    private PasswordField confirmPasswordField = new PasswordField("Passwort bestätigen");

    public StudentRegistrationView() {
        // Header mit App-Namen
        H1 header = new H1("Studentenregistrierung");

        // FormLayout für die Eingabefelder
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(emailField, "");
        formLayout.addFormItem(passwordField, "");
        formLayout.addFormItem(confirmPasswordField, "");

        // Konfiguration für die vertikale Anordnung der Formularfelder
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0px", 1),
                new FormLayout.ResponsiveStep("600px", 1),
                new FormLayout.ResponsiveStep("900px", 1),
                new FormLayout.ResponsiveStep("1200px", 1)
        );

        // "Registrieren"-Button
        Button registerButton = new Button("Registrieren", event -> {
            // Hier können Sie die Validierung und Registrierungslogik implementieren
            validateAndRegister();
        });

        // "abbrechen"-Button
        Button backButton = new Button("abbrechen", event -> {
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
        if (email == null || !email.endsWith("@student.htw-berlin.de") || email.indexOf('@') == 0) {
            showError("Die E-Mail muss auf @student.htw-berlin.de enden.");
            return;
        }

        // Validierung des Passworts
        String password = passwordField.getValue();
        String confirmPassword = confirmPasswordField.getValue();
        if (password.length() < 8 || password.length() > 30 || !password.equals(confirmPassword)) {
            showError("Das Passwort muss zwischen 8 und 30 Zeichen lang sein und die Passwörter müssen übereinstimmen.");
            return;
        }

        // Hier können Sie die Registrierungslogik für den Studenten implementieren
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
