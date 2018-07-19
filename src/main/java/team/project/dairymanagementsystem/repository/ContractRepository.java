package team.project.dairymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.project.dairymanagementsystem.model.ContractApplication;

@Repository
public interface ContractRepository extends JpaRepository<ContractApplication, Integer>{
}
