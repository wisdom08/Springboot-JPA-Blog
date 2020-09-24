package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired //의존성 주입(DI)
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {

        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
        }

        return "삭제되었습니다."+id ;
    }


    //svae 함수는 id를 전달하지 않으면 insert를 해주고
    //save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 udpate를 해주고
    //save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해요
    // email. password 수정 할 예정 (@RequestBody => json데이터로 email, password 요청해서 수정한다)
    @Transactional //함수 종료시에 자동 commit이 됨.
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) { //json데이터요청=>java object(message converter의 jakson라이브러리가 변환해서 받아준다.)
        System.out.println("id = " + id);
        System.out.println("request.getPassword() = " + requestUser.getPassword());
        System.out.println("requestUser.getEmail() = " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

      //  userRepository.save(user);
        //더티체킹

        return user;
    }


    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user")
    public List<User> pageList(
            @PageableDefault(size=2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);

        /*if (pagingUser.isLast()) {
        }*/

        List<User> users = pagingUser.getContent();
        return users;
    }


    // {id} 주소로 파라미터를 전달 받을 수 있음.
    // http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id)  {
       User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get()  {
                return new IllegalArgumentException("해당 유저는 없습니다. id: " + id);
            }
        });
       //요청: 웹브라우저
       //user객체 = 자바 오브젝트
        // 변환(웹브라우저가 이해할 수 있는 데이터) => json(gson 라이브러리)
        //스프링부트 = messageconverter가 응답시에 자동 작동
        //만약에 자바 오브젝트를 리턴하게 되면 messageconverter가 jackson 라이브러리르 호출해서
        //user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
        return user;
    }


    /*
    람다식 활용
    @GetMapping("/dummy/user/{id}")
    public User detail2(@PathVariable int id) throws Throwable {
        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당사용자는 없습니다.");
        });
        return user;
    }*/



    // http://localhost:8000/blog/dummy/join 요청
    // http의 body에 username, password, email 데이터를 가지고 요청
    @PostMapping("/dummy/join")
   // public String join(String username, String password, String email) key=value(약속된규칙)
    public String join(User user) {
        System.out.println("DummyControllerTest.join");
        System.out.println("user.getUsername() = " + user.getUsername());
        System.out.println("user.getPassword() = " + user.getPassword());
        System.out.println("user.getEmail() = " + user.getEmail());
        System.out.println("user.getRole() = " + user.getRole());
        System.out.println("user.getCreatedDate() = " + user.getCreatedDate());
        System.out.println("user.getId() = " + user.getId());
        System.out.println("user.getClass() = " + user.getClass());

        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "회원가입이 완료되었습니다.";
    }
}
