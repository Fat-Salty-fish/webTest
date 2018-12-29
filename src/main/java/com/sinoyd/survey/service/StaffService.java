/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-29 15:38
 */
package com.sinoyd.survey.service;

import com.sinoyd.survey.entity.Staff;
import com.sinoyd.survey.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    public Staff getOneByNo(String no){
        return staffRepository.findByStaffNo(no);
    }
}
