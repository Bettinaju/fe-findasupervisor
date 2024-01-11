package com.example.application.views.list;

import com.example.application.views.login.LoginView;
import com.example.application.views.student.StudentProfileView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("offer-list")
@PageTitle("Angebotsliste")
public class OfferListView extends VerticalLayout {

    public OfferListView() {
        // Header mit Überschrift
        HorizontalLayout headerLayout = createHeaderLayout();

        // "Mein Profil"-Button
        Button myProfileButton = new Button("Mein Profil", event -> {
            // Hier können Sie die Logik zum Navigieren zum Studentenprofil implementieren
            getUI().ifPresent(ui -> ui.navigate(StudentProfileView.class));
        });

        // Logout-Button
        Button logoutButton = new Button("Logout", event -> {
            // Hier können Sie die Logout-Logik implementieren
            // Beispiel: Navigieren Sie zurück zur Login-Seite
            getUI().ifPresent(ui -> ui.navigate(LoginView.class));
        });

        // Horizontales Layout für "Mein Profil"-Button und Logout-Button
        HorizontalLayout buttonLayout = new HorizontalLayout(myProfileButton, logoutButton);
        buttonLayout.setSizeFull();
        buttonLayout.setJustifyContentMode(JustifyContentMode.END);

        // Layout für die Angebotsliste
        VerticalLayout mainLayout = new VerticalLayout(headerLayout, buttonLayout);
        mainLayout.setSizeFull();
        mainLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER); // Hier setzen Sie die zentrale Ausrichtung

        // Beispiel für drei Einträge in der Liste
        for (int i = 1; i <= 3; i++) {
            Div offerItem = createOfferItem("Supervisor " + i, "Beschreibung des Supervisors " + i);
            mainLayout.add(offerItem);
        }

        add(mainLayout);
    }

    private HorizontalLayout createHeaderLayout() {
        // Überschrift im Header
        H1 headerTitle = new H1("Hier ist eine Liste");
        headerTitle.getElement().getThemeList().add("primary");

        // Horizontales Layout für den Header mit Überschrift
        HorizontalLayout headerLayout = new HorizontalLayout(headerTitle);
        headerLayout.setSizeFull();
        headerLayout.setJustifyContentMode(JustifyContentMode.START);

        return headerLayout;
    }

    private Div createOfferItem(String name, String description) {
        // Erstellen Sie ein Div für einen Eintrag in der Angebotsliste
        Div offerItem = new Div();
        offerItem.addClassName("offer-item"); // Fügen Sie eine CSS-Klasse für Styling hinzu

        // Inhalt des Eintrags
        offerItem.add(new H1(name));
        offerItem.add(new Div("Fachgebiet: XYZ")); // Hier können Sie das Fachgebiet des Supervisors einfügen
        offerItem.add(new Div(description));

        // Beispiel für eine Schaltfläche im Eintrag
        Button viewButton = new Button("Profil ansehen", event -> {
            // Hier können Sie die Logik zum Anzeigen des Profils des Supervisors implementieren
            Notification.show("Profil von " + name + " anzeigen");
        });

        offerItem.add(viewButton);

        return offerItem;
    }
}
