package team.project.dairymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.project.dairymanagementsystem.model.Contract;
import team.project.dairymanagementsystem.model.Supplier;
import team.project.dairymanagementsystem.model.TenderInfo;
import team.project.dairymanagementsystem.model.enumerated.Status;
import team.project.dairymanagementsystem.service.ContractService;
import team.project.dairymanagementsystem.service.SupplierService;
import team.project.dairymanagementsystem.service.TenderInfoService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a controller class that handles contract management
 * It links to classes that concern with contract management when necessary
 * It handles redirection to various templates that relate to contracts
 * It is also concerned with storing required data to models before redirection
 */

@Controller
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;
    @Autowired
    private TenderInfoService tenderInfoService;
    @Autowired
    private SupplierService supplierService;
    private String message; //holds any necessary notification message
    private String statusDisplay;  //determines the contracts to show according to their status
    //constant to identify success messages
    private String SUCCESS = "SUCCESS: ";
    //constant to identify error messages
    private String ERROR = "ERROR: ";

    /**
     * This method allows redirection to the contract application page
     *
     * @param model - Spring model interface
     * @return - the contract application template
     */
    @GetMapping("/contract")
    public String addContract(Model model, HttpServletRequest request) {
        model.addAttribute("supplier", new Supplier()); //add a supplier object to the template to store supplier details
        model.addAttribute("error", ""); //no error message
        //check whether user is logged in to determine whether to show a logout or login button
        if (request.getSession().getAttribute("loggedIn") != null) {
            model.addAttribute("loggedIn", true);
            //check if the user is an admin
            if (request.getSession().getAttribute("admin") != null) {
                model.addAttribute("admin", true);
            }
        } else {
            model.addAttribute("loggedIn", false);
        }
        return "contract/ContractForm";
    }

    /**
     * Creates a new supplier who has not been approved yet
     *
     * @param supplier -  a class containing the supplier information
     * @param file     - a file that the supplier opted to upload that contains more information
     *                 about him
     * @param model    - Spring's model interface
     * @return - home page template
     */
    @PostMapping("/newcontract")
    public String addContract(@ModelAttribute(name = "supplier") Supplier supplier, MultipartFile file, Model model) {
        supplier.getContract().setStatus(Status.PENDING.toString()); //set the status to pending because it is a new contract
        supplier.getContract().setSupplierId(supplier.getNationalId());
        System.out.println(supplier.getNationalId());
        try {
            //get uploaded files in bytes
            byte[] bytes = file.getBytes();
            //set bytes to corresponding supplier attributes
            supplier.setAttachment(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.supplierService.createSupplier(supplier) == null) {
            return "redirect:/";
        } else {
            //new applicant
            DefaultController.message = SUCCESS + "Application sent successfully";
            return "redirect:/";
        }
    }

    /**
     * Redirects the admin to a page where he can view all contracts and manage them
     *
     * @param model - Spring's model interface
     * @return - the contracts template that shows all contracts
     */
    @GetMapping("/contracts")
    public String getAllContracts(Model model) {
        statusDisplay = null; //set field to null so that all contracts are displayed
        setModelAttributes(model, "");
        return "contract/contracts";
    }

    /**
     * Filters out all contracts except those whose status is PENDING
     *
     * @param model - Spring's model interface
     * @return - the contracts template to show all pending contracts
     */
    @GetMapping("/pending")
    public String getPendingContracts(Model model) {
        statusDisplay = Status.PENDING.toString(); //show only pending contracts
        setModelAttributes(model, "");
        return "contract/contracts";
    }

    /**
     * Filters out all contracts except those whose status is DENIED
     *
     * @param model - Spring's model interface
     * @return - the contracts template to show all denied contracts
     */
    @GetMapping("/denied")
    public String getDeniedContracts(Model model) {
        statusDisplay = Status.DENIED.toString(); //show only denied contracts
        setModelAttributes(model, "");
        return "contract/contracts";
    }

    /**
     * Filters out all contracts except those whose status is CANCELLED
     *
     * @param model - Spring's model interface
     * @return - the contracts template to show all cancelled contracts
     */
    @GetMapping("/cancelled")
    public String getCancelledContracts(Model model) {
        statusDisplay = Status.CANCELLED.toString(); //show only cancelled contracts
        setModelAttributes(model, "");
        return "contract/contracts";
    }

    /**
     * Filters out all contracts except those whose status is APPROVED
     *
     * @param model - Spring's model interface
     * @return - the contracts template to show all approved contracts
     */
    @GetMapping("/approved")
    public String getApprovedContracts(Model model) {
        statusDisplay = Status.APPROVED.toString(); //show only approved contracts
        setModelAttributes(model, "");
        return "contract/contracts";
    }

    /**
     * Approve a contract
     *
     * @param id    - the contract's id
     * @param model - Spring's model interface
     * @return - contracts template depending on whether the status changed
     */
    @PostMapping("/approve/{id}")
    public String approveContract(@PathVariable(name = "id") int id, Model model) {
        message = this.contractService.approveContract(id);
        setModelAttributes(model, message);
        return checkStatusChange(message);
    }

    /**
     * Deny a contract
     *
     * @param id    - the contract's id
     * @param model - Spring's model interface
     * @return - contracts template depending on whether the status changed
     */
    @PostMapping("/deny/{id}")
    public String denyContract(@PathVariable(name = "id") int id, Model model) {
        message = this.contractService.denyContract(id);
        setModelAttributes(model, message);
        return checkStatusChange(message);
    }

    /**
     * Cancel a contract
     *
     * @param id    - the contract's id
     * @param model - Spring's model interface
     * @return - contracts template depending on whether the status changed
     */
    @PostMapping("/cancel/{id}")
    public String cancelContract(@PathVariable(name = "id") int id, Model model) {
        message = this.contractService.cancelContract(id);
        setModelAttributes(model, message);
        return checkStatusChange(message);
    }

    /**
     * Delete a contract
     *
     * @param id    - the contract's id
     * @param model - Spring's model interface
     * @return - redirection to contracts template
     */
    @PostMapping("/delete/{id}")
    public String deleteContract(@PathVariable(name = "id") int id, Model model) {
        message = this.contractService.deleteContract(id);
        setModelAttributes(model, message);
        return "redirect:/contract/contracts";
    }

    /**
     * Gets information about a specific supplier
     *
     * @param id    - national id of supplier to find
     * @param model - Spring's model interface
     * @return - template that shows a supplier's information
     */
    @GetMapping("/view-supplier/{id}")
    public String viewSupplier(@PathVariable(name = "id") int id, Model model) {
        Supplier supplier = this.supplierService.getSupplier(id);
        model.addAttribute("supplier", supplier);
        return "viewSupplier";
    }

    /**
     * Adds necessary messages to the model attribute
     *
     * @param model   - Spring's model interface
     * @param message - message to store in the model
     */
    private void setModelAttributes(Model model, String message) {
        model.addAttribute("message", message);
        getByStatus(model, statusDisplay);
    }

    /**
     * Decide on whether to get new content or retain previous content
     * depending on whether a contract's status changed
     *
     * @param message - indicates whether the status changed
     * @return - contract's template
     */
    private String checkStatusChange(String message) {
        if (message.equals("SAME_STATUS")) {
            return "redirect:/contract/contracts"; //get new content because no message should appear
        } else {
            //retain previous content so that any informative message regarding the
            //status change is retained
            return "contract/contracts";
        }
    }

    /**
     * Gets a list of contracts and suppliers depending on the required status
     * and stores them in the model
     *
     * @param model  - Spring's model interface
     * @param status - status required for the contracts to be displayed
     */
    private void getByStatus(Model model, String status) {
        List<Contract> contracts;
        List<Supplier> suppliers;
        if (status == null) {
            contracts = this.contractService.getAllContracts();
            suppliers = this.supplierService.getAllSuppliers();
        } else {
            contracts = this.contractService.getContractsWithStatus(status);
            suppliers = this.supplierService.getByStatus(status);
        }
        model.addAttribute("contracts", contracts);
        model.addAttribute("suppliers", suppliers);
        //get total cost and amount of milk supplied by approved contracts
        Map<String, Integer> map = getTotalCostAndAmountOfMilk();
        int totalCost = map.get("cost");
        int totalAmount = map.get("amount");
        model.addAttribute("totalCost", totalCost);
        model.addAttribute("totalAmount", totalAmount);
    }

    /**
     * Gets daily total cost and amount of milk
     *
     * @return - a map data structure to store total cost and amount of milk
     * expected to be supplied by approved contracts
     */
    private Map<String, Integer> getTotalCostAndAmountOfMilk() {
        TenderInfo tenderInfo = tenderInfoService.findLatestTender();
        int totalCost = 0;
        int totalAmount = 0;
        if (tenderInfo != null) {
            totalCost = tenderInfo.getTotalCost();
            totalAmount = tenderInfo.getMilkAmount();
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("cost", totalCost);
        map.put("amount", totalAmount);
        return map;
    }
}
