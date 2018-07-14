package team.project.dairymanagementsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.project.dairymanagementsystem.component.CustomUser;
import team.project.dairymanagementsystem.service.DairyStaffService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomUser customUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = customUser.getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        return user;

    }
}
