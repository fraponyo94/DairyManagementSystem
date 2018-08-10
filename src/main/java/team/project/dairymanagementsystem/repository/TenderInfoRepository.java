package team.project.dairymanagementsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.project.dairymanagementsystem.model.TenderInfo;

import java.util.List;

@Repository
public interface TenderInfoRepository extends JpaRepository<TenderInfo,Long> {
    TenderInfo findByStatus(Boolean status);
}
