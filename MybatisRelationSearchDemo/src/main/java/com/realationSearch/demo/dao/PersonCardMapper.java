package com.realationSearch.demo.dao;

import com.realationSearch.demo.model.PersonCard;

public interface PersonCardMapper {
    PersonCard selectPersonCardByPerson(int personId);
}
