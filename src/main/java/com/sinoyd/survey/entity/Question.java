package com.sinoyd.survey.entity;
import com.sinoyd.frame.base.entity.BaseEntity;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @Description 这个类描述了题目 包括一个选项list 用来保存每个题目下的选项
 * @auther 李忠杰
 * @create 2018-12-28 8:41
 */
@Entity
@Table(name = "questions")
@Data
public class Question implements BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name ="survey_id")
    private Integer surveyId;

    @Column(name = "question_name")
    private String questionName;

    @Column(name = "score")
    private Integer score;

    @Column(name = "created_time")
    private Date createdTime;

    @Transient
    private List<Option> options = new ArrayList<>(0);
}
