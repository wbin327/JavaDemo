package com.dubboConsumer.demo.service.serviceImpl;

import com.dubboConsumer.demo.service.ConsumerService;
import com.dubbo.demo.service.ProviderService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    // 指定接口名称，必须和provider的接口名称一致，否则匹配不上
    @Reference
    private ProviderService providerService;

    public String helloConsumer(String name) {
        return "Hello i'm Consumer </br>" + providerService.sayHello(name);
    }
}
