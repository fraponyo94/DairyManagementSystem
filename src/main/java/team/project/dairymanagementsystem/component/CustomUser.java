package team.project.dairymanagementsystem.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import team.project.dairymanagementsystem.model.ContractApplicantAccount;
import team.project.dairymanagementsystem.model.DairyStaff;
import team.project.dairymanagementsystem.model.Supplier;
import team.project.dairymanagementsystem.service.ContractApplicantAccountService;
import team.project.dairymanagementsystem.service.DairyStaffService;
import team.project.dairymanagementsystem.service.SupplierService;
import team.project.dairymanagementsystem.service.UserService;

@Component
public class CustomUser {
                                                                                                                                                                                                                                    
      @Autowired
      private DairyStaffService dairyStaffService;

      @Autowired
      private UserService userService;

      @Autowired
      private ContractApplicantAccountService accountService;

      private DairyStaff dairyStaff;

      private ContractApplicantAccount applicantAccount;

      private team.project.dairymanagementsystem.model.User user;


    public User getUser(String username){

        try {
            if ((dairyStaff = dairyStaffService.findByUsername(username)) != null) {
                return new User(dairyStaff.getPayrollId(), dairyStaff.getPassword(),
                        true, true, true,
                        true, dairyStaff.getAuthorities(dairyStaff.getRoleGroup().getRole()));


            }else if((applicantAccount = accountService.findByUsername(username)) != null){
                return  new User(applicantAccount.getUsername(),applicantAccount.getPassword(),applicantAccount.isEnabled(),
                        true,true,true,applicantAccount.getAuthorities(applicantAccount.getRole().getRole()));

            }else if((user = userService.findByUsername(username)) != null){
                 return new User(user.getUsername(), user.getPassword(),
                         true, true, true,
                         true, user.getAuthorities(user.getRoleGroup().getRole()));
            }
            else {
                throw new UsernameNotFoundException("user not found");
            }

        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

}
