package com.example.javalambda.exception;

import java.util.function.Function;

public class LambdaExcTest {


    public static void main(String[] args) {
        Function<String, String> function = s -> add(s);

        Function<String, String> then = function
                .andThen(s -> add1(s))
                .andThen(s -> add2(s));
        String xcption = then.apply("xcption");
        System.out.println(xcption);


    }

    public static String add(String str) {
        try {
            String s = "hello " + str;
            if (str.contains("e")) {
                int i = 10 / 0;
            }
            return s;
        } catch (Exception e) {
            throw new TestException("0001", e);
        }

    }

    public static String add1(String str) {
        try {

            if (str.contains("n")) {
                return str.toUpperCase();

            } else {
                throw new NullPointerException("aaaaa");
            }
        } catch (Exception e) {
            throw new TestException("0002", e);
        }

    }

    public static String add2(String str) {
        try {

            if (str.toLowerCase().contains("hello")) {
                return str + " this end!";
            } else {
                throw new NullPointerException("bbbbbb");
            }
        } catch (Exception e) {
            throw new TestException("0003", e);
        }

    }


}
