package com.finalproj.devapp.api.common;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

@Data
public class ApiResponse implements Serializable {
    private Integer code;
    private String errorMsg;
    private Object data;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    private ApiResponse(int code, String errorMsg, Object data) {
        this.code = code;
        this.errorMsg = errorMsg;
        this.data = data;
        this.dateTime = LocalDateTimeUtil.now();
    }

    public ApiResponse(){

    }

    public static ApiResponse ok() {
        return new ApiResponse(20000, "", new HashMap<>());
    }

    public static ApiResponse ok(Object data) {
        return new ApiResponse(0, "", data);
    }

    public static ApiResponse error(String errorMsg) {
        return new ApiResponse(20000, errorMsg, new HashMap<>());
    }

    public static ApiResponse ok(String errorMsg, Object data) {
        return new ApiResponse(20000, errorMsg, data);
    }
}
