package com.dogmeat.petconnect.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String name;

    private String nickname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String membershipLevel;

    @PrePersist
    public void prePersist() {
        // nickname이 null이거나 비어있을 경우 name으로 설정
        if (this.nickname == null || this.nickname.isEmpty()) {
            this.nickname = this.name;
        }

        // membershipLevel이 null일 경우 기본값 'USER'로 설정
        if (this.membershipLevel == null || this.membershipLevel.isEmpty()) {
            this.membershipLevel = "USER";
        }
    }
}
