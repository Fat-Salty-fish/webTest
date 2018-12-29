/**
 * @Description 用于接收登录请求并且返回一个token
 * @auther 李忠杰
 * @create 2018-12-27 13:43
 */
package com.sinoyd.survey.controller;

import com.sinoyd.survey.entity.Admin;
import com.sinoyd.survey.service.AdminService;
import com.sinoyd.survey.token.MyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class loginController {
    @Autowired
    private AdminService adminService ;


    @PostMapping("login")
    public Object adminLogin(@RequestBody Admin admin) {
        try
        {
            return adminService.login(admin);
        }
        catch (Exception e)
        {
            return "登录错误 请重新登录";
        }
    }

}
