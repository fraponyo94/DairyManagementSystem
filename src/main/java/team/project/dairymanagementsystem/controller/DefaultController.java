package team.project.dairymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import team.project.dairymanagementsystem.model.TenderInfo;
import team.project.dairymanagementsystem.service.TenderInfoService;

@Controller
public class DefaultController{
    @Autowired
    private TenderInfoService tenderInfoService;

    //Home page
    @GetMapping(value={"/"})
    public ModelAndView defaultHomePage(ModelAndView modelAndView) {
        TenderInfo activeTenderInfo = tenderInfoService.findActiveTender();
        boolean tender = false;
         if (activeTenderInfo != null){
            modelAndView.addObject("tenderInfo",activeTenderInfo );
            tender = true;
         }
         modelAndView.addObject("tender",tender);
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
