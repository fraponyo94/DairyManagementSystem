package team.project.dairymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import team.project.dairymanagementsystem.model.Supplier;
import team.project.dairymanagementsystem.service.SupplierService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/getCv/{id}")
    public String getCv(@PathVariable(name = "id") int id) throws IOException {
        String home = System.getProperty("user.home");  //get home directory
        Supplier supplier = this.supplierService.getSupplier(id);
        byte[] picBytes = supplier.getPic();
        try{
            Path path = Paths.get(home + "/Downloads/" + supplier.getName().trim() + "-ContractCV.pdf");
            Files.write(path, picBytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/contract/contracts";
    }
}