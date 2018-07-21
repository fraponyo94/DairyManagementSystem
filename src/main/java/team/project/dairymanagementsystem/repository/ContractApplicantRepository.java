package team.project.dairymanagementsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.project.dairymanagementsystem.model.ContractApplicant;

@Repository
public interface ContractApplicantRepository extends JpaRepository<ContractApplicant,Integer> {
}
