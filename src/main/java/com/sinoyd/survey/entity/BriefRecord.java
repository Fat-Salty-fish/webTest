/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-29 16:59
 */
package com.sinoyd.survey.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

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

    public BriefRecord(Integer staffId,Integer surveyId){
        this.setStaffId(staffId);
        this.setSurveyId(surveyId);
    }
}
