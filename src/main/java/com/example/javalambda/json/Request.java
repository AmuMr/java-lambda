package com.example.javalambda.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;

@Data
public class Request<T>{

    private String applicationNo;

    private T data;


    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String asString = null;
        try {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("applicationNo","sse");
            objectNode.putPOJO("data", getData());
            asString = mapper.writeValueAsString(objectNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return asString;
    }
}
