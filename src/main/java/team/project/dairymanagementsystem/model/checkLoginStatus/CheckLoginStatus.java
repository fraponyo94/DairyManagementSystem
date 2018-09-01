package team.project.dairymanagementsystem.model.checkLoginStatus;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public class CheckLoginStatus {
    public static void checkStatus(ModelAndView modelAndView, HttpServletRequest request){
        //check whether user is logged in to determine whether to show a logout or login button
        if(request.getSession().getAttribute("loggedIn") != null){
            modelAndView.addObject("loggedIn", true);
            //check if the user is an admin
            if(request.getSession().getAttribute("admin") != null){
                modelAndView.addObject("admin", true);
            }else{
                modelAndView.addObject("admin", false);
            }
        }else{
            modelAndView.addObject("loggedIn", false);
            modelAndView.addObject("admin", false);
        }
    }
}
