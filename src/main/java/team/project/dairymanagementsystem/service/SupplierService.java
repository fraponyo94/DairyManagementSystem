package team.project.dairymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.project.dairymanagementsystem.model.Supplier;
import team.project.dairymanagementsystem.repository.SupplierRepository;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier createSupplier(Supplier supplier){
        System.out.println(supplier.getNationalId());
        Supplier object = supplierRepository.findByNationalId(supplier.getNationalId());
        if (object!=null) {
           return null;
        }else {
            return this.supplierRepository.save(supplier);
        }
    }

    public Supplier getSupplier(Integer id){
        Supplier supplier = this.supplierRepository.findByNationalId(id);
        return supplier;
    }

    public List<Supplier> getAllSuppliers(){
        return this.supplierRepository.findAll();
    }

    public List<Supplier> getByStatus(String status){
        return this.supplierRepository.findByContractStatus(status);
    }

    public void deleteSupplier(int supplierId) {
        supplierRepository.delete(supplierId);
    }
}
