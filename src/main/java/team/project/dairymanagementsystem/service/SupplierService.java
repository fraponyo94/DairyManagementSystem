package team.project.dairymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.project.dairymanagementsystem.controller.DefaultController;
import team.project.dairymanagementsystem.model.Contract;
import team.project.dairymanagementsystem.model.Supplier;
import team.project.dairymanagementsystem.model.TenderInfo;
import team.project.dairymanagementsystem.repository.ContractRepository;
import team.project.dairymanagementsystem.repository.SupplierRepository;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private TenderInfoService tenderInfoService;
    //constant to identify success messages
    private String SUCCESS = "SUCCESS: ";
    //constant to identify error messages
    private String ERROR = "ERROR: ";

    public Supplier createSupplier(Supplier supplier) {
        Supplier object = getSupplier(supplier.getNationalId());
        Contract contract = checkTenderApplication(supplier);
        if (object != null) {
            if (contract == null) { //no application made for current tender
                supplierRepository.delete(object.getNationalId());
                supplier.getContract().setTenderInfoId(tenderInfoService.findLatestTender());
                supplierRepository.save(supplier); //update supplier contract with new contract
                DefaultController.message = SUCCESS + "Application sent successfully";
            } else {
                DefaultController.message = ERROR + "You already made an application";
            }
            return null;
        } else {
            supplier.getContract().setTenderInfoId(tenderInfoService.findLatestTender());
            return this.supplierRepository.save(supplier);
        }
    }

    public Supplier getSupplier(Integer id) {
        Supplier supplier = this.supplierRepository.findByNationalId(id);
        return supplier;
    }

    //check if a potential supplier has already applied for the latest tender
    //this prevents him from submitting 2 applications for the same tender
    public Contract checkTenderApplication(Supplier supplier) {
        long tenderId = tenderInfoService.findLatestTender().getId(); //get the id of the latest tender
        int nationalId = supplier.getNationalId();
        //return true if he/she has already made an application for the current tender
        return contractRepository.findBySupplierIdAndTenderInfoId(nationalId, tenderInfoService.findLatestTender());
    }

    public List<Supplier> getAllSuppliers() {
        return this.supplierRepository.findAllByContractTenderInfoId(tenderInfoService.findLatestTender());
    }

    public List<Supplier> getByStatus(String status) {
        return this.supplierRepository.findByContractStatusAndContractTenderInfoId(status, tenderInfoService.findLatestTender());
    }

    public void deleteSupplier(int supplierId) {
        supplierRepository.delete(supplierId);
    }

    private long getLatestTenderInfoId() {
        TenderInfo tenderInfo = tenderInfoService.findLatestTender();
        if (tenderInfo != null) {
            return tenderInfo.getId();
        } else {
            return -1;
        }
    }
}
