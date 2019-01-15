package com.sinoyd.survey.entity;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.Map;

/**
 * @Description 简要信息 每次答题都会生成 同时保存各个能力的成绩以及要展示的能力
 * @auther 李忠杰
 * @create 2018-12-29 16:59
 */
@Entity
@Table(name = "brief")
@Data

public class BriefRecord {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer staffId;
    private Integer surveyId;
    private Date    createdTime;
    private Integer PL;
    private Integer RI;
    private Integer CO;
    private Integer SH;
    private Integer ME;
    private Integer TW;
    private Integer IM;
    private Integer CF;
    private Integer SP;
    private String capacity;

    @Transient
    private Map<String,Integer> codeLevel;

    public BriefRecord(Integer staffId,Integer surveyId){
        this.setStaffId(staffId);
        this.setSurveyId(surveyId);
    }

    public BriefRecord(){};
}
