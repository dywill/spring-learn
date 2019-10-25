package com.dy.component.converters;

import com.dy.entities.Person;
import org.springframework.core.convert.converter.Converter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestComverter implements Converter<String, Person> {

    public Person convert(String s) {

        String[] split = s.split("-");
        Person person = new Person();
        person.setName(split[0]);

        SimpleDateFormat instance = new SimpleDateFormat();
        instance.applyPattern("yyyy/MM/dd");
        try {
            person.setBirth(instance.parse(split[1]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        person.setSex(Integer.valueOf(split[2]));
        return person;
    }
}
