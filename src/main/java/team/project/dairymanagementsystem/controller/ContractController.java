package team.project.dairymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.project.dairymanagementsystem.model.Contract;
import team.project.dairymanagementsystem.service.ContractService;
import java.util.List;


@Controller
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;


    @GetMapping("/contracts")
    public String getAllContracts(Model model){
        List<Contract> contracts = this.contractService.getAllContracts();
        model.addAttribute("contracts", contracts);
        return "contracts";
    }


}
