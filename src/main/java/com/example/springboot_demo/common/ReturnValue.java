package com.example.springboot_demo.common;

import com.example.springboot_demo.constants.ReturnCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author fengahao
 * @date 2020/10/20
 */
@ApiModel("接口返回的数据格式")
@Data
public final class ReturnValue<T> {

    @ApiModelProperty("接口是否返回成功")
    private boolean success;
    @ApiModelProperty("单个数据")
    private T data;
    @ApiModelProperty("数据集合")
    private List<T> dataList;
    @ApiModelProperty("响应编码")
    private String code;
    @ApiModelProperty("响应信息")
    private String message;


    /**
     * 操作成功，但是不需要返回数据
     * @param <T>
     * @return
     */
    public static <T> ReturnValue ok(){
        return ReturnValue.makeMessageCode(ReturnCodeEnum.SUCCESS, false, true);
    }
    public static <T> ReturnValue okWithoutMsg(){
        return ReturnValue.makeMessageCode(ReturnCodeEnum.SUCCESS, true, true);
    }
    /**
     * 操作成功
     * @param data 单个对象
     * @param <T>
     * @return
     */
    public static <T> ReturnValue ok(T data){
        ReturnValue<T> resp = ReturnValue.makeMessageCode(ReturnCodeEnum.SUCCESS, false, true);
        resp.data = data;
        return resp;
    }
    /**
     * 操作成功
     * @param data 单个对象
     * @param <T>
     * @return
     */
    public static <T> ReturnValue okWithoutMsg(T data){
        ReturnValue<T> resp = ReturnValue.makeMessageCode(ReturnCodeEnum.SUCCESS, true, true);
        resp.data = data;
        return resp;
    }
    /**
     * 操作成功
     * @param dataList 返回的数据集合
     * @param <T>
     * @return
     */
    public static <T> ReturnValue ok(List<T> dataList){
        ReturnValue<T> resp = ReturnValue.makeMessageCode(ReturnCodeEnum.SUCCESS, false, true);
        resp.dataList = dataList;
        return resp;
    }
    /**
     * 操作成功
     * @param dataList 返回的数据集合
     * @param <T>
     * @return
     */
    public static <T> ReturnValue okWithoutMsg(List<T> dataList){
        ReturnValue<T> resp = ReturnValue.makeMessageCode(ReturnCodeEnum.SUCCESS, true, true);
        resp.dataList = dataList;
        return resp;
    }


    /**
     * 操作失败
     * @param returnCodeEnum
     * @param <T>
     * @return
     */
    public static <T> ReturnValue fail(ReturnCodeEnum returnCodeEnum){
        return ReturnValue.makeMessageCode(returnCodeEnum, false, false);
    }
    /**
     * 操作失败
     * @param <T>
     * @return
     */
    public static <T> ReturnValue fail(String code, String message){
        ReturnValue<T> resp = new ReturnValue();
        resp.code = code;
        resp.message = message;
        return resp;
    }
    /**
     * 操作失败
     * @param returnCodeEnum
     * @param <T>
     * @return
     */
    public static <T> ReturnValue failWithoutMsg(ReturnCodeEnum returnCodeEnum){
        return ReturnValue.makeMessageCode(returnCodeEnum, true, false);
    }

    protected static <T> ReturnValue makeMessageCode(ReturnCodeEnum returnCodeEnum, boolean isWithoutMsg, boolean isSuccess){
        ReturnValue<T> resp = new ReturnValue();
        resp.code = returnCodeEnum.getCode();
        resp.success = isSuccess;
        if (isWithoutMsg){
            return resp;
        }
        resp.message = returnCodeEnum.getMessage();
        return resp;
    }


}
