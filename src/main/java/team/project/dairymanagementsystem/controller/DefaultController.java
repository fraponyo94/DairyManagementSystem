package team.project.dairymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import team.project.dairymanagementsystem.model.TenderInfo;
import team.project.dairymanagementsystem.service.TenderInfoService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Controller
public class DefaultController {
    @Autowired
    private TenderInfoService tenderInfoService;

    //Home page
    @GetMapping(value = {"/"})
    public ModelAndView defaultHomePage(ModelAndView modelAndView) throws ParseException {
        //get current date
        TimeZone local = TimeZone.getTimeZone("Africa/Nairobi");
        Calendar now = Calendar.getInstance(local); //get time in the current time zone
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setTimeZone(local);
        String date = formatter.format(now.getTime());
        Date currentDate = formatter.parse(date);

        TenderInfo activeTenderInfo = tenderInfoService.findActiveTender();
        boolean tender = false;
        if (activeTenderInfo != null) {
            Date deadline = activeTenderInfo.getDeadline();

            //check whether deadline has passed
            if (currentDate.compareTo(deadline) > 0) {
                //deadline has passed
                activeTenderInfo.setStatus(false);
                tenderInfoService.addTenderInfo(activeTenderInfo);
            } else if (currentDate.compareTo(deadline) < 0) {
                tender = true; //a tender is available
                modelAndView.addObject("tenderInfo", activeTenderInfo);
            } else {
                System.out.println("How did we get here?");
            }
        }
        modelAndView.addObject("tender", tender);
        modelAndView.setViewName("welcome");
        return modelAndView;
    }

    //Login page
    @GetMapping(value = "/login")
    public ModelAndView loginPage(ModelAndView modelAndView) {
        modelAndView.setViewName("login/login");
        return modelAndView;
    }

}
