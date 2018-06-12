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

    public ResponseEntity<?> deleteContract(Integer contractId) {
        Optional<Contract> optional = contractRepository.findById(contractId);
        if (optional.isPresent()) {
            Contract contract = optional.get();
            contractRepository.delete(contract);
            return ResponseEntity.ok(contract);
        }
        return ResponseEntity.notFound().build();

    }

    public ResponseEntity<?> updateContract(Integer id, Contract contract){
        Optional<Contract> optional = contractRepository.findById(id);
        if (optional.isPresent()){
            Contract contract1 = optional.get();
            contract1.setSupplierId(contract.getSupplierId());
            contract1.setAmountPerDay(contract.getAmountPerDay());
            contract1.setCostPerLitre(contract.getCostPerLitre());
            contract1.setStatus(contract.getStatus());
            return ResponseEntity.ok(contract1);
        }
        return ResponseEntity.notFound().build();
    }
}
