package com.transaction.demo.dao;

import com.transaction.demo.model.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountDao {
    void updateAccount(Account account);
    Account select(int id);
}
