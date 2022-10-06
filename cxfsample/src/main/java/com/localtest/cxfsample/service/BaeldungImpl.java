package com.localtest.cxfsample.service;

import com.localtest.cxfsample.endpoint.Baeldung;
import com.localtest.cxfsample.model.Student;
import org.springframework.stereotype.Service;

@Service
public class BaeldungImpl implements Baeldung {
    private int counter;

    @Override
    public String hello(String name) {
        return "Hello " + name + "!";
    }

    @Override
    public String register(Student student) {
        counter++;
        return student.getName() + " is registered student number " + counter;
    }
}
