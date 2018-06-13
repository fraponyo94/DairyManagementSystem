package team.project.dairymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.project.dairymanagementsystem.model.RoleGroup;

import java.util.List;

public interface RoleGroupRepository extends JpaRepository<RoleGroup,Integer>{
    List<RoleGroup> findByUserId(int userId);
}
