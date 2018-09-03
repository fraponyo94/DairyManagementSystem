package team.project.dairymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.project.dairymanagementsystem.model.Contract;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer>{
    List<Contract> findByStatusAndTenderInfoId(String status, long id);
    List<Contract> findAllByTenderInfoId(long tenderInfoId);
    Contract findBySupplierId(int id);
    //check if a potential supplier has already applied for a particular tender
    Contract findBySupplierIdAndTenderInfoId(int nationalId, long tenderInfoId);
}
