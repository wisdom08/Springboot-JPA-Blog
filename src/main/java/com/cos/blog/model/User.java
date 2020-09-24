package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlEnum;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
@Entity //User 클래스가 mysql에 테이블에 생성이 된다.
//@DynamicInsert //insert시에 null인 필드를 제외시켜준다.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //시퀀스, auto_increatement
    private int id;

    @Column(nullable = false, length = 30, unique = true)
     private String username;

    @Column(nullable = false, length = 100) //12345 => 해쉬(비밀번호 암호화)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    //@ColumnDefault("'user'")
    @Enumerated(EnumType.STRING)
    private RoleType role; //ENUM을 쓰는게 좋다. //admin, user, manager

    @CreationTimestamp //시간 자동 입력
    private Timestamp createdDate;
}
