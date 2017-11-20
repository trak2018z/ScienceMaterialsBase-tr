package com.dpiotr.services;

import com.dpiotr.model.SystemUser;
import com.dpiotr.model.viewmodels.RegistrationViewModel;
import com.dpiotr.repository.SystemUserRepository;
import com.dpiotr.utilities.PasswordUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by dpiotr on 20.11.17.
 */
@Component
public class RegistrationService {

    private SystemUserRepository systemUserRepository;

    @Autowired
    public RegistrationService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    public void registerUser(RegistrationViewModel rvm){
        SystemUser systemUser = new SystemUser();
        systemUser.setEmail(rvm.getEmail());
        systemUser.setPassword(PasswordUtilities.getHashFor(rvm.getPassword()));
        systemUser.setName(rvm.getFirstName());
        systemUser.setSurname(rvm.getLastName());
        systemUser.setRole("student");
        systemUserRepository.save(systemUser);
    }
}

