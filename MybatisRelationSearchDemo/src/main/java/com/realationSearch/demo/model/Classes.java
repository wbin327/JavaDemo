package com.realationSearch.demo.model;

import java.util.List;

public class Classes {

    private Integer id;
    private String name;
    // 一个班级有多个学生，所以使用list来保存关联关系
    private List<Student> students;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    public List<Student> getStudents(){return students;}
    public void setStudents(List<Student> students){this.students = students;}
}