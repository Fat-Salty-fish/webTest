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

    @Column(name = "brief_id")
    private Integer briefId;

    @Column(name = "staff_id")
    private Integer staffId;

    @Column(name = "survey_id")
    private Integer surveyId;

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name ="option_id")
    private Integer optionId;

    @Column(name = "code")
    private String code;

    @Column(name = "score")
    private Integer score;

    @Column(name = "created_time")
    private Date createdTime;
}
