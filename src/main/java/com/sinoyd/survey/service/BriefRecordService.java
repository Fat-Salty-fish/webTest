/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-29 17:06
 */
package com.sinoyd.survey.service;

import com.sinoyd.survey.entity.BriefRecord;
import com.sinoyd.survey.repository.BriefRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BriefRecordService {
    @Autowired
    private BriefRecordRepository briefRecordRepository;

    public List<BriefRecord>  getBriefRecord(){
        return briefRecordRepository.findAll();
    }

    public BriefRecord save(Integer staffId,Integer surveyId){
        return briefRecordRepository.save(new BriefRecord(staffId,surveyId));
    }
}
