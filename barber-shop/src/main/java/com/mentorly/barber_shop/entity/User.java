/**
 * @author Starling Diaz on ${DATE}.
 * @Github https://github.com/NSTLRD
 *
 * @Website https://mentorly.blog/
 * @Academy https://www.mentor-ly.com/

 * @version ${PROJECT_NAME} 1.0
 * @since ${DATE}.
 */

package com.mentorly.barber_shop.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.Subject;
import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails, Principal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String dni;
    private String profession;
    private String address;
    private String country;

    @Column(unique = true)
    private String email;
    private String password;
    private String token;

    private boolean enabled = false; // for account activation

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
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
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String FullName() {
        return name + " " + lastName;
    }

}
