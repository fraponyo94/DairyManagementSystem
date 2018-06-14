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
        return this.supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSuppliers(){
        return this.supplierRepository.findAll();
    }

}
