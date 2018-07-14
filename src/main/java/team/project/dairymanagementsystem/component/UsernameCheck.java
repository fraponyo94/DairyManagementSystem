
package team.project.dairymanagementsystem.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import team.project.dairymanagementsystem.model.User;
import team.project.dairymanagementsystem.service.UserService;

@Component
public class UsernameCheck {
    @Autowired
    private UserService userService;



    public User getUser(User user){
        User user1;
        User user2;
        try{
            if (((user1 = userService.findByUsername(user.getUsername()))!= null)
                    || ((user2 = userService.findByNationalId(user.getNationalId())) !=null)){
                return null;
            }else {
                user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
               return userService.createUser(user);
            }
        }catch (final Exception e){
            throw new RuntimeException(e);
        }
    }
}

