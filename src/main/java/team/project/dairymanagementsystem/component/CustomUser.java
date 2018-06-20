package team.project.dairymanagementsystem.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import team.project.dairymanagementsystem.model.DairyStaff;
import team.project.dairymanagementsystem.service.DairyStaffService;

@Component
public class CustomUser {

      @Autowired
     private DairyStaffService dairyStaffService;

      private DairyStaff dairyStaff;


    public User getUser(String username){

        try {
            if ((dairyStaff = dairyStaffService.findByUsername(username)) != null) {
                return new User(dairyStaff.getPayrollId(), dairyStaff.getPassword(),
                        true, true, true,
                        true, dairyStaff.getAuthorities());


            }else {
                throw new UsernameNotFoundException("user not found");
            }

        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
}}
