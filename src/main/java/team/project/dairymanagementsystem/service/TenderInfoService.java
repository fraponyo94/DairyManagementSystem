package team.project.dairymanagementsystem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.project.dairymanagementsystem.model.TenderInfo;
import team.project.dairymanagementsystem.repository.TenderInfoRepository;

import java.util.List;


@Service
public class TenderInfoService {
    @Autowired
    private TenderInfoRepository tenderInfoRepository;

    //Add Tender Description
    public void addTenderInfo(TenderInfo tenderInfo){
        tenderInfoRepository.save(tenderInfo);
    }

    /*get more recent tender*/
    public TenderInfo findLatestTender(){
        return  getLatestTenderInfo();
    }

    //filter out the latest tender
    private TenderInfo getLatestTenderInfo() {
        //get all tenders
        List<TenderInfo> tenderInfos = tenderInfoRepository.findAll();
        //check if the list is empty
        if(tenderInfos.isEmpty()){
            return null;
        }
        //assume the first tender has the latest tender
        long max = tenderInfos.get(0).getId();
        TenderInfo tender = tenderInfos.get(0);
        for (TenderInfo tenderInfo : tenderInfos) {
            long id = tenderInfo.getId();
            //if a tender has a bigger id than the current one, store its id in max
            if (id > max) {
                max = id;
                tender = tenderInfo;
            }
        }
        return tender;
    }
}
