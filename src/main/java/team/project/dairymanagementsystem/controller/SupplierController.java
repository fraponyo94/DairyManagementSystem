package team.project.dairymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import team.project.dairymanagementsystem.model.Supplier;
import team.project.dairymanagementsystem.service.SupplierService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/getCv/{id}")
    public String getCv(@PathVariable(name = "id") int id) throws IOException {
        String home = System.getProperty("user.home");  //get home directory
        Supplier supplier = this.supplierService.getSupplier(id);
        FileCopyUtils.copy(supplier.getPic(), new FileOutputStream(new File(home + "/Downloads/"+ supplier.getName().trim() + ".png")));
        return "redirect:/contract/contracts";
    }
}