package com.dy.controllers;

import com.dy.entities.Person;
import com.sun.deploy.net.HttpResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@Controller
public class TestController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    /**
     * 测试 自定义的数据转化器
     * @param person
     * @return
     */
    @ResponseBody
    @RequestMapping("/test/conversion")
    public String testConverter(Person person){
        System.out.println(person);
        return "ok";
    }

    /**
     * 测试 获取参数时的 格式转化注解
     * @param person
     * @return
     */
    @ResponseBody
    @RequestMapping("/test/format")
    public String testFormat(Person person){
        System.out.println(person);
        return "ok";
    }

    /**
     * 测试 基于注解的jsr303 数据校验的使用
     * @param person
     * @param bindingResult
     * @return
     */
    @ResponseBody
    @RequestMapping("/test/validate")
    public String testValidate(@Valid Person person, BindingResult bindingResult){
        System.out.println(person);
        if(bindingResult.hasErrors()){
            return bindingResult.getAllErrors().get(0).getDefaultMessage();
        }
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/test/httpentity")
    public String testEntity(HttpEntity<String> entity){

        System.out.println(entity);
        return "ok";
    }

    /**
     * 测试下载
     *
     * 若返回值为 ResponseEntity ， 自定义类型 等特殊的类型，（即不为路径 string 等 ）
     * Spring会使用HttpMessageConverter
     *
     * @return
     */
    @RequestMapping("/test/download")
    public ResponseEntity<byte[]> testDownLoad(){

        byte[] bytes = {11,11,11,110};
        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie","aaa=123123");
        /**
         * 创建响应体对象， 第一个参数为响应体内容，
         *                  第二个参数为响应头设置
         *                  第三个参数为响应体的http状态码
         */
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(bytes,headers, HttpStatus.OK);
        return responseEntity;
    }

    @ResponseBody
    @RequestMapping(value = "/test/json", produces = "application/dy")
    public Person testJson(){
        Person person = new Person();

        person.setName("jack");
        person.setBirth(new Date());
        person.setSex(0);

        return person;
    }

    @ResponseBody
    @RequestMapping("/test/json001")
    public Person testJson001(HttpServletResponse response){
        response.setContentType("application/dy");
        Person person = new Person();

        person.setName("jack");
        person.setBirth(new Date());
        person.setSex(0);

        return person;
    }
}