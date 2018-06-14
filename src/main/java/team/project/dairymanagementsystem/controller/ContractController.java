package team.project.dairymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import team.project.dairymanagementsystem.model.Contract;
import team.project.dairymanagementsystem.model.Supplier;
import team.project.dairymanagementsystem.model.enumerated.Status;
import team.project.dairymanagementsystem.service.ContractService;
import team.project.dairymanagementsystem.service.SupplierService;

import java.util.List;


@Controller
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @Autowired
    private SupplierService supplierService;


    @GetMapping("/addcontract")
    public ModelAndView addContract(ModelAndView modelAndView){
        modelAndView.addObject("supplier",new Supplier());
          modelAndView.setViewName("applycontract");
        return modelAndView;
    }

    @PostMapping("/newcontract")
    public String addContract(@ModelAttribute(name = "supplier") Supplier supplier){
        int nationalId = supplier.getNational_id();
        Contract contract = supplier.getContract();
        contract.setStatus(Status.PENDING.toString());
        contract.setSupplierId(nationalId);
        this.supplierService.createSupplier(supplier);
        return "welcome";
    }

    @GetMapping("/contracts")
    public String getAllContracts(Model model){
        List<Contract> contracts = this.contractService.getAllContracts();
        model.addAttribute("contract", contracts);
        return "contract";
    }


}
