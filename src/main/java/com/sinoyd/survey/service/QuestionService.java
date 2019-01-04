/**
 * @Description 有关question的业务类 实现question的获取等功能
 * @auther 李忠杰
 * @create 2018-12-28 9:01
 */
package com.sinoyd.survey.service;

import com.sinoyd.survey.entity.Question;
import com.sinoyd.survey.repository.OptionRepository;
import com.sinoyd.survey.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionService optionService;

    public List<Question> getQuestions(Integer id)
    {
        return questionRepository.getAllBySurveyId(id);
    }

    public String require(List<Question> questions) {
        StringBuilder builder = new StringBuilder();
        for (Question eachQuestion : questions) {
            if (!optionService.equalToTen(eachQuestion.getOptions())) {
                builder.append(eachQuestion.getId()).append(",");
            }
        }
        if (builder.toString().equals("")) {
            return "所有题目的选项得分加起来均为十分";
        } else {
            builder.append("题目的得分加起来不为十分,请重新选择");
            return builder.toString();
        }
    }
}
