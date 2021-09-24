package tacos.nacos.impl2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tacos.nacos.impl1.Result;
import tacos.nacos.impl1.ResultUtil;

/**
 * @author yuanfu
 * @date 2021/9/17 16:22
 */
@RestController
@RequestMapping("/lock")
public class LockLongPollingController {
    @Autowired
    private LockLongPollingService lockLongPollingService;

    @RequestMapping("/pull")
    public Result pull() {
        String result = lockLongPollingService.pull();
        return ResultUtil.success(result);
    }

    @RequestMapping("/push")
    public Result push(@RequestParam("data") String data) {
        String result = lockLongPollingService.push(data);
        return ResultUtil.success(result);
    }
}
