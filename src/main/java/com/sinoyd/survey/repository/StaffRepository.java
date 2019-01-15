package com.sinoyd.survey.repository;
import com.sinoyd.survey.entity.Staff;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-29 15:38
 */
public interface StaffRepository extends CrudRepository<Staff, Integer> {
    Staff findByStaffNo(String no);

    List<Staff> findAllByNameLike(String name);

    List<Staff> findAllByIdIn(List<Integer> ids);

}
