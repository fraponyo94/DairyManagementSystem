package team.project.dairymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.project.dairymanagementsystem.model.Contract;
import team.project.dairymanagementsystem.model.Supplier;
import team.project.dairymanagementsystem.model.enumerated.Status;
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

    //approve a contract
    public String approveContract(int id) {
        //check if contract is present
        Contract savedContract = checkContract(id);
        if (savedContract != null) {
            changeStatus(id, Status.APPROVED.toString());
            return "Contract approved successfully";
        }
        return "ApproveA operation failed";
    }

    //deny a contract
    public String denyContract(int id) {
        //check if contract is present
        Contract savedContract = checkContract(id);
        if (savedContract != null) {
            changeStatus(id, Status.DENIED.toString());
            return "Contract denied successfully";
        }
        return "Denied operation failed";
    }

    public String cancelContract(int id) {
        //check if contract is present
        Contract savedContract = checkContract(id);
        if (savedContract != null) {
            changeStatus(id, Status.CANCELLED.toString());
            return "Contract cancelled successfully";
        }
        return "Cancel operation failed";
    }

    public String deleteContract(int id) {
        //get saved contract
        Contract savedContract = checkContract(id);
        if (savedContract != null) {
            //delete this contract
            contractRepository.delete(savedContract);
            return savedContract.getSupplierId() + " 's contract deleted successfully";
        }
        // TODO: 20-Jun-18 Throw deletion exception
        return "delete unsuccessful";
    }

    private Contract checkContract(int id){
        //check if contract is present
        //return saved contract from the database
        return contractRepository.findById(id);
    }

    private Contract changeStatus(int id, String status){
        Contract savedContract = checkContract(id);
        if (savedContract != null) {
            savedContract.setStatus(status);
            //save the contract back to the database
            return contractRepository.save(savedContract);
        }
        return null;
    }

}
