package team.project.dairymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.project.dairymanagementsystem.model.RoleGroup;

import java.util.List;

@Repository
public interface RoleGroupRepository extends JpaRepository<RoleGroup,Integer>{

}
