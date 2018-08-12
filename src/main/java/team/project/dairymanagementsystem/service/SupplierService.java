package team.project.dairymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.project.dairymanagementsystem.model.Supplier;
import team.project.dairymanagementsystem.model.TenderInfo;
import team.project.dairymanagementsystem.repository.SupplierRepository;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private TenderInfoService tenderInfoService;

    public Supplier createSupplier(Supplier supplier){
        System.out.println(supplier.getNationalId());
        Supplier object = supplierRepository.findByNationalId(supplier.getNationalId());
        if (object!=null) {
           return null;
        }else {
            long tenderInfoId = getLatestTenderInfoId();
            supplier.getContract().setTenderInfoId(tenderInfoId);
            return this.supplierRepository.save(supplier);
        }
    }

    public Supplier getSupplier(Integer id){
        Supplier supplier = this.supplierRepository.findByNationalId(id);
        return supplier;
    }

    public List<Supplier> getAllSuppliers(){
        return this.supplierRepository.findAllByContractTenderInfoId(getLatestTenderInfoId());
    }

    public List<Supplier> getByStatus(String status){
        return this.supplierRepository.findByContractStatusAndContractTenderInfoId(status, getLatestTenderInfoId());
    }

    public void deleteSupplier(int supplierId) {
        supplierRepository.delete(supplierId);
    }

    private long getLatestTenderInfoId() {
        TenderInfo tenderInfo = tenderInfoService.findLatestTender();
        if (tenderInfo != null) {
            return tenderInfo.getId();
        }else{
            return -1;
        }
    }
}
