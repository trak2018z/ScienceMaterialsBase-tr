package com.dpiotr.services;

import com.dpiotr.model.SystemUser;
import com.dpiotr.model.viewmodels.LoginViewModel;
import com.dpiotr.repository.SystemUserRepository;
import com.dpiotr.session.CurrentSystemUser;
import com.dpiotr.utilities.PasswordUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by dpiotr on 20.11.17.
 */
@Component
public class LoginService {

    private SystemUserRepository systemUserRepository;
    private CurrentSystemUser currentSystemUser;

    @Autowired
    public LoginService(SystemUserRepository systemUserRepository, CurrentSystemUser currentSystemUser) {
        this.systemUserRepository = systemUserRepository;
        this.currentSystemUser = currentSystemUser;
    }

    public boolean userIsLogged() {
        return currentSystemUser.isLogged();
    }

    public boolean adminIsLogged(){return currentSystemUser.isAdmin();}

    public boolean logUser(LoginViewModel model) {
        SystemUser user = systemUserRepository.findByEmail(model.getEmail());
        if (user == null) {
            return false;
        }

        if (PasswordUtilities.validatePassword(model.getPassword(), user.getPassword())) {
            this.currentSystemUser.setSystemUser(user);
            return true;
        }
        return false;
    }

    public void logoutUser() {
        currentSystemUser.logOut();
    }
}