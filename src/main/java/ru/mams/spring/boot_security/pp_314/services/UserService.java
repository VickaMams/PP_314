package ru.mams.spring.boot_security.pp_314.services;


import ru.mams.spring.boot_security.pp_314.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    void saveUser(User user);
    void deleteById(Long id);

    User findById(Long id);

    void updateUser(User user);

}

