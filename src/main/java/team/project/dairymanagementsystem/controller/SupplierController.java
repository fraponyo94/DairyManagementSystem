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
import team.project.dairymanagementsystem.model.Supplier;
import team.project.dairymanagementsystem.service.SupplierService;

import java.io.*;

@Controller
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/getCv/{id}")
    public ResponseEntity<byte[]> getCv(@PathVariable(name = "id") int id) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String home = System.getProperty("user.home");  //get home directory
        Supplier supplier = this.supplierService.getSupplier(id);
        String fileName = supplier.getName().trim() + "-ContractCV.pdf";

        headers.add("content-disposition", "inline;filename=" + fileName);
        headers.setCacheControl("must-revalidate,post-check=0,pre-check=0");
        byte[] picBytes = supplier.getPic();

        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(picBytes,headers, HttpStatus.OK);
        return response;
    }
    @GetMapping("/applicant-details")
    public String getApplicantDetails(Model model){
        Supplier suppler =supplierService.getSupplier(123);
        model.addAttribute("supplier", suppler);

        return "Supplier";
    }

}