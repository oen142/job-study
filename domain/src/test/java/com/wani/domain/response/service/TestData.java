package com.wani.domain.response.service;

import java.util.Objects;

public class TestData {

    private String name;
    private int age;


    public TestData(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestData testData = (TestData) o;
        return age == testData.age && Objects.equals(name, testData.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
