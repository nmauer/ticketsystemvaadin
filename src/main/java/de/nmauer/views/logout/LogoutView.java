package de.nmauer.views.logout;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import de.nmauer.security.AuthenticatedUser;
import jakarta.annotation.security.PermitAll;

@Route("logout")
@PermitAll
public class LogoutView extends HorizontalLayout {

    public LogoutView(AuthenticatedUser authenticatedUser) {
        authenticatedUser.logout();
    }
}
