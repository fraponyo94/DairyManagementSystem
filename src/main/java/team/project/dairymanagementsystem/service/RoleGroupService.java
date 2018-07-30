package team.project.dairymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.project.dairymanagementsystem.model.RoleGroup;
import team.project.dairymanagementsystem.repository.RoleGroupRepository;

@Service
public class RoleGroupService {
    @Autowired
    private RoleGroupRepository roleRepository;

    /*Find role*/
    public RoleGroup findRole(String role){
         return roleRepository.findByRole(role);
    }
}
