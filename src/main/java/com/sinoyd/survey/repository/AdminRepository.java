/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-27 13:45
 */
package com.sinoyd.survey.repository;

import com.sinoyd.survey.entity.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends CrudRepository<Admin,Integer> {
    @Query(value = "select  a from Admin a where a.userName = :userName and a.password = :password")
    Admin findAdmin(@Param("userName") String userName, @Param("password") String password);
}
