package team.project.dairymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.project.dairymanagementsystem.model.Supplier;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    Supplier findByNationalId(int nationalId);
    List<Supplier> findByContractStatusAndContractTenderInfoId(String status, long tenderInfoId);
    List<Supplier> findAllByContractTenderInfoId(long tenderInfoId);
}
