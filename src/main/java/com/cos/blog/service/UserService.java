package com.cos.blog.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IOC를 해준다.
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

     @Transactional
    public void join(User user) {

         String rawPassword = user.getPassword(); //1234원문
         String encPassword = encoder.encode(rawPassword);//해쉬
         user.setPassword(encPassword);

         user.setRole(RoleType.USER);
         userRepository.save(user);
    }

    @Transactional
    public void modifyUser(User user) {
         //수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
         //select를 해서 User 오브젝트를 db로부터 가져오는 이유는 영속화를 하기 위해서
        //영속화된 오브젝트를 변경하면, 자동으로 db에 update문을 날려준다.
        User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
            return new IllegalArgumentException("회원 찾기 실패");
        });
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        persistance.setPassword(encPassword);
        persistance.setEmail(user.getEmail());
        //회원 수정 함수 종료시 = 서비스 종료 = 트랜젝션 종료 = commit이 자동으로 됩니다.
        //영속화된 persistence 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌.
    }


/*    @Transactional(readOnly = true) // select할 때 트렌젝션 시작, 서비스 종료시에 트랜젝션 종료(정합성 유지)
    public User login(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }*/
}
