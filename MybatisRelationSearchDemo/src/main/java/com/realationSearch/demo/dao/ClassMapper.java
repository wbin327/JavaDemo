package com.realationSearch.demo.dao;

import com.realationSearch.demo.model.Classes;

public interface ClassMapper {
    // 一对多单次查询
    Classes selectClassOneTime(int id);
    // 一对多多次查询
    Classes selectClassManyTime(int id);
}
