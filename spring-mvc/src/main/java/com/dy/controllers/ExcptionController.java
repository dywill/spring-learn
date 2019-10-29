package com.dy.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 1.专门用于全局处理异常的控制器
 *      会优先采用抛出异常类的中的 @ExceptionHandler 若该类中没有，则会使用全局的异常解析
 */
@ControllerAdvice
public class ExcptionController {

//    @ResponseBody  不可以使用该注解
    @ExceptionHandler(value = {NullPointerException.class})
    public String handle(){

        return "error";
    }
}
