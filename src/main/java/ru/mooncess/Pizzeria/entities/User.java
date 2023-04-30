package ru.mooncess.Pizzeria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.mooncess.Pizzeria.security.Role;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "user_")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
