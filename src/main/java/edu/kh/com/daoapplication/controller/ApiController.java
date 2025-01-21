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

    /**
     *           @RequestParam 으로 전달받은 id 값을
     *           URLSearchParams = URL 주소에서 parameters (파라미터들)을 search 검색해서
     *           urlParams 변수 이름에 ? 뒤에 오는 키=값 을 모두 담아둠
     *           urlParams 에서 원하는 키의 값을 get 해서 가져옴
     *           id 라는 변수 이름에 키에 해당하는 값을 저장
     *
     *           const urlParams = new URLSearchParams(window.location.search);
     *           const id = urlParams.get('id');
     *
     * @param id 는 get('id' 로 가져온 값을 활용해서 ajax 로 db 에서 id 값에 해당하는 데이터를 가져오기
     * @return
     */
    @GetMapping("/user/{id}")
    public KHTUser findById(@PathVariable("id") int id) {
        KHTUser khtUser = khtUserService.findById(id);
        log.info(khtUser.toString());
        return khtUser; // 가져온 데이터가 있든 없는 html에 전달
    }

    @GetMapping("/products/{id}")
    public KHTProduct findProductById(@PathVariable("id") int id) {
        KHTProduct product = khtProductService.findProductById(id);
        log.info(product.toString());
        return product;

    }
}
