package com.example.application.views.supervisor;// SupervisorProfileView.java
import com.example.application.views.login.LoginView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;

@Route("supervisor-profile")
@PageTitle("Mein Supervisorprofil")
public class SupervisorProfileView extends VerticalLayout {

    private TextField firstNameField = new TextField("Vorname");
    private TextField lastNameField = new TextField("Nachname");
    private TextField emailField = new TextField("Email");
    private TextField phoneNumberField = new TextField("Telefonnummer");
    private TextField descriptionField = new TextField("Beschreibung");
    private Checkbox availableCheckbox = new Checkbox("Verfügbar");

    public SupervisorProfileView() {
        // Header mit App-Namen
        H1 header = new H1("Meine App");

        // Logout-Button
        Button logoutButton = new Button("Logout", event -> {
            // Hier können Sie die Logout-Logik implementieren
            // Beispiel: Navigieren Sie zurück zur Login-Seite
            getUI().ifPresent(ui -> ui.navigate(LoginView.class));
        });

        // Horizontales Layout für Header mit Name, Logout-Button und "Profil löschen"-Button
        HorizontalLayout headerLayout = new HorizontalLayout(header, logoutButton);
        headerLayout.expand(header);
        headerLayout.setWidthFull();

        // FormLayout für die Paare von TextField- und Checkbox-Komponenten
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(firstNameField, "");
        formLayout.addFormItem(lastNameField, "");
        formLayout.addFormItem(emailField, "");
        formLayout.addFormItem(phoneNumberField, "");
        formLayout.addFormItem(descriptionField, "");
        formLayout.addFormItem(availableCheckbox, "");

        // Speichern und Zurücksetzen Buttons
        Button saveButton = new Button("Speichern", event -> {
            // Hier können Sie die Profildaten speichern
            saveProfile();
        });

        Button resetButton = new Button("Zurücksetzen", event -> {
            // Hier können Sie die Profildaten zurücksetzen
            resetProfile();
        });

        // "Profil löschen"-Button
        Button deleteButton = new Button("Profil löschen");
        deleteButton.getStyle().set("background-color", "red");
        deleteButton.addClickListener(event -> showDeleteConfirmationDialog());

        // Vertikales Layout für Header, FormLayout, Buttons und "Profil löschen"-Button
        VerticalLayout mainLayout = new VerticalLayout(headerLayout, formLayout, saveButton, resetButton, deleteButton);
        mainLayout.setHorizontalComponentAlignment(Alignment.CENTER, formLayout, saveButton, resetButton, deleteButton);
        mainLayout.setAlignSelf(Alignment.END, deleteButton); // "Profil löschen"-Button am rechten Rand ausrichten

        add(mainLayout);
    }

    private void saveProfile() {
        // Hier implementieren Sie die Logik zum Speichern der Profildaten
        Notification.show("Profil gespeichert!");
    }

    private void resetProfile() {
        // Hier implementieren Sie die Logik zum Zurücksetzen der Profildaten
        // Zum Beispiel: Setzen Sie die Felder auf leere Werte zurück
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        phoneNumberField.clear();
        descriptionField.clear();
        availableCheckbox.setValue(false);

        Notification.show("Profil zurückgesetzt!");
    }

    private void showDeleteConfirmationDialog() {
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);

        VerticalLayout layout = new VerticalLayout();
        layout.add("Profil wirklich löschen?");

        Button yesButton = new Button("Ja", e -> {
            // Hier können Sie die Logik zum Löschen des Profils implementieren
            // Beispiel: Navigieren Sie zurück zur Login-Seite nach dem Löschen
            getUI().ifPresent(ui -> ui.navigate(LoginView.class));
            dialog.close();
        });

        Button noButton = new Button("Nein", e -> dialog.close());

        layout.add(yesButton, noButton);

        dialog.add(layout);
        dialog.open();
    }
}
