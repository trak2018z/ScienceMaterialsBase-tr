package com.dpiotr.session;

import com.dpiotr.model.SystemUser;
import com.dpiotr.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Created by dpiotr on 20.11.17.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CurrentSystemUser {

    private SystemUser systemUser;
    private SystemUserRepository systemUserRepository;

    @Autowired
    public CurrentSystemUser(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    public SystemUser getSystemUser() {
        if(systemUser != null){
            systemUser = systemUserRepository.findOne(systemUser.getId());
        }
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public boolean isLogged(){
        return systemUser != null;
    }


    public void logOut(){
        systemUser = null;
    }

    public boolean isStudent() {
        if(!isLogged()){
            return false;
        }
        systemUser = systemUserRepository.findOne(systemUser.getId());
        return systemUser.getRole().equals("student");
    }

    public boolean isAdmin(){
        if(!isLogged()){
            return false;
        }
        systemUser = systemUserRepository.findOne(systemUser.getId());
        return systemUser.getRole().equals("administrator");
    }
}