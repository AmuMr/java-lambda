package com.example.javalambda.async;


public interface AsyncService {
    /**
     * 异步调用，无返回值
     */
    void asyncTask();

    /**
     * 异步调用，有返回值
     */
  //  Future<String> asyncTask(String s);

    /**
     * 异步调用，无返回值，事务测试
     */
  //  void asyncTaskForTransaction(Boolean exFlag);
}