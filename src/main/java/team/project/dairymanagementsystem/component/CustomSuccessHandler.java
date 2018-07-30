package team.project.dairymanagementsystem.component;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import team.project.dairymanagementsystem.model.enumerated.Roles;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        Collection < ? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(authority -> {
           //
            if(authority.getAuthority().equals("Admin")) {
                try {
                    redirectStrategy.sendRedirect(request, response, "admin/");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if(authority.getAuthority().equals("SUPPLIER")) {
                try {
                    redirectStrategy.sendRedirect(request, response, "/");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if(authority.getAuthority().equals("Applicant")) {
                try{
                    redirectStrategy.sendRedirect(request,response,"/contract/apply");
                }catch (Exception e){
                    e.printStackTrace();
                }

            }else{
                    throw new IllegalStateException();

            }
        });

    }

}
