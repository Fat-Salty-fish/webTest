package com.sinoyd.survey.entity;
import lombok.Data;
import javax.persistence.*;

/**
 * @Description 得分对照表 根据答题得分 对照表内信息 获取百分制下的得分
 * @auther 李忠杰
 * @create 2019-01-02 10:43
 */
@Entity
@Table(name = "score_contrast")
@Data
public class ScoreContrast {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "sum_score")
    private Integer sumScore;

    @Column(name = "code_id")
    private Integer codeId;

    @Column(name = "score")
    private Integer score;

    @Column(name = "code")
    private String code;

    @Column(name = "value")
    private String value;

    @Column(name = "level")
    private Integer level;
}
