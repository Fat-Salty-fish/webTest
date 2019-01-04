/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-29 16:09
 */
package com.sinoyd.survey.service;

import com.sinoyd.survey.entity.Record;
import com.sinoyd.survey.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    @Autowired
    private RecordRepository recordRepository;

    public List<Record> saveRecord(List<Record> records) {
        return (List<Record>) recordRepository.save(records);
    }

    public List<Record> findAll(){
        return (List<Record>) recordRepository.findAll();
    }
}
