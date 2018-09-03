package team.project.dairymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.project.dairymanagementsystem.model.Contract;
import team.project.dairymanagementsystem.model.Supplier;
import team.project.dairymanagementsystem.model.TenderInfo;
import team.project.dairymanagementsystem.model.enumerated.Status;
import team.project.dairymanagementsystem.repository.ContractRepository;
import team.project.dairymanagementsystem.repository.TenderInfoRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContractService {

    ArrayList<Contract> contract = new ArrayList<>();
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private TenderInfoRepository tenderInfoRepository;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private TenderInfoService tenderInfoService;
    //    constant to identify success messages
    private String SUCCESS = "SUCCESS: ";
    //    constant to identify error messages
    private String ERROR = "ERROR: ";
    private String SAME_STATUS = "SAME_STATUS";


    private long getLatestTenderInfoId() {
        TenderInfo tenderInfo = tenderInfoService.findLatestTender();
        if (tenderInfo != null) {
            return tenderInfo.getId();
        }else{
            return -1;
        }
    }

    public Contract createContract(Contract contract) {
        contract.setTenderInfoId(tenderInfoService.findLatestTender());
        return contractRepository.save(contract);
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAllByTenderInfoId(tenderInfoService.findLatestTender());
    }

    public List<Contract> getContractsWithStatus(String status) {
        return contractRepository.findByStatusAndTenderInfoId(status, tenderInfoService.findLatestTender());
    }

    //approve a contract
    public String approveContract(int id) {
        //check if contract is present
        Contract savedContract = checkContract(id);

        if (savedContract != null) {
            if (!isSameStatus(savedContract, Status.APPROVED.toString())) {
                changeStatus(id, Status.APPROVED.toString());

                //get current total amount of milk and cost for all approved contracts
                TenderInfo tenderInfo = tenderInfoService.findLatestTender();
                int milkAmount = tenderInfo.getMilkAmount();
                int totalCost = tenderInfo.getTotalCost();

                //add this contract's milk amount and cost to the total amount and cost respectively
                milkAmount += savedContract.getAmountPerDay();
                totalCost += savedContract.getCostPerLitre();
                tenderInfo.setMilkAmount(milkAmount);
                tenderInfo.setTotalCost(totalCost);

                //save this information back to the database
                System.out.println("NEW_TENDER: " + tenderInfo);
                tenderInfoRepository.save(tenderInfo);

                //get supplier id
                int supplierId = savedContract.getSupplierId();
                //get supplier
                Supplier supplier = supplierService.getSupplier(supplierId);
                //get supplier email
                String supplierEmail = supplier.getEmail_address();
                Map<String, Object> variable = new HashMap<>();
                variable.put("supplier", supplier);
                String message = emailService.sendEmail(
                        "mozdemilly@gmail.com", supplierEmail, "Contract Application Approval", variable, "email-approval");
                if (message.equalsIgnoreCase(SUCCESS)) {
                    return message + " Contract approved successfully";
                } else {
                    return message + "Failed to notify supplier by email. Kindly notify him by phone";
                }
            } else {
                return SAME_STATUS;
            }
        }
        return ERROR + "Contract does not exist";
    }

    //deny a contract
    public String denyContract(int id) {
        //check if contract is present
        Contract savedContract = checkContract(id);
        if (savedContract != null) {
            if (!isSameStatus(savedContract, Status.DENIED.toString())) {
                changeStatus(id, Status.DENIED.toString());
                //get supplier id
                int supplierId = savedContract.getSupplierId();
                //get supplier
                Supplier supplier = supplierService.getSupplier(supplierId);
                //get supplier email
                String supplierEmail = supplier.getEmail_address();
                Map<String, Object> variable = new HashMap<>();
                variable.put("supplier", supplier);
                String message = emailService.sendEmail(
                        "mozdemilly@gmail.com", supplierEmail, "Contract Denial", variable, "email-denial");
                return SUCCESS + "Contract denied successfully";
            } else {
                return SAME_STATUS;
            }
        }
        return ERROR + "Contract does not exist";
    }

    //cancel an approved contract
    public String cancelContract(int id) {
        //check if contract is present
        Contract savedContract = checkContract(id);
        if (savedContract != null) {
            if (!isSameStatus(savedContract, Status.CANCELLED.toString())) {
                changeStatus(id, Status.CANCELLED.toString());

                //get supplier id
                int supplierId = savedContract.getSupplierId();
                //get supplier
                Supplier supplier = supplierService.getSupplier(supplierId);
                //get supplier email
                String supplierEmail = supplier.getEmail_address();
                Map<String, Object> variable = new HashMap<>();
                variable.put("supplier", supplier);
                String message = emailService.sendEmail(
                        "mozdemilly@gmail.com", supplierEmail, "Contract Cancellation", variable, "email-cancelled");

                //get current total amount of milk and cost for all approved contracts
                TenderInfo tenderInfo = tenderInfoService.findLatestTender();
                int milkAmount = tenderInfo.getMilkAmount();
                int totalCost = tenderInfo.getTotalCost();

                //subtract this contract's milk amount and cost from total milk and cost supplied by approved suppliers
                milkAmount -= savedContract.getAmountPerDay();
                totalCost -= savedContract.getCostPerLitre();
                tenderInfo.setMilkAmount(milkAmount);
                tenderInfo.setTotalCost(totalCost);

                //save the tender information back to the database
                tenderInfoRepository.save(tenderInfo);

                return SUCCESS + "Contract cancelled successfully";
            } else {
                return SAME_STATUS;
            }
        }
        return ERROR + "Contract does not exist";
    }

    public String deleteContract(int id) {
        //get saved contract
        Contract savedContract = checkContract(id);

        if (savedContract != null) {
            //get supplierId
            int supplierId = savedContract.getSupplierId();
            //delete this contract
            supplierService.deleteSupplier(supplierId);
            return SUCCESS + "Contract deleted successfully";
        }
        return ERROR + "Contract does not exist";
    }

    private Contract checkContract(int id) {
        //check if contract is present
        //return saved contract from the database
        return contractRepository.findOne(id);
    }

    //changes the status of the contract of the passed id to the status passed
    private void changeStatus(int id, String status) {
        Contract savedContract = checkContract(id);
        if (savedContract != null) {
            savedContract.setStatus(status);
            //save the contract back to the database
            contractRepository.save(savedContract);
        }
    }

    //checks whether this contract passed has the status passed
    private boolean isSameStatus(Contract contract, String status) {
        if (status.equalsIgnoreCase(contract.getStatus())) {
            return true;
        } else {
            return false;
        }
    }
}
