package team.project.dairymanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController{

    //Home page
    @GetMapping(value={"/"})
    public ModelAndView defaultHomePage(ModelAndView modelAndView) {
        modelAndView.setViewName("welcome");
        return modelAndView;
    }

    //Login page
    @GetMapping(value="/login")
    public ModelAndView loginPage(ModelAndView modelAndView){
        modelAndView.setViewName("login/login");
        return modelAndView;
    }

}
