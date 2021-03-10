package com.yy.shirosecurity.base.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private int code;
    private String msg;
    private Object data;

    public static Result success(Object data){
        return new Result(200,"",data);
    }

    public static Result failed(String msg){
        return new Result(400,msg,null);
    }

    public static Result success(){
        return new Result(200,"",null);
    }

}
