package com.example.javalambda.callback;


public class Main {


    public static void test(String s0, ITest test) {
        test.flag(s0, s0);
    }


    public static void main(String[] args) {
        test("hello", (String s0, String s1) -> System.out.println(s0 + s1));
    }

}
