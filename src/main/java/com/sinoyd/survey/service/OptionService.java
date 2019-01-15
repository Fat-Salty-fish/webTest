package com.sinoyd.survey.service;
import com.sinoyd.survey.entity.Option;
import com.sinoyd.survey.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-28 9:01
 */
@Service
public class OptionService {
    @Autowired
    private OptionRepository optionRepository;

    public List<Option> getOptions(Integer surveyId)
    {
        return optionRepository.getAllBySurveyId(surveyId);
    }

    public boolean equalToTen(List<Option> options){
        Integer score = 0;
        for(Option eachOption:options){
            score += eachOption.getScore();
        }
        if(score==10){
            return true;
        }
        else {
            return false;
        }
    }
}
