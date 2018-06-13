package team.project.dairymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.project.dairymanagementsystem.model.Contract;
import team.project.dairymanagementsystem.service.ContractService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/contract")
public class ContractController {
    private ContractService contractService;
    @Autowired
    public ContractController(ContractService contractService){
        this.contractService = contractService;
    }
    @PostMapping("/contract")
    public Contract createContract(@Valid @RequestBody Contract contract){
         return this.contractService.createContract(contract);
    }
    @GetMapping("/contracts")
    public String getAllContracts(Model model){
        List<Contract> contracts = this.contractService.getAllContracts();
        model.addAttribute("contracts", contracts);
        return "contracts";
    }
    @DeleteMapping("/contractors/{id}")
    public ResponseEntity deleteContract(@PathVariable(value = "id") int contractId){
        return this.contractService.deleteContract(contractId);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateContract(@PathVariable(value = "id") int contractId, @Valid @RequestBody Contract contract){
        return this.contractService.updateContract(contractId, contract);
    }


}
