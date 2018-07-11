package team.project.dairymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.project.dairymanagementsystem.model.Contract;

import team.project.dairymanagementsystem.repository.ContractRepository;

import java.util.ArrayList;
import java.util.List;



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


}
