package com.demo.springcloud.lb;


import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements ILoadBalancer{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        do{
            current = atomicInteger.get();
            next = current + 1 >=2147483647 ? 0 : current+1;
        }while (!atomicInteger.compareAndSet(current,next));
        System.out.println("调用的次数--------->" + next);
        return next;
    }


    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int allService = serviceInstances.size();
        int index = getAndIncrement() % allService;
        ServiceInstance serviceInstance = serviceInstances.get(index);
        return serviceInstance;
    }
}
