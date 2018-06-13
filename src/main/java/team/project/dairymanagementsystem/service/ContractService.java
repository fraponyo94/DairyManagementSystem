package team.project.dairymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import team.project.dairymanagementsystem.model.Contract;
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

    public Contract deleteContract(Integer contractId) {
        Contract contract = new Contract();
        Optional<Contract> optional = contractRepository.findById(contractId);
        if (optional.isPresent()) {
            Contract contract1 = optional.get();
            contractRepository.delete(contract1);
            return contract1;
        }
        return contract;

    }

    public Contract updateContract(Integer id, Contract contract){
        Contract contract1 = new Contract();
        Optional<Contract> optional = contractRepository.findById(id);
        if (optional.isPresent()){
            Contract contracts = optional.get();
            contracts.setSupplierId(contract.getSupplierId());
            contracts.setAmountPerDay(contract.getAmountPerDay());
            contracts.setCostPerLitre(contract.getCostPerLitre());
            contracts.setStatus(contract.getStatus());
            return contracts;
        }
        return contract1;
    }
}
