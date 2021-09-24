package tacos.nacos.impl1;

import lombok.Data;

/**
 * @author yuanfu
 * @date 2021/9/17 14:33
 */
@Data
public class Result<T> {
    private T data;
    private Integer code;
    private Boolean success;
}
