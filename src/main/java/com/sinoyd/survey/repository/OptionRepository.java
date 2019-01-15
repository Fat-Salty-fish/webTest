package com.sinoyd.survey.repository;
import com.sinoyd.survey.entity.Option;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-28 9:01
 */
public interface OptionRepository extends CrudRepository<Option,Integer> {
    List<Option> getAllBySurveyId(Integer id);

}
