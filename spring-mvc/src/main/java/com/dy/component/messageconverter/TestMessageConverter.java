package com.dy.component.messageconverter;

import com.dy.entities.Person;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class TestMessageConverter implements HttpMessageConverter<Person> {

    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        if(clazz.equals(Person.class) && mediaType != null && mediaType.getSubtype().equals("dy")){
            return true;
        }
        return false;
    }

    /**
     * 能否支持转换该数据
     * @param clazz
     * @param mediaType
     * @return
     */
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        if(clazz.equals(Person.class) && mediaType != null && mediaType.getSubtype().equals("dy")){
            return true;
        }
        return false;
    }

    /**
     * 该转换器支持的所有 MediaType 类型
     * @return
     */
    public List<MediaType> getSupportedMediaTypes() {
        ArrayList<MediaType> mediaTypes = new ArrayList<MediaType>();
        MediaType mediaType = new MediaType("application","dy");
        mediaTypes.add(mediaType);
        return mediaTypes;
    }

    public Person read(Class<? extends Person> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return new Person();
    }

    /**
     * 数据输出的转换流程
     * @param person
     * @param contentType
     * @param outputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    public void write(Person person, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        System.out.println(person);

        OutputStream outputStream = outputMessage.getBody();

    }

}
