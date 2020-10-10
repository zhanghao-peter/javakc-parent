package com.javakc.servicebase.hanler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor//构造函数，包含所有已声明字段属性参数
@NoArgsConstructor//无参构造函数
public class HctfException extends RuntimeException{

    //状态码
    private Integer code;
    //信息
    private String msg;
}
