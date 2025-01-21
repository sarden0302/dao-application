package edu.kh.com.daoapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     * @Request=요청해서 가져옴 Param=매개변수, 파라미터에 해당하는 ("id") 값을 ? 물음표 뒤에
     *           오는 키=값 형태로 값을 가져옴
     *           /user?id=${아이디} 에 해당하는 페이지를 열어준다
     * @param id 는 userDetail(=유저 상세보기) 페이지에 유저 번호로 해당하는 유저 상세 정보를
     *
     * @return  userDtail.html 에서 번호에 해당하는 유저를 볼 수 있도록 설정하는 parameter
     */
    @GetMapping("/user")
    public String getUser(@RequestParam("id") int id) {
        return "userDetail";
    }

    @GetMapping("/products/detail")
    public String getAProduct(@RequestParam("id") int id) {
        return "productDetail";
    }

    @GetMapping("/books")
    public String getBooks() {
        return "books";
    }

    @GetMapping("/book")
    public String getBookById(@RequestParam("id") int id) {
        return "bookDetail";
    }

    @GetMapping("/book/add")
    public String saveBook() {
        return "newBooks";
    }
}
