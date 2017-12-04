package com.dpiotr.repository;

import com.dpiotr.model.SystemUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SystemUserRepository extends CrudRepository<SystemUser, Long> {
    List<SystemUser> findByName(String name);

    List<SystemUser> findBySurname(String surname);

    SystemUser findByEmail(String email);

    boolean existsByEmail(String email);

    SystemUser findById(Long id);
}
