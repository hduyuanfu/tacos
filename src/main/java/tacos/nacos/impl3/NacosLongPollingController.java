package tacos.nacos.impl3;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tacos.nacos.impl1.Result;
import tacos.nacos.impl1.ResultUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuanfu
 * @date 2021/9/17 16:31
 */
@RestController
@RequestMapping("/nacos")
public class NacosLongPollingController extends HttpServlet {
    @Autowired
    private NacosLongPollingService nacosLongPollingService;

    @GetMapping("/pull")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String dataId = req.getParameter("dataId");
        if (StringUtils.isEmpty(dataId)) {
            throw new IllegalArgumentException("请求参数异常,dataId能为空");
        }
        nacosLongPollingService.doGet(dataId, req, resp);
    }
    //为了在浏览器中演示,我这里先用Get请求,dataId可以区分不同应用的请求
    @GetMapping("/push")
    public Result push(@RequestParam("dataId") String dataId, @RequestParam("data") String data) {
        if (StringUtils.isEmpty(dataId) || StringUtils.isEmpty(data)) {
            throw new IllegalArgumentException("请求参数异常,dataId和data均不能为空");
        }
        nacosLongPollingService.push(dataId, data);
        return ResultUtil.success();
    }
}
