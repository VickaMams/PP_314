package ru.mams.spring.boot_security.pp_314.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mams.spring.boot_security.pp_314.models.User;
import ru.mams.spring.boot_security.pp_314.repositories.UserRepository;


import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @org.springframework.transaction.annotation.Transactional
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    @org.springframework.transaction.annotation.Transactional
    public void updateUser(User user) {
        if (!user.getPassword().equals(Objects.requireNonNull(userRepository.findById(user.getId()).orElse(null)).getPassword())){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }else {
            user.setPassword(Objects.requireNonNull(userRepository.findById(user.getId()).orElse(null)).getPassword());
        }

        userRepository.saveAndFlush(user);
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return user;
    }

}
