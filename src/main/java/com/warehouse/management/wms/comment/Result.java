package com.warehouse.management.wms.comment;


import lombok.Data;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Setter
@Data
public class Result {

    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    public Result() {
    }

    /**
     * 返回正确结果
     *
     * @return 返回正确结果
     */
    public static Result ok() {
        Result result = new Result();
        result.setCode(ResponseEnum.SUCCESS.getCode());
        result.setMessage(ResponseEnum.SUCCESS.getMessage());
        return result;
    }

    /**
     * 返回错误结果
     *
     * @return 错误结果
     */
    public static Result error() {
        Result result = new Result();
        result.setCode(ResponseEnum.ERROR.getCode());
        result.setMessage(ResponseEnum.ERROR.getMessage());
        return result;
    }


    /**
     * 设置特定的结果
     * @param responseEnum 结果
     * @return 特定的结果
     */
    public static Result setResult(ResponseEnum responseEnum) {
        Result result = new Result();
        result.setCode(responseEnum.getCode());
        result.setMessage(responseEnum.getMessage());
        return result;
    }

    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

    /**
     * 指定特定响应消息
     * @param message 响应信息
     * @return 响应消息
     */
    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    /**
     * 指定特定响应码
     * @param code 响应码
     * @return 响应码
     */
    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }
}
