package com.sinoyd.survey.entity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @Description   问卷信息类
 * @auther 李忠杰
 * @create 2018-12-29 8:37
 */
@Entity
@Table(name = "surveys")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Survey {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "survey_name")
    private String surveyName;

    @Column(name = "survey_show")
    private String surveyShow;

    @Column(name = "survey_type")
    private String surveyType;

    @Column(name = "created_time")
    private Date   createdTime;

    @Transient
    private List<Question> questions;
}
