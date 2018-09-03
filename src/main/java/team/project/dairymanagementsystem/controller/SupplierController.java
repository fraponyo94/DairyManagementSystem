package team.project.dairymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import team.project.dairymanagementsystem.model.Supplier;
import team.project.dairymanagementsystem.model.checkLoginStatus.CheckLoginStatus;
import team.project.dairymanagementsystem.service.SupplierService;
import team.project.dairymanagementsystem.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.security.Principal;

@Controller
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private UserService userService;

    @GetMapping("/getCv/{id}")
    public ResponseEntity<byte[]> getCv(@PathVariable(name = "id") int id) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String home = System.getProperty("user.home");  //get home directory
        Supplier supplier = this.supplierService.getSupplier(id);
        String fileName = supplier.getName().trim() + "-ContractCV.pdf";

        headers.add("content-disposition", "inline;filename=" + fileName);
        headers.setCacheControl("must-revalidate,post-check=0,pre-check=0");
        byte[] attachment = supplier.getAttachment();

        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(attachment, headers, HttpStatus.OK);
        return response;
    }

    @GetMapping("/applicant-details")
    public ModelAndView getApplicantDetails(Principal principal, ModelAndView modelAndView, HttpServletRequest request) {
        String username;
        if (principal != null) {
            username = principal.getName();
            int id = userService.findByUsername(username).getNationalId();
            Supplier suppler = supplierService.getSupplier(id);
            modelAndView.addObject("supplier", suppler);
            CheckLoginStatus.checkStatus(modelAndView, request);
            modelAndView.setViewName("Supplier");
        }else{
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }

}