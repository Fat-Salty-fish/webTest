/**
 * @Description    这个类主要描述了员工的各种信息
 * @auther 李忠杰
 * @create 2018-12-28 15:38
 */
package com.sinoyd.survey.entity;
import com.sinoyd.frame.base.entity.BaseEntity;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "staffs")
@Data
public class Staff implements BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "staff_name")
    private String name;

    @Column(name = "staff_no")
    private String staffNo;

    @Column(name = "mobilephone")
    private String mobilephone;

    @Column(name = "created_time")
    private Date createdTime;

    @Transient
    private String sex;

}
