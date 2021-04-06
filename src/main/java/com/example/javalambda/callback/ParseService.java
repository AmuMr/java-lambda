package com.example.javalambda.callback;

import com.example.javalambda.util.SpringContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParseService extends AparseSerivce {

    Map<String, IParse> serviceMap = new HashMap<>();

    @Autowired
    private SpringContext springContext;

    static {
        System.out.println("init");
    }


    ParseService(List<IParse> list) {
        for (IParse iParse : list) {
            serviceMap.put(iParse.type(), iParse);
        }

    }

    public void parse(String type) {
        IParse iParse = serviceMap.get(type);

        iParse.parse();
        if (iParse instanceof Callback) {
            Callback callback = (Callback) iParse;
            callback.call(callback.getClass().getSimpleName());
        }


    }
}
