package de.nmauer.views.settings;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.nmauer.views.MainLayout;
import jakarta.annotation.security.RolesAllowed;

import java.util.ArrayList;

@PageTitle("Settings")
@Route(value = "settings", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class SettingsView extends FlexLayout {

    private ArrayList<Div> cells;

    public SettingsView() {
        UI.getCurrent().getPage().addStyleSheet("./themes/ticketsystemvaadin/views/settings/settings.css");
        addClassName("settings-overview-flex_layout");
        setWidthFull();

        setJustifyContentMode(JustifyContentMode.CENTER);
        setMaxWidth("80%");
        setFlexWrap(FlexWrap.WRAP);

        cells = new ArrayList<>();

        cells.add(createCell("Users", "settings/users"));
        cells.add(createCell("Companies", "settings/companies"));
        cells.add(createCell("Groups", "settings/groups"));
        cells.add(createCell("Departments", "settings/departments"));

        for(Div cell : cells) {
            add(cell);
        }
    }

    private Div createCell(String title, String route){
        Div cell = new Div();
        Paragraph p = new Paragraph(title);

        p.addClassName("settings-overview-cell-p");

        cell.add(p);
        cell.addClassName("settings-overview-cell");
        cell.addClickListener(divClickEvent -> {
            getUI().get().getPage().setLocation(route);
        });
        cell.setWidth("200px");
        cell.setHeight("100px");

        return cell;
    }

}
