/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-29 16:59
 */
package com.sinoyd.survey.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

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
