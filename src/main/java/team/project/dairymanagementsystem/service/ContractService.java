package team.project.dairymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import team.project.dairymanagementsystem.model.Contract;
import team.project.dairymanagementsystem.model.enums.Status;
import team.project.dairymanagementsystem.repository.ContractRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ContractService {
    ArrayList<Contract> contract = new ArrayList<>();
    @Autowired
    private ContractRepository contractRepository;

    public Contract createContract(Contract contract) {
        return contractRepository.save(contract);
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public void approveContract(int id) {
        changeStatus(id, Status.APPROVED.toString());
    }

    //method to change status of contract
    private void changeStatus(int id, String status){
        //get  this contract from the database
        Optional<Contract> optional = contractRepository.findById(id);
        //check if contract is available
        if(optional.isPresent()){
            //get contract
            Contract contract = optional.get();
            //change status of contract
            contract.setStatus(status);
            //save contract back to database
            contractRepository.save(contract);
        }
    }
}
