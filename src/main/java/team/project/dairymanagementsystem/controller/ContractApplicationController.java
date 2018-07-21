
package team.project.dairymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.project.dairymanagementsystem.model.ContractApplication;
import team.project.dairymanagementsystem.model.Supplier;
import team.project.dairymanagementsystem.model.enumerated.Status;

import team.project.dairymanagementsystem.service.SupplierService;

import java.util.List;


@Controller
@RequestMapping("/contract")
public class ContractApplicationController {

   // @Autowired
   // private ContractService contractService;

    @Autowired
    private SupplierService supplierService;
    private String message;
    private boolean managed_contract; //determines if the admin has just managed a contract


    @GetMapping("/contract")
    public String addContract(Model model){
        model.addAttribute("supplier",new Supplier());
        model.addAttribute("error", "");
        return "contract/ContractForm";
    }


//    @PostMapping("/newcontract")
//    public String addContract(@ModelAttribute(name = "supplier") Supplier supplier, MultipartFile file, Model model){
//        supplier.getContractApplication().setStatus(Status.PENDING.toString());
//        supplier.getContractApplication().setSupplierId(supplier.getNationalId());
//        System.out.println(supplier.getNationalId());
//        try{
//            //get uploaded files in bytes
//            byte[] bytes = file.getBytes();
//            //set bytes to corresponding supplier attributes
//            supplier.setPic(bytes);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        if(this.supplierService.createSupplier(supplier)==null){
//            model.addAttribute("error", "National Id exists!");
//            return "contract/ContractForm";
//        }else {
//            return "redirect:/";
//        }
//    }
//
//    @GetMapping("/contracts")
//    public String getAllContracts(Model model){
//        //add an attribute to determine whether you came from this controller
////        model.addAttribute("managed_contract",managed_contract);
//        setModelAttributes(model, "");
//        return "contract/contracts";
//    }

//    @PostMapping("/approve/{id}")
//    public String approveContract(@PathVariable(name = "id") int id, Model model) {
////        managed_contract = true;
//        message = this.contractService.approveContract(id);
//        setModelAttributes(model,message);
//        return checkStatusChange(message);
//    }
//
//    @PostMapping("/deny/{id}")
//    public String denyContract(@PathVariable(name = "id") int id, Model model) {
////        managed_contract = true;
//        message = this.contractService.denyContract(id);
//        setModelAttributes(model, message);
//        return checkStatusChange(message);
//    }
//
//    @PostMapping("/cancel/{id}")
//    public String cancelContract(@PathVariable(name = "id") int id, Model model) {
////        managed_contract = true;
//        message = this.contractService.cancelContract(id);
//        setModelAttributes(model, message);
//        return checkStatusChange(message);
//    }

//    @PostMapping("/delete/{id}")
//    public String deleteContract(@PathVariable(name = "id") int id, Model model) {
////        managed_contract = true;
//        message = this.contractService.deleteContract(id);
//        setModelAttributes(model, message);
//        return "redirect:/contract/contracts";
//    }
//
//    @GetMapping("/view-supplier/{id}")
//    public String viewSupplier(@PathVariable(name = "id") int id, Model model) {
//        Supplier supplier = this.supplierService.getSupplier(id);
//        model.addAttribute("supplier", supplier);
//        return "viewSupplier";
//    }
//
//    private void setModelAttributes(Model model, String message){
//        List<ContractApplication> contracts = this.contractService.getAllContracts();
//        List<Supplier> suppliers = this.supplierService.getAllSuppliers();
//        model.addAttribute("contracts", contracts);
//        model.addAttribute("suppliers", suppliers);
//        model.addAttribute("message", message);
//    }

    private String checkStatusChange(String message){
        if(message.equals("SAME_STATUS")){
            return "redirect:/contract/contracts";
        }else{
            return "contract/contracts";
        }
    }
}

