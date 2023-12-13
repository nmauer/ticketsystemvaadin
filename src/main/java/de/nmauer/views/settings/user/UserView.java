package de.nmauer.views.settings.user;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayoutVariant;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializableBiConsumer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.nmauer.data.User;
import de.nmauer.data.UserRepository;
import de.nmauer.services.UserService;
import de.nmauer.views.MainLayout;
import jakarta.annotation.security.PermitAll;

import java.util.stream.Stream;

@Route(value = "settings/users", layout = MainLayout.class)
@PageTitle("User Settings")
@PermitAll
public class UserView extends SplitLayout {

    // user data providers
    private UserRepository repository;
    private UserService userService;

    // grid data view
    private GridListDataView<User> dataView;

    // grid columns
    private Grid.Column<User> idColumn, emailColumn, nameColumn, profilePictureColumn, usernameColumn;

    public UserView(UserRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;

        setSplitterPosition(15);
        addThemeVariants(SplitLayoutVariant.LUMO_SMALL);
        setSizeFull();

        addToPrimary(createDrawer());
        addToSecondary(createGrid());
    }

    private VerticalLayout createDrawer(){
        return null;
    }

    private Grid<User> createGrid(){
        Grid<User> grid = new Grid<>();

        grid.setSizeFull();
        grid.addThemeVariants(GridVariant.MATERIAL_COLUMN_DIVIDERS);

        profilePictureColumn = grid.addColumn(createProfilePictureComponentRenderer());
        idColumn = grid.addColumn(User::getId).setHeader("ID");
        usernameColumn = grid.addColumn(User::getUsername).setHeader("Username");
        nameColumn = grid.addColumn(User::getName).setHeader("Name");
        emailColumn = grid.addColumn(User::getEmail).setHeader("E-Mail");

        Stream.of(idColumn, emailColumn, nameColumn, profilePictureColumn, usernameColumn).forEach(column -> {
            column.setSortable(true);
            column.setAutoWidth(true);
            column.setResizable(true);
        });

        dataView = grid.setItems(repository.findAll());

        return grid;
    }

    private final SerializableBiConsumer<Span, User> profilePictureUpdater = (span, user) -> {
        span.add(user.getProfilePictureAsAvatar());
    };

    private ComponentRenderer<Span, User> createProfilePictureComponentRenderer(){
        return new ComponentRenderer<>(Span::new, profilePictureUpdater);
    }
}
