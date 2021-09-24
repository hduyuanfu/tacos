package tacos.nacos.impl1;

/**
 * @author yuanfu
 * @date 2021/9/17 14:36
 */
public class ResultUtil {

    public static Result success() {
        Result result = new Result();
        result.setCode(200);
        result.setSuccess(true);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setData(data);
        result.setCode(200);
        return result;
    }
}
