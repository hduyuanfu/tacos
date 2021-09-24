package tacos.controller.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanfu
 * @data 2021/5/15 19:13
 */
@RestController
@Slf4j
@RequestMapping("/redis")
public class RedisController {

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisController(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @GetMapping(value = "/add")
    public String add(@RequestParam("key") String key, @RequestParam("value") String value) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(key, value);
        return "add " + key + " : " + value + " success";
    }

    @GetMapping("/get")
    public String get(@RequestParam("key") String key) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        return  (String) ops.get(key);
    }
}
