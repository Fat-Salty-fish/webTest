/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-27 15:39
 */
package com.sinoyd.survey.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sinoyd.frame.base.repository.CommonRepository;
import com.sinoyd.frame.base.util.PageBean;

import com.sinoyd.survey.criteria.SurveyCriteria;
import com.sinoyd.survey.entity.*;
import com.sinoyd.survey.repository.OptionRepository;
import com.sinoyd.survey.repository.QuestionRepository;
import com.sinoyd.survey.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private CommonRepository commonRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CodeContrastService codeContrastService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private RecordService recordService;


    public void getSurveyByPage(PageBean pageBean, SurveyCriteria surveyCriteria){
        pageBean.setEntityName("Survey a");
        pageBean.setSelect("Select a");
        commonRepository.findByPage(pageBean,surveyCriteria);
    }

    public Survey getOne(Integer surveyId)
    {
        Survey survey = surveyRepository.findOne(surveyId);                          //获取survey
        survey.setQuestions(new ArrayList<Question>());                         //设置survey下的question
        List<Question> questions = questionRepository.getAllBySurveyId(surveyId);       //获取surveyid有关的问题集合
        Map<Integer,CodeContrast> contrastMap = codeContrastService.getContrastToMap();
        for (Question question : questions)                                         //设置question下的options
        {
            question.setOptions(new ArrayList<Option>());
        }
        List<Option> options = optionRepository.getAllBySurveyId(surveyId);               //获取所有的option 并进行设置 设置题目序号 将code匹配codeid
        for(Option eachOption:options) {
            eachOption.setId(eachOption.getId() - eachOption.getId() / 10 * 10);
            eachOption.setCode((eachOption.getCodeId()!=null)?(contrastMap.get(eachOption.getCodeId()).getCode()):"");
            eachOption.setCodeId(null);
            if (eachOption.getId() == 0) {
                eachOption.setId(10);
            }
        }
        for (Question eachQuestion : questions) {                                   //将option添加到question中去
            for (Option eachOption : options) {
                if (eachQuestion.getId() == eachOption.getQuestionId()) {
                    eachQuestion.getOptions().add(eachOption);
                }
            }
        }
        survey.setQuestions(questions);
        return survey;
    }

    public Object getResult(Map<String, Object> model)
    {
        Staff staff = JSON.parseObject(JSON.toJSONString(model.get("staff")), new TypeReference<Staff>() {});
        staff = staffService.getOneByNo(staff.getStaffNo());
        if(staff==null)
        {
            return "查无此员工 请先注册";
        }
        String noncestr = (String) model.get("noncestr");
        ArrayList<Question> questions = JSON.parseObject(JSON.toJSONString(model.get("questions")), new TypeReference<ArrayList<Question>>(){});
        String require = questionService.require(questions);
        if(require.equals("所有题目的选项得分加起来均为十分")){
            List<Record> records = new ArrayList<>();
            Map<String,Integer> capacity = codeContrastService.getContrastToMapForScore();          //计算总共的能力值
            for(Question eachQuestion:questions){
                for(Option eachOption:eachQuestion.getOptions()){
                    String code = eachOption.getCode();
                    Integer previousScore = capacity.get(code);
                    capacity.put(code,previousScore+eachOption.getScore());
                    Record record = new Record();
                    record.setStaffId(staff.getId());
                    record.setSurveyId(Integer.parseInt(noncestr.substring(0,1)));
                    record.setQuestionId(eachQuestion.getId());
                    record.setOptionId(eachOption.getId());
                    record.setCode(code);
                    record.setScore(eachOption.getScore());
                    records.add(record);
                }
            }
            recordService.saveRecord(records);
            List<SurveyAccept> acceptInfos = new ArrayList<>();
            for (Map.Entry<String,Integer> score:capacity.entrySet()) {
                SurveyAccept info = new SurveyAccept();
                info.setStaff_id(staff.getId());
                info.setSurvey_id(Integer.parseInt(noncestr.substring(0,1)));
                info.setCode(score.getKey());
                info.setScore(score.getValue());
                acceptInfos.add(info);
            }
            return acceptInfos;
        }
        else{
            return require;
        }
    }

    public Object summary(){

        return null;
    }
}
