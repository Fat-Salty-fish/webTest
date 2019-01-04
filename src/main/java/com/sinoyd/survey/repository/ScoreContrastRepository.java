/**
 * @Description
 * @auther 李忠杰
 * @create 2019-01-02 10:54
 */
package com.sinoyd.survey.repository;

import com.sinoyd.survey.entity.ScoreContrast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScoreContrastRepository extends JpaRepository<ScoreContrast,Integer> {
    @Query(value = "select a from ScoreContrast as a where a.level = 2 group by a.code")
    List<ScoreContrast> findHighScore();

    @Query(value = "select a from ScoreContrast as a where a.level = 1 group by a.code")
    List<ScoreContrast> findMiddleScore();

    List<ScoreContrast> findAll();
}
