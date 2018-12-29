/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-29 17:05
 */
package com.sinoyd.survey.repository;

import com.sinoyd.survey.entity.BriefRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BriefRecordRepository extends JpaRepository<BriefRecord,Integer> {
}
