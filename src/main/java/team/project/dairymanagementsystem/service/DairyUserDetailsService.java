package team.project.dairymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team.project.dairymanagementsystem.component.DairyUserPrincipal;
import team.project.dairymanagementsystem.model.RoleGroup;
import team.project.dairymanagementsystem.model.User;
import team.project.dairymanagementsystem.repository.RoleGroupRepository;
import team.project.dairymanagementsystem.repository.UserRepository;

import javax.management.relation.Role;
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

    public User saveUser(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
        String hashedPassword = encoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        RoleGroup roleGroup = new RoleGroup(user.getNationalId(), "admin");
        roleGroupRepository.save(roleGroup);
        return userRepository.save(user);
    }
}
