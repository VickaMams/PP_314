package ru.mams.spring.boot_security.pp_314.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.mams.spring.boot_security.pp_314.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u JOIN FETCH u.roles where u.username = :email")
    User findByUsername(String email);
    @Query("select u from User u join fetch u.roles where u.id =:id")
    Optional<User> findById(Long id);

    @Query("select distinct u from User u join fetch u.roles ")
    List<User> findAll();

    void deleteById(Long id);


}