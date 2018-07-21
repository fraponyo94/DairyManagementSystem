package team.project.dairymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.project.dairymanagementsystem.model.ContractApplicantAccount;

@Repository
public interface ContractApplicantAccountRepository extends JpaRepository<ContractApplicantAccount,String> {
    ContractApplicantAccount findByUsername(String username);
    ContractApplicantAccount findByEmail(String email);
}
