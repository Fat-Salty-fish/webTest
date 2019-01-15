package com.sinoyd.survey.entity;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * @Description 管理员登录信息
 * @auther 李忠杰
 * @create 2018-12-27 11:50
 */
@Entity
@Table(name = "users")
@Data
public class Admin {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "created_time")
    private Date created_Time;
}
