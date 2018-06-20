package team.project.dairymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.project.dairymanagementsystem.model.Contract;
import team.project.dairymanagementsystem.service.ContractService;

import javax.validation.Valid;
import java.util.List;


@Controller

public class ContractController {
    private ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @RequestMapping(value = "/showform", method = RequestMethod.GET)
    public String showForm(Model model) {
        Contract contract = new Contract();
        model.addAttribute("newcontract", contract);
        return "newcontract";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String ShowWelcome() {
        return "welcome";
    }


    @PostMapping("/contract")
    public Contract createContract(@Valid @RequestBody Contract contract) {
        return this.contractService.createContract(contract);
    }

    @GetMapping("/contracts")
    public String getAllContracts(Model model) {
        List<Contract> contracts = this.contractService.getAllContracts();
        model.addAttribute("contracts", contracts);
        return "contracts";
    }

    @PostMapping("/approve/{id}")
    public String approveContract(@PathVariable(name = "id") int id) {
        String msg = this.contractService.approveContract(id);
        return "redirect:/contract/contracts";
    }

    @PostMapping("/deny/{id}")
    public String denyContract(@PathVariable(name = "id") int id) {
        String msg = this.contractService.denyContract(id);
        return "redirect:/contract/contracts";
    }

    @PostMapping("/cancel/{id}")
    public String cancelContract(@PathVariable(name = "id") int id) {
        String msg = this.contractService.cancelContract(id);
        return "redirect:/contract/contracts";
    }

    @PostMapping("/delete/{id}")
    public String deleteContract(@PathVariable(name = "id") int id) {
        String msg = this.contractService.deleteContract(id);
        return "redirect:/contract/contracts";
    }
}
