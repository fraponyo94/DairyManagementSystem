package team.project.dairymanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import team.project.dairymanagementsystem.model.TenderInfo;

@Controller
@RequestMapping("/admin")
public class TenderAdminController {

    //Admin home
    @GetMapping("/")
    public ModelAndView adminHome(ModelAndView modelAndView){
        modelAndView.addObject("tenderInfo",new TenderInfo());
        modelAndView.setViewName("admin/admin-home");
        return modelAndView;
    }

}
