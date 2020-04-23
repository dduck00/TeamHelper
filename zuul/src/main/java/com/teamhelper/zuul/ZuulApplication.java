package com.teamhelper.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class ZuulApplication {

    private final String[] resourceUris = {
            "/resources/**",
            "/webjars/**",
            "/"
    };

    private final String[] webSocketUris = {
            "/ws/connect/**",
            "/sub/group/**",
            "/pub/get/msg/**",
            "/ws"
    };


    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }


    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
//                .route(p -> p
//                        .path("/**")
//                        .filters(f -> f.rewritePath("^\\/[0-9a-zA-Z\\/]*$", "/login"))
//                        .uri("lb://AUTH-SERVICE")
//                        .predicate(pre -> pre.getRequest().getCookies().isEmpty()))
                .route(p -> p
                        .path("/api/auth/**")
                        .uri("lb://AUTH-SERVICE"))
                .route(p -> p
                        .path(webSocketUris)
                        .uri("lb://WS-SERVICE"))
                .route(p ->p
                        .path(resourceUris)
                        .uri("lb://RESOURCE-SERVICE"))
                .build();
    }

}
