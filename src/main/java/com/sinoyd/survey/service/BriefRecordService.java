package com.sinoyd.survey.service;
import com.sinoyd.survey.entity.BriefRecord;
import com.sinoyd.survey.repository.BriefRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-29 17:06
 */
@Service
public class BriefRecordService {
    @Autowired
    private BriefRecordRepository briefRecordRepository;

    public List<BriefRecord>  getBriefRecord(){
        return briefRecordRepository.findAll();
    }

    public BriefRecord save(BriefRecord briefRecord){
        return briefRecordRepository.save(briefRecord);
    }

    public Long count(){
        return briefRecordRepository.count();
    }

    public Long countByStaffId(List<Integer> ids){
        return briefRecordRepository.countAllByStaffIdIn(ids);
    }

    public List<BriefRecord> getListByPage(Integer currentPage,Integer limit){
        return briefRecordRepository.findAllByPage((currentPage-1)*limit,limit);
    }

    public List<BriefRecord> getListByPageAndStaffIds(List<Integer> ids ,Integer currentPage,Integer limit){
        return briefRecordRepository.findAllByPageAndId(ids,(currentPage-1)*limit,limit);
    }

    @Transactional
    public BriefRecord update(BriefRecord briefRecord){
        return briefRecordRepository.save(briefRecord);
    }
}
