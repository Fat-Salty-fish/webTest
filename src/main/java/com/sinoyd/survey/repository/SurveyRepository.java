package com.sinoyd.survey.repository;
import com.sinoyd.survey.entity.Survey;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-29 8:40
 */
public interface SurveyRepository extends CrudRepository<Survey,Integer> {
    List<Survey> findAllByIdIn(List<Integer> ids);
}
