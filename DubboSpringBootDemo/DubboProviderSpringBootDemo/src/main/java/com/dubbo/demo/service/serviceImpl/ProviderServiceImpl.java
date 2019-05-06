package com.dubbo.demo.service.serviceImpl;

import com.dubbo.demo.service.ProviderService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;


@Service
public class ProviderServiceImpl implements ProviderService {

    @Value("${dubbo.application.name}")
    private String serviceName;

    public String sayHello(String name) {
        // return String.format("[%s] : Hello, %s", serviceName, name);
        return "Hello i'm provider </br>";
    }
}
