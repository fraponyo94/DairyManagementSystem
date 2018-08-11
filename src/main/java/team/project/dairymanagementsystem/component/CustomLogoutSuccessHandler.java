package team.project.dairymanagementsystem.component;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This class invalidates the user's http session upon logging out.
 */

@Component
public class CustomLogoutSuccessHandler  implements LogoutSuccessHandler  {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        final HttpSession session = request.getSession();
        //check if session is not null
        if (session != null) {
            //get user session object and remove it using its key
            session.removeAttribute("user");
            session.removeAttribute("loggedIn");
            session.removeAttribute("admin");
            session.invalidate();
        }

        //send user back to welcome page
        response.sendRedirect("/");
    }
}
