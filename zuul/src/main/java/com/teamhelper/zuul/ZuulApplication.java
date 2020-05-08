package com.teamhelper.zuul;

import com.teamhelper.zuul.jwt.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Map;

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
            "/pub/get/msg/**"
    };

    private final JwtToken JWT_TOKEN;


    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @Autowired
    ZuulApplication(JwtToken jwtToken) {
        this.JWT_TOKEN = jwtToken;
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/**")
                        .filters(f -> f.rewritePath("^\\/[0-9a-zA-Z\\/]*$", "/"))
                        .uri("lb://AUTH-SERVICE")
                        .predicate(pre -> {
                            Map<String, String> token = JWT_TOKEN.parseHeader(pre.getRequest());
                            if (token == null) {
                                return true;
                            }
                            pre.getRequest().getHeaders().setAll(token);
                            return false;
                        }))
                .route(p -> p
                        .path("/api/auth/**")
                        .uri("lb://AUTH-SERVICE"))
                .route(p -> p
                        .path(webSocketUris)
                        .uri("lb://WS-SERVICE"))
                .route(p -> p
                        .path(resourceUris)
                        .uri("lb://RESOURCE-SERVICE"))
                .build();
    }

}
