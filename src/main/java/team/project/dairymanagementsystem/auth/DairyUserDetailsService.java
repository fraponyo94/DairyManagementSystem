package team.project.dairymanagementsystem.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.project.dairymanagementsystem.domain.User;

import java.util.List;

@Service
public class DairyUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    private RoleGroupRepository roleGroupRepository;

    public DairyUserDetailsService(UserRepository userRepository, RoleGroupRepository roleGroupRepository) {
        this.userRepository = userRepository;
        this.roleGroupRepository = roleGroupRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByNationalId(Integer.parseInt(s));
        if(user == null) {
            throw new UsernameNotFoundException("user "+ s + " not found");
        }
        List<RoleGroup> roles = roleGroupRepository.findByUserId(Integer.parseInt(s));
        return new DairyUserPrincipal(user,roles);
    }
}
