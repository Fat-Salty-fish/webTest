/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-29 14:57
 */
package com.sinoyd.survey.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "record")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Record {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer briefId;
    private Integer staffId;
    private Integer surveyId;
    private Integer questionId;
    private Integer optionId;
    private String code;
    private Integer score;
    private Date surveyTime;
}
