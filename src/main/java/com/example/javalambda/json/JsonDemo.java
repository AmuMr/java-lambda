package com.example.javalambda.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

public class JsonDemo {

    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        singleJson();

        // multipleJson();

    }

    private static void multipleJson() throws JsonProcessingException {
        Response response = new Response();
        response.setCode(200);
        response.setMessage("成功");

        User user1 = new User();
        user1.setAge(18);
        user1.setName("jay");
        user1.setSex("man");

        response.setData(user1);

        User user2 = new User();
        user2.setAge(20);
        user2.setName("tom");
        user2.setSex("girl");

        response.setData(Arrays.asList(user1, user2));


        //多个对象
        String multipleJson = mapper.writeValueAsString(response);

        Response<List<User>> userResponse = mapper.readValue(multipleJson, new TypeReference<Response<List<User>>>() {
        });
        System.out.println(userResponse.getData());
    }

    private static void singleJson() throws JsonProcessingException {
        Response response = new Response();
        response.setCode(200);
        response.setMessage("成功");

        User user = new User();
        user.setAge(18);
        user.setName("jay");
        user.setSex("man");

        response.setData(user);
        //单个对象
        String singleJson = mapper.writeValueAsString(response);

        Response<User> userResponse = mapper.readValue(singleJson, Response.class);
        System.out.println(userResponse.data);


    }


    public static class User {

        private String name;
        private String sex;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", sex='" + sex + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


    public static class Response<T> {

        private int code;

        private String message;

        private T data;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
