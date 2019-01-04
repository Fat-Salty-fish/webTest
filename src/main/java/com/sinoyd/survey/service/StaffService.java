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

import java.beans.Transient;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    public Staff getOneByNo(String no){
        return staffRepository.findByStaffNo(no);
    }

    public List<Staff> findAll(){
        return (List<Staff>) staffRepository.findAll();
    }

    @Transient
    public Staff saveOrUpdate(Staff staff){
        Staff findStaff = staffRepository.findByStaffNo(staff.getStaffNo());
        if(findStaff !=null){
            staff.setId(findStaff.getId());
        }
        return staffRepository.save(staff);
    }

    public List<Staff> findStaffByName(String staffName) {
        return staffRepository.findAllByNameLike("%"+staffName+"%");
    }

    public List<Staff> findStaffByIds(List<Integer> ids){
        return staffRepository.findAllByIdIn(ids);
    };
}
