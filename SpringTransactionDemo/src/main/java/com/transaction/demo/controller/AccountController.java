package com.transaction.demo.controller;

import com.transaction.demo.model.Account;
import com.transaction.demo.model.RequestJson;
import com.transaction.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    private AccountService service;

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public Map<String, Account> transferAccounts(@RequestBody RequestJson json){
        return service.transferAccounts(json.getaId(), json.getbId(), json.getMoney());
    }
}
