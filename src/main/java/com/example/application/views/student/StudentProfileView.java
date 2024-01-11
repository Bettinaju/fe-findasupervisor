package com.example.application.views.student;

import com.example.application.views.login.LoginView;
import com.example.application.views.list.OfferListView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.textfield.TextField;

@Route("student-profile")
@PageTitle("Mein Studentenprofil")
public class StudentProfileView extends VerticalLayout {

    private TextField firstNameField = new TextField("Vorname");
    private TextField lastNameField = new TextField("Nachname");
    private TextField emailField = new TextField("Email");

    public StudentProfileView() {
        // Hier setzen Sie die vorzufüllenden Werte für die Textfelder
        // Zum Beispiel: Annahme, dass die Werte aus einer Datenbank oder Session kommen
        // Sie müssen diese Werte entsprechend Ihrer Anwendung anpassen
        firstNameField.setValue("Betti");
        lastNameField.setValue("Ju");
        emailField.setValue("email@student.com");

        // Setzen Sie die Textfelder als schreibgeschützt (read-only)
        firstNameField.setReadOnly(true);
        lastNameField.setReadOnly(true);
        emailField.setReadOnly(true);

        // Header mit App-Namen
        H1 header = new H1("Hi Studentin, das hier ist dein Profil!");

        // Logout-Button
        Button logoutButton = new Button("Logout", event -> {
            // Hier können Sie die Logout-Logik implementieren
            // Beispiel: Navigieren Sie zurück zur Login-Seite
            getUI().ifPresent(ui -> ui.navigate(LoginView.class));
        });

        // "Zurück zur Liste"-Button
        Button backToListButton = new Button("Zurück zur Liste", event -> {
            // Hier können Sie die Logik zum Navigieren zur Angebotsliste implementieren
            getUI().ifPresent(ui -> ui.navigate(OfferListView.class));
        });

        // Horizontales Layout für Header mit Name und Buttons
        HorizontalLayout headerLayout = new HorizontalLayout(header, logoutButton, backToListButton);
        headerLayout.expand(header);
        headerLayout.setWidthFull();

        // FormLayout für die nicht bearbeitbaren Felder
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(firstNameField, "");
        formLayout.addFormItem(lastNameField, "");
        formLayout.addFormItem(emailField, "");

        // "Profil löschen"-Button
        Button deleteButton = new Button("Profil löschen");
        deleteButton.getStyle().set("background-color", "red");
        deleteButton.addClickListener(event -> showDeleteConfirmationDialog());

        // Vertikales Layout für Header, FormLayout, und "Profil löschen"-Button
        VerticalLayout mainLayout = new VerticalLayout(headerLayout, formLayout, deleteButton);
        mainLayout.setHorizontalComponentAlignment(Alignment.CENTER, formLayout, deleteButton);
        mainLayout.setAlignSelf(Alignment.END, deleteButton); // "Profil löschen"-Button am rechten Rand ausrichten

        add(mainLayout);
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
