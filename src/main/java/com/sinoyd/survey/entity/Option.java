package com.sinoyd.survey.entity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sinoyd.frame.base.entity.BaseEntity;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * @Description 这个类描述了每个题目下的选项
 * @auther 李忠杰
 * @create 2018-12-28 8:54
 */
@Entity
@Table(name = "options")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Option implements BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "survey_id")
    private Integer surveyId;

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "option_name")
    private String optionName;

    @Column(name = "code_id")
    private Integer codeId;

    @Column(name = "score")
    private Integer score;

    @Column(name = "created_time")
    private Date createdTime;

    @Transient
    private String code;
}
