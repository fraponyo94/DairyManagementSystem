package team.project.dairymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.project.dairymanagementsystem.model.ContractApplicantAccount;
import team.project.dairymanagementsystem.repository.ContractApplicantAccountRepository;

@Service
public class ContractApplicantAccountService {

    @Autowired
    private ContractApplicantAccountRepository contractApplicantAccountRepository;

    /*Find account given username*/
    public ContractApplicantAccount findByUsername(String username){
        return contractApplicantAccountRepository.findByUsername(username);
    }

    /*Find account given email*/
    public ContractApplicantAccount findByEmail(String email){
        return contractApplicantAccountRepository.findByEmail(email);
    }

    /*Save Account created*/
    public void saveAccount(ContractApplicantAccount contractApplicantAccount){
        contractApplicantAccountRepository.save(contractApplicantAccount);
    }

}
