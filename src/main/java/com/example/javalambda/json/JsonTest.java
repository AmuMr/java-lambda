package com.example.javalambda.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class JsonTest<T> {


    private static ObjectMapper mapper = new ObjectMapper();

    static {
        //空值处理
        mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeNull();
            }
        });
    }

    public static void main(String[] args) throws Exception {


        TradeRequest tradeRequest = new TradeRequest();
        tradeRequest.setAccountNo(RandomStringUtils.randomNumeric(10));
        tradeRequest.setAmount(new BigDecimal("1111100000"));

        Request request = new Request();
        request.setData(tradeRequest);
        request.setApplicationNo(RandomStringUtils.randomNumeric(20));


        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("applicationNo", request.getApplicationNo());

        String value = mapper.writeValueAsString(request.getData());
        byte[] encode = Base64.getEncoder().encode(value.getBytes(StandardCharsets.UTF_8));
        objectNode.put("data", new String(encode, StandardCharsets.UTF_8));
        String asString = mapper.writeValueAsString(objectNode);
       // System.out.println(asString);


        Request<TradeRequest> response = trade(asString, TradeRequest.class);
        // openAccount();
        System.out.println(response.getData());

    }

    private static <T> T trade(String asString, Class tClass) throws JsonProcessingException {
        JsonNode jsonNode = mapper.readTree(asString);
        String data = jsonNode.get("data").textValue();

        String jsonNode1 = jsonNode.asText("data");
        System.out.println(jsonNode1==null);

        byte[] decode = Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8));
        ObjectNode nodes = jsonNode.deepCopy();
        String decodeStr = new String(decode, StandardCharsets.UTF_8);
        nodes.putPOJO("data", "");

        String asString1 = mapper.writeValueAsString(nodes);

        System.out.println(asString1);
        return (T) mapper.readValue(asString1, new TypeReference<Request>() {
        });
    }


//    private static <T> T  extracted(String asString, TypeReference<T> tClass) throws JsonProcessingException {
//        JsonNode jsonNode = mapper.readTree(asString);
//        String data = jsonNode.get("data").textValue();
//        byte[] decode = Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8));
//        ObjectNode nodes = jsonNode.deepCopy();
//        String decodeStr = new String(decode, StandardCharsets.UTF_8);
//        nodes.putPOJO("data", mapper.readValue(decodeStr, tClass));
//
//        String  string = mapper.writeValueAsString(nodes);
//       return   mapper.readValue(string, tClass);
//    }


    private static void openAccount() throws JsonProcessingException {
        OpenAccountRequest openAccountRequest = new OpenAccountRequest();
        openAccountRequest.setName("tom");
        openAccountRequest.setAge(12);
        openAccountRequest.setBirthday(new Date());

        Request request = new Request();
        request.setApplicationNo(RandomStringUtils.randomNumeric(20));
        request.setData(openAccountRequest);


        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("aaaaaaa", "bbbbbb");
        objectNode.putPOJO("data", openAccountRequest);

        String requestJson = mapper.writeValueAsString(objectNode);

        System.out.println(requestJson);
        //System.out.println(request.getClass().getSimpleName() + "：" + requestJson);


        OpenAccountResponse openAccountResponse = new OpenAccountResponse();
        openAccountResponse.setOpenStatus(0);
        openAccountResponse.setOpenTime(new Date());
        Response response = new Response();
        response.setCode(0);
        response.setMessage("success");
        response.setData(openAccountResponse);
        String openResponseJson = mapper.writeValueAsString(response);
        Response<OpenAccountResponse> accountResponse1 = mapper.readValue(openResponseJson, new TypeReference<Response<OpenAccountResponse>>() {
        });
        //System.out.println(accountResponse1.getClass().getSimpleName()+"："+accountResponse1.getData().toString());


        List<OpenAccountResponse> list = new ArrayList<>();

        OpenAccountResponse openAccountResponse1 = new OpenAccountResponse();
        openAccountResponse1.setOpenStatus(0);
        openAccountResponse1.setOpenTime(new Date());

        OpenAccountResponse openAccountResponse2 = new OpenAccountResponse();
        openAccountResponse2.setOpenStatus(0);
        openAccountResponse2.setOpenTime(new Date());
        list.add(openAccountResponse1);
        list.add(openAccountResponse2);

        Response response1 = new Response();
        response1.setCode(0);
        response1.setMessage("success");
        response1.setData(list);
        String asString = mapper.writeValueAsString(response1);

        Response<List<OpenAccountResponse>> listResponse = mapper.readValue(asString, new TypeReference<Response<List<OpenAccountResponse>>>() {
        });

        List<OpenAccountResponse> data = listResponse.getData();
        // System.out.println("data : "+data);
    }


}
