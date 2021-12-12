package com.softdelete.softdeletedemo.dto;

import lombok.Data;

@Data
public class HttpResponseDto<T> {
    private boolean status;
    private String message;
    private T payload;
}
