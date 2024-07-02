package com.winestoreapp.interceptor;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.lang.annotation.Annotation;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;

public class Interceptor {
    @AroundConstruct
    private void aroundConstruct(InvocationContext invocationContext) throws Exception{
        System.out.println("aroundConstruct");
        invocationContext.proceed();
    }

    @PostConstruct
    private void postConstruct(){
        System.out.println("postConstruct");
    }

    @AroundInvoke
    private Object around(InvocationContext invocationContext) throws Exception {
        return invocationContext.proceed();
    }

    @PreDestroy
    private void destroy(){
        System.out.println("destroy");
    }
}
