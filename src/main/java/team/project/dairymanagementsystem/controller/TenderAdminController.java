package team.project.dairymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import team.project.dairymanagementsystem.model.TenderInfo;
import team.project.dairymanagementsystem.service.TenderInfoService;

@Controller
@RequestMapping("/admin")
public class TenderAdminController {
    @Autowired
    private TenderInfoService tenderInfoService;

    //Admin home
    @GetMapping("/")
    public ModelAndView adminHome(ModelAndView modelAndView) {
        //check if there is an existing active tender
        TenderInfo tenderInfo = tenderInfoService.findLatestTender();
        if (tenderInfo != null) {
            boolean active = tenderInfo.isStatus();
            modelAndView.addObject("active", active);
            modelAndView.addObject("tender", tenderInfo);
        }else{
            modelAndView.addObject("active", false);
        }
        modelAndView.addObject("tenderInfo", new TenderInfo());
        modelAndView.setViewName("admin/admin-home");
        return modelAndView;
    }

}
