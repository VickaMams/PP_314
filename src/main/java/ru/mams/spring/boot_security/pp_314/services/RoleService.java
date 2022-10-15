package ru.mams.spring.boot_security.pp_314.services;



import ru.mams.spring.boot_security.pp_314.models.Role;

import java.util.Set;

public interface RoleService {

    Set<Role> findAll();

    Role findByName(String name);

    void save(Role role);

    Role findById(Long id);

}