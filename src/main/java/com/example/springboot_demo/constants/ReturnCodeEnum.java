package com.example.springboot_demo.constants;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fengahao
 * @date 2020/10/20
 */
public enum ReturnCodeEnum {

    /**------提示信息----START------*/
    SUCCESS("10000", "操作成功！"),
    VALIDATE_FAILED("10001", "参数校验失败"),



    FAIL("99999", "操作失败！");
    /**-----提示信息-----END-------*/


    private String code;
    private String message;

    ReturnCodeEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode(){
        return this.code;
    }
    public String getMessage(){
        return this.message;
    }
}
