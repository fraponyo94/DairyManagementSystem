package team.project.dairymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import team.project.dairymanagementsystem.component.CustomSuccessHandler;
import team.project.dairymanagementsystem.model.ContractApplicant;
import team.project.dairymanagementsystem.model.ContractApplicantAccount;
import team.project.dairymanagementsystem.model.RoleGroup;
import team.project.dairymanagementsystem.model.Supplier;
import team.project.dairymanagementsystem.service.ContractApplicantAccountService;
import team.project.dairymanagementsystem.service.RoleGroupService;
import team.project.dairymanagementsystem.service.SupplierService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.Map;

@Controller
@RequestMapping("contract")
public class ContractController {

    @Autowired
    private ContractApplicantAccountService accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CustomSuccessHandler successHandler;

    @Autowired
    private RoleGroupService roleService;

    @Autowired
    private SupplierService supplierService;

    /* Contract login*/
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView contractLogin(ModelAndView modelAndView){
        modelAndView.setViewName("contract/contract-login");
        return  modelAndView;
    }

    /* Contract application account creation --GET---*/
    @RequestMapping(value ="/create-account",method = RequestMethod.GET)
    public ModelAndView contractAccountCreation(ModelAndView modelAndView){
        modelAndView.addObject("contractApplicantAccount",new ContractApplicantAccount());
        modelAndView.setViewName("contract/contract-account");
        return modelAndView;
    }

    /*Contract application account creation ---POST---*/
    @RequestMapping(value = "/create-account",method = RequestMethod.POST)
    public void saveContractAccountCreation(@Valid  @ModelAttribute ContractApplicantAccount contractApplicantAccount, BindingResult result, ModelAndView modelAndView,
                                                    @RequestParam Map<String, String> requestParams, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if(result.hasErrors()){
            modelAndView.setViewName("contract/contract-account");
        }else{
                 ContractApplicantAccount account = accountService.findByUsername(contractApplicantAccount.getUsername());
                 if(account != null){
                     result.reject("username","Username already exist,please try another one or login");
                 }else{
                         ContractApplicantAccount account1 = accountService.findByEmail(contractApplicantAccount.getEmail());

                         if (account != null){
                             result.reject("email","the email you provided already exists,please login or provide a different email");
                         }
                         else {

                             /*Save the Account*/
                             contractApplicantAccount.setPassword(passwordEncoder.encode(requestParams.get("password")));
                             contractApplicantAccount.setEnabled(true);

                             //Check if role exists
                             RoleGroup role = roleService.findRole("Applicant");
                             if(role == null){
                                 role = new RoleGroup("Applicant");
                             }

                             contractApplicantAccount.setRole(role);
                             accountService.saveAccount(contractApplicantAccount);

                             /*Autologin configuration after account creation*/
                             Authentication authentication = authWithAuthManager(request,requestParams.get("username"),requestParams.get("password"));

                             /*Redirect after auto login*/
                             successHandler.onAuthenticationSuccess(request,response,authentication);
                         }

                     }
            }
    }

    /*Apply for contract ---GET---*/
    @RequestMapping(value = "/apply",method = RequestMethod.GET)
    public ModelAndView applyContract(ModelAndView modelAndView){
        modelAndView.addObject("contractApplication",new ContractApplicantAccount());
        modelAndView.setViewName("contract/contract-apply");
        return modelAndView;
    }   


    /*Applyy for contract -----POST------*/
    @RequestMapping(value = "/apply}",method = RequestMethod.POST )
    public ModelAndView saveContractApplication(ModelAndView modelAndView){
        return modelAndView;
    }


    @GetMapping("/getCv/{id}")
    public ResponseEntity<byte[]> getCv(@PathVariable(name = "id") int id) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String home = System.getProperty("user.home");  //get home directory
        Supplier supplier = this.supplierService.getSupplier(id);
        String fileName = supplier.getName().trim() + "-ContractCV.pdf";

        headers.add("content-disposition", "inline;filename=" + fileName);
        headers.setCacheControl("must-revalidate,post-check=0,pre-check=0");
        byte[] picBytes = supplier.getPic();

        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(picBytes,headers, HttpStatus.OK);
        return response;
    }






    //Auto login configuration
    public Authentication authWithAuthManager(HttpServletRequest request, String username, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        authToken.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        return authentication;
    }
}