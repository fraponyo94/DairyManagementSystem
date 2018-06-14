package team.project.dairymanagementsystem;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {
    @GetMapping(value={"/", "/index"})
    public String getHomePage(Model model){
        return "welcome";
    }

    @GetMapping(value="/login")
    public String getLoginPage(Model model){
        return "login";
    }

    @GetMapping(value="/logout-success")
    public String getLogoutPage(Model model){
        return "logout";
    }

    @GetMapping(value = "/editContracts")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String ediContracts(Model model) {
        return "editContracts";
    }
}
