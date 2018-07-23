package team.project.dairymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.project.dairymanagementsystem.model.User;

@Repository
public interface  UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByNationalId(int nationalId);

}
