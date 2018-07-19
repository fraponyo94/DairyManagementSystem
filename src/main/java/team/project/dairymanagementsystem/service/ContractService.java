package team.project.dairymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.project.dairymanagementsystem.model.ContractApplication;
import team.project.dairymanagementsystem.model.Supplier;
import team.project.dairymanagementsystem.model.enumerated.Status;
import team.project.dairymanagementsystem.repository.ContractRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContractService {

    ArrayList<ContractApplication> contract = new ArrayList<>();
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private EmailService emailService;
//    constant to identify success messages
    private String SUCCESS = "SUCCESS: ";
//    constant to identify error messages
    private String ERROR = "ERROR: ";
    private String SAME_STATUS = "SAME_STATUS";

    public ContractApplication createContract(ContractApplication contractApplication) {
        return contractRepository.save(contractApplication);
    }

    public List<ContractApplication> getAllContracts() {
        return contractRepository.findAll();
    }

    //approve a contract
    public String approveContract(int id) {
        //check if contract is present
        ContractApplication savedContract = checkContract(id);

        if (savedContract != null) {
            if(!isSameStatus(savedContract, Status.APPROVED.toString())){
                changeStatus(id, Status.APPROVED.toString());

                //get supplier id
                int supplierId = savedContract.getSupplierId();
                //get supplier
                Supplier supplier = supplierService.getSupplier(supplierId);
                //get supplier email
                String supplierEmail = supplier.getEmail_address();
                Map<String, Object> variable = new HashMap<>();
                variable.put("supplier", supplier);
                String message = emailService.sendEmail(
                        "mozdemilly@gmail.com", supplierEmail, "ContractApplication Application Approval", variable, "emailtrial");
                if (message.equalsIgnoreCase(SUCCESS)) {
                    return message+" ContractApplication approved successfully";
                }else{
                    return message+"Failed to notify supplier by email. Kindly notify him by phone";
                }
            }else{
                return SAME_STATUS;
            }
        }
        return ERROR + "ContractApplication does not exist";
    }

    //deny a contract
    public String denyContract(int id) {
        //check if contract is present
        ContractApplication savedContract = checkContract(id);
        if (savedContract != null) {
            if(!isSameStatus(savedContract, Status.DENIED.toString())){
                changeStatus(id, Status.DENIED.toString());
                return SUCCESS + "ContractApplication denied successfully";
            }else{
                return SAME_STATUS;
            }
        }
        return ERROR + "ContractApplication does not exist";
    }

    public String cancelContract(int id) {
        //check if contract is present
        ContractApplication savedContract = checkContract(id);
        if (savedContract != null) {
            if(!isSameStatus(savedContract, Status.CANCELLED.toString())){
                changeStatus(id, Status.CANCELLED.toString());
                return SUCCESS + "ContractApplication cancelled successfully";
            }else{
                return SAME_STATUS;
            }
        }
        return ERROR + "ContractApplication does not exist";
    }

    public String deleteContract(int id) {
        //get saved contract
        ContractApplication savedContract = checkContract(id);

        if (savedContract != null) {
            //get supplierId
            int supplierId = savedContract.getSupplierId();
            //delete this contract
            supplierService.deleteSupplier(supplierId);
            return SUCCESS + "ContractApplication deleted successfully";
        }
        return ERROR + "ContractApplication does not exist";
    }

    private ContractApplication checkContract(int id) {
        //check if contract is present
        //return saved contract from the database
        return contractRepository.findOne(id);
    }

    private void changeStatus(int id, String status) {
        ContractApplication savedContract = checkContract(id);
        if (savedContract != null) {
            savedContract.setStatus(status);
            //save the contract back to the database
            contractRepository.save(savedContract);
        }
    }

    private boolean isSameStatus(ContractApplication contractApplication, String status){
        if(status.equalsIgnoreCase(contractApplication.getStatus())){
            return true;
        }else{
            return false;
        }
    }

}
