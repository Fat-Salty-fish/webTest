package com.sinoyd.survey.entity;
import lombok.Data;
import javax.persistence.*;

/**
 * @Description 代码对照表 根据代码对照所展示的能力
 * @auther 李忠杰
 * @create 2018-12-29 9:56
 */
@Entity
@Table(name = "code_contrast")
@Data
public class CodeContrast {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "value")
    private String value;
}
