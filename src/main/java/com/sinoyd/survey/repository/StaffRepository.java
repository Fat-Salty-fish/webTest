/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-29 15:38
 */
package com.sinoyd.survey.repository;

import com.sinoyd.survey.entity.Staff;
import org.springframework.data.repository.CrudRepository;

public interface StaffRepository extends CrudRepository<Staff,Integer> {
    Staff findByStaffNo(String no);
}
