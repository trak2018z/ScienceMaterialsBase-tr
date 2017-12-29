package com.dpiotr.services;

import com.dpiotr.model.SystemUser;
import com.dpiotr.model.viewmodels.SystemUserViewModel;
import com.dpiotr.repository.SystemUserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static java.util.Collections.emptyList;

/**
 * Created by dpiotr on 04.12.17.
 */
@Component
public class SystemUserService implements UserDetailsService {
    private SystemUserRepository systemUserRepository;

    public SystemUserService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SystemUser applicationUser = systemUserRepository.findByEmail(email);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(applicationUser.getEmail(), applicationUser.getPassword(), emptyList());
    }

    public void editSystemUser(SystemUser su, SystemUserViewModel suvm){
        //TODO
    }
}
