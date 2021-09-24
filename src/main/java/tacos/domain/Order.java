package tacos.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author yuanfu
 * @data 2021/5/12 18:57
 */
@Data
public class Order {

    private long id;

    @NotBlank(message = "收货人姓名不能为空")
    @Size(min=1, max=15, message = "收货人姓名不多于15个字")
    private String name;

    @NotBlank(message = "收货人电话不能为空")
    @Size(min=11, max=11, message = "收货人电话长度必须为11位")
    private String phone;

    @NotBlank(message = "收货人地址不能为空")
    @Size(min=1, max=90, message = "收货人地址不多于90个字")
    private String address;

    // @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String create_at;

}
