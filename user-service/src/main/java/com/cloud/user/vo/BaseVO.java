package com.cloud.user.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseVO {

    private Boolean flag;
    private Integer code;
    private String msg;
    private Object data;

    public BaseVO(Object data) {
        super();
        this.flag = true;
        this.code = 200;
        this.msg = "success";
        this.data = data;
    }

    public BaseVO(Integer code, String msg) {
        super();
        this.flag = false;
        this.code = code;
        this.msg = msg;
        this.data = "";
    }

}
