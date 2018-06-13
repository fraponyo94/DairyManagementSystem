package team.project.dairymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.project.dairymanagementsystem.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    User findByNationalId(int id);
}

