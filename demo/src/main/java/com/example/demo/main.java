package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * main
 */
@RestController
public class main {

    @GetMapping(value ="/va")
    String arg(){
        return "ASDF";
    }
    
}