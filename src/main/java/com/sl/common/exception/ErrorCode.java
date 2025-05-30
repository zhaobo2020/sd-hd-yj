package com.sl.common.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorCode{
    private Integer code ;
    private String meg ;
}
