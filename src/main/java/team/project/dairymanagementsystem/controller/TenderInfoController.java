package team.project.dairymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import team.project.dairymanagementsystem.model.TenderInfo;
import team.project.dairymanagementsystem.model.checkLoginStatus.CheckLoginStatus;
import team.project.dairymanagementsystem.service.TenderInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class TenderInfoController {
    //    constant to identify success messages
    private String SUCCESS = "SUCCESS: ";
    //    constant to identify error messages
    private String ERROR = "ERROR: ";

    @Autowired
    private TenderInfoService tenderInfoService;

    /*Add Tender -------POST-----*/
    @PostMapping("/tender/add")
    public ModelAndView addContract(@Valid @ModelAttribute TenderInfo tenderInfo, BindingResult result, MultipartFile file, ModelAndView modelAndView) {

        System.out.println(tenderInfo.toString());

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors().toString());
            modelAndView.setViewName("admin/admin-home");
        } else {
            if (!file.isEmpty()) {
                try {
                    byte[] fileAttachment = file.getBytes();
                    tenderInfo.setFileAttachment(fileAttachment);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            tenderInfo.setStatus(true);
            tenderInfoService.addTenderInfo(tenderInfo);
            DefaultController.message = SUCCESS + "Tender created successfully";
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    //Go to tender information
    @GetMapping("/tender/tender-info")
    public ModelAndView tenderInfo(ModelAndView modelAndView, HttpServletRequest request) {
        CheckLoginStatus.checkStatus(modelAndView, request);
        TenderInfo tenderInfo = tenderInfoService.findLatestTender();
        modelAndView.addObject("tender", tenderInfo);
        if (tenderInfo.isStatus()) {
            modelAndView.setViewName("tender/tender-info");
        }else{
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }

    //   Access dairy tender description pdf
    @RequestMapping(value = "/tender/tender-pdf", method = RequestMethod.GET)
    public String getDairyTenderPdfDescription(HttpServletRequest request, HttpSession httpSession, HttpServletResponse response) {
        try {
            TenderInfo tenderInfo = tenderInfoService.findLatestTender();
            byte[] bytes = tenderInfo.getFileAttachment();
            response.setHeader("Content-Disposition", "inline; filename=\"dairy-tender.pdf\"");
            //response.setDateHeader("Expires", -1);
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);

            response.getOutputStream().write(bytes);
        } catch (Exception ignored) {

        }
        return null;

    }


}
