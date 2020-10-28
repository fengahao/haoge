package com.example.springboot_demo.service;

import com.example.springboot_demo.common.ReturnValue;
import com.example.springboot_demo.dto.info.UserInfo;
import org.springframework.stereotype.Service;

/**
 * @author fengahao
 * @date 2020/10/26
 */
@Service
public class UserService {

    public ReturnValue addUser(UserInfo user){
        System.out.println(user.getAsdfl().equals(""));

        System.out.println(Integer.valueOf(user.getAsdfl()));
        return ReturnValue.okWithoutMsg(user);
    }
}
