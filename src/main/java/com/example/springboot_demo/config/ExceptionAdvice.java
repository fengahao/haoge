package com.example.springboot_demo.config;

import com.example.springboot_demo.common.ReturnValue;
import com.example.springboot_demo.constants.ReturnCodeEnum;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**入参校验  异常信息
 * @author fengahao
 * @date 2020/10/20
 */
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public ReturnValue allException(Exception e){
        return ReturnValue.fail(ReturnCodeEnum.FAIL.getCode(), ExceptionAdvice.getExceptionAllInformation(e));
    }
    /**
     * 获取到异常的详细信息
     * @param ex
     * @return
     */
    public static String getExceptionAllInformation(Exception ex) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(out);
        ex.printStackTrace(pout);
        String ret = new String(out.toByteArray());
        pout.close();
        try {
            out.close();
        } catch (Exception e) {
        }
        return ret;
    }



    @ExceptionHandler(ConstraintViolationException.class)
    public ReturnValue ConstraintViolationExceptionHandler(ConstraintViolationException e){
        if (e == null || CollectionUtils.isEmpty(e.getConstraintViolations())){
            return ReturnValue.okWithoutMsg();
        }
        for (ConstraintViolation objectError : e.getConstraintViolations()){
            return ReturnValue.fail(ReturnCodeEnum.VALIDATE_FAILED.getCode(), objectError.getMessage());
        }
        return ReturnValue.okWithoutMsg();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ReturnValue MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        if (e == null || e.getBindingResult() == null || CollectionUtils.isEmpty(e.getBindingResult().getAllErrors())){
            return ReturnValue.okWithoutMsg();
        }
        return ReturnValue.fail(ReturnCodeEnum.VALIDATE_FAILED.getCode(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }


}
