/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-28 9:00
 */
package com.sinoyd.survey.repository;


import com.sinoyd.survey.entity.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question,Integer> {

    List<Question> getAllBySurveyId(Integer id);
}
