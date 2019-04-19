package com.transaction.demo.service;

import com.transaction.demo.dao.AccountDao;
import com.transaction.demo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    @Transactional
    public Map<String, Account> transferAccounts(int aId, int bId, int money){
        Map<String, Account> accountMap = new HashMap();
        Account userA = accountDao.select(aId);
        Account userB = accountDao.select(bId);
        userA.setMoney(userA.getMoney() - money);
        userB.setMoney(userB.getMoney() + money);
        accountMap.put("userA", userA);
        accountMap.put("userB", userB);
        accountDao.updateAccount(userA);
        // 模拟异常情况，事务回滚
        int i=10/0;
        accountDao.updateAccount(userB);
        return accountMap;
    }
}
