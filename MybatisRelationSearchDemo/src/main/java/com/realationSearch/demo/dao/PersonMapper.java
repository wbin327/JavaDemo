package com.realationSearch.demo.dao;

import com.realationSearch.demo.model.Person;
import java.util.List;

public interface PersonMapper {
    // 一对一多次查询
    List<Person> selectPersons();
    // 一对一单次查询
    List<Person> selectPersonsOneTime();
}
