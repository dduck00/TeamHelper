package com.teamhelper.chatservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@FeignClient(name = "feign", url="http://localhost:9001")
public interface ViewFeign {
    @GetMapping("/chat")
    ModelAndView chatView();
}
