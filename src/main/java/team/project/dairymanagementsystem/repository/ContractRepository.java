package team.project.dairymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.project.dairymanagementsystem.model.Contract;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer>{
    List<Contract> findByStatus(String status);
}
