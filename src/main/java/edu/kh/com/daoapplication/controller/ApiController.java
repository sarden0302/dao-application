package edu.kh.com.daoapplication.controller;

import edu.kh.com.daoapplication.dao.KHTProduct;
import edu.kh.com.daoapplication.dao.KHTUser;
import edu.kh.com.daoapplication.service.KHTProductService;
import edu.kh.com.daoapplication.service.KHTUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api") // 맨 앞에 공통으로 들어갈 url api 명칭 작성
public class ApiController {

    @Autowired
    private KHTUserService khtUserService;

    @Autowired
    private KHTProductService khtProductService;

    // ajax url 을 이용해서 DB에 저장된 DB 불러오기
    @GetMapping("/users")
    public List<KHTUser> findAll() {
        List<KHTUser> users = khtUserService.findAll();
        log.info(users.toString());
        return users;
    }

    // ajax url 을 이용해서 DB 에 회원 저장하기
    @PostMapping("/saveUser")
    public KHTUser saveUser(@RequestBody KHTUser khtUser) {
        return khtUserService.save(khtUser);
    }

    @GetMapping("/products")
    public List<KHTProduct> findAllProducts() {
        List<KHTProduct> products = khtProductService.findAll();
        return products;
    }

    @PostMapping("/saveProduct")
    public KHTProduct saveProduct(@RequestBody KHTProduct khtProduct) {
        //return khtProductService.save(khtProduct);
        KHTProduct savedProduct = khtProductService.save(khtProduct);
        return savedProduct;
    }
}
