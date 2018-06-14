package team.project.dairymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.project.dairymanagementsystem.model.DairyStaff;
import team.project.dairymanagementsystem.repository.DairyStaffRepository;

@Service
public class DairyStaffService {
    @Autowired
    private DairyStaffRepository dairyStaffRepository;

    //Find staff user given payrollId
    public DairyStaff findByUsername(String payrollId){
        return dairyStaffRepository.findByPayrollId(payrollId);
    }

    //add staff
    public void addDairyStaff(DairyStaff dairyStaff){
        DairyStaff dairyStaff1 = dairyStaffRepository.findByPayrollId(dairyStaff.getPayrollId());
        if(dairyStaff1 != null){
            dairyStaff = dairyStaff1;
        }
        dairyStaffRepository.save(dairyStaff);
    }


}
