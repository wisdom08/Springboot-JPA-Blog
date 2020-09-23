package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

//사용자가 요청    => 응답(data)
@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTEST : ";

    @GetMapping("/http/lombok")
    public String lombokTest() {
        Member m = Member.builder().username("jihye").password("1234").email("email").build();
        System.out.println(TAG + "m.getId() = " + m.getId());
        m.setId(5000);
        System.out.println(TAG + "m.getId() = " + m.getId());
        return "lombok test 완료";
    }

    @GetMapping("/http/get")
    public String getTest(Member m) {
        return "get 요청" + m.getId() +":::::" + m.getUsername() + "=====" + m.getPassword()+"email"+m.getEmail() ;
    }

    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m) {
        return "get 요청" + m.getId() +":::::" + m.getUsername() + "=====" + m.getPassword()+"email"+m.getEmail() ;
    }

    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m) {
        return "put 요청" + m.getId()+m.getPassword();
    }

    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }
}
