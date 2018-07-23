package team.project.dairymanagementsystem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.project.dairymanagementsystem.model.TenderInfo;
import team.project.dairymanagementsystem.repository.TenderInfoRepository;



@Service
public class TenderInfoService {
    @Autowired
    private TenderInfoRepository tenderInfoRepository;

    //Add Tender Description
    public void addTenderInfo(TenderInfo tenderInfo){
        tenderInfoRepository.save(tenderInfo);

    }

    /*TenderInfo whose status is active*/
    public TenderInfo   findActiveTender(){
        return  tenderInfoRepository.findByStatus(true);
    }




}
