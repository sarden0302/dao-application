package edu.kh.com.daoapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/product")
    public String getProduct() {
        return "product";
    }


}
