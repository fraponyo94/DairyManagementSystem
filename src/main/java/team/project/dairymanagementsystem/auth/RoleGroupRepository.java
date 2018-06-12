package team.project.dairymanagementsystem.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleGroupRepository extends JpaRepository<RoleGroup,Integer>{
    List<RoleGroup> findByUserId(int userId);
}
