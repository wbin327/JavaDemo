package com.realationSearch.demo.model;

public class PersonCard {

    private Integer cardId;
    private Integer personId;
    // 关系属性，一张卡对应一个人
    private Person person;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
}