package com.example.javalambda.config;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.slf4j.MDC;

public class ThreadLocalTest {

    public static final ThreadLocal<String> CONTEXT = new TransmittableThreadLocal<>();

    public static void set(String var) {
        CONTEXT.set(var);
        MDC.put("ThreadLocalTest", var);
    }

    public static String get() {
        return CONTEXT.get();
    }


    public static void remove() {
        CONTEXT.remove();
        MDC.remove("ThreadLocalTest");
    }


}
