package ru.mooncess.Pizzeria.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mooncess.Pizzeria.dto.user.UserDTO;
import ru.mooncess.Pizzeria.exceptions.UserAlreadyExistsException;
import ru.mooncess.Pizzeria.mappers.UserMapper;
import ru.mooncess.Pizzeria.repositories.UserRepository;
import ru.mooncess.Pizzeria.entities.User;
import ru.mooncess.Pizzeria.security.Role;
import ru.mooncess.Pizzeria.security.SecurityConfig;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public User get(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Transactional
    public boolean create(UserDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail()))
            throw new UserAlreadyExistsException();
        User user = userMapper.toEntity(dto);
        user.setRole(Role.ADMIN);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
    }
}
