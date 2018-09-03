
package team.project.dairymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.project.dairymanagementsystem.component.UsernameCheck;
import team.project.dairymanagementsystem.model.User;
import team.project.dairymanagementsystem.model.VisitorEmail;
import team.project.dairymanagementsystem.repository.SupplierRepository;
import team.project.dairymanagementsystem.service.UserService;

@Controller

public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SupplierRepository supplierRepository;

    private int variable_id;

    private String message;

    //constant to identify success messages
    private String SUCCESS = "SUCCESS: ";
    //constant to identify error messages
    private String ERROR = "ERROR: ";

    @Autowired
    private UsernameCheck usernameCheck;

    @GetMapping("/create-user/{id}")
    public String displayContractDetails(@PathVariable(name = "id") Integer id, Model model){
        model.addAttribute("user", new User());
        model.addAttribute("id", id);
        model.addAttribute("error", message);
        message = "";
        variable_id = id;
        return "createuser";
    }

    @PostMapping("/create-user")
    public String createUserDetails(@ModelAttribute(name = "user") User user,@ModelAttribute(name = "id") int id, Model model){
        user.setNationalId(id);
        System.out.println(user.getUsername());
        if (usernameCheck.getUser(user)!= null){
            DefaultController.message = SUCCESS + "Account created successfully";
            return "redirect:/";
        }
        else {
            message = "Username or national id exist!";
            return "redirect:/create-user/"+variable_id;
        }

    }

}

