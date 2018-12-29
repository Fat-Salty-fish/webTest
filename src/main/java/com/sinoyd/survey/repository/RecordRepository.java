/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-29 15:01
 */
package com.sinoyd.survey.repository;

import com.sinoyd.survey.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record,Integer> {

}
