package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//file return
@Controller
public class TempControllerTest {

    @GetMapping("/temp/home")
     public String tempHome() {
         System.out.println("tempHome()");

         //파일 리턴 기본경로: src/main/resources/static
        //리턴명 : /home.html
        //풀경로 : src/main/resources/static/home.html
         return "/home.html";
     }

    @GetMapping("/temp/img")
    public String tempImg() {
        System.out.println("tempImg되라()");
        return "/a.jpg";
    }


    @GetMapping("/temp/jp")
    public String testJsp() {
        System.out.println("tempjsp되라()");
        return "test";
    }
}
