/**
 * @Description   这个类主要描述了问卷信息 包括一个选项的list用来存放各个选项
 * @auther 李忠杰
 * @create 2018-12-29 8:37
 */
package com.sinoyd.survey.entity;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "surveys")
@Data
public class Survey {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "survey_name")
    private String survey_name;

    @Column(name = "survey_show")
    private String survey_show;

    @Column(name = "survey_type")
    private String survey_type;

    @Column(name = "created_time")
    private Date   created_time;

    @Transient
    private List<Question> questions;

}
