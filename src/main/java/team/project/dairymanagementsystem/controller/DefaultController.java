package team.project.dairymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import team.project.dairymanagementsystem.model.VisitorEmail;
import team.project.dairymanagementsystem.model.TenderInfo;
import team.project.dairymanagementsystem.model.checkLoginStatus.CheckLoginStatus;
import team.project.dairymanagementsystem.service.EmailService;
import team.project.dairymanagementsystem.service.TenderInfoService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class DefaultController {
    @Autowired
    private TenderInfoService tenderInfoService;
    @Autowired
    private EmailService emailService;
    public static String message;

    //Home page
    @GetMapping(value = {"/"})
    public ModelAndView defaultHomePage(ModelAndView modelAndView, HttpServletRequest request) throws ParseException {
        //get current date
        TimeZone local = TimeZone.getTimeZone("Africa/Nairobi");
        Calendar now = Calendar.getInstance(local); //get time in the current time zone
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setTimeZone(local);
        String date = formatter.format(now.getTime());
        Date currentDate = formatter.parse(date);

        TenderInfo latestTenderInfo = tenderInfoService.findLatestTender();
        boolean tender = false;
        if (latestTenderInfo != null) {
            Date deadline = latestTenderInfo.getDeadline();
            //check whether deadline has passed
            if (currentDate.compareTo(deadline) > 0) {
                //deadline has passed
                latestTenderInfo.setStatus(false);
                tenderInfoService.addTenderInfo(latestTenderInfo);
            } else if (currentDate.compareTo(deadline) < 0) {
                tender = true; //a tender is available
                modelAndView.addObject("tenderInfo", latestTenderInfo);
            } else {
                System.out.println("How did we get here?");
            }
        }
        CheckLoginStatus.checkStatus(modelAndView, request);
        modelAndView.addObject("tender", tender);
        if (message != null) {
            modelAndView.addObject("message", message);
            //reset message
            message = null;
        }
        modelAndView.addObject("visitorEmail", new VisitorEmail());
        modelAndView.setViewName("welcome");
        return modelAndView;
    }

    //Login page
    @GetMapping(value = "/login")
    public ModelAndView loginPage(ModelAndView modelAndView) {
        modelAndView.setViewName("login/login");
        return modelAndView;
    }

    @PostMapping("/email")
    public ModelAndView sendMail(@ModelAttribute(name = "senderEmail") VisitorEmail visitorEmail, ModelAndView modelAndView) {
        Map<String, Object> variable = new HashMap<>();
        variable.put("visitorEmail", visitorEmail);
        String response = emailService.sendEmail(visitorEmail.getEmail(), "ricdechox@gmail.com",
                visitorEmail.getSubject(), variable, "visitor-email");
        if (response.equals("SUCCESS: ")) {
            response += "Email sent successfully";
        } else {
            response += "An error occurred. Please try again later";
        }
        message = response;
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}
