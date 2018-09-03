package team.project.dairymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.project.dairymanagementsystem.model.Supplier;
import team.project.dairymanagementsystem.model.TenderInfo;
import team.project.dairymanagementsystem.service.TenderInfoService;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    Supplier findByNationalId(int nationalId);
    List<Supplier> findByContractStatusAndContractTenderInfoId(String status, TenderInfo tenderInfo);
    List<Supplier> findAllByContractTenderInfoId(TenderInfo tenderInfo);
}
