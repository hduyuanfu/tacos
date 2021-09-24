package tacos.nacos.impl1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanfu
 * @date 2021/9/17 15:03
 */
@RestController
@RequestMapping("/loop")
public class LoopLongPollingController {
    @Autowired
    LoopLongPollingService loopLongPollingService;

    /**
     * 从服务端拉取被变更的数据
     * @return
     */
    @GetMapping("/pull")
    public Result pull() {
        String result = loopLongPollingService.pull();
        return ResultUtil.success(result);
    }

    /**
     * 向服务端推送被变更的数据
     * @param data
     * @return
     */
    @GetMapping("/push")
    public Result push(@RequestParam("data") String data) {
        String result = loopLongPollingService.push(data);
        return ResultUtil.success(result);
    }
}
