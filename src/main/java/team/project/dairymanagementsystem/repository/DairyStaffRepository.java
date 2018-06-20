package team.project.dairymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.project.dairymanagementsystem.model.DairyStaff;

@Repository
public interface DairyStaffRepository extends JpaRepository<DairyStaff,String> {
    //Find staff given payrollId
    DairyStaff findByPayrollId(String payrollId);
}
