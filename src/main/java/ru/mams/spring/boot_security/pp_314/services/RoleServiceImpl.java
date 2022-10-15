package ru.mams.spring.boot_security.pp_314.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mams.spring.boot_security.pp_314.models.Role;
import ru.mams.spring.boot_security.pp_314.repositories.RoleRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Set<Role> findAll() {
        return new HashSet<>(roleRepository.findAll());
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
    @Transactional
    public void save(Role role) {
        roleRepository.saveAndFlush(role);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(Math.toIntExact(id)).orElse(null);
    }
}