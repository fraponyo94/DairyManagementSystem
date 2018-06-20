
package team.project.dairymanagementsystem.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {


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

