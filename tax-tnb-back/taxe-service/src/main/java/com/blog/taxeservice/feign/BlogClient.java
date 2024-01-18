package com.blog.taxeservice.feign;


import com.blog.taxeservice.model.Terrain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "social", url = "${application.config.blog-url}")
public interface BlogClient {

    @GetMapping("/{id}")
    Terrain blogExists(@PathVariable ("id") Long id);
}
