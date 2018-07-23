package team.project.dairymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.project.dairymanagementsystem.model.RoleGroup;
import team.project.dairymanagementsystem.model.User;
import team.project.dairymanagementsystem.model.enumerated.Roles;
import team.project.dairymanagementsystem.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByNationalId(int nationalId){
        return this.userRepository.findByNationalId(nationalId);
    }

    public User findByUsername(String username){
        return this.userRepository.findByUsername(username);

    }

    public User createUser(User user){
        user.setRoleGroup(new RoleGroup(Roles.SUPPLIER.toString()));
        return this.userRepository.save(user);
    }

}