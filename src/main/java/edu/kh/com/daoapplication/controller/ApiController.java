package edu.kh.com.daoapplication.controller;

import edu.kh.com.daoapplication.model.entity.KHTBook;
import edu.kh.com.daoapplication.model.entity.KHTProduct;
import edu.kh.com.daoapplication.model.entity.KHTUser;
import edu.kh.com.daoapplication.model.vo.VerificationRequest;
import edu.kh.com.daoapplication.service.KHTBookService;
import edu.kh.com.daoapplication.service.KHTProductService;
import edu.kh.com.daoapplication.service.KHTUserService;
import edu.kh.com.daoapplication.service.VerificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api") // 맨 앞에 공통으로 들어갈 url api 명칭 작성
public class ApiController {

    @Autowired
    private KHTUserService khtUserService;

    @Autowired
    private KHTProductService khtProductService;

    @Autowired
    private KHTBookService khtBookService;


    // ajax url 을 이용해서 DB에 저장된 DB 불러오기
    @GetMapping("/users")
    public List<KHTUser> findAll() {
        List<KHTUser> users = khtUserService.findAll();
        log.info(users.toString());
        return users;
    }

    // ajax url 을 이용해서 DB 에 회원 저장하기
    @PostMapping("/saveUserImage")
    public KHTUser saveUserImage(@RequestParam("password") String password,
                                 @RequestParam("username") String username,
                                 @RequestParam("file") MultipartFile file
                                 ) {

        return khtUserService.save(password, username, file);
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

    @GetMapping("/books")
    public List<KHTBook> books() {
        return khtBookService.getAllBooks();
    }

    @GetMapping("/book/{id}") // id 조회
    public KHTBook book(@PathVariable("id") int id) {
        return khtBookService.getBookById(id);
    }

    /* 기본 글자 타입만 한 번에 저장하기
     * @param khtBook = Body = 통째로 바디 내 세부 설정 없이 한 번에 가져온 그대로 저장
     * @return        = 저장 역할을 하는 save 로 데이터 그대로 전달
    @PostMapping("/bookSave")
    public KHTBook saveBook(@RequestBody KHTBook khtBook) {
        KHTBook savedBook = khtBookService.save(khtBook);
        log.info(savedBook.toString());
        return savedBook;
    }
    */
    @PostMapping("/bookSaveImg")
    public KHTBook saveBookImg(@RequestParam("title") String title,
                               @RequestParam("author") String author,
                               @RequestParam("genre") String genre,
                               @RequestParam("file") MultipartFile file) {
        return khtBookService.save(title, author, genre, file);
    }

    @PutMapping("/books/{id}/update")
    public int updateBooks(@PathVariable("id") int id,
                           @RequestBody KHTBook khtBook) {
        khtBook.setId(id);
        return 1;
    }
    /**************************** 이메일 인증 ***********************************/
    @Autowired
    private VerificationService verificationService;

    @PostMapping("/sendCode")
    public String sendCode(@RequestBody VerificationRequest vr) {
        log.info("==== Request Controller /api/sendCode ====");
        String email = vr.getEmail();
        log.info("controller - email : {}", email);

        String code = verificationService.randomCode();
        log.info("controller - code : {}", code);

        verificationService.saveEmailCode(email, code);
        log.info("controller - Save Method : {} -> {}", email, code);

        verificationService.sendEmail(email, code);
        log.info("controller - Send Method(이메일을 성공적으로 보냄) : {}", code);


        return "이메일을 성공적으로 보냈습니다." + email;
    }

    // 인증번호 일치하는지 확인
    @PostMapping("/checkCode")
    public String checkCode(@RequestBody VerificationRequest vr) {
        boolean isValid = verificationService.verifyCodeWithVO(vr);

        log.info("Controller - checkCode method isValid : {}", isValid);
        if (isValid) {
            return "인증번호가 일치합니다.";
        }
        return "인증번호가 일치하지 않습니다.";
    }
}
