package com.healthtrainer.htserver.domain.register;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Table(name = "User")
@Entity
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "picture")
    private String picture;

    @Column(name = "age")
    private int age;

    @Column(name = "sex")
    private char sex;

    @Column(name = "height")
    private int height;

    @Column(name = "weight")
    private int weight;

    @Column(name = "email")
    private String email;

    @Column(name = "profile_state")
    private char profileState; // 프로필 공개 여부, value = Y or N이기 때문에 Data type이 char형

    @Column(name = "withdrawl_state")
    private char withdrawlState; // 탈퇴여부, 기존의 회원이 탈퇴할 경우 value = Y

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Builder
    public User(String password, String name, String picture, int age, char sex, int height,
                int weight, String email, char profileState, char withdrawlState, List<String> roles){
        this.password = password;
        this.name = name;
        this.picture = picture;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.email = email;
        this.profileState = profileState;
        this.withdrawlState = withdrawlState;
        this.roles = roles;
    }

}
