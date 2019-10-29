package com.dy.component.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 抛出该异常时，会跳到 有该reason的异常页面
 */
@ResponseStatus(reason = "测试异常方法出现异常", code = HttpStatus.BAD_GATEWAY)
public class TestException extends RuntimeException {
}
