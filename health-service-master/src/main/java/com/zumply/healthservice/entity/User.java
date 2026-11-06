package com.zumply.healthservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends Audit implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    int id;

    @Column(name = "user_name", unique = true, nullable = false)
    @NotEmpty(message = "Username can't be empty")
    private String username;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Password can't be empty")
    private String password;

    @Column(name = "category")
    @NotEmpty(message = "Category can't be empty")
    private String category;

    @Column(name = "last_login_date")
    private Timestamp lastLoginDate;

//    @NotEmpty(message = "Email can't be empty")
//    @Email(message = "Enter a Valid Email")
//    private String email;
//    @NotEmpty(message = "Phone can't be empty")
//    private String phone;
//    @NotEmpty(message = "First Name can't be empty")
//    private String firstName;
//    @NotEmpty(message = "Last Name can't be empty")
//    private String lastName;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
}
