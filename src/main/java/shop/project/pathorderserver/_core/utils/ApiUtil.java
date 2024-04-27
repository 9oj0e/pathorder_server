package shop.project.pathorderserver._core.utils;

import lombok.Data;

@Data
public class ApiUtil<T> {
    private Integer status;
    private String msg;
    private T body;

    public ApiUtil(T body) {
        this.status = 200;
        this.msg = "성공";
        this.body = body;
    }

    public ApiUtil(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
        this.body = null;
    }
}
