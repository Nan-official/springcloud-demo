package com.sentinel.contorller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.entity.Result;
import com.utils.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nxy
 * @title: RateLimitController
 * @projectName lanka
 * @description: TODO
 */
@RestController
@RequestMapping("/rateLimit")
public class RateLimitController {

    @GetMapping("/hi")
    @SentinelResource(value = "hi")
    public String sayHello(String name) {
        return "Hello, " + name;
    }
    /**
     * 按资源名称限流，需要指定限流处理逻辑
     */
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public Result byResource() {
        return ResultUtil.getInstance().setSuccessMsg("按资源名称限流");
    }

    /**
     * 按URL限流，有默认的限流处理逻辑
     */
    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl",blockHandler = "handleException")
    public Result byUrl() {
        return ResultUtil.getInstance().setSuccessMsg("按url限流");
    }

    public Result handleException(BlockException exception){
        return ResultUtil.getInstance().setSuccessMsg(exception.getClass().getCanonicalName());
    }
}
