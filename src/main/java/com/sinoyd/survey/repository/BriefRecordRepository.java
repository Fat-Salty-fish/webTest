/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-29 17:05
 */
package com.sinoyd.survey.repository;

import com.sinoyd.survey.entity.BriefRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BriefRecordRepository extends JpaRepository<BriefRecord,Integer> {
    @Query(value = "select * from brief as a order by a.id limit ?1,?2",nativeQuery = true)
    List<BriefRecord> findAllByPage(Integer index,Integer limited);

    @Query(value = "select * from brief as a where a.staff_id IN(?1) order by a.id limit ?2 , ?3 ",nativeQuery = true)
    List<BriefRecord> findAllByPageAndId(List<Integer> ids,Integer index,Integer limited);

    Long countAllByStaffIdIn(List<Integer> ids);
}
