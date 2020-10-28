package com.example.springboot_demo.web;

import com.example.springboot_demo.common.ReturnValue;
import com.example.springboot_demo.constants.ReturnCodeEnum;
import com.example.springboot_demo.dto.info.UserInfo;
import com.example.springboot_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @author fengahao
 * @date 2020/10/19
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/addUser1")
    public ReturnValue addUser1(@RequestBody @Valid UserInfo user){
        return userService.addUser(user);
    }
    @RequestMapping("/addUser2")
    public ReturnValue addUser2(@RequestBody @Valid UserInfo user){

        return ReturnValue.okWithoutMsg(user);
    }
    @RequestMapping("/addUser3")
    public ReturnValue addUser3(@RequestBody @Valid UserInfo user){

        return ReturnValue.fail(ReturnCodeEnum.FAIL);
    }
}
