/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-27 13:46
 */
package com.sinoyd.survey.service;


import com.sinoyd.survey.entity.Admin;
import com.sinoyd.survey.repository.AdminRepository;
import com.sinoyd.survey.token.MyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public String login(Admin admin) throws Exception {
        if(adminRepository.findAdmin(admin.getUserName(),admin.getPassword())!=null)
        {
            return MyToken.createJWT(admin.getUserName(),1000*60*120);        //设置token有效时间是120分钟 即两小时
        }
        else
        {
            return "用户名或密码错误";
        }
    }
}
