package team.project.dairymanagementsystem.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.project.dairymanagementsystem.model.User;
import team.project.dairymanagementsystem.repository.UserRepository;
import team.project.dairymanagementsystem.service.DairyUserDetailsService;

@Controller
@RequestMapping("/")
public class UserController {
    private DairyUserDetailsService userDetailsService;
    public UserController(DairyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

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

    //add an  admin user to the system
    @GetMapping("/add-user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addUser.html";
    }

    //save the admin user to the system
    @PostMapping("/add-user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addUser(@ModelAttribute(value = "user") User user) {
        userDetailsService.saveUser(user);
        return "addUser.html";
    }

}
