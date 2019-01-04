/**
 * @Description 这个controller下主要提供了一些关于问卷的内容
 * @auther 李忠杰
 * @create 2018-12-27 15:31
 */
package com.sinoyd.survey.controller;

import com.alibaba.fastjson.JSON;
import com.sinoyd.frame.base.controller.BaseController;
import com.sinoyd.frame.base.util.PageBean;
import com.sinoyd.survey.criteria.SurveyCriteria;
import com.sinoyd.survey.entity.Survey;
import com.sinoyd.survey.generateUrl.GenerateUrl;
import com.sinoyd.survey.service.CodeContrastService;
import com.sinoyd.survey.service.SurveyService;
import com.sinoyd.survey.token.MyToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class surveyController extends BaseController {
    @Autowired
    private SurveyService surveyService;

    @Autowired
    private CodeContrastService codeContrastService;

    @GetMapping(value = "/survey", produces = "application/json;charset=UTF-8")
    public Object getByPage(SurveyCriteria criteria) {
        PageBean pageBean = super.getPageBean();
        surveyService.getSurveyByPage(pageBean, criteria);
        return super.setJsonPaginationMap(pageBean);
    }

    @GetMapping(value = "/survey/{id}")
    public Object getQuestions(@PathVariable("id") Integer surveyId) {
        return surveyService.getOne(surveyId);
    }


    @GetMapping(value = "/url/generate")
    public Object getUrls(@RequestHeader("Authorization") String authorization, @RequestHeader("survey_id") Integer surveyId, @RequestHeader("num") Integer num) {
        try {
            if (MyToken.Verify(authorization)) {
                return GenerateUrl.generate(surveyId, num);
            } else {
                return "认证未通过 请重新认证";
            }
        } catch (Exception e) {
            return "发生了错误" + e.toString();
        }
    }

    @PostMapping(value = "/survey")
    public Object submit(@RequestBody Map<String, Object> model) {
        return surveyService.getResult(model);
    }


    @GetMapping(value = "/survey/summary")
    public Object summaryTest(@RequestHeader("Authorization") String authorization,
                              @RequestParam(value = "limit",required = false) Integer limit,
                              @RequestParam(value = "page",required = false)Integer page,
                              @RequestParam(value = "staffName",required = false)String staffName) {
        return surveyService.summary(limit,page,staffName);
    }

}

