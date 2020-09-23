package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//DAO
//자동으로 Bean 등록이 된다 @Repository 생량 가능
//User 클래스, pk가 integer
public interface UserRepository extends JpaRepository<User, Integer> {


}
