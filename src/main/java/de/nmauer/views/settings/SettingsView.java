package de.nmauer.views.settings;

import com.vaadin.flow.component.html.Div;
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
        addClassName("./themes/ticketsystemvaadin/views/settings/settings.css");

        cells = new ArrayList<>();

        cells.add(createCell("Users", "settings/users"));
        cells.add(createCell("Companies", "settings/companies"));

        for(Div cell : cells) {
            add(cell);
        }
    }

    private Div createCell(String title, String route){
        Div cell = new Div();

        cell.setText(title);
        cell.addClassName("settings-overview-cell");
        cell.addClickListener(divClickEvent -> {
            getUI().get().getPage().setLocation(route);
        });
        cell.setWidth("200px");
        cell.setHeight("100px");

        return cell;
    }

}
