package com.minrui.web.controller.jimureport;

import lombok.Data;

import java.util.HashMap;

@Data
public class JMResult extends HashMap<String, Object>{
    private HashMap<String, Object> Data;
    private Integer total;
    private Integer count;

    public JMResult(){
    }

    public static JMResult result(Object data){
        JMResult jmResult = new JMResult();
        jmResult.put("data",data);
        return jmResult;
    }

    public static JMResult result(Object data,Integer total,Integer count){
        JMResult jmResult = new JMResult();
        jmResult.put("data",data);
        jmResult.put("total",total);
        jmResult.put("count",count);
        return jmResult;
    }
}
